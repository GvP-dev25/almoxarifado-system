package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.CodeNotFoundException;
import br.com.almoxarifado.exception.InvalidQuantityException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Request {
    private String numberRequest;
    private Branch branch;
    private List<ProductRequest> productRequestList;
    private List<ProductRequest> productRequestView;


    public Request(String numberRequest, Branch branch) {
        this.numberRequest = numberRequest;
        this.branch = branch;
        productRequestList = new ArrayList<>();
        productRequestView = Collections.unmodifiableList(productRequestList);
    }

    public void addProductRequest(BranchProduct product, int requestedQuantity) {
        ProductRequest findProductRequest = findProductRequest(product.getProduct().getCode());
        if (requestedQuantity <= 0) {
            throw new InvalidQuantityException();
        }
        if (findProductRequest != null) {
            findProductRequest.addRequestQuantity(requestedQuantity);
            return;
        }
        ProductRequest newProductRequest = new ProductRequest(product, requestedQuantity);
        productRequestList.add(newProductRequest);
    }

    public void attendedProduct(String code, int attendedQuantity) {
        ProductRequest findProductRequest = findProductRequest(code);
        if (findProductRequest != null) {
            findProductRequest.attendedQuantity(attendedQuantity, OriginType.REQUEST, numberRequest);
            return;
        }
        throw new CodeNotFoundException();
    }

    public void reversalProduct(String code) {
        ProductRequest findProductRequest = findProductRequest(code);
        if (findProductRequest != null) {
            findProductRequest.reversal(OriginType.REQUEST, numberRequest);
            return;
        }
        throw new CodeNotFoundException();
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
