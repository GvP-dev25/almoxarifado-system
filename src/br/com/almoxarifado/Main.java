package br.com.almoxarifado;

import br.com.almoxarifado.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Product newProduct = new Product("1", "Parafuso 1/2 x 1");


        Product newProduct2 = new Product("2", "Parafuso 1/2 x 2");

        Product newproduct3 = new Product("3", "Parafuso 1/2 x 3");

        Product newProduct4 = new Product("4", "Parafuso 1/2 x 4");

        Branch branchSouth = new Branch("001", "Sul");

        BranchProduct product1 = new BranchProduct(newProduct, branchSouth, 100);
        BranchProduct product2 = new BranchProduct(newProduct2, branchSouth, 60);
        BranchProduct product3 = new BranchProduct(newproduct3, branchSouth, 200);
        BranchProduct product4 = new BranchProduct(newProduct4, branchSouth, 60);

        branchSouth.addProduct(product1);
        branchSouth.addProduct(product2);
        branchSouth.addProduct(product3);

        //branchSouth.productReceipt(newProduct, 20);
        //branchSouth.productReceipt(newProduct4, -30);

        BranchProduct newP = new BranchProduct();
        newP = branchSouth.findBranchProduct("1");


        Request request = new Request("123", branchSouth);


        System.out.println("Saldo inicial: " + newP.getQuantity());

        System.out.println("Saldo inicial: " + product2.getQuantity());

        System.out.println("Saldo inicial: " + product3.getQuantity());

        System.out.println("Saldo inicial: " + product4.getQuantity());


        request.addProductRequest(newP, 50);
        request.attendedProduct("1", 50);

        request.addProductRequest(product2, 100);
        request.attendedProduct("2", 60);

        request.addProductRequest(product3, 100);
        request.attendedProduct("3", 150);

        request.addProductRequest(product4, 100);
        request.attendedProduct("4", 80);

        System.out.println("Saldo final: " + newP.getQuantity());

        System.out.println("Saldo final: " + product2.getQuantity());

        System.out.println("Saldo final: " + product3.getQuantity());

        System.out.println("Saldo final: " + product4.getQuantity());

        request.attendedProduct("3", 100);

        System.out.println("Saldo final: " + product3.getQuantity());

        request.reversalProduct("1");
        boolean secondReversal = request.reversalProduct("1");

        System.out.println("Saldo final Reversal: " + newP.getQuantity());
        System.out.println("Second Reversal: " + secondReversal);


        String out = "";

        for (int i = 0; i < request.getProductRequestList().size(); i++) {
            out += ("Request number: " + request.getNumberRequest() + "\nDescription: " + request.getProductRequestList().get(i).getBranchProduct().getProduct().getDescription() +
                    "\nQuantity Requested: " + request.getProductRequestList().get(i).getRequestedQuantity() + "\nQuantity Attended: " + request.getProductRequestList().get(i).getAttendedQuantity()) + "\n";
        }
        System.out.println(out);


        System.out.println("Saldo inicial: " + newP.getQuantity());

        System.out.println("Saldo inicial: " + product2.getQuantity());

        System.out.println("Saldo inicial: " + product3.getQuantity());

        Invoice invoice1 = new Invoice("123", branchSouth);
        invoice1.addProductInvoice(newProduct, 1000, Destination.STOCK);
        invoice1.addProductInvoice(newProduct2, 1000, Destination.STOCK);
        invoice1.addProductInvoice(newproduct3, 1000, Destination.DIRECT);
        invoice1.processInvoice();
        invoice1.processInvoice();

        System.out.println("Saldo final: " + newP.getQuantity());
        System.out.println("Saldo final: " + product2.getQuantity());
        System.out.println("Saldo final: " + product3.getQuantity());


        Request request1 = new Request("155",branchSouth);





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