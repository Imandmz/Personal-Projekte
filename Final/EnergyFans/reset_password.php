<?php
session_start();
require 'config.php';
require_once __DIR__ . '/includes/GoogleAuthenticator.php';

$token = $_GET['token'] ?? '';
$error = '';
$success = '';
$phase = 'verify'; // Standard: Code prüfen

// Nutzer anhand Token und Zeit prüfen
$stmt = $pdo->prepare("SELECT * FROM kunden WHERE reset_token = ? AND reset_token_time > NOW() - INTERVAL 30 MINUTE");
$stmt->execute([$token]);
$user = $stmt->fetch();

if (!$user) {
    $error = "⛔ Der Link ist abgelaufen oder ungültig.";
    $phase = 'invalid';
}

$ga = new PHPGangsta_GoogleAuthenticator();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['code']) && $phase === 'verify') {
        $code = $_POST['code'];
        if ($ga->verifyCode($user['secret'], $code, 2)) {
            $_SESSION['reset_user_id'] = $user['id'];
            $_SESSION['reset_verified'] = true;
            $phase = 'reset';
        } else {
            $error = "❌ Falscher 2FA-Code.";
        }
    } elseif (isset($_POST['password']) && isset($_POST['confirm']) && $_SESSION['reset_verified'] === true) {
        if ($_POST['password'] !== $_POST['confirm']) {
            $error = "❌ Passwörter stimmen nicht überein.";
            $phase = 'reset';
        } elseif (strlen($_POST['password']) < 9 || !preg_match('/[A-Z]/', $_POST['password']) || !preg_match('/[a-z]/', $_POST['password']) || !preg_match('/[0-9]/', $_POST['password'])) {
            $error = "❌ Passwort muss mind. 9 Zeichen, Groß-/Kleinbuchstaben & Zahl enthalten.";
            $phase = 'reset';
        } else {
            $hash = hash('sha512', $_POST['password']);
            $stmt = $pdo->prepare("UPDATE kunden SET password = ?, reset_token = NULL, reset_token_time = NULL WHERE id = ?");
            $stmt->execute([$hash, $_SESSION['reset_user_id']]);
            unset($_SESSION['reset_user_id'], $_SESSION['reset_verified']);
            $success = "✅ Passwort wurde erfolgreich geändert. <a href='login.php' style='color:#fff;'>Jetzt einloggen</a>";
            $phase = 'done';
        }
    }
}
?>

<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Passwort zurücksetzen</title>
  <link rel="stylesheet" href="assets/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
        background: url('assets/images/background.jpg') no-repeat center center fixed;
        background-size: cover;
        color: #fff;
        font-family: Arial, sans-serif;
    }
    .form-box {
        max-width: 440px;
        margin: 120px auto;
        padding: 2rem;
        background: rgba(0, 0, 0, 0.8);
        border-radius: 15px;
        box-shadow: 0 0 20px rgba(0,0,0,0.6);
    }
    input {
        background: rgba(255,255,255,0.1);
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 12px;
        margin-bottom: 15px;
        width: 100%;
    }
    input::placeholder {
        color: #ccc;
    }
    button {
        width: 100%;
        background-color: #28a745;
        color: white;
        font-weight: bold;
        border: none;
        padding: 12px;
        border-radius: 5px;
    }
    .error, .success {
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        text-align: center;
    }
    .error {
        background: rgba(255,0,0,0.2);
        color: #ffb3b3;
    }
    .success {
        background: rgba(0,128,0,0.2);
        color: #b2ffb2;
    }
  </style>
</head>
<body>

<div class="form-box">
  <h2 class="text-center">🔐 Passwort zurücksetzen</h2>

  <?php if ($error): ?>
    <div class="error"><?= htmlspecialchars($error) ?></div>
    <a href="login.php" class="btn btn-primary mt-2 w-100" role="button">Zurück zum Login</a>
  <?php endif; ?>

  <?php if ($success): ?>
    <div class="success"><?= $success ?></div>
    <a href="login.php" class="btn btn-primary mt-2 w-100" role="button">Zurück zum Login</a>

  <?php elseif ($phase === 'verify'): ?>
    <form method="post">
        <label for="code">Gib deinen 6-stelligen Code aus Google Authenticator ein:</label>
        <input type="text" name="code" id="code" placeholder="123456" required>
        <button type="submit">✔ Code bestätigen</button>
        <a href="login.php" class="btn btn-primary mt-2 w-100" role="button">Zurück zum Login</a>
    </form>

  <?php elseif ($phase === 'reset'): ?>
    <form method="post">
        <input type="password" name="password" placeholder="Neues Passwort" required>
        <input type="password" name="confirm" placeholder="Passwort bestätigen" required>
        <button type="submit">💾 Passwort speichern</button>
        <a href="login.php" class="btn btn-primary mt-2 w-100" role="button">Zurück zum Login</a>
    </form>

  <?php elseif ($phase === 'invalid'): ?>
    <p class="text-center">Der Link ist nicht mehr gültig oder abgelaufen.</p>
    <a href="login.php" class="btn btn-primary mt-2 w-100" role="button">Zurück zum Login</a>
  <?php endif; ?>
</div>

</body>
</html>

