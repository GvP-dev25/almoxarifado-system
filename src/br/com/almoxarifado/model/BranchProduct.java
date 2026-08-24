package br.com.almoxarifado.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BranchProduct {
    private Product product;
    private Branch filial;
    private int quantity;
    private String location;
    private List<Movement> movementList;
    private List<Movement> movementView;

    public BranchProduct() {
    }

    public BranchProduct(Product product, Branch filial, int quantity) {
        this.product = product;
        this.filial = filial;
        this.quantity = quantity;
        this.location = null;
        movementList = new ArrayList<>();
        movementView = Collections.unmodifiableList(movementList);
        if(quantity > 0 ){
            registerMovement(quantity,MovementType.ENTRY, "Initial stock");
        }
    }


    public boolean addQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        } else {
            this.quantity += quantity;
            registerMovement(quantity, MovementType.ENTRY, "Tax Invoice");
            return true;
        }
    }

    public boolean removeQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        } else if (this.quantity < quantity) {
            return false;
        }
        this.quantity -= quantity;
        registerMovement(quantity, MovementType.OUTPUT, "Request");
        return true;
    }

   // public boolean reverseEntry(UUID id){


    //}

     private void registerMovement(int quantity, MovementType type, String description) {
        Movement movement = new Movement(quantity, type, description);
        movementList.add(movement);

    }


    public Product getProduct() {
        return product;
    }

    public List<Movement> getMovementList() {
        return movementView;
    }

    public Branch getBranch() {
        return filial;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }
}
