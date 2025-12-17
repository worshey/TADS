import java.sql.*;

public class Periodo {
    private int id;
    private String nomePeriodo;

    public Periodo() {}
    public Periodo(String nomePeriodo) { this.nomePeriodo = nomePeriodo; }

    public boolean salvar() {
        Connection con = ModuloConexao.conectar();
        String sql = "INSERT INTO periodo (nome_periodo) VALUES (?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nomePeriodo);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);
            System.out.println("LOG: Periodo salvo. ID: " + this.id);
            return true;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao salvar periodo: " + e.getMessage());
            return false;
        } finally { ModuloConexao.desconectar(con); }
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomePeriodo() { return nomePeriodo; }
    public void setNomePeriodo(String nome) { this.nomePeriodo = nome; }
}