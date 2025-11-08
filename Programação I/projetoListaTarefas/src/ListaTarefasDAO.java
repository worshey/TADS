import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaTarefasDAO {

    public void salvar(ListaTarefas tarefa) {
        String sql = "INSERT INTO listatarefas (data_tarefa, descricao_tarefa, observacao, prioridade_id, responsavel_id) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(tarefa.getData_tarefa().getTime()));
            stmt.setString(2, tarefa.getDescricao_tarefa());
            stmt.setString(3, tarefa.getObservacao());
            stmt.setInt(4, tarefa.getPrioridade().getId());
            stmt.setInt(5, tarefa.getResponsavel().getId());
            stmt.executeUpdate();
            System.out.println("Tarefa salva com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar tarefa: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public List<ListaTarefas> listar() {
        String sql = "SELECT t.*, p.descricao AS prioridade_descricao, r.nome AS responsavel_nome " +
                     "FROM listatarefas t " +
                     "JOIN prioridade p ON t.prioridade_id = p.id " +
                     "JOIN responsavel r ON t.responsavel_id = r.id";

        List<ListaTarefas> listaDeTarefas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
              
                Prioridade p = new Prioridade();
                p.setId(rs.getInt("prioridade_id"));
                p.setDescricao(rs.getString("prioridade_descricao")); 
              
                Responsavel r = new Responsavel();
                r.setId(rs.getInt("responsavel_id"));
                r.setNome(rs.getString("responsavel_nome")); 

                ListaTarefas tarefa = new ListaTarefas();
                tarefa.setId(rs.getInt("id"));
                tarefa.setData_tarefa(rs.getDate("data_tarefa"));
                tarefa.setDescricao_tarefa(rs.getString("descricao_tarefa"));
                tarefa.setObservacao(rs.getString("observacao"));
                
                tarefa.setPrioridade(p);
                tarefa.setResponsavel(r);

                listaDeTarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listaDeTarefas;
    }

    public void alterar(ListaTarefas tarefa) {
        String sql = "UPDATE listatarefas SET data_tarefa = ?, descricao_tarefa = ?, observacao = ?, " +
                     "prioridade_id = ?, responsavel_id = ? WHERE id = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(tarefa.getData_tarefa().getTime()));
            stmt.setString(2, tarefa.getDescricao_tarefa());
            stmt.setString(3, tarefa.getObservacao());
            stmt.setInt(4, tarefa.getPrioridade().getId());
            stmt.setInt(5, tarefa.getResponsavel().getId());
            stmt.setInt(6, tarefa.getId()); 
            
            stmt.executeUpdate();
            System.out.println("Tarefa alterada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao alterar tarefa: " + e.getMessage());
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
        String sql = "DELETE FROM listatarefas WHERE id = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Tarefa excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir tarefa: " + e.getMessage());
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