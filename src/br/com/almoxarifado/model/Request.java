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
        ProductRequest findProductRequest = findProductRequest(product.getProduct().getCode());
        if (requestedQuantity <= 0) {
            return false;
        }
        if (findProductRequest != null) {
            findProductRequest.addRequestQuantity(requestedQuantity);
            return true;
        }
        ProductRequest newProductRequest = new ProductRequest(product, requestedQuantity);
        productRequestList.add(newProductRequest);
        return true;
    }

    public boolean attendedProduct(String code, int attendedQuantity) {
        ProductRequest findProductRequest = findProductRequest(code);
        if (findProductRequest != null) {
            boolean result = findProductRequest.attendedQuantity(attendedQuantity, OriginType.REQUEST, numberRequest);
            return result;
        }
        return false;
    }

    public boolean reversalProduct(String code) {
        ProductRequest findProductRequest = findProductRequest(code);
        if (findProductRequest != null) {
            boolean result = findProductRequest.reversal(OriginType.REQUEST, numberRequest);
            return result;
        }
        return false;
    }

    public ProductRequest findProductRequest(String code) {
        for (int i = 0; i < productRequestList.size(); i++) {
            if (productRequestList.get(i).getBranchProduct().getProduct().getCode().equals(code)) {
                return productRequestList.get(i);
            }
        }
        return null;
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
