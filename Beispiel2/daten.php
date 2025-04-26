<?php
if (isset($_GET['studienart'])) {
    $studienart = $_GET['studienart'];
    $module = [];

    if ($studienart == "wi") {
        $module = ["Grundlagen der Wirtschaftsinformatik", "Grundlagen der Wirtschaftswissenschaften", "Grundlagen der Informatik", "Mathematische Grundlagen"];
    } elseif ($studienart == "muk") {
        $module = ["Theoretische Grundlagen 1 Vorlesung", "Theoretische Grundlagen 1 Praktikum", "Informatik 1 Vorlesung", "Informatik 1 Praktikum", "Grafik", "Fotografie"];
    } elseif ($studienart == "mti") {
        $module = ["Formale Methoden 1 Vorlesung", "Formale Methoden 1 Praktikum", "Informatik 1 Vorlesung", "Medizininformatik", "Medizinische Grundlagen"];
    }

    function ausgabe($daten) {
        $html = "<ul>";
        foreach ($daten as $modul) {
            $html .= "<li>" . htmlspecialchars($modul) . "</li>";
        }
        $html .= "</ul>";
        echo $html;
    }

    ausgabe($module);
}
?>