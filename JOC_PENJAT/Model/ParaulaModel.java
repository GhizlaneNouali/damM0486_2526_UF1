package Model;

public class ParaulaModel {

    public static void validarParaula(String text) throws IllegalArgumentException {
        if (text == null || text.isBlank()) throw new IllegalArgumentException("La paraula no pot ser buida");
        if (text.length() > 20) throw new IllegalArgumentException("La paraula no pot tenir més de 20 caràcters");
        if (!text.matches("[a-zA-Z]+")) throw new IllegalArgumentException("La paraula només pot tenir lletres");
    }

    public static void validarPuntuacio(int punts) throws IllegalArgumentException {
        if (punts < 0) throw new IllegalArgumentException("La puntuació no pot ser negativa");
    }
}


