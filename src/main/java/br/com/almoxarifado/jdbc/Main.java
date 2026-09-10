package br.com.almoxarifado.jdbc;


import br.com.almoxarifado.model.BranchProduct;
import br.com.almoxarifado.model.Movement;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        BranchProductRepository branchProductRepository = new BranchProductRepository();
        BranchProduct branchProduct = branchProductRepository.findBranchProduct(3);
        System.out.println(branchProduct);
        System.out.println("\n\n");
        List<Movement> movementList = branchProduct.getMovementList();
        String out = "";
        for (Movement m : movementList){
            out += m + "\n";
        }
        System.out.println(out);

    }

}
