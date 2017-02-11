/**
 * Created by User on 10.02.2017.
 */
public class Product {
    private String category;
    private String name;
    private Double price;
    private int productId;

    @Override
    public String toString() {
        return "\n"+"Product: "+"\n"+  "category= " + category +"\n"+ "name= " + name +"\n"+"price= " + price +"\n"+ "productId=" + productId +"\n";
    }

    public Product() {
    }

    public Product(String category, String name, Double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
