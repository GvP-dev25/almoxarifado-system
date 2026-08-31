package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductRequestTest {
    @Test
    void creatRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 100);
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 100);

        assertEquals(100, productRequest.getRequestedQuantity());
        assertEquals(0, productRequest.getAttendedQuantity());
        assertEquals(false, productRequest.isProcessed());
        assertEquals(false, productRequest.isReversed());
    }

    @Test
    void attendRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 100);
        productRequest.attendedQuantity(50, OriginType.REQUEST, "155");
        assertEquals(50, branchProduct.getQuantity());
        assertEquals(50, productRequest.getAttendedQuantity());
        assertEquals(true, productRequest.isProcessed());
        assertEquals(false, productRequest.isReversed());
    }

    @Test
    void addQuantityZero() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 100);
        productRequest.attendedQuantity(0, OriginType.REQUEST, "155");
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(0, productRequest.getAttendedQuantity());
        assertEquals(true, productRequest.isProcessed());
        assertEquals(false, productRequest.isReversed());
    }

    @Test
    void addQuantitySmall() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        assertThrows(InvalidQuantityException.class, () ->
                productRequest.attendedQuantity(-50, OriginType.REQUEST, "155"));
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(80, productRequest.getRequestedQuantity());
        assertEquals(false, productRequest.isProcessed());
    }

    @Test
    void addQuantityLarger() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        assertThrows(RequestedQuantityExceededException.class, () ->
                productRequest.attendedQuantity(150, OriginType.REQUEST, "155"));
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(0, productRequest.getAttendedQuantity());
        assertEquals(false, productRequest.isProcessed());
    }

    @Test
    void fulfillQuantityDuplicate() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        productRequest.attendedQuantity(50, OriginType.REQUEST, "155");
        assertThrows(ProductRequestFulFilledException.class, () ->
                productRequest.attendedQuantity(50, OriginType.REQUEST, "155"));
        assertEquals(50, branchProduct.getQuantity());
        assertEquals(50, productRequest.getAttendedQuantity());
        assertEquals(true, productRequest.isProcessed());
    }

    @Test
    void reversal() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        productRequest.attendedQuantity(50, OriginType.REQUEST, "155");
        productRequest.reversal(OriginType.REQUEST, "155");
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(50, productRequest.getAttendedQuantity());
        assertEquals(true, productRequest.isProcessed());
        assertEquals(true, productRequest.isReversed());
    }

    @Test
    void reversalMovementNotFound() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        productRequest.attendedQuantity(50, OriginType.REQUEST, "155");
        assertThrows(MovementNotFoundException.class, () ->
                productRequest.reversal(OriginType.REQUEST, "299"));
    }

    @Test
    void reversalDuplicated() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        productRequest.attendedQuantity(50, OriginType.REQUEST, "155");
        productRequest.reversal(OriginType.REQUEST, "155");
        assertThrows(ProductRequestAlreadyRevertedException.class, () ->
                productRequest.reversal(OriginType.REQUEST, "155"));
    }

    @Test
    void reversalZero() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        productRequest.attendedQuantity(0, OriginType.REQUEST, "155");
        assertThrows(NoReversionException.class, () ->
                productRequest.reversal(OriginType.REQUEST, "155"));

    }
    @Test
    void reversalNotAttended() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        ProductRequest productRequest = new ProductRequest(branchProduct, 80);
        Request request = new Request("155", branchSouth);
        assertThrows(NoReversionException.class, () ->
                productRequest.reversal(OriginType.REQUEST, "155"));

    }


}
