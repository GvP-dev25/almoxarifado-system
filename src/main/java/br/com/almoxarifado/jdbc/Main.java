package br.com.almoxarifado.jdbc;

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
        int result = productRepository.deleteProductById(41);
        System.out.println(result);


     /*    Product p = productRepository.findById(41);
        System.out.println("ID: " + p.getId() + "\nCODE: " + p.getCode() + "\nNAME: " + p.getDescription());
        System.out.println("\n\n");
        p = productRepository.findByCode("123");
        System.out.println("ID: " + p.getId() + "\nCODE: " + p.getCode() + "\nNAME: " + p.getDescription());
    */


    }

}
