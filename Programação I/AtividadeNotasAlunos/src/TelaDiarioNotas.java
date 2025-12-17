import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaDiarioNotas extends JFrame {

    // ComboBoxes para seleção (ItemCombo guarda ID e Nome)
    private JComboBox<ItemCombo> cbAluno, cbDisciplina, cbTurma, cbPeriodo;
    private JLabel lblIdDiarioGerado;
    
    // Componentes para lançar Notas
    private JTextField txtNota;
    private JButton btnLancarNota, btnCalcular;
    private JLabel lblResultado;

    // Variável para guardar o ID do diário atual na memória da tela
    private int idDiarioAtual = -1;

    public TelaDiarioNotas() {
        setTitle("Lançamento de Notas e Diário (Modo Avançado)");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- PASSO 1: ABERTURA ---
        JLabel titulo1 = new JLabel("PASSO 1: Abertura de Diário");
        titulo1.setFont(new Font("Arial", Font.BOLD, 14));
        titulo1.setBounds(20, 10, 300, 20);
        add(titulo1);

        // 1. Aluno
        addLabel("Selecione o Aluno:", 20, 40);
        cbAluno = new JComboBox<>();
        cbAluno.setBounds(150, 40, 400, 25);
        add(cbAluno);

        // 2. Disciplina
        addLabel("Disciplina:", 20, 80);
        cbDisciplina = new JComboBox<>();
        cbDisciplina.setBounds(150, 80, 400, 25);
        add(cbDisciplina);

        // 3. Turma
        addLabel("Turma:", 20, 120);
        cbTurma = new JComboBox<>();
        cbTurma.setBounds(150, 120, 180, 25);
        add(cbTurma);

        // 4. Periodo
        addLabel("Período:", 350, 120);
        cbPeriodo = new JComboBox<>();
        cbPeriodo.setBounds(410, 120, 140, 25);
        add(cbPeriodo);

        // --- CARREGAR DADOS DO BANCO ---
        // Aqui chamamos o método que busca os dados para preencher as caixas acima
        carregarCombos();

        JButton btnCriarDiario = new JButton("Abrir Diário");
        btnCriarDiario.setBounds(20, 160, 530, 30);
        add(btnCriarDiario);

        lblIdDiarioGerado = new JLabel("Diário ainda não criado.");
        lblIdDiarioGerado.setBounds(20, 200, 400, 20);
        lblIdDiarioGerado.setForeground(Color.BLUE);
        add(lblIdDiarioGerado);

        // --- PASSO 2: NOTAS ---
        JSeparator sep = new JSeparator();
        sep.setBounds(0, 230, 600, 10);
        add(sep);

        JLabel titulo2 = new JLabel("PASSO 2: Lançamento de Notas");
        titulo2.setFont(new Font("Arial", Font.BOLD, 14));
        titulo2.setBounds(20, 240, 300, 20);
        add(titulo2);

        addLabel("Valor da Nota:", 20, 280);
        txtNota = new JTextField();
        txtNota.setBounds(120, 280, 80, 25);
        add(txtNota);
        
        btnLancarNota = new JButton("Adicionar Nota");
        btnLancarNota.setBounds(230, 280, 150, 25);
        btnLancarNota.setEnabled(false); // Começa bloqueado
        add(btnLancarNota);

        btnCalcular = new JButton("Encerrar Semestre e Calcular Média");
        btnCalcular.setBounds(20, 330, 360, 40);
        btnCalcular.setEnabled(false); // Começa bloqueado
        add(btnCalcular);

        lblResultado = new JLabel("Situação: Aguardando...");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 18));
        lblResultado.setBounds(20, 390, 400, 30);
        add(lblResultado);

        // --- AÇÕES DOS BOTÕES ---

        // AÇÃO 1: CRIAR DIÁRIO (VINCULAR TUDO)
        btnCriarDiario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pega os itens selecionados nas caixas
                ItemCombo itemAluno = (ItemCombo) cbAluno.getSelectedItem();
                ItemCombo itemDisc = (ItemCombo) cbDisciplina.getSelectedItem();
                ItemCombo itemTurma = (ItemCombo) cbTurma.getSelectedItem();
                ItemCombo itemPeriodo = (ItemCombo) cbPeriodo.getSelectedItem();

                if(itemAluno == null || itemDisc == null || itemTurma == null || itemPeriodo == null) {
                    JOptionPane.showMessageDialog(null, "Selecione todos os campos!");
                    return;
                }

                // Cria o diário usando os IDs escondidos dentro do ItemCombo
                Diario diario = new Diario(itemAluno.getId(), itemDisc.getId(), itemTurma.getId(), itemPeriodo.getId());
                
                if (diario.salvar()) {
                    idDiarioAtual = diario.getId();
                    lblIdDiarioGerado.setText("Diário Aberto! ID: " + idDiarioAtual);
                    
                    // Libera os botões de nota
                    btnLancarNota.setEnabled(true);
                    btnCalcular.setEnabled(true);
                    
                    JOptionPane.showMessageDialog(null, "Diário criado para o aluno: " + itemAluno);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao criar diário. Veja o log.");
                }
            }
        });

        // AÇÃO 2: LANÇAR NOTA
        btnLancarNota.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(txtNota.getText().replace(",", "."));
                Nota nota = new Nota(valor, idDiarioAtual);
                
                if (nota.salvar()) {
                    JOptionPane.showMessageDialog(null, "Nota lançada com sucesso!");
                    txtNota.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Nota inválida (Deve ser entre 0 e 10).");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Digite apenas números.");
            }
        });

        // AÇÃO 3: CALCULAR RESULTADO
        btnCalcular.addActionListener(e -> {
            Diario d = new Diario();
            d.setId(idDiarioAtual);
            d.atualizarSituacao(); // Vai no banco calcular
            
            lblResultado.setText("Processamento Concluído. Verifique o Log.");
            lblResultado.setForeground(Color.MAGENTA);
        });
    }

    // --- MÉTODOS AUXILIARES ---

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 120, 25);
        add(lbl);
    }

    // MÉTODO QUE BUSCA DADOS NO BANCO E PREENCHE OS COMBOS
    private void carregarCombos() {
        Connection con = ModuloConexao.conectar();
        if (con == null) return;

        try {
            // 1. Carregar Alunos (CORREÇÃO AQUI: "aluno.id" para tirar ambiguidade)
            PreparedStatement stmt = con.prepareStatement("SELECT aluno.id, pessoa.nome FROM pessoa INNER JOIN aluno ON pessoa.id = aluno.id_pessoa");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                cbAluno.addItem(new ItemCombo(rs.getInt("id"), rs.getString("nome")));
            }

            // 2. Carregar Disciplinas
            stmt = con.prepareStatement("SELECT id, nome_disciplina FROM disciplina");
            rs = stmt.executeQuery();
            while(rs.next()) cbDisciplina.addItem(new ItemCombo(rs.getInt("id"), rs.getString("nome_disciplina")));

            // 3. Carregar Turmas
            stmt = con.prepareStatement("SELECT id, nome_turma FROM turma");
            rs = stmt.executeQuery();
            while(rs.next()) cbTurma.addItem(new ItemCombo(rs.getInt("id"), rs.getString("nome_turma")));

            // 4. Carregar Periodos
            stmt = con.prepareStatement("SELECT id, nome_periodo FROM periodo");
            rs = stmt.executeQuery();
            while(rs.next()) cbPeriodo.addItem(new ItemCombo(rs.getInt("id"), rs.getString("nome_periodo")));

        } catch (Exception e) {
            e.printStackTrace(); // Mostra erro no console se houver
            JOptionPane.showMessageDialog(null, "Erro ao carregar listas: " + e.getMessage());
        } finally {
            ModuloConexao.desconectar(con);
        }
    }
}