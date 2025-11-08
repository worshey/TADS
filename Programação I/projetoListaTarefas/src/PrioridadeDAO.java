import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrioridadeDAO {


    public void salvar(Prioridade prioridade) {
        String sql = "INSERT INTO prioridade (descricao) VALUES (?)";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, prioridade.getDescricao());
            stmt.executeUpdate();
            System.out.println("Prioridade salva com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar prioridade: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public List<Prioridade> listar() {
        String sql = "SELECT * FROM prioridade ORDER BY id";
        List<Prioridade> listaDePrioridades = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            
            while (rs.next()) {
                Prioridade p = new Prioridade();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                listaDePrioridades.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar prioridades: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listaDePrioridades;
    }

    public void alterar(Prioridade prioridade) {
        String sql = "UPDATE prioridade SET descricao = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, prioridade.getDescricao());
            stmt.setInt(2, prioridade.getId()); // O ID é a condição para o UPDATE
            stmt.executeUpdate();
            System.out.println("Prioridade alterada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao alterar prioridade: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public void excluir(Integer id) {
        String sql = "DELETE FROM prioridade WHERE id = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Prioridade excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir prioridade: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}