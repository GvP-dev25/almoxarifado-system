package br.com.almoxarifado.model;

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

    public Invoice(String numberInvoice, Branch branchDestination) {
        this.numberInvoice = numberInvoice;
        this.branchDestination = branchDestination;
        date = LocalDateTime.now();
        productInvoiceList = new ArrayList<>();
        productInvoiceView = Collections.unmodifiableList(productInvoiceList);
        processed = false;
    }

    public boolean addProductInvoice(Product product, int quantity, Destination destination) {
        if (quantity <= 0) {
            return false;
        }
        ProductInvoice newProductInvoice = new ProductInvoice(product, quantity, destination);
        productInvoiceList.add(newProductInvoice);
        return true;
    }

    public void processInvoice() {
        if (!processed) {
            for (int i = 0; i < productInvoiceList.size(); i++) {
                Destination destination = productInvoiceList.get(i).getDestination();
                if (destination == Destination.STOCK) {
                    BranchProduct branchProduct = branchDestination.findBranchProduct(productInvoiceList.get(i).getProduct().getCode());
                    if (branchProduct != null) {
                        branchProduct.addQuantity(productInvoiceList.get(i).getQuantity(), OriginType.INVOICE, numberInvoice);
                    } else {
                        BranchProduct newBranchProduct = new BranchProduct(productInvoiceList.get(i).getProduct(), branchDestination, productInvoiceList.get(i).getQuantity());
                        branchDestination.addProduct(newBranchProduct);
                    }
                }
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


    public List<ProductInvoice> getProductInvoicView() {
        return productInvoiceView;
    }
}
