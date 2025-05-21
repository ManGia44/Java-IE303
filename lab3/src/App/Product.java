package App;

public class Product {
    public String name;
    public String description;
    public String branch;
    public String imagePath;
    public double price;

    public Product(String name, String branch, double price, String imagePath, String description) {
        this.name = name;
        this.description = description;
        this.branch = branch;
        this.imagePath = imagePath;
        this.price = price;
    };
}
