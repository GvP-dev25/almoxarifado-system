package br.com.almoxarifado.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void createProduct() {
        Product product = new Product("1", "Parafuso 1/2");
        assertEquals("1", product.getCode());
        assertEquals("Parafuso 1/2", product.getDescription());
    }

    @Test
    void setDescription() {
        Product product = new Product("1", "Parafuso 1/2");
        assertEquals("Parafuso 1/2", product.getDescription());
        product.setDescription("Parafuso 1/2 x 2");
        assertEquals("Parafuso 1/2 x 2", product.getDescription());
    }



}
