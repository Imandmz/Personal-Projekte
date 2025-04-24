package app.menschaergerdichnicht;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Hauptklasse extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Laden der Benutzeroberfläche aus der FXML-Datei
            Parent root = loadRootFromFXML();

            // Erstellen und Konfigurieren der Szene
            Scene scene = createScene(root);

            // Konfigurieren der Hauptbühne (PrimaryStage)
            configurePrimaryStage(primaryStage, scene);

        } catch (Exception e) {
            // Fehlerbehandlung, falls ein Fehler auftritt
            handleException(e);
        }
    }

    // Methode zum Laden der Benutzeroberfläche aus der FXML-Datei
    private Parent loadRootFromFXML() throws Exception {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/menschaergerdichnicht/Main.fxml")));
    }

    // Methode zum Erstellen einer Szene mit der geladenen Benutzeroberfläche
    private Scene createScene(Parent root) {
        Scene scene = new Scene(root, 600, 800);
        // Hinzufügen von CSS-Stilen zur Szene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/app/menschaergerdichnicht/application.css")).toExternalForm());
        return scene;
    }

    // Methode zum Konfigurieren der Hauptbühne (PrimaryStage)
    private void configurePrimaryStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mensch Ärgere Dich Nicht"); // Setzen des Fenstertitels
        primaryStage.setResizable(false); // Festlegen, dass das Fenster nicht veränderbar in der Größe ist
        primaryStage.show(); // Anzeigen des Fensters
    }

    // Methode zur Fehlerbehandlung, falls ein Fehler auftritt
    private void handleException(Exception e) {
        e.printStackTrace();
    }

    // Die Hauptmethode, die die Anwendung startet
    public static void main(String[] args) {
        launch(args); // Startet die JavaFX-Anwendung
    }
}

