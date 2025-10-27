package Model;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscripuraParaula {

    public void escriureParaules(Paraula[] paraules) {
        try (RandomAccessFile raf = new RandomAccessFile("paraules.dat", "rw")) {
            
            raf.seek(raf.length()); 
            
            for (Paraula p : paraules) {
                StringBuilder sb = new StringBuilder(p.getParaula().toLowerCase());
                sb.setLength(20);
                
                String palabra = sb.toString();
                for (int i = 0; i < 20; i++) {
                    raf.writeChar(palabra.charAt(i));
                }
                
                raf.writeInt(p.getPuntuacio());
            }
            
            System.out.println("Fitxer escrit correctament"); 
        } catch (IOException e) {
            System.err.println("Error d'escriptura: " + e.getMessage());
            e.printStackTrace();
        }
    }
}