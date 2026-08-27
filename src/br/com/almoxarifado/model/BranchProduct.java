package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.CodeNotFoundException;
import br.com.almoxarifado.exception.InsufficientStockException;
import br.com.almoxarifado.exception.InvalidQuantityException;
import br.com.almoxarifado.exception.ProductReversalProcessedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        if (quantity > 0) {
            registerMovement(quantity, MovementType.ENTRY, OriginType.INITIALSTOCK, "01");
        }
    }


    public void addQuantity(int quantity, OriginType originType, String originNumber) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
        this.quantity += quantity;
        registerMovement(quantity, MovementType.ENTRY, originType, originNumber);
    }

    public void removeQuantity(int quantity, OriginType originType, String originNumber) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        } else if (this.quantity < quantity) {
            throw new InsufficientStockException("Insufficient Stock. Available: " + this.quantity + ", requested: " + quantity);
        }
        this.quantity -= quantity;
        registerMovement(quantity, MovementType.OUTPUT, originType, originNumber);
    }

    public void removeQuantityReversal(int quantity, OriginType originType, String originNumber) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
        for (int i = 0; i < movementList.size(); i++) {
            if (movementList.get(i).getMovementType() == MovementType.ENTRY) {
                if (movementList.get(i).getOriginType() == originType && movementList.get(i).getOriginNumber().equals(originNumber)) {
                    this.quantity -= quantity;
                    registerMovement(quantity, MovementType.REVERSAL, originType, originNumber);
                    return;
                }
            }
            if (movementList.get(i).getMovementType() == MovementType.OUTPUT) {
                if (movementList.get(i).getOriginType() == originType && movementList.get(i).getOriginNumber().equals(originNumber)) {
                    this.quantity += quantity;
                    registerMovement(quantity, MovementType.REVERSAL, originType, originNumber);
                    return;
                }
            }
            if (movementList.get(i).getMovementType() == MovementType.REVERSAL && movementList.get(i).getOriginType()
                    == originType && movementList.get(i).getOriginNumber().equals(originNumber)) {
                throw new ProductReversalProcessedException();
            }
        }
    }

    // public boolean reverseEntry(UUID id){


    //}

    private void registerMovement(int quantity, MovementType type, OriginType originType, String originNumber) {
        Movement movement = new Movement(quantity, type, originType, originNumber);
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
