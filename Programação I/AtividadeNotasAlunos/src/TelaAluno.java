import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAluno extends JFrame {

    // Componentes da tela (precisam ser variáveis de classe para acessarmos nos botões)
    private JTextField txtId, txtNome, txtEndereco, txtTelefone, txtEmail, txtMatricula, txtPai, txtMae;

    public TelaAluno() {
        setTitle("Gerenciar Alunos");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só essa janela, não o programa todo
        setLocationRelativeTo(null);
        setLayout(null); // Layout manual para facilitar o entendimento posicional

        // --- RÓTULOS (Labels) E CAMPOS DE TEXTO (TextFields) ---
        
        // ID (Usado para Pesquisar/Excluir)
        JLabel lblId = new JLabel("ID (Para busca):");
        lblId.setBounds(20, 20, 150, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(180, 20, 100, 25);
        add(txtId);

        JButton btnBuscar = new JButton("Pesquisar ID");
        btnBuscar.setBounds(290, 20, 150, 25);
        add(btnBuscar);

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 100, 25);
        add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(120, 60, 320, 25);
        add(txtNome);

        // Matrícula
        JLabel lblMat = new JLabel("Matrícula (10 núm):");
        lblMat.setBounds(20, 100, 150, 25);
        add(lblMat);
        txtMatricula = new JTextField();
        txtMatricula.setBounds(180, 100, 260, 25);
        add(txtMatricula);

        // Endereço
        JLabel lblEnd = new JLabel("Endereço:");
        lblEnd.setBounds(20, 140, 100, 25);
        add(lblEnd);
        txtEndereco = new JTextField();
        txtEndereco.setBounds(120, 140, 320, 25);
        add(txtEndereco);

        // Telefone
        JLabel lblTel = new JLabel("Telefone:");
        lblTel.setBounds(20, 180, 100, 25);
        add(lblTel);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(120, 180, 320, 25);
        add(txtTelefone);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 220, 100, 25);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(120, 220, 320, 25);
        add(txtEmail);

        // Pai
        JLabel lblPai = new JLabel("Nome do Pai:");
        lblPai.setBounds(20, 260, 100, 25);
        add(lblPai);
        txtPai = new JTextField();
        txtPai.setBounds(120, 260, 320, 25);
        add(txtPai);

        // Mãe
        JLabel lblMae = new JLabel("Nome da Mãe:");
        lblMae.setBounds(20, 300, 100, 25);
        add(lblMae);
        txtMae = new JTextField();
        txtMae.setBounds(120, 300, 320, 25);
        add(txtMae);

        // --- BOTÕES DE AÇÃO ---

        JButton btnSalvar = new JButton("Salvar Novo");
        btnSalvar.setBounds(50, 360, 120, 40);
        add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(180, 360, 120, 40);
        add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(310, 360, 120, 40);
        add(btnExcluir);

        // --- LÓGICA DOS BOTÕES ---

        // AÇÃO SALVAR
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Criamos o objeto Aluno e preenchemos com o que o usuário digitou
                Aluno a = new Aluno();
                a.setNome(txtNome.getText());
                a.setMatricula(txtMatricula.getText());
                a.setEndereco(txtEndereco.getText());
                a.setTelefone(txtTelefone.getText());
                a.setEmail(txtEmail.getText());
                a.setNomePai(txtPai.getText());
                a.setNomeMae(txtMae.getText());

                // Chama o método do banco
                if (a.salvar()) {
                    JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso! ID: " + a.getId());
                    txtId.setText(String.valueOf(a.getId())); // Mostra o ID gerado
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar. Verifique o console ou a matrícula.");
                }
            }
        });

        // AÇÃO PESQUISAR
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    Aluno a = new Aluno();
                    if (a.pesquisar(id)) {
                        // Se achou, preenchemos a tela com os dados do banco
                        txtNome.setText(a.getNome());
                        txtMatricula.setText(a.getMatricula());
                        txtEndereco.setText(a.getEndereco());
                        txtTelefone.setText(a.getTelefone());
                        txtEmail.setText(a.getEmail());
                        txtPai.setText(a.getNomePai());
                        txtMae.setText(a.getNomeMae());
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido no ID.");
                }
            }
        });

        // AÇÃO ALTERAR
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Para alterar, precisamos do ID e de todos os dados novos
                try {
                    int id = Integer.parseInt(txtId.getText());
                    Aluno a = new Aluno();
                    a.setId(id); // Importante: Setar o ID para o WHERE do SQL
                    a.setNome(txtNome.getText());
                    a.setMatricula(txtMatricula.getText());
                    a.setEndereco(txtEndereco.getText());
                    a.setTelefone(txtTelefone.getText());
                    a.setEmail(txtEmail.getText());
                    a.setNomePai(txtPai.getText());
                    a.setNomeMae(txtMae.getText());

                    if (a.alterar()) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao alterar.");
                    }
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(null, "Verifique se o ID está preenchido.");
                }
            }
        });

        // AÇÃO EXCLUIR
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    Aluno a = new Aluno();
                    a.setId(id);
                    
                    // Pergunta de confirmação
                    int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (a.excluir()) {
                            JOptionPane.showMessageDialog(null, "Aluno excluído.");
                            // Limpar campos
                            txtNome.setText("");
                            txtId.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao excluir.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Digite o ID para excluir.");
                }
            }
        });
    }
}