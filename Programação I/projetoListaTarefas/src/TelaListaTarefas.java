// TelaListaTarefas.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.Date; 
import java.text.ParseException;

public class TelaListaTarefas extends JFrame {

    JPanel painelFormulario;
    JTextField txtDescricao, txtData, txtObservacao;
    JComboBox<Prioridade> comboPrioridade;
    JComboBox<Responsavel> comboResponsavel;
    JButton btnSalvar, btnAlterar, btnExcluir, btnLimpar;

    JTable tabela;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    ListaTarefasDAO tarefaDAO;
    PrioridadeDAO prioridadeDAO;
    ResponsavelDAO responsavelDAO;

    private Integer idSelecionado = null;

    public TelaListaTarefas() {
        setTitle("Cadastro de Tarefas");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tarefaDAO = new ListaTarefasDAO();
        prioridadeDAO = new PrioridadeDAO();
        responsavelDAO = new ResponsavelDAO();


        painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10)); // 0 linhas, 2 colunas

        painelFormulario.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField(20);
        painelFormulario.add(txtDescricao);

        painelFormulario.add(new JLabel("Observação:"));
        txtObservacao = new JTextField(20);
        painelFormulario.add(txtObservacao);

        painelFormulario.add(new JLabel("Data (AAAA-MM-DD):"));
        txtData = new JTextField(10);
        painelFormulario.add(txtData);

        painelFormulario.add(new JLabel("Prioridade:"));
        comboPrioridade = new JComboBox<>();
        painelFormulario.add(comboPrioridade);

        painelFormulario.add(new JLabel("Responsável:"));
        comboResponsavel = new JComboBox<>();
        painelFormulario.add(comboResponsavel);
        

        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);


        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(painelFormulario, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.SOUTH);
        
        this.add(painelTopo, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new Object[]{"ID", "Data", "Descrição", "Prioridade", "Responsável", "Observação"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);

        carregarComboBoxes();
        atualizarTabela();


        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ListaTarefas tarefa = new ListaTarefas();
                    tarefa.setDescricao_tarefa(txtDescricao.getText());
                    tarefa.setObservacao(txtObservacao.getText());
                    tarefa.setData_tarefa(Date.valueOf(txtData.getText())); // Converte String AAAA-MM-DD para Date
                    tarefa.setPrioridade((Prioridade) comboPrioridade.getSelectedItem());
                    tarefa.setResponsavel((Responsavel) comboResponsavel.getSelectedItem());

                    tarefaDAO.salvar(tarefa);
                    JOptionPane.showMessageDialog(null, "Tarefa salva com sucesso!");
                    atualizarTabela();
                    limparFormulario();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar: Verifique o formato da data (AAAA-MM-DD).");
                }
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma tarefa para alterar.");
                    return;
                }
                try {
                    ListaTarefas tarefa = new ListaTarefas();
                    tarefa.setId(idSelecionado);
                    tarefa.setDescricao_tarefa(txtDescricao.getText());
                    tarefa.setObservacao(txtObservacao.getText());
                    tarefa.setData_tarefa(Date.valueOf(txtData.getText()));
                    tarefa.setPrioridade((Prioridade) comboPrioridade.getSelectedItem());
                    tarefa.setResponsavel((Responsavel) comboResponsavel.getSelectedItem());

                    tarefaDAO.alterar(tarefa);
                    JOptionPane.showMessageDialog(null, "Tarefa alterada com sucesso!");
                    atualizarTabela();
                    limparFormulario();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar: Verifique o formato da data (AAAA-MM-DD).");
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelecionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma tarefa para excluir.");
                    return;
                }
                int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    tarefaDAO.excluir(idSelecionado);
                    JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!");
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

        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabela.getSelectedRow();
                    if (selectedRow != -1) {
                        idSelecionado = (Integer) tableModel.getValueAt(selectedRow, 0);
                        txtData.setText(tableModel.getValueAt(selectedRow, 1).toString());
                        txtDescricao.setText((String) tableModel.getValueAt(selectedRow, 2));
                        comboPrioridade.setSelectedItem(tableModel.getValueAt(selectedRow, 3));
                        comboResponsavel.setSelectedItem(tableModel.getValueAt(selectedRow, 4));
                        txtObservacao.setText((String) tableModel.getValueAt(selectedRow, 5));
                    }
                }
            }
        });
    }

    private void carregarComboBoxes() {
        comboPrioridade.removeAllItems();
        List<Prioridade> prioridades = prioridadeDAO.listar();
        for (Prioridade p : prioridades) {
            comboPrioridade.addItem(p);
        }

        comboResponsavel.removeAllItems();
        List<Responsavel> responsaveis = responsavelDAO.listar();
        for (Responsavel r : responsaveis) {
            comboResponsavel.addItem(r);
        }
    }

    private void atualizarTabela() {
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        List<ListaTarefas> tarefas = tarefaDAO.listar();
        for (ListaTarefas t : tarefas) {
            tableModel.addRow(new Object[]{
                t.getId(),
                t.getData_tarefa(),
                t.getDescricao_tarefa(),
                t.getPrioridade(),
                t.getResponsavel(), 
                t.getObservacao()
            });
        }
    }

    private void limparFormulario() {
        idSelecionado = null;
        txtDescricao.setText("");
        txtObservacao.setText("");
        txtData.setText("");
        comboPrioridade.setSelectedIndex(-1);
        comboResponsavel.setSelectedIndex(-1);
        tabela.clearSelection();
    }
    
    public static void main(String[] args) {
        TelaListaTarefas tela = new TelaListaTarefas();
        tela.setVisible(true);
    }
}