public class Responsavel {
    private Integer id;
    private String nome;   
    
    public Responsavel() {
        
    }
    
    public Responsavel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    
    public boolean salvar() {
        return true;
    }
    
    public boolean alterar() {
        return true;
    }

    public boolean excluir() {
        return true;
    }

    public Responsavel pesquisar(Integer id) {
        return null;
    }


    @Override
    public String toString() {
        return this.getNome();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Responsavel that = (Responsavel) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }



}
