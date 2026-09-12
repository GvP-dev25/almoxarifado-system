package br.com.almoxarifado.jdbc;


import br.com.almoxarifado.model.BranchProduct;
import br.com.almoxarifado.model.Movement;
import br.com.almoxarifado.model.MovementType;
import br.com.almoxarifado.model.OriginType;

import java.util.List;
import java.util.UUID;


public class Main {

    public static void main(String[] args) {
        BranchProductRepository branchProductRepository = new BranchProductRepository();
        BranchProduct branchProduct = branchProductRepository.findBranchProduct(2);
        System.out.println(branchProduct);
        System.out.println("\n\n");
        List<Movement> movementList = branchProduct.getMovementList();
        String out = "";
        for (Movement m : movementList){
            out += m + "\n";
        }
        System.out.println(out);


        Movement movement = new Movement(100, MovementType.ENTRY,OriginType.INVOICE,"500");

      int rows =  branchProductRepository.removeQuantity(branchProduct,movement);
        System.out.println(rows);
    }



}
