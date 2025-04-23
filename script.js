document.getElementById('startBtn').addEventListener('click', () => {
  const count = parseInt(document.getElementById('buttonCount').value);
  const container = document.getElementById('buttonContainer');

  container.innerHTML = ''; // Alte Buttons löschen

  if (isNaN(count) || count < 1) {
    alert('Bitte gib eine gültige Zahl ein.');
    return;
  }

  for (let i = 1; i <= count; i++) {
    const btn = createButton(i);  // Button wird mit der Funktion erstellt
    container.appendChild(btn);
  }
});

// Funktion zur Button-Erstellung
function createButton(i) {
  const btn = document.createElement('button');
  btn.textContent = `New Button Nr:${i}`;

  // Event Listener für alle Buttons
  btn.addEventListener('click', () => {
    let message = `Hello Button Nr:${i}`;
    if (i >= 2) {
      message += `\n\n☑ Diese Seiten daran hindern, weitere Dialoge zu öffnen`;
    }
    alert(message);
  });

  return btn;
}

// Funktion zum Schließen des Modals (optional, falls du später wieder Modals nutzt)
function closeModal() {
  const modal = document.getElementById('customModal');
  modal.style.display = 'none';
}

