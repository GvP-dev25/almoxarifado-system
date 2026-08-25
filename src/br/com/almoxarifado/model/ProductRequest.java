package br.com.almoxarifado.model;

public class ProductRequest {
    private BranchProduct branchProduct;
    private int requestedQuantity;
    private int attendedQuantity;


    public ProductRequest(BranchProduct branchProduct, int requestedQuantity) {
        this.branchProduct = branchProduct;
        this.requestedQuantity = requestedQuantity;
        attendedQuantity = 0;
    }


    public boolean attendedQuantity(int attendedQuantity) {
        if (attendedQuantity <= branchProduct.getQuantity() && attendedQuantity > 0 && attendedQuantity <= requestedQuantity) {
            this.attendedQuantity = attendedQuantity;
            boolean attended = branchProduct.removeQuantity(attendedQuantity);
            return attended;
        }
        return false;
    }

    public boolean addRequestQuantity(int requestedQuantity) {
        if (requestedQuantity < 0) {
            return false;
        }
        this.requestedQuantity += requestedQuantity;
        return true;
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
