public class Veiculo {
    private String nome;
    private Integer ano;
    private Integer modelo;
    private String cor;
    private String placa;
    private Boolean unico_dono;
    private Categoria categoria;
    private Garagem garagem;

    public Veiculo() {
        
    }

    public Veiculo(String nome, Integer ano, Integer modelo, String cor, String placa, Boolean unico_dono, Categoria categoria, Garagem garagem) {
        this.nome = nome;
        this.ano = ano;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.unico_dono = unico_dono;
        this.categoria = categoria;
        this.garagem = garagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUnico_dono() {
        return unico_dono.toString();
    }

    public void setUnico_dono(String unico_dono) {
        this.unico_dono = Boolean.valueOf(unico_dono);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Garagem getGaragem() {
        return garagem;
    }

    public void setGaragem(Garagem garagem) {
        this.garagem = garagem;
    }

    public void salvar() {
        System.out.println("Veículo salvo com sucesso!");
    }

    public void alterar() {
        System.out.println("Veículo alterado com sucesso!");
    }

    public void excluir() {
        System.out.println("Veículo exlcuído com sucesso!");
    }

    public void pesquisar() {
        System.out.println("Veículo pesquisado com sucesso!");
    }
}