package br.com.almoxarifado.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BranchTest {

    @Test
    void createBranch() {
        Branch branchSouth = new Branch("001", "Branch South");
        assertEquals("001", branchSouth.getCode());
        assertEquals("Branch South", branchSouth.getName());
        assertEquals(0, branchSouth.getProducts().size());
    }

    @Test
    void addBranchProduct() {
        Branch branchSouth = new Branch("001", "Branch South");
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        branchSouth.addProduct(branchProduct);
        assertEquals(branchProduct, branchSouth.getProducts().get(0));
        assertEquals(branchProduct, branchSouth.findBranchProduct("1"));
        assertEquals(1, branchSouth.getProducts().size());
    }
    @Test
    void findProductNotFound() {
        Branch branchSouth = new Branch("001", "Branch South");
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        branchSouth.addProduct(branchProduct);
        assertEquals(null, branchSouth.findBranchProduct("001"));
        assertEquals(1, branchSouth.getProducts().size());
    }

    @Test
    void addProductRepeat() {
        Branch branchSouth = new Branch("001", "Branch South");
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        branchSouth.addProduct(branchProduct);
        assertFalse(branchSouth.addProduct(branchProduct));
        assertEquals(1, branchSouth.getProducts().size());
    }

    @Test
    void listUnmodifiable() {
        Branch branchSouth = new Branch("001", "Branch South");
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        branchSouth.addProduct(branchProduct);
        assertThrows(UnsupportedOperationException.class, ()->
                branchSouth.getProducts().clear());
        assertEquals(1, branchSouth.getProducts().size());
    }



}
