package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.InvalidQuantityException;
import br.com.almoxarifado.exception.InvoiceAlreadyProcessedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    void createInvoice() {
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        assertEquals("123", invoice.getNumberInvoice());
        assertEquals("Branch South", invoice.getBranchDestination().getName());
        assertNotNull(invoice.getDate());
        assertEquals(0, invoice.getProductInvoiceView().size());
        assertFalse(invoice.isProcessed());
    }

    @Test
    void addProductInvoice() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        invoice.addProductInvoice(newProduct, 100, Destination.STOCK);
        assertEquals(100, invoice.getProductInvoiceView().get(0).getQuantity());
        assertEquals("1", invoice.getProductInvoiceView().get(0).getProduct().getCode());
        assertEquals(Destination.STOCK, invoice.getProductInvoiceView().get(0).getDestination());
    }

    @Test
    void addProductInvoiceWithZeroQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        assertThrows(InvalidQuantityException.class, () ->
                invoice.addProductInvoice(newProduct, 0, Destination.STOCK));
        assertEquals(0, invoice.getProductInvoiceView().size());
    }

    @Test
    void addProductInvoiceWithNegativeQuantity() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        assertThrows(InvalidQuantityException.class, () ->
                invoice.addProductInvoice(newProduct, -50, Destination.STOCK));
        assertEquals(0, invoice.getProductInvoiceView().size());
    }

    @Test
    void processInvoiceToStock() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        invoice.addProductInvoice(newProduct, 100, Destination.STOCK);
        invoice.processInvoice();
        BranchProduct branchProduct = branchSouth.findBranchProduct("1");
        assertTrue(invoice.isProcessed());
        assertNotNull(branchProduct);
        assertEquals(100, branchProduct.getQuantity());
        assertEquals(1, branchProduct.getMovementList().size());
        assertEquals(OriginType.INITIALSTOCK, branchProduct.getMovementList().get(0).getOriginType());
    }

    @Test
    void processInvoiceWithDirectDestination() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        invoice.addProductInvoice(newProduct, 100, Destination.DIRECT);
        invoice.processInvoice();
        assertTrue(invoice.isProcessed());
        assertNull(branchSouth.findBranchProduct("1"));
    }

    @Test
    void processInvoiceWithRepeatedProduct() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchSouth.addProduct(branchProduct);
        Invoice invoice = new Invoice("123", branchSouth);
        assertEquals(1, branchProduct.getMovementList().size());
        invoice.addProductInvoice(newProduct, 100, Destination.STOCK);
        invoice.addProductInvoice(newProduct, 100, Destination.STOCK);
        invoice.addProductInvoice(newProduct, 100, Destination.STOCK);
        invoice.processInvoice();
        assertEquals(400, branchProduct.getQuantity());
        assertEquals(4, branchProduct.getMovementList().size());
        assertEquals(OriginType.INVOICE, branchProduct.getMovementList().get(1).getOriginType());
        assertEquals("123", branchProduct.getMovementList().get(1).getOriginNumber());
        assertEquals(OriginType.INVOICE, branchProduct.getMovementList().get(2).getOriginType());
        assertEquals("123", branchProduct.getMovementList().get(2).getOriginNumber());
        assertEquals(OriginType.INVOICE, branchProduct.getMovementList().get(3).getOriginType());
        assertEquals("123", branchProduct.getMovementList().get(3).getOriginNumber());
    }

    @Test
    void processInvoiceException() {
        Product newProduct = new Product("1", "Parafuso 1/2 x 1");
        Branch branchSouth = new Branch("001", "Branch South");
        BranchProduct branchProduct = new BranchProduct(newProduct, branchSouth, 100);
        branchSouth.addProduct(branchProduct);
        Invoice invoice = new Invoice("123", branchSouth);
        assertEquals(1, branchProduct.getMovementList().size());
        invoice.addProductInvoice(newProduct, 100, Destination.STOCK);
        invoice.processInvoice();
        assertEquals(200, branchProduct.getQuantity());
        assertTrue(invoice.isProcessed());
        assertThrows(InvoiceAlreadyProcessedException.class, () ->
                invoice.processInvoice());
        assertEquals(200, branchProduct.getQuantity());
        assertEquals(2, branchProduct.getMovementList().size());

    }


}
