package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.CodeNotFoundException;
import br.com.almoxarifado.exception.InvalidQuantityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {


    @Test
    void createRequest() {
        Branch branchSouth = new Branch("001", "Branch South");
        Request request = new Request("155", branchSouth);
        assertEquals("155", request.getNumberRequest());
        assertEquals("Branch South", request.getBranch().getName());
        assertEquals(0, request.getProductRequestList().size());
    }

    @Test
    void addProductRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 100);
        assertEquals(1, request.getProductRequestList().size());
        assertEquals(100, request.getProductRequestList().get(0).getRequestedQuantity());
        assertEquals(branchProduct, request.getProductRequestList().get(0).getBranchProduct());
    }

    @Test
    void addRepeatProduct() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 100);
        request.addProductRequest(branchProduct, 50);
        assertEquals(1, request.getProductRequestList().size());
        assertEquals(150, request.getProductRequestList().get(0).getRequestedQuantity());
    }


    @Test
    void addQuantityZero() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        assertThrows(InvalidQuantityException.class, () ->
                request.addProductRequest(branchProduct, 0));

        assertEquals(0, request.getProductRequestList().size());
    }

    @Test
    void addInvalidQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        assertThrows(InvalidQuantityException.class, () ->
                request.addProductRequest(branchProduct, -50));
        assertEquals(0, request.getProductRequestList().size());
    }

    @Test
    void findProductRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Product newProduct2 = new Product("2", "Parafuso 1/2 x 2");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        BranchProduct branchProduct2 = new BranchProduct(newProduct2, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 100);
        request.addProductRequest(branchProduct2, 100);
        assertEquals(request.getProductRequestList().get(0), request.findProductRequest("1"));
        assertNull(request.findProductRequest("999"));
    }

    @Test
    void attendedProductRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 80);
        request.attendedProduct("1", 50);
        assertEquals(50, request.findProductRequest("1").getBranchProduct().getQuantity());
        assertEquals(80, request.findProductRequest("1").getRequestedQuantity());
        assertEquals(50, request.findProductRequest("1").getAttendedQuantity());
        assertTrue(request.findProductRequest("1").isProcessed());
        assertFalse(request.findProductRequest("1").isReversed());
    }

    @Test
    void codeNotFound() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 80);
        assertThrows(CodeNotFoundException.class, () ->
                request.attendedProduct("999", 50));
        assertEquals(100, request.findProductRequest("1").getBranchProduct().getQuantity());
        assertEquals(0, request.findProductRequest("1").getAttendedQuantity());
        assertEquals(false, request.findProductRequest("1").isProcessed());
    }

    @Test
    void reversalProductRequest() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 80);
        request.attendedProduct("1", 50);
        request.reversalProduct("1");
        assertEquals(100, request.findProductRequest("1").getBranchProduct().getQuantity());
        assertEquals(80, request.findProductRequest("1").getRequestedQuantity());
        assertEquals(50, request.findProductRequest("1").getAttendedQuantity());
        assertEquals(true, request.findProductRequest("1").isProcessed());
        assertEquals(true, request.findProductRequest("1").isReversed());
    }

    @Test
    void reversalProductRequestCodeNotFound() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100, OriginType.INVOICE, "1234");
        Request request = new Request("155", branchSouth);
        request.addProductRequest(branchProduct, 80);
        request.attendedProduct("1", 50);
        assertThrows(CodeNotFoundException.class, () ->
                request.reversalProduct("999"));
        assertEquals(50, request.findProductRequest("1").getBranchProduct().getQuantity());
        assertEquals(80, request.findProductRequest("1").getRequestedQuantity());
        assertEquals(50, request.findProductRequest("1").getAttendedQuantity());
        assertEquals(true, request.findProductRequest("1").isProcessed());
        assertEquals(false, request.findProductRequest("1").isReversed());
    }

}
