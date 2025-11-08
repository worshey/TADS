import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/gerenciador_tarefas";

    private static final String USUARIO = "postgres";

    private static final String SENHA = "1806";

    public static Connection getConnection() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados realizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}