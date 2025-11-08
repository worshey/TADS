import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AutorDAO {

    private static final String NOME_ARQUIVO = "autor.txt";

    public void salvar(Autor autor) throws IOException {
        // 'true' no FileWriter indica que o conteúdo será adicionado ao final do arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            writer.write(String.format("ID: %d, Nome: %s, Cidade: %s%n",
                autor.getId(),
                autor.getNome(),
                autor.getCidade()));
        }
    }

    // Futuramente, você pode adicionar outros métodos aqui
    // public List<Autor> pesquisarTodos() { ... }
    // public void alterar(Autor autor) { ... }
    // public void excluir(int id) { ... }
}