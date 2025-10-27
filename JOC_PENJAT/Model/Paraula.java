package Model;

public class Paraula {
    private String paraula;
    private int puntuacio;

    public Paraula(String paraula, int puntuacio) {
        this.paraula = paraula;
        this.puntuacio = puntuacio;
    }

    public String getParaula() {
        return paraula;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public String toStrings(){
        return "- PARAULA: " + paraula + " PUNTUACIÓ: " + puntuacio;
    }
}
