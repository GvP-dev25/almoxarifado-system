package br.com.almoxarifado.model;

import main.java.br.com.almoxarifado.exception.InvalidQuantityException;
import main.java.br.com.almoxarifado.model.Destination;
import main.java.br.com.almoxarifado.model.Product;
import main.java.br.com.almoxarifado.model.ProductInvoice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductInvoiceTest {
    @Test
    void createProductInvoice() {
        Product product = new Product("1", "Parafuso 1/2");
        ProductInvoice productInvoice = new ProductInvoice(product,100, Destination.STOCK);
        assertEquals(product, productInvoice.getProduct());
        assertEquals(100, productInvoice.getQuantity());
        assertEquals(Destination.STOCK, productInvoice.getDestination());
    }
    @Test
    void cannotCreateProductInvoiceInvalidQuantity(){
        Product product = new Product("1", "Parafuso 1/2");
        assertThrows(InvalidQuantityException.class, () ->
                new ProductInvoice(product,0,Destination.STOCK));
        assertThrows(InvalidQuantityException.class, () ->
                new ProductInvoice(product,-50,Destination.STOCK));
    }

    @Test
    void cannotCreateProductInvoiceNullValues(){
        Product product = new Product("1", "Parafuso 1/2");
        assertThrows(NullPointerException.class, () ->
                new ProductInvoice(null,100,Destination.STOCK));
        assertThrows(NullPointerException.class, () ->
                new ProductInvoice(product,100,null));
    }
    }



