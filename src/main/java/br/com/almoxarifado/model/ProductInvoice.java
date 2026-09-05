package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.InvalidQuantityException;

public class ProductInvoice {
    private Destination destination;
    private Product product;
    private int quantity;

    public ProductInvoice(Product product, int quantity, Destination destination) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
        if(product == null || destination == null ){
            throw new NullPointerException();
        }
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
