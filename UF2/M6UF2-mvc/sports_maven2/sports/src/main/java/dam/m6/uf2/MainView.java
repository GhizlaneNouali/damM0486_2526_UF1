package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {

    /**
     * @return
     */

    private final Scanner sc = new Scanner(System.in);

    public int mainMenu() {
        // TODO Auto-generated method stub
        System.out.println(
                "\nMENU PRINCIPAL\n1. Llistar esports\n2. Afegir Esport\n3. Llistar atletes per esport\n4. Afegir atleta\n\n0 Sortir --> conn.close()");
        int option = Integer.parseInt(sc.nextLine()); 

        return option;
        // throw new UnsupportedOperationException("Unimplemented method 'mainMenu'");
    }

    public void showdDeporte(List<deporte> list) {
        System.out.println("\n=== Llistat d'esports ===");
        for (deporte d : list) {
            System.out.println(d.getId() + " - " + d.getNom());
        }
    }

    public deporte addDeporteForm() {
        System.out.println("\n=== Formulari afegir esport ===");
        System.out.print("Nom de l'esport: ");
        String nom = sc.nextLine();

        return new deporte(nom);
    }

    public void showDeportistas(List<deportista> list) {
        System.out.println("\n=== Llistat de deportistas ===");
        for (deportista d : list) {
            System.out.println(d.getNom() + "\t" + d.getCodi() + "\tEsport ID: " + d.getEsport_id());
        }
    }

    public int demanaEsport() {
        System.out.print("Introdueix l'ID de l'esport: ");
        int esportID = -1;
        try {
            esportID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Id no valid");
        }
        return esportID;
    }

    public deportista addDeportistaForm(int esportId) {
        System.out.println("\n--- Formulari afegir atleta ---");
        System.out.print("Nom de l’atleta: ");
        String nom = sc.nextLine();
        System.out.print("Codi de l’atleta: ");
        String codi = sc.nextLine();

        return new deportista(nom, codi, esportId);
    }

}
