import java.util.Date;

public class ListaTarefas {
    private Integer id;
    private Date data_tarefa;
    private String descricao_tarefa;
    private String observacao;
    private Prioridade prioridade;
    private Responsavel responsavel;

    
 
    public ListaTarefas() {
    }

    public ListaTarefas(Integer id, Date data_tarefa, String descricao_tarefa, String observacao, Prioridade prioridade,
            Responsavel responsavel) {
        this.id = id;
        this.data_tarefa = data_tarefa;
        this.descricao_tarefa = descricao_tarefa;
        this.observacao = observacao;
        this.prioridade = prioridade;
        this.responsavel = responsavel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_tarefa() {
        return data_tarefa;
    }

    public void setData_tarefa(Date data_tarefa) {
        this.data_tarefa = data_tarefa;
    }

    public String getDescricao_tarefa() {
        return descricao_tarefa;
    }

    public void setDescricao_tarefa(String descricao_tarefa) {
        this.descricao_tarefa = descricao_tarefa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
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

    public ListaTarefas pesquisar(Integer id) {
        return null;
    }

}
