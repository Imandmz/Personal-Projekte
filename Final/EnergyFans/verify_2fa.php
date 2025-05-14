<?php
session_start();
require_once 'config.php';
require_once __DIR__ . '/includes/GoogleAuthenticator.php';

if (!isset($_SESSION['2fa_secret'])) {
    header('Location: login.php');
    exit();
}

$ga = new PHPGangsta_GoogleAuthenticator();
$error = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $code = $_POST['code'] ?? '';
    $secret = $_SESSION['2fa_secret'];

    if ($ga->verifyCode($secret, $code, 2)) {
        $userId = $_SESSION['user_id'] ?? $_SESSION['temp_user_id'] ?? null;

        if ($userId) {
            // Letzten Login-Wert merken (für Anzeige)
            $stmt = $pdo->prepare("SELECT last_login FROM kunden WHERE id = ?");
            $stmt->execute([$userId]);
            $_SESSION['last_login_display'] = $stmt->fetchColumn();

            // Jetzt last_login updaten
            $pdo->prepare("UPDATE kunden SET last_login = NOW() WHERE id = ?")->execute([$userId]);

            $_SESSION['user_id'] = $userId;
            unset($_SESSION['temp_user_id']);
            unset($_SESSION['2fa_secret']);

            header('Location: index.php');
            exit();
        } else {
            $error = "Ungültige Sitzung. Bitte erneut anmelden.";
        }
    } else {
        $error = "❌ Falscher Code. Bitte erneut versuchen.";
    }
}
?>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>2FA Verifizierung</title>
  <link rel="stylesheet" href="assets/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
        background: url('assets/images/background.jpg') no-repeat center center fixed;
        background-size: cover;
        color: #fff;
    }
    .login-form {
        max-width: 400px;
        margin: 120px auto;
        padding: 2rem;
        background-color: rgba(0, 0, 0, 0.75);
        border-radius: 15px;
        box-shadow: 0 0 20px rgba(0,0,0,0.6);
    }
    input, button {
        width: 100%;
        margin-bottom: 15px;
        padding: 12px;
        border-radius: 5px;
        border: none;
    }
    input {
        background-color: rgba(255,255,255,0.1);
        color: white;
    }
    input::placeholder {
        color: #ccc;
    }
    button {
        background-color: #28a745;
        font-weight: bold;
        color: white;
    }
    .error {
        color: #f8d7da;
        background: rgba(255,0,0,0.2);
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 10px;
    }
  </style>
</head>
<body>
<div class="login-form">
  <h2 class="text-center">🔐 2FA-Code eingeben</h2>
  <?php if ($error): ?>
    <div class="error"><?= htmlspecialchars($error) ?></div>
  <?php endif; ?>
  <form method="post">
    <input type="text" name="code" placeholder="6-stelliger Code" required>
    <button type="submit">Verifizieren</button>
  </form>
</div>
<?php include 'footer.php'; ?>
</body>
</html>
