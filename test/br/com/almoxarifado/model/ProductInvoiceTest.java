package br.com.almoxarifado.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductInvoiceTest {
    @Test
    void createProductInvoice() {
        Product product = new Product("1", "Parafuso 1/2");
        ProductInvoice productInvoice = new ProductInvoice(product,100,Destination.STOCK);
        assertEquals(product, productInvoice.getProduct());
        assertEquals(100, productInvoice.getQuantity());
        assertEquals(Destination.STOCK, productInvoice.getDestination());
    }
}
