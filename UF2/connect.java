// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.Statement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class introduccio {
//     public static void main(String[] args) {
//         String url = "jdbc:postgresql://localhost:5432/sports";
//         String user = "ghizlane";
//         String password = "1234";

//          try (Connection conn = DriverManager.getConnection(url, user, password);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM DEPORTES")){
//             System.out.println("Llista de DEPORTES");

//             while(rs.next()){
//                 String nombre = rs.getString("NOMBRE");
//                 Integer cod_deporte = rs.getInt("COD_DEPORTE");
//                 System.out.println("NOM: " + nombre + " Cod_Deporte: " + cod_deporte);
//             }
//         } catch (SQLException ex){
//             ex.printStackTrace();
//         }
//     }
// }
