public class TipoCapa {
    private Integer id;
    private String descricao;


    public TipoCapa() {

    }

    public TipoCapa(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void salvar() {
        System.out.println("Você salvou o tipo de capa " + this.descricao);
    }

    public void alterar() {
        System.out.println("Você alterou o tipo de capa " + this.descricao);
    }

    public void excluir() {
        System.out.println("Você excluiu o tipo de capa " + this.descricao);
    }

    public void pesquisar() {
        System.out.println("Você pesquisou o tipo de capa " + this.descricao);
    }
}
