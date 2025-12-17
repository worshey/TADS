import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ModuloConexao {

    // Método para conectar ao banco
    public static Connection conectar() {
        Connection con = null;
        
        String url = "jdbc:postgresql://localhost:5432/escola_db"; 
        String user = "postgres"; 
        String password = "1806";  

        try {
            Class.forName("org.postgresql.Driver");
            
            con = DriverManager.getConnection(url, user, password);
            
            System.out.println("LOG: Conexão com o banco realizada com sucesso!");
            return con;
            
        } catch (ClassNotFoundException e) {
            System.out.println("LOG: Erro - Driver JDBC não encontrado. Verifique se o .jar está nas 'Referenced Libraries'.");
            return null;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao conectar no banco: " + e.getMessage());
            return null;
        }
    }


    public static void desconectar(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("LOG: Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao encerrar conexão: " + e.getMessage());
        }
    }
}