package app.menschaergerdichnicht;


import javafx.scene.shape.Circle;

public class Spieler {


    // Spieler array
    static Spieler[] Spielerr = new Spieler[4];

    // Name des Spielers
    String Name;

    // Die Startposition des Spielers
    int SpielerStartPosition;

    // Koordinaten für die Spielerposition auf dem Spielfeld
    int pEX;
    int pEY;

    static int [][] Spieler_Position = new int [][] {{87,119,148,177,208,238 ,269,269,269,269,269,269 ,301,331 ,331,331,331,331,331 ,362,392,422,451,482,513 ,513,513 ,482,451,422,392,362 ,331,331,331,331,331,331 ,301,269 ,269,269,269,269,269 ,238,208,177,148,119,87 ,87},
            {332,332,332,332,332,332 ,302,272,241,211,183,151 ,151,151 ,183,211,241,272,302 ,332,332,332,332,332,332 ,362,393 ,393,393,393,393,393 ,427,459,487,516,547,577 ,577,577 ,547,516,487,459,427 ,393,393,393,393,393,393 ,362}};
    //Start positionen
    static int [][] StartP = new int [][] {{90,170,430,510},{150,242,482,574}};

    //Aktueller Spieler
    static int AktuelSpieler =-1;

    // Boolean-Wert, der den Gewinnstatus des Spielers angibt
    boolean Gewin;

    // Array zur Speicherung der Positionen der Figuren
    int[] FigurePosition = new int[4];

    // Array zur Verfolgung, ob bestimmte Figuren eingenommen wurden
    boolean[] FigureEinnahme = new boolean[4];


    // Initiales Array zur Speicherung der Positionen der Figuren
    int[][] FigurePositionIniniale = new int[2][4];

    // Array, das den aktuellen Status der Figuren angibt
    boolean[] FigureAktuel = new boolean[4];

    // Array, das angibt, ob eine Figur betreten wurde
    boolean[] FigureEntered = new boolean[4];

    // Array von Kreisen zur Darstellung der Figuren
    Circle[] Figuren = new Circle[4];

    // Aktueller Fortschritt des Spielers im Spiel
    double AktuellerProzess;

    // Farbe des Spielers
    String color;


    // Konstruktor für die Spielerklasse
    Spieler(String Name, String color) {
        this.Name = Name;
        this.color = color;

        Gewin = false; // Initialisierung des Gewinnstatus

        AktuellerProzess = 0; // Initialisierung des Fortschritts

        // Setzen der Anfangspositionen für die Figuren
        for (int i = 0; i < 4; i++) {
            FigurePosition[i] = 0;
        }
    }

}