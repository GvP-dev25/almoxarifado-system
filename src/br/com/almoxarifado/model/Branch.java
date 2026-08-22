package br.com.almoxarifado.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Branch {
    private String code, name;
    private List<BranchProduct> products;
    private List<BranchProduct> productsView;

    public Branch(String code, String name) {
        this.code = code;
        this.name = name;
        products = new ArrayList<>();
        productsView = Collections.unmodifiableList(products);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<BranchProduct> getProducts() {
        return productsView;
    }

    public boolean addProduct(BranchProduct product) {
        BranchProduct search;
        search = findBranchProduct(product.getProduct().getCode());
        if (search == null) {
            products.add(product);
            return true;
        }
        return false;
    }

    public BranchProduct findBranchProduct(String code) {
        for (BranchProduct searchProducts : this.productsView) {
            if (searchProducts.getProduct().getCode().equals(code)) {
                return searchProducts;
            }
        }

        return null;
    }


}
