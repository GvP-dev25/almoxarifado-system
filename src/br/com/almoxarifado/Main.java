package br.com.almoxarifado;

import br.com.almoxarifado.model.Branch;
import br.com.almoxarifado.model.BranchProduct;
import br.com.almoxarifado.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Product newProduct = new Product("1", "Parafuso 1/2 x 1");


        Product newProduct2 = new Product("2", "Parafuso 1/2 x 2");

        Product newProdcut3 = new Product("3", "Parafuso 1/2 x 3");

        Product newProduct4 = new Product("4", "Parafuso 1/2 x 4");

        Branch branchSouth = new Branch("001", "Sul");

        BranchProduct product1 = new BranchProduct(newProduct, branchSouth, 10);
        BranchProduct product2 = new BranchProduct(newProduct2, branchSouth, 300);
        BranchProduct product3 = new BranchProduct(newProdcut3, branchSouth, 150);

        branchSouth.addProduct(product1);
        branchSouth.addProduct(product2);
        branchSouth.addProduct(product3);

        branchSouth.productReceipt(newProduct, 20);
        branchSouth.productReceipt(newProduct4, -30);

        BranchProduct newP = new BranchProduct();
        newP = branchSouth.findBranchProduct("1");
        System.out.println(newP.getProduct().getCode() + ": " + newP.getProduct().getDescription() + " - " + newP.getQuantity());
        newP = branchSouth.findBranchProduct("4");
        System.out.println(newP.getProduct().getCode() + ": " + newP.getProduct().getDescription() + " - " + newP.getQuantity());

        System.out.println();



        /*

        String message = "";
        List<BranchProduct> products = branchSouth.getProducts();

        product1.setLocation("C003.P023.A.D01");
        product2.setLocation("C003.P023.B.D01");
        product3.setLocation("C003.P023.C.D01");


        for (BranchProduct prod : products) {
            message += "Code: " + prod.getProduct().getCode() + "\nDescription: "
                    + prod.getProduct().getDescription() + "\nBranch: " + prod.getBranch().getCode() + "\nName: "
                    + prod.getBranch().getName() + "\nQuantity: " + prod.getQuantity() + "\nLocation: "
                    + prod.getLocation() + "\n -----------------------------------------";
        }


        System.out.println(message);

        BranchProduct search = new BranchProduct();

        search = branchSouth.findBranchProduct("10");

        if (search != null) {
            System.out.println("Branch: " + search.getBranch().getName() + "\n Code: " + search.getProduct().getCode() +
                    "\nDescription: " + search.getProduct().getDescription());
        } else {
            System.out.println("Produto não encontrado!");
        }

         */
    }


}