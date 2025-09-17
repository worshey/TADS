public class App {
    public static void main(String[] args) throws Exception {
        Produto xuxu = new Produto();
        xuxu.setNome("Fita Isolante");
        xuxu.setMarca("Desconhecido");
        xuxu.setPreco(3.50);
        xuxu.setQtde(10);

        System.out.println("Nome: " + xuxu.getNome());
        System.out.println("Marca: " + xuxu.getMarca());
        System.out.println("Preço: " + xuxu.getPreco());
        System.out.println("Quantidade: " + xuxu.getQtde());
        System.out.println("Total: " + (xuxu.getPreco() * xuxu.getQtde()));
    }
}