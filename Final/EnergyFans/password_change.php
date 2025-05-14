<?php 
session_start();
require_once 'config.php';

if (!isset($_SESSION['temp_user_id'])) {
    header('Location: login.php');
    exit();
}

$error = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['password1'], $_POST['password2'])) {
    if ($_POST['password1'] !== $_POST['password2']) {
        $error = "Passwörter stimmen nicht überein.";
    } else {
        $password = $_POST['password1'];

        if (
            strlen($password) < 9 ||
            !preg_match('/[A-Z]/', $password) ||
            !preg_match('/[a-z]/', $password) ||
            !preg_match('/[0-9]/', $password)
        ) {
            $error = "Passwort muss mindestens 9 Zeichen, Großbuchstaben, Kleinbuchstaben und eine Zahl enthalten.";
        } else {
            $newPasswordHash = hash('sha512', $password);

            // Passwort speichern & first_login auf 0
            $stmt = $pdo->prepare("UPDATE kunden SET password = ?, first_login = 0 WHERE id = ?");
            $stmt->execute([$newPasswordHash, $_SESSION['temp_user_id']]);

            // Secret + E-Mail laden
            $stmt = $pdo->prepare("SELECT email, secret FROM kunden WHERE id = ?");
            $stmt->execute([$_SESSION['temp_user_id']]);
            $user = $stmt->fetch();

            if ($user) {
                $_SESSION['user_email'] = $user['email'];
                $_SESSION['2fa_secret'] = $user['secret'];
                $_SESSION['user_id'] = $_SESSION['temp_user_id'];
                unset($_SESSION['temp_user_id']);
                header('Location: verify_2fa.php');
                exit();
            } else {
                $error = "Benutzerdaten konnten nicht geladen werden.";
            }
        }
    }
}
?>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Passwort ändern</title>
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
            background-color: rgba(0,0,0,0.75);
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
        }
    </style>
</head>
<body>
<div class="login-form">
    <h2 class="text-center">Neues Passwort setzen</h2>
    <?php if (!empty($error)) echo "<div class='error'>$error</div>"; ?>
    <form method="post">
        <input type="password" name="password1" placeholder="Neues Passwort" required>
        <input type="password" name="password2" placeholder="Passwort bestätigen" required>
        <button type="submit">Speichern & Weiter</button>
    </form>
</div>
<?php include 'footer.php'; ?>
</body>
</html>
