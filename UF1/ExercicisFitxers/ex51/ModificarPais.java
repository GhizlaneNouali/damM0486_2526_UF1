package ExercicisFitxers.ex51;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ModificarPais {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw")) {

            int midaRegistre = 174;

            System.out.print("Introdueix el número del país (1-5): ");
            int index = sc.nextInt();
            sc.nextLine();

            System.out.print("Camp a modificar (nom / codi / capital / poblacio): ");
            String camp = sc.nextLine().toLowerCase();

            System.out.print("Nou valor: ");
            String nouValor = sc.nextLine();

            long posicio = (index - 1) * midaRegistre;
            fitxer.seek(posicio);

            if (camp.equals("nom")) {
                fitxer.skipBytes(4); 
                escriureText(fitxer, nouValor, 40);

            } else if (camp.equals("codi")) {
                fitxer.skipBytes(4 + 80); 
                escriureText(fitxer, nouValor, 3);

            } else if (camp.equals("capital")) {
                fitxer.skipBytes(4 + 80 + 6); 
                escriureText(fitxer, nouValor, 40);

            } else if (camp.equals("poblacio")) {
                fitxer.skipBytes(4 + 80 + 6 + 80); 
                int valor = Integer.parseInt(nouValor);
                fitxer.writeInt(valor);

            } else {
                System.out.println("Camp no vàlid!");
                return;
            }

            System.out.println("Registre modificat correctament!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }

    static void escriureText(RandomAccessFile fitxer, String text, int mida) throws IOException {
        StringBuilder b = new StringBuilder(text);
        b.setLength(mida);
        fitxer.writeChars(b.toString());
    }
}
