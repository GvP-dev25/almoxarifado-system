package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.InvalidProductIdException;
import br.com.almoxarifado.exception.InvalidQuantityException;
import br.com.almoxarifado.exception.ProductAlreadyExists;
import br.com.almoxarifado.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void createProduct() {
        Product product = new Product("1", "Parafuso 1/2");
        assertEquals("1", product.getCode());
        assertEquals("Parafuso 1/2", product.getDescription());
        assertEquals(0, product.getId());
    }

    @Test
    void setDescription() {
        Product product = new Product("1", "Parafuso 1/2");
        assertEquals("Parafuso 1/2", product.getDescription());
        product.setDescription("Parafuso 1/2 x 2");
        assertEquals("Parafuso 1/2 x 2", product.getDescription());
    }


    @Test
    void assignIdProductAlreadyExists() {
        Product product = new Product("1", "Parafuso 1/2");
        assertEquals(0, product.getId());
        product.assignId(28);
        assertEquals(28, product.getId());
        assertThrows(ProductAlreadyExists.class, () ->
                product.assignId(28));
    }


    @Test
    void assignIdWithInvalidId(){
        Product product = new Product("1", "Parafuso 1/2");
        assertThrows(InvalidProductIdException.class, () ->
                product.assignId(0));
        assertThrows(InvalidProductIdException.class, () ->
                product.assignId(-10));
    }

}
