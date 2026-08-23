package br.com.almoxarifado.model;

public class BranchProduct {
    private Product product;
    private Branch filial;
    private int quantity;
    private String location;

    public BranchProduct() {
    }

    public BranchProduct(Product product, Branch filial, int quantity) {
        this.product = product;
        this.filial = filial;
        this.quantity = quantity;
        this.location = null;
    }


    public boolean addQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        } else {
            this.quantity += quantity;
            return true;
        }
    }


    public Product getProduct() {
        return product;
    }

    public Branch getBranch() {
        return filial;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }
}
