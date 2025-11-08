import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelDAO {


    public void salvar(Responsavel responsavel) {
        String sql = "INSERT INTO responsavel (nome) VALUES (?)";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, responsavel.getNome());
            stmt.executeUpdate();
            System.out.println("Responsavel salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar o responsavel: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public List<Responsavel> listar() {
        String sql = "SELECT * FROM responsavel ORDER BY id";
        List<Responsavel> listaDeResponsavel = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            
            while (rs.next()) {
                Responsavel p = new Responsavel();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                listaDeResponsavel.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os responsaveis: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listaDeResponsavel;
    }

    public void alterar(Responsavel responsavel) {
        String sql = "UPDATE responsavel SET nome = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, responsavel.getNome());
            stmt.setInt(2, responsavel.getId());
            stmt.executeUpdate();
            System.out.println("Responsavel alterado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao alterar responsavel: " + e.getMessage());
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
        String sql = "DELETE FROM responsavel WHERE id = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Responsavel excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir o responsavel: " + e.getMessage());
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