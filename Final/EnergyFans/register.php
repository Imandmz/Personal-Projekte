<?php
session_start();
require_once 'config.php';
require_once __DIR__ . '/includes/GoogleAuthenticator.php';


$error = '';
$showQRCode = false;
$qrCodeUrl = '';
$genPass = '';

if (isset($_POST['email'])) {
    $email = $_POST['email'];

    if (strlen($email) < 5 || strpos($email, '@') === false) {
        $error = "Ungültige E-Mail Adresse.";
    } else {
        $stmt = $pdo->prepare("SELECT id FROM kunden WHERE email = ?");
        $stmt->execute([$email]);

        if ($stmt->rowCount() === 0) {
            $password = bin2hex(random_bytes(5)); // z. B. 10 Zeichen
            $passwordHash = hash('sha512', $password);

            $gAuth = new PHPGangsta_GoogleAuthenticator();
            $secret = $gAuth->createSecret();

            $insert = $pdo->prepare("INSERT INTO kunden (email, password, secret, punkte, first_login) VALUES (?, ?, ?, 100, 1)");
            $insert->execute([$email, $passwordHash, $secret]);

            $genPass = $password;
            $qrCodeUrl = $gAuth->getQRCodeGoogleUrl("EnergyFans:$email", $secret, "EnergyFans");
            $showQRCode = true;
        } else {
            $error = "Benutzer existiert bereits.";
        }
    }
}
?>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Registrieren - EnergyFans</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        background: url('assets/images/background.jpg') no-repeat center center fixed;
        background-size: cover;
        color: #fff;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .login-form {
        max-width: 420px;
        margin: 80px auto;
        padding: 2rem;
        background: rgba(0, 0, 0, 0.8);
        border-radius: 15px;
        text-align: center;
    }

    input {
        background: rgba(255,255,255,0.1);
        color: #fff;
        margin-bottom: 1rem;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        width: 100%;
    }

    input::placeholder {
        color: #ccc;
    }

    button {
        width: 100%;
        background-color: #28a745;
        color: #fff;
        font-weight: bold;
        border: none;
        padding: 10px;
        border-radius: 8px;
        margin-bottom: 10px;
    }

    .btn-back {
        background-color: #007bff;
        color: #fff;
    }

    /* Stil für QR-Code-Bereich */
    .qrcode {
        text-align: center;
        margin-top: 25px;
    }

    .error {
        color: red;
        text-align: center;
    }

    /* Angepasster Zurück-zum-Login-Button */
    a.btn-primary {
        background-color: #007bff !important;
        color: #fff !important;
        font-weight: bold;
        border: none;
        border-radius: 8px;
        padding: 10px;
        display: block;
        width: 100%;
        text-align: center;
        text-decoration: none;
        margin-top: 10px;
    }

    a.btn-primary:hover {
        background-color: #3399ff !important;
        color: #fff !important;
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
</style>
</head>
<body>

<div class="login-form">
    <h2>Registrieren</h2>
    <?php if (!empty($error)) echo "<p class='error'>$error</p>"; ?>

    <?php if ($showQRCode): ?>
        <p><strong>📩 Ihr Startpasswort:</strong> <?= htmlspecialchars($genPass) ?></p>
        <p>Bitte speichern Sie dieses Passwort gut ab.</p>
        <div class="qrcode">
            <p>🔐 Scannen Sie diesen QR-Code in Ihrer Authenticator-App:</p>
            <img src="<?= $qrCodeUrl ?>" alt="QR Code"><br>
        </div>
        <a href="login.php"><button class="btn-back mt-3">Zum Login</button></a>
    <?php else: ?>
        <form method="post">
            <input type="email" name="email" id="email" placeholder="E-Mail" required>
            <small id="feedback" class="text-info"></small>
            <button type="submit">Registrieren</button>
        </form>
        <a href="login.php" class="btn btn-primary mt-0 w-100" role="button">Zurück zum Login</a>

    <?php endif; ?>
</div>

<script>
document.getElementById("email")?.addEventListener("input", function () {
    const email = this.value;
    if (email.length >= 5) {
        fetch("check_username.php?email=" + encodeURIComponent(email))
            .then(res => res.text())
            .then(text => {
                const feedback = document.getElementById("feedback");
                if (text === "belegt") {
                    feedback.textContent = "❌ Diese E-Mail ist bereits vergeben.";
                    feedback.style.color = "red";
            });
    }
});
</script>

<?php include 'footer.php'; ?>
</body>
</html>
