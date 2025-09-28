public class App {
    public static void main(String[] args) throws Exception {
       
    Fabricante vw = new Fabricante();
    vw.setId(1);
    vw.setNome("Volkswagen");
    vw.setCnpj("00.234.000/0001-00");
    vw.setCidade("Jatai");
       
    Fabricante honda = new Fabricante();
    honda.setId(2);
    honda.setNome("honda");
    honda.setCnpj("00.235.000/0001-00");
    honda.setCidade("Tokyo");
       
       
    Veiculo fox = new Veiculo();
    fox.setId(1);
    fox.setPlaca("OGW8F67");
    fox.setNome("Fox");
    fox.setFabricante(vw);
    fox.setAno(2011);
    fox.setModelo(2012);

    Veiculo titan = new Veiculo();
    titan.setId(2);
    titan.setPlaca("PQQ9H67");
    titan.setNome("Titan");
    titan.setFabricante(honda);
    titan.setAno(2015);
    titan.setModelo(2015);

    fox.ImprimirDados();
    titan.ImprimirDados();
    
 }
}

