

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex22 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "usertext.txt";
        File f = new File(file);

        boolean tencar = false;
        String text;
        try {

            FileOutputStream fos = new FileOutputStream(f);

            while (!tencar) {
                System.out.println("Introdueix el text");
                text = sc.nextLine();
                if (text.equals("quit")) {
                    tencar = true;

                } else {

                    fos.write(text.getBytes());
                    fos.write("\n".getBytes());

                }

            }
            fos.close();

            System.out.println("\nContingut de l'arxiu:");
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}