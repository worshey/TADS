import java.time.LocalDate;

public class Venda {
    private LocalDate data;
    private Double valorTotal;
    private Produto produto;
    private Integer numeroVenda;
   
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Integer getNumeroVenda() {
        return numeroVenda;
    }
    public void setNumeroVenda(Integer numeroVenda) {
        this.numeroVenda = numeroVenda;
    }
    
    

    public void ImprimirVenda(){
        System.out.println("--------------------DADOS DA VENDA--------------------");
        System.out.println("Número da venda: " +getNumeroVenda());
        
        System.out.println("Data da venda: " +getData());

        System.out.println("--------------------DADOS DO PRODUTO--------------------");
        System.out.println("Produto Vendido: " +getProduto().getNome());
        System.out.println("Marca: " +getProduto().getMarca());
        System.out.println("Preço: " +getProduto().getPreco());
        System.out.println("Quantidade em estoque: " +getProduto().getQtde());
        System.out.println("========================================================");
    }
}