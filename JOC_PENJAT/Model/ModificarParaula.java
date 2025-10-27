package Model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ModificarParaula {

    Integer tamanyParaula = 20;
    Integer midaRegistre = tamanyParaula * 2 + 4;

     public List<Paraula> llegirParaules() {
        List<Paraula> paraules = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("paraules.dat", "r")) {
            long numRegistres = raf.length() / midaRegistre;

            for (int i = 0; i < numRegistres; i++) {
                raf.seek(i * midaRegistre);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < tamanyParaula; j++) {
                    sb.append(raf.readChar());
                }
                String text = sb.toString().trim();
                int punts = raf.readInt();
                paraules.add(new Paraula(text, punts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paraules;
    }

    public void modificarParaula(int index, String novaParaula, int novaPuntuacio) {
        try (RandomAccessFile raf = new RandomAccessFile("paraules.dat", "rw")) {
            raf.seek(index * midaRegistre);

            StringBuilder sb = new StringBuilder(novaParaula.toLowerCase());
            sb.setLength(tamanyParaula);
            for (int i = 0; i < tamanyParaula; i++) {
                raf.writeChar(sb.charAt(i));
            }

            raf.writeInt(novaPuntuacio);
        } catch (IOException e) {
            System.err.println("Error modificant paraula: " + e.getMessage());
        }
    }    
}
