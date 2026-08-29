package br.com.almoxarifado.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseFlowTest {

    @Test
    void invoiceToStockThenRequestAttendance() {
        Branch branchSouth = new Branch("001", "Branch South");
        Invoice invoice = new Invoice("123", branchSouth);
        Product product1 = new Product("1", "Parafuso 1/2 x 1");
        Product product2 = new Product("2", "Parafuso 1/2 x 2");
        invoice.addProductInvoice(product1, 100, Destination.STOCK);
        invoice.addProductInvoice(product2, 100, Destination.STOCK);
        invoice.processInvoice();
        assertEquals(100, invoice.getProductInvoiceView().get(0).getQuantity());
        assertEquals(100, invoice.getProductInvoiceView().get(1).getQuantity());
        assertEquals(2, invoice.getProductInvoiceView().size());
        assertTrue(invoice.isProcessed());
        Request request = new Request("123", branchSouth);
        request.addProductRequest(branchSouth.findBranchProduct("1"), 120);
        request.addProductRequest(branchSouth.findBranchProduct("2"), 120);
        request.attendedProduct("1", 100);
        request.attendedProduct("2", 100);
        assertEquals(2, request.getProductRequestList().size());
        assertEquals(120, request.getProductRequestList().get(0).getRequestedQuantity());
        assertEquals(branchSouth.findBranchProduct("1"), request.getProductRequestList().get(0).getBranchProduct());
        assertEquals(120, request.getProductRequestList().get(1).getRequestedQuantity());
        assertEquals(branchSouth.findBranchProduct("2"), request.getProductRequestList().get(1).getBranchProduct());
        assertEquals(0, request.getProductRequestList().get(0).getBranchProduct().getQuantity());
        assertEquals(0, request.getProductRequestList().get(1).getBranchProduct().getQuantity());
        assertEquals(100, request.getProductRequestList().get(0).getAttendedQuantity());
        assertEquals(100, request.getProductRequestList().get(1).getAttendedQuantity());
        assertTrue(request.getProductRequestList().get(0).isProcessed());
        assertTrue(request.getProductRequestList().get(1).isProcessed());
        assertFalse(request.getProductRequestList().get(0).isReversed());
        assertFalse(request.getProductRequestList().get(1).isReversed());
    }

}