import javax.swing.plaf.synth.SynthSpinnerUI;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        System.out.println("Salam");
        int zahl;
        zahl = 10;
        zahl += 10 + 5 * 3;
        System.out.println(zahl);

        double zahl1;
        zahl1 = 10.5455465;
        zahl1 += 10 + 5 * 3;
        System.out.println(zahl1);

        char buchstabe = 'c';
        char buchstabe2 = 'c';
        System.out.println(buchstabe);
        System.out.println(buchstabe2);
        System.out.println(buchstabe + buchstabe2);

        String buchstabee = "c";
        char buchstabee2 = 'c';
        System.out.println(buchstabee);
        System.out.println(buchstabee2);
        System.out.println(buchstabee + buchstabee2);

        boolean condition;
        condition = false;
        if (condition)
        {
            System.out.println("Aussage wahr");
        }
        else
        {
            System.out.println("Aussage falsch");
            System.out.println();
        }

        boolean bool = 5 > 2;
        bool = 5 > 2 || 2 < 1;
        if(bool)
        {
            System.out.println("Das ist wahr");
        }

        char monat ='c';
        switch (monat) {
            case 'j': System.out.println("Januar");
                break;
            case 'f': System.out.println("Februar");
                break;
            case 'c': System.out.println("nicht im ersten Quartal");
                break;
        }

        String monat1 = "februar";
        switch (monat1) {
            case "januar":
                System.out.println("Januar");
                break;
            case "februar":
                System.out.println("Februar");
                break;
            case "märz":
                System.out.println("März");
                break;
            default:
                System.out.println("Nicht im ersten Quartal");
                break;
        }

        // mit zwei for
        int[] ssc = new int[10];
        for (int zahll = 0; zahll < 10; zahll++)
        {
            ssc[zahll] = zahll + 5;
        }
        for (int i = 0; i < 10 ; i++)
        {
            System.out.println(ssc[i]);
        }

        // Alternative nur mit einem for
        int[] sssc = new int[10];

        for (int zahll = 0; zahll < 10; zahll++)
        {
            ssc[zahll] = zahll + 5;
            System.out.println(ssc[zahll]); // Direkt ausgeben, während das Array befüllt wird
        }
        System.out.println();

        // I/O
        String input;
        Scanner scan = new Scanner(System.in);

        System.out.println("Input: ");
        input = scan.nextLine();

        System.out.println("Output: ");
        System.out.println(input);

        // I/O
        Scanner scan1 = new Scanner(System.in);
        int var = 0;
        System.out.println("Input 2: ");
        if (scan.hasNextInt()) {
            var = scan.nextInt();
            System.out.println(var);
        }
        else {
            System.out.println("Int = Ganze Zahl! ");
        }
    }
}
