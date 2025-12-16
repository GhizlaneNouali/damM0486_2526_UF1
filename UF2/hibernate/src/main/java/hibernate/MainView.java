package hibernate;

import java.util.List;
import java.util.Scanner;

public class MainView {

    private final Scanner sc = new Scanner(System.in);

    public int mainMenu() {
        System.out.println(
                "\nMENU PRINCIPAL\n1. Llistar esports\n2. Afegir Esport\n3. Llistar atletes per esport\n4. Afegir atleta\n\n0. Sortir");
        int option = -1;
        try {
            option = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opció no vàlida");
        }
        return option;
    }

    public void showdDeporte(List<deporte> list) {
        System.out.println("\n=== Llistat d'esports ===");
        if (list.isEmpty()) {
            System.out.println("No hi ha esports");
        } else {
            for (deporte d : list) {
                System.out.println(d.getId() + " - " + d.getNom());
            }
        }
    }

    public deporte addDeporteForm() {
        System.out.println("\n=== Formulari afegir esport ===");
        System.out.print("Nom de l'esport: ");
        String nom = sc.nextLine();

        return new deporte(nom);
    }

    public void showDeportistas(List<deportista> list) {
        System.out.println("\n=== Llistat de deportistes ===");
        if (list.isEmpty()) {
            System.out.println("No hi ha atletes");
        } else {
            for (deportista d : list) {
                System.out.println(d.getNom() + "\t" + d.getCodi() + "\tEsport: " + d.getEsport_nom());
            }
        }
    }

    public int demanaEsport() {
        System.out.print("Introdueix l'ID de l'esport: ");
        int esportID = -1;
        try {
            esportID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Id no vàlid");
        }
        return esportID;
    }

    public deportista addDeportistaForm(deporte deporteObj) {
        System.out.println("\n--- Formulari afegir atleta ---");
        System.out.print("Nom de l'atleta: ");
        String nom = sc.nextLine();
        
        return new deportista(nom, null, deporteObj);
    }
}