package br.com.almoxarifado.jdbc;

import br.com.almoxarifado.model.Branch;
import br.com.almoxarifado.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
       /* List<Product> products = new ArrayList<Product>();
        products = productRepository.findAllProducts();
        String out = "";
        for (Product p : products) {
            out += "ID: " + p.getId() + "\nCODE: " + p.getCode() + "\nNAME: " + p.getDescription() + "\n";
        }
        System.out.println(out);
*/

        // productRepository.updateProductById(41, "Parafuso 5/8 x 5");
        //Product p2 = productRepository.findById(41);
        // System.out.println("ID: " + p2.getId() + "\nCODE: " + p2.getCode() + "\nNAME: " + p2.getDescription());
        BranchRepository branchRepository = new BranchRepository();
        Branch branch = new Branch("030", "Filial Norte");
        Branch branch1 = branchRepository.findByCode("030");
        System.out.println("ID: " + branch1.getId() + "\nCODE: " + branch1.getCode() + "\nNAME: " + branch1.getName());

        Branch branch2 = branchRepository.findById(3);
        System.out.println("ID: " + branch2.getId() + "\nCODE: " + branch2.getCode() + "\nNAME: " + branch2.getName() + "\n\n");

        int rows = branchRepository.updateNameByCode("030", "Filial Sudeste");
        System.out.println("\nRows Affeted: " + rows);

        List<Branch> branchList = branchRepository.findByAll();
        String out = "";
        for (Branch p : branchList) {
            out += "ID: " + p.getId() + "\nCODE: " + p.getCode() + "\nNAME: " + p.getName() + "\n";
        }
        System.out.println(out);


     /*    Product p = productRepository.findById(41);
        System.out.println("ID: " + p.getId() + "\nCODE: " + p.getCode() + "\nNAME: " + p.getDescription());
        System.out.println("\n\n");
        p = productRepository.findByCode("123");
        System.out.println("ID: " + p.getId() + "\nCODE: " + p.getCode() + "\nNAME: " + p.getDescription());
    */


    }

}
