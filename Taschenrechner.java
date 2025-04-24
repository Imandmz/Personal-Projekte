import java.util.Scanner;

public class Taschenrechner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Geben Sie die erste Zahl ein:");
        while (!scanner.hasNextDouble()) {
            System.out.println("Bitte eine gültige Zahl eingeben:");
            scanner.next();
        }
        double zahl1 = scanner.nextDouble();

        System.out.println("Geben Sie die zweite Zahl ein:");
        while (!scanner.hasNextDouble()) {
            System.out.println("Bitte eine gültige Zahl eingeben:");
            scanner.next();
        }
        double zahl2 = scanner.nextDouble();

        double summe = zahl1 + zahl2;
        System.out.println("Die Summe ist: " + summe);

        scanner.close();
    }
}
