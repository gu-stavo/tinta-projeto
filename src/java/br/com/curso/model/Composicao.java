package br.com.curso.model;

public class Composicao {

    private Integer idComposicao;
    private String nomeComposicao;

    public Composicao() {
        this.idComposicao = 0;
        this.nomeComposicao = "";
    }

    public Composicao(Integer idComposicao, String nomeComposicao) {
        this.idComposicao = idComposicao;
        this.nomeComposicao = nomeComposicao;
    }

    public Integer getIdComposicao() {
        return idComposicao;
    }

    public void setIdComposicao(Integer idComposicao) {
        this.idComposicao = idComposicao;
    }

    public String getNomeComposicao() {
        return nomeComposicao;
    }

    public void setNomeComposicao(String nomeComposicao) {
        this.nomeComposicao = nomeComposicao;
    }
    
    
    
}
