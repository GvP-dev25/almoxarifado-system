package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.InvalidQuantityException;
import br.com.almoxarifado.exception.InsufficientStockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BranchProductTest {
    @Test
    void addQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.addQuantity(50, OriginType.INITIALSTOCK, "123");
        assertEquals(150, branchProduct.getQuantity());
    }

    @Test
    void addQuantityException() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        assertThrows(InvalidQuantityException.class, () -> {
            branchProduct.addQuantity(0, OriginType.INITIALSTOCK, "123");
        });
    }

    @Test
    void addQuantityNegative() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        assertThrows(InvalidQuantityException.class, () -> {
            branchProduct.addQuantity(-50, OriginType.INITIALSTOCK, "123");
        });
    }


    @Test
    void removeQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.removeQuantity(70, OriginType.INITIALSTOCK, "123");
        assertEquals(30, branchProduct.getQuantity());
    }

    @Test
    void removeZeroQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        assertThrows(InvalidQuantityException.class, () -> {
            branchProduct.removeQuantity(0, OriginType.INITIALSTOCK, "123");
        });
    }

    @Test
    void removeQuantityExceedsStock() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        assertThrows(InsufficientStockException.class, () -> {
            branchProduct.removeQuantity(150, OriginType.INITIALSTOCK, "123");
        });
    }

    @Test
    void movementListSize() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.addQuantity(50, OriginType.INITIALSTOCK, "123");
        assertEquals(2, branchProduct.getMovementList().size());
        assertEquals(100, branchProduct.getMovementList().get(0).getQuantity());
        assertEquals(50, branchProduct.getMovementList().get(1).getQuantity());
    }

    @Test
    void movimentsResults() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.addQuantity(50, OriginType.INITIALSTOCK, "123");
        assertEquals(OriginType.INITIALSTOCK, branchProduct.getMovementList().get(1).getOriginType());
        assertEquals("123", branchProduct.getMovementList().get(1).getOriginNumber());
    }

    @Test
    void removeQuantityMoviments() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.removeQuantity(50, OriginType.REQUEST, "123");
        assertEquals(OriginType.REQUEST, branchProduct.getMovementList().get(1).getOriginType());
        assertEquals("123", branchProduct.getMovementList().get(1).getOriginNumber());
        assertEquals(MovementType.OUTPUT, branchProduct.getMovementList().get(1).getMovementType());
        assertEquals(50, branchProduct.getMovementList().get(1).getQuantity());
    }

    @Test
    void reversalQuantityNegative() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.addQuantity(100, OriginType.INITIALSTOCK, "123");
        assertThrows(InvalidQuantityException.class, () -> {
            branchProduct.removeQuantityReversal(-10, OriginType.REQUEST, "123");
        });
    }

    @Test
    void reversalQuantityRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchProduct.removeQuantity(100, OriginType.REQUEST, "123");
        branchProduct.removeQuantityReversal(100,OriginType.REQUEST,"123");
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(OriginType.REQUEST, branchProduct.getMovementList().get(2).getOriginType());
        assertEquals("123", branchProduct.getMovementList().get(2).getOriginNumber());
        assertEquals(MovementType.REVERSAL, branchProduct.getMovementList().get(2).getMovementType());
        assertEquals(100, branchProduct.getMovementList().get(2).getQuantity());
    }


}
