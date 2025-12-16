package hibernate;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "esport") 
public class deporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @OneToMany(mappedBy = "deporte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<deportista> deportistas = new ArrayList<>();

    public deporte() {}

    public deporte(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public deporte(String nom) {
        this.nom = nom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public List<deportista> getDeportistas() { return deportistas; }
    public void setDeportistas(List<deportista> deportistas) { this.deportistas = deportistas; }

    @Override
    public String toString() {
        return id + " - " + nom;
    }
}