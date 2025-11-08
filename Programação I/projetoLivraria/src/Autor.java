public class Autor {
    private Integer id;
    private String nome;
    private String cidade;


    public Autor() {

    }

    public Autor(Integer id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void salvar() {
        System.out.println("Você salvou o autor " + this.nome);
    }

    public void alterar() {
        System.out.println("Você alterou o autor " + this.nome);
    }

    public void excluir() {
        System.out.println("Você excluiu o autor " + this.nome);
    }

    public void pesquisar() {
        System.out.println("Você pesquisou o autor " + this.nome);
    }
    
    
}