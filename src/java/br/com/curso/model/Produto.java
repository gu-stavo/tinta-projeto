package br.com.curso.model;

public class Produto {

    private Integer idProduto;
    private String nomeProduto;
    private String situacao;
    private double preco;
    private Cor cor;
    private Marca marca;
    private Composicao composicao;
    private TipoPagamento tipoPagamento;
    
    public Produto() {
        this.idProduto = 0;
        this.nomeProduto = "";
        this.situacao = "";
        this.preco = 0;
        this.cor = new Cor();
        this.marca = new Marca();
        this.composicao = new Composicao();
        this.tipoPagamento =  new TipoPagamento();
    }

    public Produto(Integer idProduto, String nomeProduto, String situacao, double preco, Cor cor, Marca marca, Composicao composicao, TipoPagamento tipoPagamento) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.situacao = situacao;
        this.preco = preco;
        this.cor = cor;
        this.marca = marca;
        this.composicao = composicao;
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Composicao getComposicao() {
        return composicao;
    }

    public void setComposicao(Composicao composicao) {
        this.composicao = composicao;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }


    
}
