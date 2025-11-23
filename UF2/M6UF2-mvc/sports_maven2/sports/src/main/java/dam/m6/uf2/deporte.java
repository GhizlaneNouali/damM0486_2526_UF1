package dam.m6.uf2;

public class deporte {
    private int id;
    private String nom;

    public deporte(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public deporte(String nom){
        this.nom = nom;
    }

    public int getId(){
        return id;
    }

    public String getNom(){
        return nom;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    @Override
    public String toString(){
        return id + " - " + nom;
    }
    
}
