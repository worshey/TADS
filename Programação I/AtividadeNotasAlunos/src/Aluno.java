import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aluno extends Pessoa {
    private String matricula;
    private String nomePai;
    private String nomeMae;

    // Construtor Vazio (Necessário para pesquisa)
    public Aluno() {
        super();
    }

    // Construtor Cheio
    public Aluno(String nome, String endereco, String telefone, String email, 
                 String matricula, String nomePai, String nomeMae) {
        super(nome, endereco, telefone, email);
        this.matricula = matricula;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }

    // Validação da Matrícula
    public boolean validarMatricula() {
        if (this.matricula != null && this.matricula.length() == 10 && this.matricula.matches("\\d+")) {
            return true;
        } else {
            System.out.println("LOG: Erro - A matrícula deve ter exatamente 10 números.");
            return false;
        }
    }

    // -- SALVAR --
    public boolean salvar() {
        if (!validarMatricula()) return false;

        Connection con = ModuloConexao.conectar();
        String sqlPessoa = "INSERT INTO pessoa (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
        String sqlAluno = "INSERT INTO aluno (id_pessoa, matricula, nome_pai, nome_mae) VALUES (?, ?, ?, ?)";

        try {
            con.setAutoCommit(false);

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
                this.setId(idPessoaGerado);
            }

            PreparedStatement stmtAluno = con.prepareStatement(sqlAluno);
            stmtAluno.setInt(1, idPessoaGerado);
            stmtAluno.setString(2, this.matricula);
            stmtAluno.setString(3, this.nomePai);
            stmtAluno.setString(4, this.nomeMae);
            stmtAluno.executeUpdate();

            con.commit();
            System.out.println("LOG: Aluno salvo com sucesso! ID gerado: " + idPessoaGerado);
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("LOG: Erro ao salvar aluno: " + e.getMessage());
            return false;
        } finally {
            ModuloConexao.desconectar(con);
        }
    }

    // -- PESQUISAR --
    public boolean pesquisar(int idBusca) {
        Connection con = ModuloConexao.conectar();
        String sql = "SELECT * FROM pessoa INNER JOIN aluno ON pessoa.id = aluno.id_pessoa WHERE pessoa.id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idBusca);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.setId(rs.getInt("id"));
                this.setNome(rs.getString("nome"));
                this.setEndereco(rs.getString("endereco"));
                this.setTelefone(rs.getString("telefone"));
                this.setEmail(rs.getString("email"));
                this.setMatricula(rs.getString("matricula"));
                this.setNomePai(rs.getString("nome_pai"));
                this.setNomeMae(rs.getString("nome_mae"));
                
                System.out.println("LOG: Aluno encontrado: " + this.getNome());
                return true;
            } else {
                System.out.println("LOG: Aluno não encontrado com ID: " + idBusca);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("LOG: Erro ao pesquisar: " + e.getMessage());
            return false;
        } finally {
            ModuloConexao.desconectar(con);
        }
    }

    // -- ALTERAR --
    public boolean alterar() {
        Connection con = ModuloConexao.conectar();
        String sqlPessoa = "UPDATE pessoa SET nome=?, endereco=?, telefone=?, email=? WHERE id=?";
        String sqlAluno = "UPDATE aluno SET matricula=?, nome_pai=?, nome_mae=? WHERE id_pessoa=?";

        try {
            con.setAutoCommit(false);

            PreparedStatement stmtPessoa = con.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, this.getNome());
            stmtPessoa.setString(2, this.getEndereco());
            stmtPessoa.setString(3, this.getTelefone());
            stmtPessoa.setString(4, this.getEmail());
            stmtPessoa.setInt(5, this.getId());
            stmtPessoa.executeUpdate();

            PreparedStatement stmtAluno = con.prepareStatement(sqlAluno);
            stmtAluno.setString(1, this.getMatricula());
            stmtAluno.setString(2, this.nomePai);
            stmtAluno.setString(3, this.nomeMae);
            stmtAluno.setInt(4, this.getId());
            stmtAluno.executeUpdate();

            con.commit();
            System.out.println("LOG: Dados do aluno atualizados com sucesso.");
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("LOG: Erro ao alterar aluno: " + e.getMessage());
            return false;
        } finally {
            ModuloConexao.desconectar(con);
        }
    }

    // -- EXCLUIR --
    public boolean excluir() {
        Connection con = ModuloConexao.conectar();
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            int afetados = stmt.executeUpdate();
            
            if (afetados > 0) {
                System.out.println("LOG: Aluno excluído com sucesso.");
                return true;
            } else {
                System.out.println("LOG: Nenhum aluno excluído.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("LOG: Erro ao excluir: " + e.getMessage());
            return false;
        } finally {
            ModuloConexao.desconectar(con);
        }
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNomePai() { return nomePai; }
    public void setNomePai(String nomePai) { this.nomePai = nomePai; }
    public String getNomeMae() { return nomeMae; }
    public void setNomeMae(String nomeMae) { this.nomeMae = nomeMae; }
}