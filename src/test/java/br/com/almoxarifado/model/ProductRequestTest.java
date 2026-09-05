package br.com.almoxarifado.model;

import main.java.br.com.almoxarifado.exception.*;
import main.java.br.com.almoxarifado.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRequestTest {
    @Test
    void creatRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        Request request = new Request("155", branchSouth);
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        branchSouth.addProduct(branchProduct);
        request.addProductRequest(newProduct, 100);
        ProductRequest productRequest = request.findProductRequest("1");
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
        branchSouth.addProduct(branchProduct);        Request request = new Request("155", branchSouth);
        request.addProductRequest(newProduct, 100);
        ProductRequest productRequest = request.findProductRequest("1");
        productRequest.attendedQuantity(50);
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
        branchSouth.addProduct(branchProduct);
        Request request = new Request("155", branchSouth);
        request.addProductRequest(newProduct, 100);
        ProductRequest productRequest = request.findProductRequest("1");
        productRequest.attendedQuantity(0);
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(0, productRequest.getAttendedQuantity());
        assertEquals(true, productRequest.isProcessed());
        assertEquals(false, productRequest.isReversed());
    }

    @Test
    void cannotAttendNegativeQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        assertThrows(InvalidQuantityException.class, () ->
                productRequest.attendedQuantity(-50));
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(80, productRequest.getRequestedQuantity());
        assertEquals(false, productRequest.isProcessed());
    }

    @Test
    void cannotExceedRequestedQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        assertThrows(RequestedQuantityExceededException.class, () ->
                productRequest.attendedQuantity(150));
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(0, productRequest.getAttendedQuantity());
        assertEquals(false, productRequest.isProcessed());
    }

    @Test
    void cannotAttendAlreadyProcessedRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        productRequest.attendedQuantity(50);
        assertThrows(ProductRequestAlreadyProcessedException.class, () ->
                productRequest.attendedQuantity(50));
        assertEquals(50, branchProduct.getQuantity());
        assertEquals(50, productRequest.getAttendedQuantity());
        assertEquals(true, productRequest.isProcessed());
    }

    @Test
    void reversal() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        productRequest.attendedQuantity(50);
        productRequest.reversal();
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
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        productRequest.attendedQuantity(50);
    }

    @Test
    void reversalDuplicated() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        productRequest.attendedQuantity(50);
        productRequest.reversal();
        assertThrows(ProductRequestAlreadyRevertedException.class, () ->
                productRequest.reversal());
    }

    @Test
    void reversalZero() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        productRequest.attendedQuantity(0);
        assertThrows(NoReversionException.class, () ->
                productRequest.reversal());

    }

    @Test
    void reversalNotAttended() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        assertThrows(NoReversionException.class, () ->
                productRequest.reversal());
    }

    @Test
    void cannotAddRequestedQuantityAfterProcessed() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        ProductRequest productRequest = new ProductRequest(request, branchProduct, 80);
        productRequest.attendedQuantity(50);
        assertTrue(productRequest.isProcessed());
        assertThrows(ProductRequestAlreadyProcessedException.class, () ->
                productRequest.addRequestQuantity(200));
        assertEquals(80, productRequest.getRequestedQuantity());
    }

    @Test
    void cannotCreateProductRequestWithInvalidQuantity(){
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        assertThrows(InvalidQuantityException.class, () ->
                new ProductRequest(request, branchProduct, 0));
        assertThrows(InvalidQuantityException.class, () ->
                new ProductRequest(request, branchProduct, -20));
    }


}
