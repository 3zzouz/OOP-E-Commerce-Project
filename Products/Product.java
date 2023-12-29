package Products;


import java.util.Scanner;

public abstract class Product {
    protected static int idCounter = 0;
    protected int id;
    protected String name;
    protected String description;
    protected double price;
    protected int stockQuantity;
    protected String imageUrl;

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

    public abstract String toString();

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    protected void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0)
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        this.stockQuantity = stockQuantity;
    }

    protected void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty())
            throw new IllegalArgumentException("Image URL cannot be empty");
        this.imageUrl = imageUrl;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected String getName() {
        return this.name;
    }

    protected String getDescription() {
        return this.description;
    }

    protected double getPrice() {
        return this.price;
    }

    protected int getStockQuantity() {
        return this.stockQuantity;
    }

    protected String getImageUrl() {
        return this.imageUrl;
    }

    protected int getId() {
        return this.id;
    }

    public static int getCounter() {
        return idCounter;
    }
}
