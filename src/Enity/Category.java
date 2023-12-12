
package Enity;

public class Category {

    private int categoryID;
    private String name;
    private int TotalProduct; 

    public Category(int categoryID, String name, int TotalProduct) {
        this.categoryID = categoryID;
        this.name = name;
        this.TotalProduct = TotalProduct;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }
    
    

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalProduct() {
        return TotalProduct;
    }
}
