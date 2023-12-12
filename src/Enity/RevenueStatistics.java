
package Enity;

public class RevenueStatistics {
   
    private double TotalAmount ; 
    private int TotalOrder ; 
    private int TotalProduct ; 

    public RevenueStatistics(double TotalAmount, int TotalOrder, int TotalProduct) {
        this.TotalAmount = TotalAmount;
        this.TotalOrder = TotalOrder;
        this.TotalProduct = TotalProduct;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public int getTotalOrder() {
        return TotalOrder;
    }

    public void setTotalOrder(int TotalOrder) {
        this.TotalOrder = TotalOrder;
    }

    public int getTotalProduct() {
        return TotalProduct;
    }

    public void setTotalProduct(int TotalProduct) {
        this.TotalProduct = TotalProduct;
    }

}
