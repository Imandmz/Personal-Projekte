<?php
session_start();
require_once 'config.php';

if (!isset($_SESSION['user_id'])) {
    header('Location: login.php');
    exit();
}

$userId = $_SESSION['user_id'];
$userEmail = $_SESSION['user_email'] ?? '';

// Letzten Login abrufen
$stmt = $pdo->prepare("SELECT last_login FROM kunden WHERE id = ?");
$stmt->execute([$userId]);
$lastLogin = $stmt->fetchColumn();
?>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>EnergyFans - Startseite</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="assets/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    #videoPreviewBox {
      position: fixed;
      bottom: 20px;
      right: 20px;
      width: 220px;
      height: 120px;
      z-index: 999;
      border-radius: 12px;
      overflow: hidden;
      box-shadow: 0 0 12px rgba(0,0,0,0.6);
      cursor: pointer;
    }

    #videoPreviewBox video {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    #videoModal {
      display: none;
      position: fixed;
      z-index: 10000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0, 0, 0, 0.85);
    }

    .videoModalContent {
      margin: 5% auto;
      padding: 20px;
      width: 90%;
      max-width: 800px;
      position: relative;
    }

    #modalVideo {
      width: 100%;
      border-radius: 10px;
    }

    .closeModal {
      position: absolute;
      top: 15px;
      right: 20px;
      font-size: 22px;
      font-weight: bold;
      background-color: #dc3545;
      color: #fff;
      border: none;
      border-radius: 50%;
      padding: 10px 14px;
      cursor: pointer;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
      transition: background-color 0.3s ease;
      z-index: 10001;
    }

    .closeModal:hover {
      background-color: #bb2d3b;
    }
  </style>
</head>
<body>

<?php include 'navbar.php'; ?>

<div class="container container-box mt-5">
  <h1>Willkommen bei EnergyFans!</h1>
  <p>
    Hallo <?= htmlspecialchars($userEmail) ?>,<br>
    <?php
      if (!empty($_SESSION['last_login_display']) && strtotime($_SESSION['last_login_display']) > 0) {
          echo "Sie waren zuletzt am " . date('d.m.Y H:i', strtotime($_SESSION['last_login_display'])) . " online.";
      } else {
          echo "Willkommen beim ersten Besuch!";
      }
    ?>
  </p>

  <!-- Produkt-Slider -->
  <div id="productCarousel" class="carousel slide mt-4" data-bs-ride="carousel">
    <div class="carousel-inner">
      <?php
      $stmt = $pdo->query("SELECT * FROM produkte");
      $products = $stmt->fetchAll();
      $first = true;
      foreach ($products as $product): ?>
        <div class="carousel-item <?= $first ? 'active' : '' ?>">
          <img src="assets/images/<?= htmlspecialchars($product['bild']) ?>" class="d-block w-100" alt="<?= htmlspecialchars($product['name']) ?>">
        </div>
      <?php $first = false; endforeach; ?>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Zurück</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Weiter</span>
    </button>
  </div>
</div>

<!-- Vorschau unten rechts -->
<div id="videoPreviewBox">
  <video id="previewVideo" muted autoplay loop playsinline>
    <source src="assets/videos/energy.mp4" type="video/mp4">
    Dein Browser unterstützt dieses Video nicht.
  </video>
</div>

<!-- Vollbild-Video -->
<div id="videoModal">
  <div class="videoModalContent">
    <button class="closeModal">✖</button>
    <video id="modalVideo" controls>
      <source src="assets/videos/energy.mp4" type="video/mp4">
    </video>
  </div>
</div>

<?php include 'footer.php'; ?>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  const previewBox = document.getElementById("videoPreviewBox");
  const videoModal = document.getElementById("videoModal");
  const closeModal = document.querySelector(".closeModal");
  const modalVideo = document.getElementById("modalVideo");

  previewBox.addEventListener("click", () => {
    videoModal.style.display = "block";
    modalVideo.currentTime = 0;
    modalVideo.pause(); // Benutzer startet manuell
  });

  closeModal.addEventListener("click", () => {
    videoModal.style.display = "none";
    modalVideo.pause();
  });

  window.addEventListener("click", e => {
    if (e.target === videoModal) {
      videoModal.style.display = "none";
      modalVideo.pause();
    }
  });
</script>
</body>
</html>
