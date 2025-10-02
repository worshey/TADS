public class Categoria {
    private String descricao;

    public Categoria(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void salvar() {
        System.out.println("Categoria salvo com sucesso!");
    }

    public void alterar() {
        System.out.println("Categoria alterado com sucesso!");
    }

    public void excluir() {
        System.out.println("Categoria exlcuído com sucesso!");
    }

    public void pesquisar() {
        System.out.println("Categoria pesquisado com sucesso!");
    }
}