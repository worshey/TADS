import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPrioridade extends JFrame {

    JPanel painelFormulario;
    JLabel lblDescricao;
    JTextField txtDescricao;
    JButton btnSalvar;
    JButton btnAlterar;
    JButton btnExcluir;
    JButton btnLimpar; 

    JTable tabela;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    PrioridadeDAO prioridadeDAO;
    
    private Integer idSelecionado = null;

    public TelaPrioridade() {
        setTitle("Cadastro de Prioridades");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        prioridadeDAO = new PrioridadeDAO();

     
        painelFormulario = new JPanel();
        lblDescricao = new JLabel("Descrição:");
        txtDescricao = new JTextField(20);
        btnSalvar = new JButton("Salvar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir"); 
        btnLimpar = new JButton("Limpar");

        painelFormulario.add(lblDescricao);
        painelFormulario.add(txtDescricao);
        painelFormulario.add(btnSalvar);
        painelFormulario.add(btnAlterar);
        painelFormulario.add(btnExcluir);
        painelFormulario.add(btnLimpar); 

        this.add(painelFormulario, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Descrição"}, 0) {
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
                String descricao = txtDescricao.getText();
                if (descricao.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A descrição não pode estar vazia.");
                    return;
                }
                
                Prioridade novaPrioridade = new Prioridade();
                novaPrioridade.setDescricao(descricao);
                
                prioridadeDAO.salvar(novaPrioridade);
                JOptionPane.showMessageDialog(null, "Prioridade salva com sucesso!");
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
                        String descricaoSelecionada = (String) tableModel.getValueAt(selectedRow, 1);
                        
                        txtDescricao.setText(descricaoSelecionada);
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
                
                String novaDescricao = txtDescricao.getText();
                
                Prioridade prioridadeParaAlterar = new Prioridade();
                prioridadeParaAlterar.setId(idSelecionado);
                prioridadeParaAlterar.setDescricao(novaDescricao);
                
                prioridadeDAO.alterar(prioridadeParaAlterar);
                
                JOptionPane.showMessageDialog(null, "Prioridade alterada com sucesso!");
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
                    prioridadeDAO.excluir(idSelecionado);
                    
                    JOptionPane.showMessageDialog(null, "Prioridade excluída com sucesso!");
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


        List<Prioridade> prioridades = prioridadeDAO.listar();
        for (Prioridade p : prioridades) {
            tableModel.addRow(new Object[]{
                p.getId(),
                p.getDescricao()
            });
        }
    }
    
    private void limparFormulario() {
        txtDescricao.setText("");
        idSelecionado = null;
        tabela.clearSelection();
    }


    public static void main(String[] args) {
        TelaPrioridade tela = new TelaPrioridade();
        tela.setVisible(true);
    }
}