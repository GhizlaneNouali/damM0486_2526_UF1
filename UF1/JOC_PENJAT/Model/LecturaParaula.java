package Model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Paraula llegirParaulaRandom() {
    long midaRegistre = 44;

    try (RandomAccessFile raf = new RandomAccessFile("paraules.dat", "r")) {

        long numParaules = raf.length() / midaRegistre;

        if (numParaules == 0) return null;

        Random random = new Random();
        int index = random.nextInt((int) numParaules);

        raf.seek(index * midaRegistre);

        StringBuilder nom = new StringBuilder();
        for (int j = 0; j < 20; j++) {
            nom.append(raf.readChar());
        }
        String text = nom.toString().trim();

        int puntuacio = raf.readInt();

        return new Paraula(text, puntuacio);

    } catch (IOException e) {
        System.err.println("Error llegint paraula random: " + e.getMessage());
        return null;
    }
}

}