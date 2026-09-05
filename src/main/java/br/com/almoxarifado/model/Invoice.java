package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.CannotProcessInvoiceWithoutProductsException;
import br.com.almoxarifado.exception.InvoiceAlreadyProcessedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Invoice {
    private String numberInvoice;
    private LocalDateTime date;
    private boolean processed;
    private Branch branchDestination;
    private List<ProductInvoice> productInvoiceList;
    private List<ProductInvoice> productInvoiceView;

    public boolean isProcessed() {
        return processed;
    }

    public Branch getBranchDestination() {
        return branchDestination;
    }

    public Invoice(String numberInvoice, Branch branchDestination) {
        this.numberInvoice = numberInvoice;
        this.branchDestination = branchDestination;
        date = LocalDateTime.now();
        productInvoiceList = new ArrayList<>();
        productInvoiceView = Collections.unmodifiableList(productInvoiceList);
        processed = false;
    }

    public void addProductInvoice(Product product, int quantity, Destination destination) {
        if(processed){
            throw new InvoiceAlreadyProcessedException();
        }
        ProductInvoice newProductInvoice = new ProductInvoice(product, quantity, destination);
        productInvoiceList.add(newProductInvoice);
    }

    public void processInvoice() {
        if(productInvoiceList.isEmpty()){
            throw new CannotProcessInvoiceWithoutProductsException();
        }
        if (processed) {
            throw new InvoiceAlreadyProcessedException();
        }
        for (int i = 0; i < productInvoiceList.size(); i++) {
            Destination destination = productInvoiceList.get(i).getDestination();
            if (destination == Destination.STOCK) {
                ProductInvoice productInvoice = productInvoiceList.get(i);
                branchDestination.receiveProduct(productInvoice.getProduct(), productInvoice.getQuantity(), OriginType.INVOICE, this.numberInvoice);
            }
        }
        processed = true;
    }


    public String getNumberInvoice() {
        return numberInvoice;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public List<ProductInvoice> getProductInvoiceView() {
        return productInvoiceView;
    }
}
