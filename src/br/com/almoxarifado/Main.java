package br.com.almoxarifado;

import br.com.almoxarifado.model.*;

public class Main {

    public static void main(String[] args) {

        Product newProduct = new Product("1", "Parafuso 1/2 x 1");


        Product newProduct2 = new Product("2", "Parafuso 1/2 x 2");

        Product newproduct3 = new Product("3", "Parafuso 1/2 x 3");

        Product newProduct4 = new Product("4", "Parafuso 1/2 x 4");

        Branch branchSouth = new Branch("001", "Sul");

        BranchProduct product1 = new BranchProduct(newProduct, branchSouth, 100);
        BranchProduct product2 = new BranchProduct(newProduct2, branchSouth, 100);
        BranchProduct product3 = new BranchProduct(newproduct3, branchSouth, 100);
        BranchProduct product4 = new BranchProduct(newProduct4, branchSouth, 100);

        branchSouth.addProduct(product1);
        branchSouth.addProduct(product2);
        branchSouth.addProduct(product3);


        Request request = new Request("123", branchSouth);
        request.addProductRequest(product1, 50);
        request.attendedProduct("1", 50);

        request.addProductRequest(product2, 100);
        request.attendedProduct("2", 60);

        request.addProductRequest(product3, 100);
        request.attendedProduct("3", 100);

        request.addProductRequest(product4, 100);
        request.attendedProduct("4", 80);


        request.reversalProduct("1");

        Invoice invoice1 = new Invoice("123", branchSouth);
        invoice1.addProductInvoice(newProduct, 500, Destination.STOCK);
        invoice1.addProductInvoice(newProduct2, 200, Destination.STOCK);
        invoice1.addProductInvoice(newproduct3, 100, Destination.DIRECT);
        invoice1.processInvoice();


        System.out.println("Saldo final: " + product1.getQuantity());

        System.out.println("Saldo final: " + product2.getQuantity());

        System.out.println("Saldo final: " + product3.getQuantity());

        System.out.println("Saldo final: " + product4.getQuantity());


        String movement = "";

        for (int i = 0; i < branchSouth.getProducts().size(); i++) {
            for (int j = 0; j < branchSouth.getProducts().get(i).getMovementList().size(); j++) {
                movement += "Tipo: " + branchSouth.getProducts().get(i).getMovementList().get(j).getMovementType() +
                        "\nProduct: " + branchSouth.getProducts().get(i).getProduct().getDescription() + "\nQuantidade: " +
                        branchSouth.getProducts().get(i).getMovementList().get(j).getQuantity() +
                        "\nData: " + branchSouth.getProducts().get(i).getMovementList().get(j).getDate() + "\nOrigin Type: " +
                        branchSouth.getProducts().get(i).getMovementList().get(j).getOriginType() + "\nOrigin Number: " +
                        branchSouth.getProducts().get(i).getMovementList().get(j).getOriginNumber() + "\n\n";
            }
        }
        System.out.println(movement);
    }


}