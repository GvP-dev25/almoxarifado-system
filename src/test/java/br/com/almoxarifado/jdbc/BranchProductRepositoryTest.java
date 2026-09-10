package br.com.almoxarifado.jdbc;

import br.com.almoxarifado.exception.BranchProductNotFoundException;
import br.com.almoxarifado.model.BranchProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BranchProductRepositoryTest {

    private BranchProductRepository branchProductRepository;

    @BeforeEach
    void setUp() {
        branchProductRepository = new BranchProductRepository();
    }

    @Test
    void shouldReturnProductWithAllMovements() {

        BranchProduct branchProduct = branchProductRepository.findBranchProduct(2);

        assertNotNull(branchProduct);
        assertNotNull(branchProduct.getMovementList());
        assertEquals(2, branchProduct.getId());
        assertEquals(2, branchProduct.getMovementList().size());
    }


    @Test
    void shouldReturnProductWithEmptyMovementListWhenNoMovementsExist() {

        BranchProduct branchProduct = branchProductRepository.findBranchProduct(3);

        assertNotNull(branchProduct);
        assertNotNull(branchProduct.getMovementList());
        assertTrue(branchProduct.getMovementList().isEmpty());
    }


    @Test
    void shouldThrowExceptionWhenProductIsNotFound() {

        assertThrows(BranchProductNotFoundException.class, () ->
                branchProductRepository.findBranchProduct(999));
    }

}
