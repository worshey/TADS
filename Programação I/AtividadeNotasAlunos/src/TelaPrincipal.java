import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        
        setTitle("Sistema Escolar - Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(5, 1, 10, 10)); 

        
        JButton btnAluno = new JButton("Gerenciar Alunos");
        JButton btnProfessor = new JButton("Gerenciar Professores");
        JButton btnTurma = new JButton("Gerenciar Turmas");
        JButton btnDisciplina = new JButton("Gerenciar Disciplinas");
        JButton btnDiario = new JButton("Diário e Notas");
        JButton btnSair = new JButton("Sair");
        
        
        btnAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                new TelaAluno().setVisible(true);
            }
        });

        btnDiario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaDiarioNotas().setVisible(true);
            }
        });

        btnDisciplina.addActionListener(e -> new TelaDisciplina().setVisible(true));
        add(btnDisciplina);
        btnProfessor.addActionListener(e -> new TelaProfessor().setVisible(true));
        btnTurma.addActionListener(e -> new TelaTurma().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));


        add(btnAluno);
        add(btnProfessor);
        add(btnTurma);
        add(btnDiario);
        add(btnSair);
    }
}