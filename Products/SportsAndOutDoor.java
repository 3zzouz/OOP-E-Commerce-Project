package Products;

import java.util.Scanner;

/**
 * The SportsAndOutDoor class represents a sports and outdoor product.
 * It extends the Product class and provides additional attributes and methods
 * specific to sports and outdoor products.
 */
public class SportsAndOutDoor extends Product {
    protected String type, brand, material, weight, sportType;

    // default constructor
    public SportsAndOutDoor() {
        System.out.println("Enter the type of the product: ");
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine();
        System.out.println("Enter the brand of the product: ");
        String brand = sc.nextLine();
        System.out.println("Enter the material of the product: ");
        String material = sc.nextLine();
        System.out.println("Enter the weight of the product: ");
        String weight = sc.nextLine();
        System.out.println("Enter the sport type of the product: ");
        String sportType = sc.nextLine();
        sc.close();
        setType(type);
        setBrand(brand);
        setMaterial(material);
        setWeight(weight);
        setSportType(sportType);
    }

    // parameterized constructor
    public SportsAndOutDoor(int id, String name, String description, double price, int stockQuantity, String imageUrl,
            String type, String brand, String material, String weight, String sportType) {
        super(id, name, description, price, stockQuantity, imageUrl);
        setType(type);
        setBrand(brand);
        setMaterial(material);
        setWeight(weight);
        setSportType(sportType);
    }

    // toString method
    public String toString() {
        return "SportsAndOutDoor , " + this.toString() + " , " + this.type + " , " + this.brand + " , " + this.material
                + " , " + this.weight
                + " , " + this.sportType;
    }

    // setters
    protected void setType(String type) {
        if (type == null || type.isEmpty())
            throw new IllegalArgumentException("Type cannot be empty");
        this.type = type;
    }

    protected void setBrand(String brand) {
        if (brand == null || brand.isEmpty())
            throw new IllegalArgumentException("Brand cannot be empty");
        this.brand = brand;
    }

    protected void setMaterial(String material) {
        if (material == null || material.isEmpty())
            throw new IllegalArgumentException("Material cannot be empty");
        this.material = material;
    }

    protected void setWeight(String weight) {
        if (weight == null || weight.isEmpty())
            throw new IllegalArgumentException("Weight cannot be empty");
        this.weight = weight;
    }

    protected void setSportType(String sportType) {
        if (sportType == null || sportType.isEmpty())
            throw new IllegalArgumentException("SportType cannot be empty");
        this.sportType = sportType;
    }

    // getters
    protected String getType() {
        return this.type;
    }

    protected String getBrand() {
        return this.brand;
    }

    protected String getMaterial() {
        return this.material;
    }

    protected String getWeight() {
        return this.weight;
    }

    protected String getSportType() {
        return this.sportType;
    }

    // clone method
    public SportsAndOutDoor clone() {
        SportsAndOutDoor sportsAndOutDoor = new SportsAndOutDoor(this.id, this.name, this.description, this.price,
                this.stockQuantity, this.imageUrl,
                this.type, this.brand, this.material, this.weight, this.sportType);
        return sportsAndOutDoor;
    }

}
