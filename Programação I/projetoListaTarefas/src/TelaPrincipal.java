// TelaPrincipal.java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Gerenciador de Tarefas");
        setSize(800, 600);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnPrioridades = new JButton("Gerenciar Prioridades");
        JButton btnResponsaveis = new JButton("Gerenciar Responsáveis");
        JButton btnTarefas = new JButton("Gerenciar Tarefas");

        painel.add(btnPrioridades);
        painel.add(btnResponsaveis);
        painel.add(btnTarefas);

        this.add(painel);


        btnPrioridades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrioridade tela = new TelaPrioridade();
                tela.setVisible(true);
            }
        });

        btnResponsaveis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaResponsavel tela = new TelaResponsavel();
                tela.setVisible(true);
            }
        });

        btnTarefas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaListaTarefas tela = new TelaListaTarefas();
                tela.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
    }
}