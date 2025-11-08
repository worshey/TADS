public class Prioridade {
    private Integer id;
    private String descricao;
   
    
    

    public Prioridade() {
    }

    public Prioridade(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Prioridade pesquisar(Integer id) {
        return null;
    }   

    @Override
    public String toString() {
        return this.getDescricao();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prioridade that = (Prioridade) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
