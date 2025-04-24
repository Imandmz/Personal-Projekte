module app.menschaergerdichnicht {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.menschaergerdichnicht to javafx.fxml;
    exports app.menschaergerdichnicht;
}