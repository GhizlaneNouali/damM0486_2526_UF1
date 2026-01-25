package activitat2;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Model {

    private MongoCollection<Document> cotxesCollection;

    public Model() {
        MongoDatabase db = ConnectionManager.getConnection();
        cotxesCollection = db.getCollection("Cotxes");
    }

    
    public void inserirCotxe(Cotxe cotxe) {
        cotxesCollection.insertOne(cotxe.toDocument());
    }

    public void updateCotxe(Cotxe cotxe) {
        cotxesCollection.replaceOne(Filters.eq("matricula", cotxe.getMatricula()), cotxe.toDocument());
    }

    public void deleteCotxe(String matricula) {
        cotxesCollection.deleteOne(Filters.eq("matricula", matricula));
    }

    public List<Cotxe> getAllCotxes() {
        List<Cotxe> cotxes = new ArrayList<>();
        for (Document doc : cotxesCollection.find()) {
            cotxes.add(documentToCotxe(doc));
        }
        return cotxes;
    }

    public List<Cotxe> getCotxesByDate(Date dataInici, Date dataFi) {
        List<Cotxe> cotxes = new ArrayList<>();
        for (Document doc : cotxesCollection.find(Filters.and(
                Filters.gte("dataRegistre", dataInici),
                Filters.lte("dataRegistre", dataFi)))) {
            cotxes.add(documentToCotxe(doc));
        }
        return cotxes;
    }

    public List<Cotxe> getFilteredCotxes(String filtre) {
        List<Cotxe> cotxes = new ArrayList<>();
        for (Document doc : cotxesCollection.find(Filters.or(
                Filters.regex("marca", ".*" + filtre + ".*", "i"),
                Filters.regex("model", ".*" + filtre + ".*", "i")))) {
            cotxes.add(documentToCotxe(doc));
        }
        return cotxes;
    }

    private Cotxe documentToCotxe(Document doc) {
        return new Cotxe(
                doc.getString("marca"),
                doc.getString("model"),
                doc.getString("matricula"),
                doc.getDate("dataRegistre"),
                doc.getBoolean("disponible"),
                doc.getString("observacions"));
    }
}
