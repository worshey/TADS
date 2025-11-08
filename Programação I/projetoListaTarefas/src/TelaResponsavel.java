// TelaResponsavel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaResponsavel extends JFrame {

    JPanel painelFormulario;
    JLabel lblNome;
    JTextField txtNome;
    JButton btnSalvar;
    JButton btnAlterar;
    JButton btnExcluir;
    JButton btnLimpar;

    JTable tabela;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    ResponsavelDAO responsavelDAO;
    private Integer idSelecionado = null;

    public TelaResponsavel() {
        setTitle("Cadastro de Responsáveis");
        setSize(1920, 1080);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        responsavelDAO = new ResponsavelDAO();

        painelFormulario = new JPanel();
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        btnSalvar = new JButton("Salvar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        painelFormulario.add(lblNome);
        painelFormulario.add(txtNome);
        painelFormulario.add(btnSalvar);
        painelFormulario.add(btnAlterar);
        painelFormulario.add(btnExcluir);
        painelFormulario.add(btnLimpar);

        this.add(painelFormulario, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);


        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O nome não pode estar vazio.");
                    return;
                }
                
                Responsavel novoResponsavel = new Responsavel();
                novoResponsavel.setNome(nome);
                
                responsavelDAO.salvar(novoResponsavel);
                JOptionPane.showMessageDialog(null, "Responsável salvo com sucesso!");
                atualizarTabela();
                limparFormulario();
            }
        });

        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabela.getSelectedRow();
                    if (selectedRow != -1) {
                        idSelecionado = (Integer) tableModel.getValueAt(selectedRow, 0);
                        String nomeSelecionado = (String) tableModel.getValueAt(selectedRow, 1);
                        txtNome.setText(nomeSelecionado);
                    }
                }
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um item na tabela para alterar.");
                    return;
                }
                
                String novoNome = txtNome.getText();
                Responsavel responsavelParaAlterar = new Responsavel();
                responsavelParaAlterar.setId(idSelecionado);
                responsavelParaAlterar.setNome(novoNome);
                
                responsavelDAO.alterar(responsavelParaAlterar);
                
                JOptionPane.showMessageDialog(null, "Responsável alterado com sucesso!");
                atualizarTabela();
                limparFormulario();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um item na tabela para excluir.");
                    return;
                }
                
                int resposta = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja excluir este item?", 
                        "Confirmação de Exclusão", 
                        JOptionPane.YES_NO_OPTION);
                
                if (resposta == JOptionPane.YES_OPTION) {
                    responsavelDAO.excluir(idSelecionado);
                    JOptionPane.showMessageDialog(null, "Responsável excluído com sucesso!");
                    atualizarTabela();
                    limparFormulario();
                }
            }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormulario();
            }
        });

        atualizarTabela();
    }
    
    private void atualizarTabela() {
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        List<Responsavel> responsaveis = responsavelDAO.listar();
        for (Responsavel r : responsaveis) {
            tableModel.addRow(new Object[]{
                r.getId(),
                r.getNome()
            });
        }
    }
    
    private void limparFormulario() {
        txtNome.setText("");
        idSelecionado = null;
        tabela.clearSelection();
    }

    public static void main(String[] args) {
        TelaResponsavel tela = new TelaResponsavel();
        tela.setVisible(true);
    }
}