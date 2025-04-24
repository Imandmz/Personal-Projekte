package app.menschaergerdichnicht;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Random;

import static app.menschaergerdichnicht.Spieler.*;

public class GameController extends Hauptklasse {
    //random Klasse
    Random RandomKlasse = new Random();

    //Figur Buttons array
    static Button[] FigB = new Button[4];
    //Wurfel X
    static int X=0;
    //Gewinn variable
    static boolean Gewin;
    //GewinNr images
    static int GewinNr;
    //String GewinStr zu Zeigen
    static String GewinStr;
    //positionen

    //Menu
    @FXML private AnchorPane SpielStartMenu;
    @FXML private AnchorPane SpielAPane;
    @FXML private Button Dice_Button;
    @FXML private Rectangle diceButtonRec;
    @FXML private Button Start_Button;
    @FXML private Label LDiceRoll;

    //Figur Buttons
    @FXML private Button FigButton1;
    @FXML private Button FigButton2;
    @FXML private Button FigButton3;
    @FXML private Button FigButton4;

    //Spieler 1 =
    @FXML private CheckBox Spieler1_C;
    @FXML private Label Spieler1_Lb;
    @FXML private TextField Spieler1_N;

    @FXML private Circle FigurS1;
    @FXML private Circle FigurS2;
    @FXML private Circle FigurS3;
    @FXML private Circle FigurS4;
    @FXML private Rectangle Spieler1_Rec;
    @FXML private Polygon Spieler1_Tri;

    //Spieler 2 =
    @FXML private CheckBox Spieler2_C;
    @FXML private Label Spieler2_Lb;
    @FXML private TextField Spieler2_N;

    @FXML private Circle FigurT1;
    @FXML private Circle FigurT2;
    @FXML private Circle FigurT3;
    @FXML private Circle FigurT4;
    @FXML private Rectangle Spieler2_Rec;
    @FXML private Polygon Spieler2_Tri;

    //Spieler 3 =
    @FXML private CheckBox Spieler3_C;
    @FXML private Label Spieler3_Lb;
    @FXML private TextField Spieler3_N;

    @FXML private Circle FigurU1;
    @FXML private Circle FigurU2;
    @FXML private Circle FigurU3;
    @FXML private Circle FigurU4;
    @FXML private Rectangle Spieler3_Rec;
    @FXML private Polygon Spieler3_Tri;

    //Spieler 4
    @FXML private CheckBox Spieler4_C;
    @FXML private Label Spieler4_Lb;
    @FXML private TextField Spieler4_N;

    @FXML private Circle FigurV1;
    @FXML private Circle FigurV2;
    @FXML private Circle FigurV3;
    @FXML private Circle FigurV4;
    @FXML private Rectangle FigurVRec;
    @FXML private Polygon Spieler4_Tri;


    //Menu
    public void checkboxChanged() {
            int ausgewaehlteSpieler = 0;
            // Überprüfe, wie viele Spieler ausgewählt sind
            if (Spieler1_C.isSelected()) ausgewaehlteSpieler++;
            if (Spieler2_C.isSelected()) ausgewaehlteSpieler++;
            if (Spieler3_C.isSelected()) ausgewaehlteSpieler++;
            if (Spieler4_C.isSelected()) ausgewaehlteSpieler++;

            // Aktiviere oder deaktiviere den Start-Button basierend auf der Anzahl der ausgewählten Spieler
            Start_Button.setDisable(ausgewaehlteSpieler < 2);
        }


    public void Start_Game() {
        LDiceRoll =new Label("");
        LDiceRoll.setTextAlignment(TextAlignment.CENTER);
        LDiceRoll.setLayoutX(100);
        LDiceRoll.setLayoutY(700);
        SpielAPane.getChildren().add(LDiceRoll);

        AktuelSpieler =-1;
        int nameInt=1;
        String color;
        String name;

        if(Spieler1_C.isSelected()) {
            if (AktuelSpieler <0) AktuelSpieler =0;

            color = getRandomColor();
            if(Spieler1_N.getText().isEmpty()) {name = "Spieler "+nameInt;nameInt++;}
            else name = Spieler1_N.getText();
            Spielerr[0] = new Spieler(name,color);
            Spielerr[0].Figuren[0] = FigurS1;
            Spielerr[0].Figuren[1] = FigurS2;
            Spielerr[0].Figuren[2] = FigurS3;
            Spielerr[0].Figuren[3] = FigurS4;
            for(int i=0;i<4;i++) Spielerr[0].Figuren[i].setFill(Paint.valueOf(Spielerr[0].color));
            for(int i=0;i<4;i++) {
                Spielerr[0].FigurePositionIniniale[0][i]=(int) Spielerr[0].Figuren[i].getLayoutX()-13;
                Spielerr[0].FigurePositionIniniale[1][i]=(int) Spielerr[0].Figuren[i].getLayoutY()-13;}
            for(int i=0;i<4;i++) Spielerr[0].FigureAktuel[i]=false;
            for(int i=0;i<4;i++) Spielerr[0].FigureEinnahme[i]=false;
            Spielerr[0].SpielerStartPosition =1;
            Spielerr[0].pEX =0;
            Spielerr[0].pEY =1;
            Spieler1_Lb.setText(Spielerr[0].Name);
            Spieler1_Lb.setTextFill(Paint.valueOf(Spielerr[0].color));
            Spieler1_Rec.setFill(Paint.valueOf(Spielerr[0].color));
            Spieler1_Tri.setFill(Paint.valueOf(Spielerr[0].color));
        }
        if(Spieler2_C.isSelected()) {
            if (AktuelSpieler <0) AktuelSpieler =1;
            color = getRandomColor();
            if(Spieler2_N.getText().isEmpty()) {name = "Spieler "+nameInt;nameInt++;}
            else name = Spieler2_N.getText();
            Spielerr[1] = new Spieler(name,color);

            Spielerr[1].Figuren[0] = FigurT1;
            Spielerr[1].Figuren[1] = FigurT2;
            Spielerr[1].Figuren[2] = FigurT3;
            Spielerr[1].Figuren[3] = FigurT4;

            for(int i=0;i<4;i++) Spielerr[1].Figuren[i].setFill(Paint.valueOf(Spielerr[1].color));
            for(int i=0;i<4;i++) {
                Spielerr[1].FigurePositionIniniale[0][i]=(int) Spielerr[1].Figuren[i].getLayoutX()-13;
                Spielerr[1].FigurePositionIniniale[1][i]=(int) Spielerr[1].Figuren[i].getLayoutY()-13;}
            for(int i=0;i<4;i++) Spielerr[1].FigureAktuel[i]=false;
            for(int i=0;i<4;i++) Spielerr[1].FigureEinnahme[i]=false;
            Spielerr[1].SpielerStartPosition =14;
            Spielerr[1].pEX =-1;
            Spielerr[1].pEY =0;
            Spieler2_Lb.setText(Spielerr[1].Name);
            Spieler2_Lb.setTextFill(Paint.valueOf(Spielerr[1].color));
            Spieler2_Rec.setFill(Paint.valueOf(Spielerr[1].color));
            Spieler2_Tri.setFill(Paint.valueOf(Spielerr[1].color));
        }
        if(Spieler4_C.isSelected()) {
            if (AktuelSpieler <0) AktuelSpieler =3;
            color = getRandomColor();
            if(Spieler4_N.getText().isEmpty()) {name = "Spieler "+nameInt;nameInt++;}
            else name = Spieler4_N.getText();
            Spielerr[3] = new Spieler(name,color);

            Spielerr[3].Figuren[0] = FigurV1;
            Spielerr[3].Figuren[1] = FigurV2;
            Spielerr[3].Figuren[2] = FigurV3;
            Spielerr[3].Figuren[3] = FigurV4;

            for(int i=0;i<4;i++) Spielerr[3].Figuren[i].setFill(Paint.valueOf(Spielerr[3].color));
            for(int i=0;i<4;i++) {
                Spielerr[3].FigurePositionIniniale[0][i]=(int) Spielerr[3].Figuren[i].getLayoutX()-13;
                Spielerr[3].FigurePositionIniniale[1][i]=(int) Spielerr[3].Figuren[i].getLayoutY()-13;}
            for(int i=0;i<4;i++) Spielerr[3].FigureAktuel[i]=false;
            for(int i=0;i<4;i++) Spielerr[3].FigureEinnahme[i]=false;
            Spielerr[3].SpielerStartPosition =27;
            Spielerr[3].pEX =0;
            Spielerr[3].pEY =-1;
            Spieler4_Lb.setText(Spielerr[3].Name);
            Spieler4_Lb.setTextFill(Paint.valueOf(Spielerr[3].color));
            FigurVRec.setFill(Paint.valueOf(Spielerr[3].color));
            Spieler4_Tri.setFill(Paint.valueOf(Spielerr[3].color));
        }
        if(Spieler3_C.isSelected()) {
            if (AktuelSpieler <0) AktuelSpieler =2;
            color = getRandomColor();
            if(Spieler3_N.getText().isEmpty()) {name = "Spieler "+nameInt;nameInt++;}
            else name = Spieler3_N.getText();
            Spielerr[2] = new Spieler(name,color);

            Spielerr[2].Figuren[0] = FigurU1;
            Spielerr[2].Figuren[1] = FigurU2;
            Spielerr[2].Figuren[2] = FigurU3;
            Spielerr[2].Figuren[3] = FigurU4;

            for(int i=0;i<4;i++) Spielerr[2].Figuren[i].setFill(Paint.valueOf(Spielerr[2].color));
            for(int i=0;i<4;i++) {
                Spielerr[2].FigurePositionIniniale[0][i]=(int) Spielerr[2].Figuren[i].getLayoutX()-13;
                Spielerr[2].FigurePositionIniniale[1][i]=(int) Spielerr[2].Figuren[i].getLayoutY()-13;}
            for(int i=0;i<4;i++) Spielerr[2].FigureAktuel[i]=false;
            for(int i=0;i<4;i++) Spielerr[2].FigureEinnahme[i]=false;
            Spielerr[2].SpielerStartPosition =40;
            Spielerr[2].pEX =1;
            Spielerr[2].pEY =0;
            Spieler3_Lb.setText(Spielerr[2].Name);
            Spieler3_Lb.setTextFill(Paint.valueOf(Spielerr[2].color));
            Spieler3_Rec.setFill(Paint.valueOf(Spielerr[2].color));
            Spieler3_Tri.setFill(Paint.valueOf(Spielerr[2].color));
        }
        diceButtonRec.setFill(Paint.valueOf(Spielerr[AktuelSpieler].color));//Die Farbe des Würfelbuttons wird auf die Farbe des aktuellen Spielers gesetzt

        FigB[0] = FigButton1;
        FigB[1] = FigButton2;
        FigB[2] = FigButton3;
        FigB[3] = FigButton4;

        GewinNr =1;
        GewinStr ="";

        SpielStartMenu.setVisible(false);
        SpielAPane.setVisible(true);
    }
    public void ZuruckzuMenu() {

        for(int i=0;i<3;i+=2) {
            for(int k=0;k<3;k+=2) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutX(StartP[0][0]);
            for(int k=1;k<4;k+=2) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutX(StartP[0][1]);
        }

        for(int i=1;i<4;i+=2) {
            for(int k=0;k<3;k+=2) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutX(StartP[0][2]);
            for(int k=1;k<4;k+=2) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutX(StartP[0][3]);
        }

        for(int i=0;i<2;i++) {
            for(int k=0;k<2;k++) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutY(StartP[1][0]);
            for(int k=2;k<4;k++) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutY(StartP[1][1]);
        }

        for(int i=2;i<4;i++) {
            for(int k=0;k<2;k++) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutY(StartP[1][2]);
            for(int k=2;k<4;k++) if(Spielerr[i]!=null) Spielerr[i].Figuren[k].setLayoutY(StartP[1][3]);
        }

        for(int i=0;i<4;i++) if(Spielerr[i]!=null) for(int k = 0; k<4; k++) Spielerr[i].Figuren[k].setFill(Paint.valueOf("#ffffff"));

        for(int i=0;i<4;i++) Spielerr[i]=null;

        Spieler1_Lb.setTextFill(Paint.valueOf("#ffffff"));
        Spieler3_Lb.setTextFill(Paint.valueOf("#ffffff"));
        Spieler3_Lb.setTextFill(Paint.valueOf("#ffffff"));
        Spieler3_Lb.setTextFill(Paint.valueOf("#ffffff"));
        Spieler1_Rec.setFill(Paint.valueOf("#ffffff"));
        Spieler2_Rec.setFill(Paint.valueOf("#ffffff"));
        Spieler3_Rec.setFill(Paint.valueOf("#ffffff"));
        FigurVRec.setFill(Paint.valueOf("#ffffff"));
        Spieler1_Tri.setFill(Paint.valueOf("#ffffff"));
        Spieler2_Tri.setFill(Paint.valueOf("#ffffff"));
        Spieler3_Tri.setFill(Paint.valueOf("#ffffff"));
        Spieler4_Tri.setFill(Paint.valueOf("#ffffff"));

        for(int i=0;i<4;i++) Spielerr[i]=null;
        Dice_Button.setDisable(false);
        setD_ButtonsNotVisible(FigB);

        SpielStartMenu.setVisible(true);
        SpielAPane.setVisible(false);

        LDiceRoll.setText("");
    }
    public String getRandomColor() {

        StringBuilder color = new StringBuilder("#");
        String alphebet = "abcdef";
        for(int i=0;i<6;i++) {
            if(RandomKlasse.nextBoolean()) color.append(RandomKlasse.nextInt(9));
            else color.append(alphebet.charAt(RandomKlasse.nextInt(alphebet.length())));
        }
        return color.toString();
    }


    public int getRandom() {
        return RandomKlasse.nextInt(6)+1;
    }
    public void rollDice() {

        Gewin = false;
        X = getRandom();
        System.out.println(X);
        // +", Wurf ist:  "+X
        LDiceRoll.setText("Spieler: "+ Spielerr[AktuelSpieler].Name);
        Dice_Button.setText(""+X);
        int D_As= Zeiger4AktiveFigure(Spielerr[AktuelSpieler]);

        if(X==6){
            int k=setD_ButtonsVisible(X, Spielerr[AktuelSpieler], FigB);
            if(k>0) {
                setD_ButtonsNotVisible(FigB);
                if(Spielerr[AktuelSpieler].FigureAktuel[k-1]) {
                    Gewin = FigureBewegen(Spielerr[AktuelSpieler],k-1,X);
                }
                else {
                    Gewin = FigureBewegen(Spielerr[AktuelSpieler],k-1, Spielerr[AktuelSpieler].SpielerStartPosition);
                    Spielerr[AktuelSpieler].FigureAktuel[k-1]=true;
                }
            }
            else if(k!=0){
                Dice_Button.setDisable(true);
            }
            return;
        }
        else if(D_As==1) {
            int y = getAktivFig();
            Gewin = FigureBewegen(Spielerr[AktuelSpieler],y,X);
        }
        else if(D_As>1){
            int k=setD_ButtonsVisible(X, Spielerr[AktuelSpieler], FigB);
            if(k>0) {
                setD_ButtonsNotVisible(FigB);
                Gewin = FigureBewegen(Spielerr[AktuelSpieler],k-1,X);
            }
            else if(k!=0){
                Dice_Button.setDisable(true);
                return;
            }
        }
        if(Gewin) {
            Gewonnen();
            Spielerr[AktuelSpieler].Gewin =true;
            GewinNr++;
            System.out.println("Win_Count: "+ GewinNr);
            return;
        }
        WechselSpieller();
    }
    public void WechselSpieller() {
        while(true) {
            switch(AktuelSpieler) {
                case 0: AktuelSpieler =1;break;
                case 1: AktuelSpieler =3;break;
                case 2: AktuelSpieler =0;break;
                case 3: AktuelSpieler =2;break;
            }
            if (Spielerr[AktuelSpieler]!=null && !Spielerr[AktuelSpieler].Gewin) {
                diceButtonRec.setFill(Paint.valueOf(Spielerr[AktuelSpieler].color));
                return;
            }
        }
    }

    public int getAktivFig() {
        for (int i=0;i<4;i++) {
            if(Spielerr[AktuelSpieler].FigureAktuel[i]) {
                return i;
            }
        }
        return -1;
    }
    public int Zeiger4AktiveFigure(Spieler Pl) {
        int AF =0;
        for(int i=0;i<4;i++) if(Pl.FigureAktuel[i]) AF++;
        return AF;
    }
    public boolean FigureBewegen(Spieler S, int Figur, int x) {
        boolean Gewinnen =false;
        S.FigurePosition[Figur]= S.FigurePosition[Figur]+x;

        if(S.FigurePosition[Figur]>51) S.FigurePosition[Figur]= S.FigurePosition[Figur]-52;

        if(!S.FigureEinnahme[Figur]) {
            int Q = S.SpielerStartPosition -7;

            if (Q <0) Q = Q +51;

            if(S.SpielerStartPosition ==1) {if(S.FigurePosition[Figur]> Q) S.FigureEinnahme[Figur]=true;}
            else if(S.FigurePosition[Figur]> Q && S.FigurePosition[Figur]< S.SpielerStartPosition) S.FigureEinnahme[Figur]=true;
            S.Figuren[Figur].setLayoutX(Spieler_Position[0][S.FigurePosition[Figur]]);
            S.Figuren[Figur].setLayoutY(Spieler_Position[1][S.FigurePosition[Figur]]);
            verarbeiteEinnahme(Figur);
        }
        else {
            if(S.FigurePosition[Figur]> S.SpielerStartPosition) {
                if(S.FigurePosition[Figur]> S.SpielerStartPosition && S.FigurePosition[Figur]< S.SpielerStartPosition +6) {
                    S.Figuren[Figur].setLayoutX(Spieler_Position[0][S.FigurePosition[Figur]-1]+30* S.pEX);
                    S.Figuren[Figur].setLayoutY(Spieler_Position[1][S.FigurePosition[Figur]-1]+30* S.pEY);
                }
                else if(S.FigurePosition[Figur]== S.SpielerStartPosition +6){
                    S.Figuren[Figur].setLayoutX(Spieler_Position[0][S.FigurePosition[Figur]-1]+60* S.pEX);
                    S.Figuren[Figur].setLayoutY(Spieler_Position[1][S.FigurePosition[Figur]-1]+60* S.pEY);
                    S.FigureEntered[Figur]=true;
                    Gewinnen = Gewonnen(S);
                    S.FigureAktuel[Figur]=false;
                }
                else if(S.FigurePosition[Figur]> S.SpielerStartPosition +6 && S.FigurePosition[Figur]< S.SpielerStartPosition +12){
                    S.FigurePosition[Figur]= S.FigurePosition[Figur]-x;
                }
                else {
                    S.Figuren[Figur].setLayoutX(Spieler_Position[0][S.FigurePosition[Figur]]);
                    S.Figuren[Figur].setLayoutY(Spieler_Position[1][S.FigurePosition[Figur]]);
                    verarbeiteEinnahme(Figur);
                }
            }
            else {

                S.Figuren[Figur].setLayoutX(Spieler_Position[0][S.FigurePosition[Figur]]);
                S.Figuren[Figur].setLayoutY(Spieler_Position[1][S.FigurePosition[Figur]]);
                verarbeiteEinnahme(Figur);
            }
        }
        return Gewinnen;
    }

    public void FigurButtonKlicker(Event Ev) {

        int x = Integer.parseInt(((Button)Ev.getSource()).getText());
        int y =	Integer.parseInt(Dice_Button.getText());
        if(Spielerr[AktuelSpieler].FigureAktuel[x]) {
            Gewin = FigureBewegen(Spielerr[AktuelSpieler],x,y);
        }
        else {
            Gewin = FigureBewegen(Spielerr[AktuelSpieler],x, Spielerr[AktuelSpieler].SpielerStartPosition);
            Spielerr[AktuelSpieler].FigureAktuel[x]=true;
        }
        setD_ButtonsNotVisible(FigB);
        Dice_Button.setDisable(false);


        if(Gewin) {
            Gewonnen();
            Spielerr[AktuelSpieler]=null;
            GewinNr++;
        }

        if(y==6) return;

        WechselSpieller();
    }
    public int setD_ButtonsVisible(int d, Spieler P, Button[] FigurBs) {
        int z =0;
        if(d==6) {
            for(int i=0;i<4;i++) {
                if(!P.FigureAktuel[i]) {
                    if(!P.FigureEntered[i]) {
                        FigurBs[i].setLayoutX(P.FigurePositionIniniale[0][i]);
                        FigurBs[i].setLayoutY(P.FigurePositionIniniale[1][i]);
                        FigurBs[i].setVisible(true);
                        if(z ==0) z =i+1;
                        else z =-1;
                    }
                }
                else if(!(P.FigureEinnahme[i] && (P.FigurePosition[i]>P.SpielerStartPosition && P.FigurePosition[i]<P.SpielerStartPosition +6))){

                    z = getZ(P, FigurBs, z, i);
                }
            }
        }
        else {

            for(int i=0;i<4;i++) {
                if(P.FigureAktuel[i]) {
                    if(!P.FigureEinnahme[i]) {
                        z = getZ(P, FigurBs, z, i);
                    }
                    else if(P.FigurePosition[i]>P.SpielerStartPosition && P.FigurePosition[i]<P.SpielerStartPosition +6) {

                        if (P.FigurePosition[i]+d<=P.SpielerStartPosition +6) {

                            FigurBs[i].setLayoutX((Spieler_Position[0][P.FigurePosition[i]-1]+30*P.pEX)-13);
                            FigurBs[i].setLayoutY((Spieler_Position[1][P.FigurePosition[i]-1]+30*P.pEY)-13);
                            FigurBs[i].setVisible(true);
                            if(z ==0) z =i+1;
                            else z =-1;
                        }
                    }
                    else {

                        z = getZ(P, FigurBs, z, i);
                    }
                }
            }
        }
        return z;
    }

    private int getZ(Spieler P, Button[] FigurBs, int z, int i) {
        FigurBs[i].setLayoutX(Spieler_Position[0][P.FigurePosition[i]]-13);
        FigurBs[i].setLayoutY(Spieler_Position[1][P.FigurePosition[i]]-13);
        FigurBs[i].setVisible(true);
        if(z ==0) z =i+1;
        else z =-1;
        return z;
    }

    public void setD_ButtonsNotVisible(Button[] DBs) {
        for(int i=0;i<4;i++) DBs[i].setVisible(false);
    }

    //Entferner Mechanicsmus
    public void verarbeiteEinnahme(int figur) {
        int AktSpieler = AktuelSpieler;

        if(!isInForbiddenPos(Spielerr[AktSpieler].FigurePosition[figur])) {
            ArrayList<Integer> Z = getSamePos(AktSpieler, figur);

            if(!Z.isEmpty()) for(int i=0;i<Z.size();i=i+2) {
                int spieler =Z.get(i);
                int SpielerFigur =Z.get(i+1);
                BewegFigurzuStart(spieler, SpielerFigur);
            }
        }
    }
    public ArrayList<Integer> getSamePos(int AktSpieler, int figur) {

        ArrayList<Integer> Z = new ArrayList<>();

        for (int i=0;i<4;i++) if(i!= AktSpieler && Spielerr[i]!=null) {
            for(int k=0;k<4;k++)
                if(Spielerr[AktSpieler].FigurePosition[figur]== Spielerr[i].FigurePosition[k] && Spielerr[i].FigureAktuel[k] && isEatable(i,k)) {
                    Z.add(i);
                    Z.add(k);
                }
        }
        return Z;
    }
    public boolean isEatable(int i,int k) {

        return Spielerr[i].FigureEntered[k] || Spielerr[i].FigurePosition[k] <= Spielerr[i].SpielerStartPosition || Spielerr[i].FigurePosition[k] >= Spielerr[i].SpielerStartPosition + 6;
    }
    public boolean isInForbiddenPos(int pos) {
        return pos == 1 || pos == 14 || pos == 27 || pos == 40;
    }
    public void BewegFigurzuStart(int s, int Figur) {

        Spielerr[s].Figuren[Figur].setLayoutX(Spielerr[s].FigurePositionIniniale[0][Figur]+13);
        Spielerr[s].Figuren[Figur].setLayoutY(Spielerr[s].FigurePositionIniniale[1][Figur]+13);

        Spielerr[s].FigureEinnahme[Figur] = false;
        Spielerr[s].FigureAktuel[Figur] = false;

        Spielerr[s].FigurePosition[Figur]=0;
    }


    public boolean Gewonnen(Spieler S) {
        for(int i=0;i<4;i++) if(!S.FigureEntered[i]) return false;
        return true;
    }


    public void Gewonnen() {

        GewinStr +="\n"+ GewinNr +") "+ Spielerr[AktuelSpieler].Name;


        Label L = new Label(""+ GewinNr);
        L.setId("toDelet");
        L.getStyleClass().add("Win_count_label");
        L.setTextFill(Paint.valueOf(Spielerr[AktuelSpieler].color));

        SpielAPane.getChildren().add(L);


        int J =1;
        for(int s = 0; s <4; s++) {
            if (Spielerr[s]==null || Spielerr[s].Gewin) J++;
            System.out.println("J: "+ J);
        }


        if(J ==3) SpielEnde();
        else WechselSpieller();

    }

    public void SpielEnde() {

        LDiceRoll.setText("");

        // Erstellt eine Informations-Alertbox
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Spiel wurde Beendet !" + GewinStr);
        alert.show();

        // Fügt ein Ereignis für das Schließen der Alertbox hinzu
        alert.setOnCloseRequest((e) -> {
              ZuruckzuMenu();

            // Schleife, die durchläuft, um Elemente zu löschen
            while (true) {
                // Sucht ein Element mit der ID "toDelet" im Game_APane
                Node N = SpielAPane.lookup("#toDelet");
                if (N != null) {

                    N.getParent().getChildrenUnmodifiable().remove(N);
                } else {

                    break;
                }
            }
        });
    }

}