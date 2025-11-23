package dam.m6.uf2;

public class deportista {

    private int id;          
    private String nom;
    private String codi;
    private int esport_id;   

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

    @Override
    public String toString() {
        return "Atleta: " + nom + " (" + codi + "), esport_id=" + esport_id;
    }
}
