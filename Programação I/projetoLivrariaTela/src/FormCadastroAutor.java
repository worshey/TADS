import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FormCadastroAutor extends JFrame {

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCidade;
    private JButton btnSalvar, btnLimpar, btnSair, btnPesquisar, btnAlterar, btnExcluir;

    // Referência para a classe que manipula os dados
    private AutorDAO autorDAO;

    public FormCadastroAutor() {
        setTitle("Cadastro de Autor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);

        // Instancia o DAO
        this.autorDAO = new AutorDAO();

        inicializarComponentes();
        adicionarListeners();

        setVisible(true);
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo ID
        gbc.gridx = 0; gbc.gridy = 0;
        painelPrincipal.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtId = new JTextField(15);
        painelPrincipal.add(txtId, gbc);

        // Campo Nome
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtNome = new JTextField(15);
        painelPrincipal.add(txtNome, gbc);

        // Campo Cidade
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        txtCidade = new JTextField(15);
        painelPrincipal.add(txtCidade, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnPesquisar = new JButton("Pesquisar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnPesquisar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSair);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelBotoes, gbc);

        add(painelPrincipal);
    }

    private void adicionarListeners() {
        btnSalvar.addActionListener(e -> salvarAutor());
        btnLimpar.addActionListener(e -> limparCampos());
        btnSair.addActionListener(e -> System.exit(0));
        btnAlterar.addActionListener(e -> System.out.println("Autor alterado!"));
        btnPesquisar.addActionListener(e -> System.out.println("Lista de autores"));
        btnExcluir.addActionListener(e -> System.out.println("Autor removido!"));
    }

    private void salvarAutor() {
        if (txtId.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty() || txtCidade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 1. Coleta os dados da tela e cria um objeto Autor
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String cidade = txtCidade.getText();
            Autor novoAutor = new Autor(id, nome, cidade);

            // 2. Pede para o DAO salvar o objeto
            autorDAO.salvar(novoAutor);

            // 3. Exibe mensagem de sucesso
            String mensagem = String.format("Autor salvo com sucesso!\n\nID: %d\nNome: %s\nCidade: %s", id, nome, cidade);
            JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo ID deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no arquivo: " + ex.getMessage(), "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtCidade.setText("");
        txtId.requestFocus();
    }
}