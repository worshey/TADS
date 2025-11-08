import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Executa a criação da janela na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormCadastroAutor();
            }
        });
    }
}