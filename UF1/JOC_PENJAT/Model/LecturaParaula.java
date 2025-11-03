package Model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LecturaParaula {

    public List<Paraula> llegirParaules() {
        List<Paraula> paraules = new ArrayList<>();

        try (RandomAccessFile raf = new RandomAccessFile("paraules.dat", "r")) {
            
            long midaRegistre = 44;
            long numParaules = raf.length() / midaRegistre;
            
            for (int i = 0; i < numParaules; i++) {
                raf.seek(i * midaRegistre);
                
                // 20 caracters
                StringBuilder nom = new StringBuilder();
                for (int j = 0; j < 20; j++) {
                    nom.append(raf.readChar());
                }
                
                String text = nom.toString().trim();
                
                // puntuació
                int puntuacio = raf.readInt();
                
                paraules.add(new Paraula(text, puntuacio));
            }

        } catch (IOException e) {
            System.err.println("Error de lectura L: " + e.getMessage());
            e.printStackTrace();
        }

        return paraules;
    }
}