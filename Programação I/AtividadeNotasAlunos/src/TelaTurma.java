import javax.swing.*;

public class TelaTurma extends JFrame {
    private JTextField txtNome;

    public TelaTurma() {
        setTitle("Cadastro de Turma");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl = new JLabel("Nome da Turma:");
        lbl.setBounds(20, 30, 100, 25);
        add(lbl);

        txtNome = new JTextField();
        txtNome.setBounds(130, 30, 130, 25);
        add(txtNome);

        JButton btn = new JButton("Salvar");
        btn.setBounds(90, 80, 100, 30);
        add(btn);

        btn.addActionListener(e -> {
            Turma t = new Turma(txtNome.getText());
            if(t.salvar()) {
                JOptionPane.showMessageDialog(null, "Turma Salva! ID: " + t.getId());
                txtNome.setText("");
            }
        });
    }
}