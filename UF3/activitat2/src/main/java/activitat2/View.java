package activitat2;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== Menú Cotxes ===");
        System.out.println("1. Afegir cotxe");
        System.out.println("2. Eliminar cotxe");
        System.out.println("3. Modificar cotxe");
        System.out.println("4. Llistar tots els cotxes");
        System.out.println("5. Llistar cotxes entre dates");
        System.out.println("6. Cercar cotxe per marca/model");
        System.out.println("0. Sortir");
        System.out.print("Tria una opció: ");
        return scanner.nextInt();
    }

    public Cotxe llegirCotxe() {
        scanner.nextLine(); 
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();
        System.out.print("Data de registre (aaaa-mm-dd): ");
        String dataStr = scanner.nextLine();
        Date dataRegistre = java.sql.Date.valueOf(dataStr);
        System.out.print("Disponible (true/false): ");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine(); 
        System.out.print("Observacions: ");
        String observacions = scanner.nextLine();

        return new Cotxe(marca, model, matricula, dataRegistre, disponible, observacions);
    }

    public String llegirMatricula() {
        scanner.nextLine();
        System.out.print("Introdueix la matrícula del cotxe: ");
        return scanner.nextLine();
    }

    public String llegirFiltre() {
        scanner.nextLine();
        System.out.print("Introdueix el filtre (marca/model): ");
        return scanner.nextLine();
    }

    public void mostrarCotxes(List<Cotxe> cotxes) {
        for (Cotxe c : cotxes) {
            System.out.println(c);
        }
    }

    public Date[] llegirDates() {
        scanner.nextLine();
        System.out.print("Data inici (aaaa-mm-dd): ");
        Date dataInici = java.sql.Date.valueOf(scanner.nextLine());
        System.out.print("Data fi (aaaa-mm-dd): ");
        Date dataFi = java.sql.Date.valueOf(scanner.nextLine());
        return new Date[] { dataInici, dataFi };
    }

    public void mostrarMissatge(String msg) {
        System.out.println(msg);
    }
}
