package br.com.almoxarifado.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Request {
    private String numberRequest;
    private Branch branch;
    private ProductRequest productRequest;
    private List<ProductRequest> productRequestList;
    private List<ProductRequest> productRequestView;


    public Request(String numberRequest, Branch branch) {
        this.numberRequest = numberRequest;
        this.branch = branch;
        productRequestList = new ArrayList<>();
        productRequestView = Collections.unmodifiableList(productRequestList);
    }

    public boolean addProductRequest(BranchProduct product, int requestedQuantity) {
        if (requestedQuantity <= 0) {
            return false;
        }
        for (int i = 0; i < productRequestList.size(); i++) {
            if (productRequestList.get(i).getBranchProduct().getProduct().getCode().equals(product.getProduct().getCode())) {
                productRequestList.get(i).addRequestQuantity(requestedQuantity);
                return true;
            }
        }
        ProductRequest newProductRequest = new ProductRequest(product, requestedQuantity);
        productRequestList.add(newProductRequest);
        return true;
    }

    public boolean attendedProduct(String code, int attendedQuantity) {
        for (int i = 0; i < productRequestList.size(); i++) {
            if (productRequestList.get(i).getBranchProduct().getProduct().getCode().equals(code)) {
                productRequestList.get(i).attendedQuantity(attendedQuantity);
                return true;
            }
        }
        return false;
    }


    public String getNumberRequest() {
        return numberRequest;
    }

    public Branch getBranch() {
        return branch;
    }

    public List<ProductRequest> getProductRequestList() {
        return productRequestView;
    }
}
