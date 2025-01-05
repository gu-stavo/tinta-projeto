package br.com.curso.model;

public class Cor {

    private int idCor;
    private String nomeCor;

    public Cor() {
        this.idCor = 0;
        this.nomeCor ="";
    }

    public Cor(int idCor, String nomeCor) {
        this.idCor = idCor;
        this.nomeCor = nomeCor;
    }

    public int getIdCor() {
        return idCor;
    }

    public void setIdCor(int idCor) {
        this.idCor = idCor;
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }
    
}
