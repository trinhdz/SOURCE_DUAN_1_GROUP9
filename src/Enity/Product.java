package Enity;


public class Product {

    private int productID;
    private int categoryID;
    private String supplier;
    private String title;
    private String description;
    private float price;
    private String img;
    private int quantity;

    public Product(int productID, int categoryID, String supplier, String title, String description, float price, String img, int quantity) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.supplier = supplier;
        this.title = title;
        this.description = description;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public Product(int categoryID, String supplier, String title, String description, float price, String img,int quantity) {
        this.categoryID = categoryID;
        this.supplier = supplier;
        this.title = title;
        this.description = description;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
