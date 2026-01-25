package activitat2;

import org.bson.Document;
import java.util.Date;

public class Cotxe {
    private String marca;
    private String model;
    private String matricula;
    private Date dataRegistre;
    private boolean disponible;
    private String observacions;

    public Cotxe(String marca, String model, String matricula, Date dataRegistre, boolean disponible,
            String observacions) {
        this.marca = marca;
        this.model = model;
        this.matricula = matricula;
        this.dataRegistre = dataRegistre;
        this.disponible = disponible;
        this.observacions = observacions;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataRegistre() {
        return dataRegistre;
    }

    public void setDataRegistre(Date dataRegistre) {
        this.dataRegistre = dataRegistre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    public Document toDocument() {
        return new Document("marca", marca)
                .append("model", model)
                .append("matricula", matricula)
                .append("dataRegistre", dataRegistre)
                .append("disponible", disponible)
                .append("observacions", observacions);
    }

    @Override
    public String toString() {
        return "Cotxe{\n" +
                "  marca='" + marca + "',\n" +
                "  model='" + model + "',\n" +
                "  matricula='" + matricula + "',\n" +
                "  dataRegistre=" + dataRegistre + ",\n" +
                "  disponible=" + disponible + ",\n" +
                "  observacions='" + observacions + "'\n" +
                "}";
    }
}
