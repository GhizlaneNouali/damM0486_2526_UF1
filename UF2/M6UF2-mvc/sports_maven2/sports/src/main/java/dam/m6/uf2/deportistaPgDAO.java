package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deportistaPgDAO implements DAO<deportista> {
    private Connection conn;

    public deportistaPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<deportista> getAll() {
        List<deportista> deportistas = new ArrayList<>();
        String sql = "SELECT id, nom, codi, esport_id FROM atleta;";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String codi = rs.getString("codi");
                int esport_id = rs.getInt("esport_id");

                deportista d = new deportista(id, nom, codi, esport_id);
                deportistas.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Error a getAll deportistas: " + e.getMessage());
        }

        return deportistas;
    }

    public List<deportista> llista_deportistasPerID(int esportId) {
        List<deportista> deportistas = new ArrayList<>();
        String sql = "SELECT * FROM llista_atletes_per_esport(?)";

        try (PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, esportId);

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    String codi = rs.getString("codi");
                    String nomEsport = rs.getString("esport_nom");
                    int esport_id = esportId;

                    deportista d = new deportista(nom, codi, esport_id);
                    d.setEsport_nom(nomEsport);
                    deportistas.add(d);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar deportistas: " + e.getMessage());
        }

        return deportistas;
    }

    @Override
    public void add(deportista item) {
        String codiGenerat = generarCodi();
        item.setCodi(codiGenerat);

        String sql = "INSERT INTO atleta (nom, codi, esport_id) VALUES (?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNom());
            stmt.setString(2, item.getCodi());
            stmt.setInt(3, item.getEsport_id());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al afegir un deportista " + e.getMessage());
        }
    }

    private String generarCodi() {
        String sql = "SELECT MAX(codi) FROM atleta";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String maxCodi = rs.getString(1);

                if (maxCodi == null)
                    return "A001";

                String lletra = maxCodi.substring(0, 1);
                String nums = maxCodi.substring(1);

                int num = Integer.parseInt(nums);
                num++;

                return lletra + String.format("%03d", num);
            }

        } catch (SQLException e) {
            System.err.println("Error generant codi: " + e.getMessage());
        }

        return "A001";
    }

    public List<deportista> llistaDeportistasPerNom(String text) {
        List<deportista> deportistas = new ArrayList<>();
        String sql = "SELECT a.id, a.nom, a.codi, a.esport_id, e.nom AS esport_nom " +
                "FROM atleta a " +
                "JOIN esport e ON e.id = a.esport_id " +
                "WHERE a.nom ILIKE ? " +
                "ORDER BY a.nom";

        try (PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, "%" + text + "%");

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String codi = rs.getString("codi");
                    String nomEsport = rs.getString("esport_nom");
                    int esport_id = rs.getInt("esport_id");

                    deportista d = new deportista(id, nom, codi, esport_id);
                    d.setEsport_nom(nomEsport);
                    deportistas.add(d);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar deportistas per nom: " + e.getMessage());
        }
        return deportistas;
    }

}
