import java.sql.*;

public class Disciplina {
    private int id;
    private String nomeDisciplina;
    private int idProfessor; // Chave estrangeira

    public Disciplina() {}
    public Disciplina(String nome, int idProfessor) {
        this.nomeDisciplina = nome;
        this.idProfessor = idProfessor;
    }

    public boolean salvar() {
        Connection con = ModuloConexao.conectar();
        String sql = "INSERT INTO disciplina (nome_disciplina, id_professor) VALUES (?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nomeDisciplina);
            stmt.setInt(2, this.idProfessor);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);
            System.out.println("LOG: Disciplina salva. ID: " + this.id);
            return true;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao salvar disciplina: " + e.getMessage());
            return false;
        } finally { ModuloConexao.desconectar(con); }
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomeDisciplina() { return nomeDisciplina; }
    public void setNomeDisciplina(String nome) { this.nomeDisciplina = nome; }
    public int getIdProfessor() { return idProfessor; }
    public void setIdProfessor(int idProfessor) { this.idProfessor = idProfessor; }
}