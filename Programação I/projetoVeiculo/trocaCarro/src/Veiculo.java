public class Veiculo {  
    private Integer id;
    private String placa;
    private String nome;
    private Fabricante Fabricante;
    private Integer ano;
    private Integer modelo;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
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
    
      public Fabricante getFabricante() {
          return Fabricante;
      }
      public void setFabricante(Fabricante fabricante) {
          Fabricante = fabricante;
      }

      public void ImprimirDados() {
        System.out.println("-----------------------");
        System.out.println("Dados do Veículo:");
        System.out.println("ID: " + id);
        System.out.println("Placa: " + placa);
        System.out.println("Nome: " + nome);
        System.out.println("Ano: " + ano);
        System.out.println("Modelo: " + modelo);
        System.out.println("Fabricante: " + Fabricante.getNome());
        System.out.println("CNPJ: " + Fabricante.getCnpj());
        System.out.println("Cidade: " + Fabricante.getCidade());
        System.out.println("-----------------------");
      }

}
