import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Professor extends Pessoa {
    private String matricula;

    public Professor() {
        super();
    }

    public Professor(String nome, String endereco, String telefone, String email, String matricula) {
        super(nome, endereco, telefone, email);
        this.matricula = matricula;
    }

    // -- SALVAR (CORRIGIDO) --
    public boolean salvar() {
        Connection con = ModuloConexao.conectar();
        String sqlPessoa = "INSERT INTO pessoa (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
        // Agora pedimos para retornar a chave também no insert do professor
        String sqlProf = "INSERT INTO professor (id_pessoa, matricula) VALUES (?, ?)";

        try {
            con.setAutoCommit(false); 

            // 1. Salva Pessoa
            PreparedStatement stmtPessoa = con.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            stmtPessoa.setString(1, this.getNome());
            stmtPessoa.setString(2, this.getEndereco());
            stmtPessoa.setString(3, this.getTelefone());
            stmtPessoa.setString(4, this.getEmail());
            stmtPessoa.executeUpdate();

            ResultSet rs = stmtPessoa.getGeneratedKeys();
            int idPessoaGerado = 0;
            if (rs.next()) {
                idPessoaGerado = rs.getInt(1);
                // Não setamos o ID aqui ainda, pois queremos o ID do Professor
            }

            // 2. Salva Professor
            PreparedStatement stmtProf = con.prepareStatement(sqlProf, Statement.RETURN_GENERATED_KEYS);
            stmtProf.setInt(1, idPessoaGerado);
            stmtProf.setString(2, this.matricula);
            stmtProf.executeUpdate();
            
            // --- CORREÇÃO AQUI ---
            // Pegamos o ID gerado na tabela PROFESSOR
            ResultSet rsProf = stmtProf.getGeneratedKeys();
            if (rsProf.next()) {
                int idProfessorGerado = rsProf.getInt(1);
                this.setId(idProfessorGerado); // Agora o objeto tem o ID correto para a Disciplina usar
            }

            con.commit(); 
            System.out.println("LOG: Professor salvo com sucesso! ID: " + this.getId());
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("LOG: Erro ao salvar professor: " + e.getMessage());
            return false;
        } finally {
            ModuloConexao.desconectar(con);
        }
    }

    // -- PESQUISAR --
    public boolean pesquisar(int idBusca) {
        Connection con = ModuloConexao.conectar();
        // Ajustei o SELECT para buscar pelo ID do professor, não da pessoa
        String sql = "SELECT * FROM pessoa INNER JOIN professor ON pessoa.id = professor.id_pessoa WHERE professor.id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idBusca);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.setId(rs.getInt("id")); // ID do professor
                this.setNome(rs.getString("nome"));
                this.setEndereco(rs.getString("endereco"));
                this.setTelefone(rs.getString("telefone"));
                this.setEmail(rs.getString("email"));
                this.setMatricula(rs.getString("matricula"));
                System.out.println("LOG: Professor encontrado: " + this.getNome());
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao pesquisar: " + e.getMessage());
            return false;
        } finally {
            ModuloConexao.desconectar(con);
        }
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}