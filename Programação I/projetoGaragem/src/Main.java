public class Main {
    public static void main(String[] args) throws Exception {
        
        Categoria categoria1 = new Categoria("SUV");
        Garagem garagem1 = new Garagem("Garagem do Zé", "São Paulo");
        Veiculo veiculo1 = new Veiculo("Fusca", 1980, 1980, "Azul", "ABC-1234", true, categoria1, garagem1);
        Carros carro1 = new Carros(100, 5,"Ar Condicionado, Direção Hidráulica");
        Motos moto1 = new Motos(150);

        System.out.println("--------------");
        carro1.salvar();
        System.out.println("--------------");

        System.out.println("--------------");
        moto1.salvar();
        System.out.println("--------------");

        System.out.println("--------------");
        categoria1.salvar();
        System.out.println("--------------");

        System.out.println("--------------");
        garagem1.salvar();
        System.out.println("--------------");

        System.out.println("--------------");
        carro1.alterar();
        System.out.println("--------------");

        System.out.println("--------------");
        carro1.pesquisar();
        System.out.println("--------------");

        System.out.println("--------------");
        carro1.excluir();
        System.out.println("--------------");

        System.out.println("--------------");
        moto1.alterar();
        System.out.println("--------------");

        System.out.println("--------------");
        moto1.pesquisar();
        System.out.println("--------------");

        System.out.println("--------------");
        moto1.excluir();
        System.out.println("--------------");

        System.out.println("--------------");
        categoria1.alterar();
        System.out.println("--------------");

        System.out.println("--------------");
        categoria1.pesquisar();
        System.out.println("--------------");

        System.out.println("--------------");
        categoria1.excluir();
        System.out.println("--------------");

        System.out.println("--------------");
        garagem1.alterar();
        System.out.println("--------------");

        System.out.println("--------------");
        garagem1.pesquisar();
        System.out.println("--------------");

        System.out.println("--------------");
        garagem1.excluir();
        System.out.println("--------------");


    }  

}