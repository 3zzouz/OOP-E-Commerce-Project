package Products;

import java.util.Scanner;

/**
 * The abstract class Product represents a generic product in an e-commerce
 * system.
 * It provides common attributes and methods that all products should have.
 */
public abstract class Product {
    protected static int idCounter = 0;
    protected int id;
    protected String name;
    protected String description;
    protected double price;
    protected int stockQuantity;
    protected String imageUrl;

    // default constructor
    public Product() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = sc.nextLine();
        System.out.println("Enter product description: ");
        String description = sc.nextLine();
        System.out.println("Enter product price: ");
        double price = sc.nextDouble();
        System.out.println("Enter product stock quantity: ");
        int stockQuantity = sc.nextInt();
        System.out.println("Enter product image URL: ");
        String imageUrl = sc.nextLine();
        sc.close();
        setName(name);
        setDescription(description);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setImageUrl(imageUrl);
        setId(idCounter++);
    }

    // parameterized constructor
    public Product(int id, String name, String description, double price, int stockQuantity, String imageUrl) {
        setName(name);
        setDescription(description);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setImageUrl(imageUrl);
        setId(id);
    }

    // override toString method
    public String toString() {
        return this.id + " , " + this.name + " , " + this.description + " , " + this.price + " , " + this.stockQuantity
                + " , " + this.imageUrl;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0)
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        this.stockQuantity = stockQuantity;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty())
            throw new IllegalArgumentException("Image URL cannot be empty");
        this.imageUrl = imageUrl;
    }

    protected void setId(int id) {
        this.id = id;
    }

    // getters
    public String getName() {
        return this.name;
    }

    protected String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    protected String getImageUrl() {
        return this.imageUrl;
    }

    public int getId() {
        return this.id;
    }

    public static int getCounter() {
        return idCounter;
    }

    // abstract method to ensure that all products have a clone method
    public abstract Product clone();
}
