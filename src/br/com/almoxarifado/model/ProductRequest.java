package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.*;

public class ProductRequest {
    private BranchProduct branchProduct;
    private int requestedQuantity;
    private int attendedQuantity;
    private boolean reversed, processed;


    public ProductRequest(BranchProduct branchProduct, int requestedQuantity) {
        this.branchProduct = branchProduct;
        this.requestedQuantity = requestedQuantity;
        attendedQuantity = 0;
        reversed = false;
        processed = false;
    }


    public void attendedQuantity(int attendedQuantity, OriginType originType, String originNumber) {
        if (processed) {
            throw new ProductRequestFulFilledException();
        }
        if (attendedQuantity < 0) {
            throw new InvalidQuantityException();
        }
        if (attendedQuantity > requestedQuantity) {
            throw new RequestedQuantityExceededException();
        }
        branchProduct.removeQuantity(attendedQuantity, originType, originNumber);
        this.attendedQuantity = attendedQuantity;
        this.processed = true;

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
        }
        if (attendedQuantity < 0) {
            throw new InvalidQuantityException();
        }
        if (!reversed && attendedQuantity == 0) {
            throw new NoReversionException();
        }
        this.getBranchProduct().processReversal(this.attendedQuantity, originType, originNumber);
        this.reversed = true;
    }

    public BranchProduct getBranchProduct() {
        return branchProduct;
    }

    public boolean isReversed() {
        return reversed;
    }

    public boolean isProcessed() {
        return processed;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAttendedQuantity() {
        return attendedQuantity;
    }
}
