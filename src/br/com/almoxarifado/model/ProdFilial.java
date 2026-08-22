package br.com.almoxarifado.model;

public class ProdFilial {
    private Produto produto;
    private Filial filial;
    private int quantidade;
    private String localizacao;

    public ProdFilial(Produto produto, Filial filial, int quantidade, String localizacao) {
        this.produto = produto;
        this.filial = filial;
        this.quantidade = quantidade;
        this.localizacao = localizacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Filial getFilial() {
        return filial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
