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
    
}