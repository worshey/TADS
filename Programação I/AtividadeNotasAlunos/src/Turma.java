import java.sql.*;

public class Turma {
    private int id;
    private String nomeTurma;

    public Turma() {}
    public Turma(String nomeTurma) { this.nomeTurma = nomeTurma; }

    public boolean salvar() {
        Connection con = ModuloConexao.conectar();
        String sql = "INSERT INTO turma (nome_turma) VALUES (?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nomeTurma);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);
            System.out.println("LOG: Turma salva. ID: " + this.id);
            return true;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao salvar turma: " + e.getMessage());
            return false;
        } finally { ModuloConexao.desconectar(con); }
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomeTurma() { return nomeTurma; }
    public void setNomeTurma(String nome) { this.nomeTurma = nome; }
}