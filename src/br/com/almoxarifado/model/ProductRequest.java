package br.com.almoxarifado.model;

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


    public boolean attendedQuantity(int attendedQuantity) {
        if (!attended) {
            if (attendedQuantity <= branchProduct.getQuantity() && attendedQuantity > 0 && attendedQuantity <= requestedQuantity) {
                this.attendedQuantity = attendedQuantity;
                boolean attended = branchProduct.removeQuantity(attendedQuantity);
               if (attended){
                   this.attended = true;
               }
                return attended;
            }
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

    public boolean reversal() {
        if (reversed) {
            return false;
        } else if (attendedQuantity <= 0) {
            return false;
        }
        boolean result = this.getBranchProduct().removeQuantityReversal(this.attendedQuantity);
        if (result) {
            this.reversed = true;
            return result;
        }
        return result;
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
