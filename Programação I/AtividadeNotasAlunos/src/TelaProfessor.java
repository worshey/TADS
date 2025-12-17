import javax.swing.*;

public class TelaProfessor extends JFrame {
    private JTextField txtId, txtNome, txtEndereco, txtTelefone, txtEmail, txtMatricula;

    public TelaProfessor() {
        setTitle("Gerenciar Professores");
        setSize(500, 450); // Menor pois tem menos campos
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campos básicos
        addLabel("ID:", 20, 20);      txtId = addText(100, 20);
        JButton btnBusca = new JButton("Buscar"); btnBusca.setBounds(200, 20, 100, 25); add(btnBusca);

        addLabel("Nome:", 20, 60);    txtNome = addText(100, 60);
        addLabel("Matrícula:", 20, 100); txtMatricula = addText(100, 100);
        addLabel("Endereço:", 20, 140); txtEndereco = addText(100, 140);
        addLabel("Telefone:", 20, 180); txtTelefone = addText(100, 180);
        addLabel("Email:", 20, 220);    txtEmail = addText(100, 220);

        JButton btnSalvar = new JButton("Salvar"); btnSalvar.setBounds(50, 280, 100, 30); add(btnSalvar);
        
        // Ação Salvar
        btnSalvar.addActionListener(e -> {
            Professor p = new Professor(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText(), txtEmail.getText(), txtMatricula.getText());
            if(p.salvar()) {
                JOptionPane.showMessageDialog(null, "Professor salvo! ID: " + p.getId());
                txtId.setText(""+p.getId());
            }
        });

        // Ação Buscar
        btnBusca.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Professor p = new Professor();
                if(p.pesquisar(id)) {
                    txtNome.setText(p.getNome());
                    txtMatricula.setText(p.getMatricula());
                    txtEndereco.setText(p.getEndereco());
                    txtTelefone.setText(p.getTelefone());
                    txtEmail.setText(p.getEmail());
                } else JOptionPane.showMessageDialog(null, "Não encontrado.");
            } catch(Exception ex) { JOptionPane.showMessageDialog(null, "ID Inválido"); }
        });
    }

    // Auxiliares
    private void addLabel(String s, int x, int y) { JLabel l = new JLabel(s); l.setBounds(x,y,80,25); add(l); }
    private JTextField addText(int x, int y) { JTextField t = new JTextField(); t.setBounds(x,y,250,25); add(t); return t; }
}