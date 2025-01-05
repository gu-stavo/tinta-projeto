package br.com.curso.model;

public class TipoPagamento {

    private Integer idTipo;
    private String nomeTipo;

    public TipoPagamento() {
        this.idTipo= 0;
        this.nomeTipo ="";
    }

    public TipoPagamento(Integer idTipo, String nomeTipo) {
        this.idTipo = idTipo;
        this.nomeTipo = nomeTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
    
    
}
