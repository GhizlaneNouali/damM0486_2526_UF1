package hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "atleta") 
public class deportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String codi;

    @ManyToOne
    @JoinColumn(name = "esport_id")
    private deporte deporte;

    @Transient
    private String esport_nom;

    public deportista() {
    }

    public deportista(int id, String nom, String codi, deporte deporte) {
        this.id = id;
        this.nom = nom;
        this.codi = codi;
        this.deporte = deporte;
    }

    public deportista(String nom, String codi, deporte deporte) {
        this.nom = nom;
        this.codi = codi;
        this.deporte = deporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(deporte deporte) {
        this.deporte = deporte;
    }

    public int getEsport_id() {
        return deporte != null ? deporte.getId() : 0;
    }

    public String getEsport_nom() {
        if (esport_nom != null) return esport_nom;
        return deporte != null ? deporte.getNom() : null;
    }

    public void setEsport_nom(String esport_nom) {
        this.esport_nom = esport_nom;
    }

    @Override
    public String toString() {
        return "Atleta: " + nom + " (" + codi + "), esport=" +
                (deporte != null ? deporte.getNom() : "null");
    }
}