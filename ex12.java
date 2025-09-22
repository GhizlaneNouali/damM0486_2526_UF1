package MP06;

import java.io.File;

public class ex12 {
    public static void main(String[] args) {
        String ruta = args[0];
        File path = new File(ruta);

        System.out.println("RUTA: " + ruta);

        File[] files = path.listFiles();

        for (File file : files) {
            String name = file.getName();
            String esDirectori = file.isDirectory() ? "d" : "-";
            String canRead = file.canRead() ? "R" : "-";
            String canWrite = file.canRead() ? "W" : "-";
            String canExecute = file.canExecute() ? "X" : "-";

            System.out.println(esDirectori + canRead + canWrite + canExecute + " " + name);

        }

    }

}