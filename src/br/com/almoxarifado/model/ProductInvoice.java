package br.com.almoxarifado.model;

public class ProductInvoice {
    private Destination destination;
    private Product product;
    private int quantity;

    public ProductInvoice(Product product, int quantity, Destination destination) {
        this.product = product;
        this.quantity = quantity;
        this.destination = destination;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Destination getDestination() {
        return destination;
    }


}
