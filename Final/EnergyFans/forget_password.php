<?php
require 'config.php';

$link = '';
$submitted = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['email'])) {
    $submitted = true;
    $email = $_POST['email'];
    $token = bin2hex(random_bytes(32));

    $stmt = $pdo->prepare("UPDATE kunden SET reset_token = ?, reset_token_time = NOW() WHERE email = ?");
    $stmt->execute([$token, $email]);

    // Link generieren, aber nicht sagen ob E-Mail existiert (Datenschutz)
    if ($stmt->rowCount()) {
        $link = "http://localhost/energyfans/reset_password.php?token=$token";
    }
}
?>

<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Passwort vergessen</title>
  <link rel="stylesheet" href="assets/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: url('assets/images/background.jpg') no-repeat center center fixed;
      background-size: cover;
      color: #fff;
    }

    .form-box {
      max-width: 400px;
      margin: 100px auto;
      padding: 2rem;
      background: rgba(0, 0, 0, 0.8);
      border-radius: 15px;
      text-align: center;
    }

    input {
      background: rgba(255,255,255,0.1);
      color: #fff;
      margin-bottom: 1rem;
    }

    button {
      width: 100%;
      background-color: #28a745;
      color: #fff;
      border: none;
    }

    .link-button {
      display: inline-block;
      margin-top: 15px;
      padding: 10px 20px;
      background-color: #ffc107;
      color: #000;
      font-weight: bold;
      border-radius: 8px;
      text-decoration: none;
    }

    .link-button:hover {
      background-color: #ffca2c;
    }

    a.back-link {
      display: block;
      margin-top: 25px;
      color: #ccc;
      text-decoration: underline;
    }
  </style>
</head>
<body>

<div class="form-box">
  <h3>🔐 Passwort vergessen</h3>

  <?php if ($submitted): ?>
    <p>📩 Wenn die E-Mail registriert ist, wurde ein Link zum Zurücksetzen erstellt.</p>

    <?php if (!empty($link)): ?>
      <a href="<?= htmlspecialchars($link) ?>" class="btn btn-success mt-2 d-block">➡ Passwort zurücksetzen</a>
    <?php endif; ?>

  <?php else: ?>
    <form method="post">
      <input type="email" name="email" class="form-control" placeholder="Deine E-Mail" required>
      <button class="btn btn-success mt-2">Link senden</button>

    </form>
  <?php endif; ?>
  <a href="login.php" class="btn btn-primary mt-2 w-100" role="button">Zurück zum Login</a>
</div>

</body>
</html>
