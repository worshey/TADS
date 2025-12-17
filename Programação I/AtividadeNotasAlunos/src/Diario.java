import java.sql.*;

public class Diario {
    private int id;
    private int idAluno;
    private int idDisciplina;
    private int idTurma;
    private int idPeriodo;
    private boolean aprovado; 

    public Diario() {}
    
    public Diario(int idAluno, int idDisciplina, int idTurma, int idPeriodo) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.idTurma = idTurma;
        this.idPeriodo = idPeriodo;
        this.aprovado = true; 
    }

    public boolean salvar() {
        Connection con = ModuloConexao.conectar();
        String sql = "INSERT INTO diario (id_aluno, id_disciplina, id_turma, id_periodo, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, this.idAluno);
            stmt.setInt(2, this.idDisciplina);
            stmt.setInt(3, this.idTurma);
            stmt.setInt(4, this.idPeriodo);
            stmt.setBoolean(5, this.aprovado);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);
            
            System.out.println("LOG: Diário criado com sucesso. ID: " + this.id);
            return true;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao criar diário: " + e.getMessage());
            return false;
        } finally { ModuloConexao.desconectar(con); }
    }

    
    public void atualizarSituacao() {
        Connection con = ModuloConexao.conectar();
    
        String sql = "SELECT AVG(valor_nota) as media FROM nota WHERE id_diario = ?";
        String sqlUpdate = "UPDATE diario SET status = ? WHERE id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double media = rs.getDouble("media");
                System.out.println("LOG: Média atual do aluno: " + media);

               
                boolean novoStatus = (media >= 6.0);
                
                
                PreparedStatement stmtUp = con.prepareStatement(sqlUpdate);
                stmtUp.setBoolean(1, novoStatus);
                stmtUp.setInt(2, this.id);
                stmtUp.executeUpdate();
                
                this.aprovado = novoStatus;
                
                String statusTexto = novoStatus ? "APROVADO" : "REPROVADO";
                System.out.println("LOG: Status atualizado para: " + statusTexto);
            }
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao calcular média: " + e.getMessage());
        } finally { ModuloConexao.desconectar(con); }
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

}