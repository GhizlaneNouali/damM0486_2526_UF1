package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class deportePgDAO implements DAO<deporte> {
    private Connection conn;

    public deportePgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<deporte> getAll() {
        List<deporte> esports = new ArrayList<>();

        String sql = "SELECT * FROM llista_esports();";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                deporte d = new deporte(id, nom);
                esports.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar esports: " + e.getMessage());
        }

        return esports;
    }

    @Override
    public void add(deporte item) {
        if (conn == null)
            return;

        String sql = "INSERT INTO esport (nom) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al afegir esport: " + e.getMessage());
        }
    }

}
