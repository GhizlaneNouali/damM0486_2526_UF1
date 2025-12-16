package dam.m6.uf2;

public class deportista {

    private int id;          
    private String nom;
    private String codi;
    private int esport_id;   
    private String esport_nom;

    public deportista(int id, String nom, String codi, int esport_id) {
        this.id = id;
        this.nom = nom;
        this.codi = codi;
        this.esport_id = esport_id;
    }

    public deportista(String nom, String codi, int esport_id) {
        this.nom = nom;
        this.codi = codi;
        this.esport_id = esport_id;
        this.esport_nom = null;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    public int getEsport_id() {
        return esport_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public void setEsport_id(int esport_id) {
        this.esport_id = esport_id;
    }

    public String getEsport_nom() {
        return esport_nom;
    }
    public void setEsport_nom(String esport_nom) {
        this.esport_nom = esport_nom;
    }

    @Override
    public String toString() {
        return "Atleta: " + nom + " (" + codi + "), esport =" + esport_nom;
    }
}
