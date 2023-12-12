package Enity;
public class Order {
      
    private int orderID ; 
    private String orderdate ; 
    private float totalAmount ; 
    private String nameCustomer ; 
    private String phoneCustomer ;
    private int EmployeeID; 

    public Order(int orderID, String orderdate, float totalAmount, String nameCustomer, String phoneCustomer, int EmployeeID) {
        this.orderID = orderID;
        this.orderdate = orderdate;
        this.totalAmount = totalAmount;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.EmployeeID = EmployeeID;
    }

    public Order(String orderdate, float totalAmount, String nameCustomer, String phoneCustomer, int EmployeeID) {
        this.orderdate = orderdate;
        this.totalAmount = totalAmount;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.EmployeeID = EmployeeID;
    }
    
    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }
    
}
