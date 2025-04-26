<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>AJAX Beispiel 2 (jQuery)</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#studienart").change(function() {
                let auswahl = $(this).val();
                if (auswahl != "") {
                    $.get("daten.php", { studienart: auswahl }, function(data) {
                        $("#module").html(data);
                    });
                }
            });
        });
    </script>
</head>
<body>
    <h1>Studienart auswählen</h1>
    <form>
        <select id="studienart">
            <option value="">wählen</option>
            <option value="wi">Wirtschaftsinformatik</option>
            <option value="muk">Medien- und Kommunikationsinformatik</option>
            <option value="mti">Medizinisch-Technische Informatik</option>
        </select>
    </form>
    <div id="module"></div>
</body>
</html>