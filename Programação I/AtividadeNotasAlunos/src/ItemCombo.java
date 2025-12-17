public class ItemCombo {
    private int id;
    private String descricao;

    public ItemCombo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    // O Java usa este método para saber o que escrever na tela.
    // Nós mandamos ele escrever a descrição (Nome), mas o ID continua guardado no objeto.
    @Override
    public String toString() {
        return descricao;
    }
}