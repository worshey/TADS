import java.sql.*;
import javax.swing.*;

public class TelaDisciplina extends JFrame {
    
    private JTextField txtNome;
    private JComboBox<ItemCombo> cbProfessor; // Caixa de seleção para o Professor

    public TelaDisciplina() {
        setTitle("Cadastro de Disciplina");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Nome da Disciplina
        JLabel lblNome = new JLabel("Nome da Disciplina:");
        lblNome.setBounds(20, 20, 150, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(20, 50, 340, 25);
        add(txtNome);

        // 2. Professor Responsável (ComboBox)
        JLabel lblProf = new JLabel("Professor Responsável:");
        lblProf.setBounds(20, 90, 150, 25);
        add(lblProf);

        cbProfessor = new JComboBox<>();
        cbProfessor.setBounds(20, 120, 340, 25);
        add(cbProfessor);

        // Carregar a lista de professores do banco
        carregarProfessores();

        // 3. Botão Salvar
        JButton btnSalvar = new JButton("Salvar Disciplina");
        btnSalvar.setBounds(100, 160, 180, 35);
        add(btnSalvar);

        // AÇÃO DO BOTÃO
        btnSalvar.addActionListener(e -> {
            // Pegar o nome digitado
            String nome = txtNome.getText();
            
            // Pegar o ID do professor selecionado na caixa
            ItemCombo itemSelecionado = (ItemCombo) cbProfessor.getSelectedItem();

            if (nome.isEmpty() || itemSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Preencha o nome e escolha um professor!");
                return;
            }

            // Criar e Salvar
            Disciplina d = new Disciplina(nome, itemSelecionado.getId());
            
            if (d.salvar()) {
                JOptionPane.showMessageDialog(null, "Disciplina salva com sucesso! ID: " + d.getId());
                txtNome.setText(""); // Limpa o campo
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao salvar disciplina.");
            }
        });
    }

    // Método para buscar os professores no banco e colocar na caixinha
    private void carregarProfessores() {
        Connection con = ModuloConexao.conectar();
        if (con == null) return;

        try {
            // JOIN para pegar o ID do professor e o Nome da pessoa
            String sql = "SELECT professor.id, pessoa.nome FROM pessoa INNER JOIN professor ON pessoa.id = professor.id_pessoa";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                // Adiciona na caixa de seleção (O ItemCombo guarda o ID escondido e mostra o Nome)
                cbProfessor.addItem(new ItemCombo(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar professores: " + e.getMessage());
        } finally {
            ModuloConexao.desconectar(con);
        }
    }
}