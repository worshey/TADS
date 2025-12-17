import java.sql.*;

public class Nota {
    private int id;
    private double valorNota;
    private int idDiario;

    public Nota() {}
    public Nota(double valorNota, int idDiario) {
        this.valorNota = valorNota;
        this.idDiario = idDiario;
    }

    public boolean salvar() {
        if (this.valorNota < 0 || this.valorNota > 10) {
            System.out.println("LOG: Erro - A nota deve ser entre 0.0 e 10.0");
            return false;
        }

        Connection con = ModuloConexao.conectar();
        String sql = "INSERT INTO nota (valor_nota, id_diario) VALUES (?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, this.valorNota);
            stmt.setInt(2, this.idDiario);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);
            System.out.println("LOG: Nota " + this.valorNota + " lançada com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao salvar nota: " + e.getMessage());
            return false;
        } finally { ModuloConexao.desconectar(con); }
    }
}