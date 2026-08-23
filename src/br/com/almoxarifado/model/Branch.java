package br.com.almoxarifado.model;

import java.util.*;

public class Branch {
    private String code, name;
    private final Map<String, BranchProduct> products;

    public Branch(String code, String name) {
        this.code = code;
        this.name = name;
        products = new HashMap<>();

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<BranchProduct> getProducts() {
        return products.values().stream().toList();
    }

    public boolean addProduct(BranchProduct product) {
        boolean search;
        String chave = product.getProduct().getCode();
        search = products.containsKey(chave);
        if (!search) {
            products.put(chave, product);
            return true;
        }
        return false;
    }


    public boolean productReceipt(Product product, int quantity) {
        boolean search;
        String chave = product.getCode();
        search = products.containsKey(chave);
        if (!search) {
            if (quantity <= 0) {
                return false;
            }
            BranchProduct newProduct = new BranchProduct(product, this, quantity);
            products.put(chave, newProduct);
            return true;

        } else {
            BranchProduct existingProduct = findBranchProduct(chave);
          boolean result =  existingProduct.addQuantity(quantity);
            return result;
        }
    }

    public BranchProduct findBranchProduct(String code) {
        return products.get(code);
    }


}
