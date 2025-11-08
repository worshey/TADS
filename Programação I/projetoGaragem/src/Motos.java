public class Motos extends Veiculo {
    private Integer cilindradas;

    public Motos() {
        
    }

    public Motos(Integer cilindradas) {
        this.cilindradas = cilindradas;
    }
    
    public Integer getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(Integer cilindradas) {
        this.cilindradas = cilindradas;
    }

    
    @Override
    public void salvar() {
        System.out.println("Moto salva com sucesso!");
        super.salvar();
    }

    @Override
    public void alterar() {
        System.out.println("Moto alterada com sucesso!");
        super.salvar();
    }

    @Override
    public void excluir() {
        System.out.println("Moto excluída com sucesso!");
        super.salvar();
    }

    @Override
    public void pesquisar() {
        System.out.println("Moto pesquisada com sucesso!");
        super.salvar();
    }

}