package br.com.almoxarifado.model;

public class BranchProduct {
    private Product product;
    private Branch filial;
    private int quantidade;
    private String location;

    public BranchProduct() {
    }

    public BranchProduct(Product product, Branch filial, int quantidade, String localizacao) {
        this.product = product;
        this.filial = filial;
        this.quantidade = quantidade;
        this.location = localizacao;
    }

    public Product getProduct() {
        return product;
    }

    public Branch getBranch() {
        return filial;
    }

    public int getQuantity() {
        return quantidade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }
}
