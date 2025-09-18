import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        Produto produto = new Produto();
        produto.setNome("Camiseta");        
        produto.setMarca("Nike");
        produto.setPreco(79.90);
        produto.setQtde(10);

        Produto produto1 = new Produto();
        produto1.setNome("Jaqueta");        
        produto1.setMarca("Vlone");
        produto1.setPreco(799.90);
        produto1.setQtde(12);
       
        Venda venda = new Venda();
        venda.setData(LocalDate.now());
        venda.setNumeroVenda(1123);
        venda.setProduto(produto);

        System.out.println("Data da venda: " + venda.getData());
        System.out.println("Número da venda: " + venda.getNumeroVenda());

        System.out.println("====================DADOS DO PRODUTO====================");
        System.out.println("Produto Vendido: " + venda.getProduto().getNome());
        System.out.println("Marca: " + venda.getProduto().getMarca());
        System.out.println("Preço: " + venda.getProduto().getPreco());
        System.out.println("Quantidade em estoque: " + venda.getProduto().getQtde());
        System.out.println("========================================================");

    }

 }