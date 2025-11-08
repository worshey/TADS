public class Garagem {
    private String nome;
    private String cidade;

    public Garagem() {
        
    }

    public Garagem(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void salvar() {
        System.out.println("Garagem salvo com sucesso!");
    }

    public void alterar() {
        System.out.println("Garagem alterado com sucesso!");
    }

    public void excluir() {
        System.out.println("Garagem exlcuído com sucesso!");
    }

    public void pesquisar() {
        System.out.println("Garagem pesquisado com sucesso!");
    }
}