package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.InvalidQuantityException;
import br.com.almoxarifado.exception.ProductRequestAlreadyRevertedException;
import br.com.almoxarifado.exception.ProductRequestFulFilledException;
import br.com.almoxarifado.exception.RequestedQuantityExceededException;

public class ProductRequest {
    private BranchProduct branchProduct;
    private int requestedQuantity;
    private int attendedQuantity;
    private boolean reversed, attended;


    public ProductRequest(BranchProduct branchProduct, int requestedQuantity) {
        this.branchProduct = branchProduct;
        this.requestedQuantity = requestedQuantity;
        attendedQuantity = 0;
        reversed = false;
        attended = false;
    }


    public void attendedQuantity(int attendedQuantity, OriginType originType, String originNumber) {
        if (attended) {
            throw new ProductRequestFulFilledException();
        }
        if (attendedQuantity <= 0) {
            throw new InvalidQuantityException();
        }
        if (attendedQuantity > requestedQuantity) {
            throw new RequestedQuantityExceededException();
        }
        branchProduct.removeQuantity(attendedQuantity, originType, originNumber);
        this.attendedQuantity = attendedQuantity;
        this.attended = true;

    }


    public void addRequestQuantity(int requestedQuantity) {
        if (requestedQuantity < 0) {
            throw new InvalidQuantityException();
        }
        this.requestedQuantity += requestedQuantity;
    }

    public void reversal(OriginType originType, String originNumber) {
        if (reversed) {
            throw new ProductRequestAlreadyRevertedException();
        } else if (attendedQuantity <= 0) {
            throw new InvalidQuantityException();
        }
        this.getBranchProduct().removeQuantityReversal(this.attendedQuantity, originType, originNumber);
        this.reversed = true;
    }

    public BranchProduct getBranchProduct() {
        return branchProduct;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAttendedQuantity() {
        return attendedQuantity;
    }
}
