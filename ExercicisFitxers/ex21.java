package ExercicisFitxers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex21 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String arxiu;
        String lletra;

        System.out.println("Introdueix el nom de l'arxiu:");
        arxiu = sc.nextLine();

        File f = new File(arxiu);
        
        System.out.println("Introdueix la lletra a buscar:");
        lletra = sc.nextLine();

        char c = lletra.charAt(0);
        int totalCaracter = 0;

        try (FileInputStream fis = new FileInputStream(f)) {
            int l;
            while ((l = fis.read()) != -1) {
                if (l == (int) c) {
                    totalCaracter++;
                }
            }

            System.out.println("La lletra '" + c + "' apareix " + totalCaracter + " vegades a l'arxiu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
