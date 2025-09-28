public class Main {
    public static void main(String[] args) throws Exception {
        Editora editora1 = new Editora(1, "Aquarela");
        Autor autor1 = new Autor(1, "Zezin", "Xique-Xique");
        TipoCapa tipoCapa1 = new TipoCapa(1, "Dura");
        Livro livro1 = new Livro(1, "Java para iniciantes", "Um livro para aprender Java", 1, 2023, 300, autor1, editora1, tipoCapa1);


        System.out.println("Detalhes do Livro:");
        System.out.println("ID: " + livro1.getId());
        System.out.println("Nome: " + livro1.getNome());
        System.out.println("Resenha: " + livro1.getResenha());
        System.out.println("Edição: " + livro1.getEdicao());
        System.out.println("Ano de Publicação: " + livro1.getAno_publicacao());
        System.out.println("Número de Páginas: " + livro1.getN_paginas());
        System.out.println("Autor: " + livro1.getAutor().getNome() + " - Cidade: " + livro1.getAutor().getCidade());
        System.out.println("Editora: " + livro1.getEditora().getNome_editora());
        System.out.println("Tipo de Capa: " + livro1.getTipo_capa().getDescricao());
        System.out.println("---------------------------------");


        livro1.salvar();
        livro1.alterar();
        livro1.pesquisar();
        livro1.excluir();

        System.out.println("---------------------------------");

        editora1.salvar();
        editora1.alterar();
        editora1.pesquisar();
        editora1.excluir();

        System.out.println("---------------------------------");

        autor1.salvar();
        autor1.alterar();
        autor1.pesquisar();
        autor1.excluir();

        System.out.println("---------------------------------");

        tipoCapa1.salvar();
        tipoCapa1.alterar();
        tipoCapa1.pesquisar();
        tipoCapa1.excluir();

        System.out.println("---------------------------------");
    }
}