package Products;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The Clothing class represents a clothing product in an e-commerce system.
 * It extends the Product class and adds additional properties such as sizes and
 * colors.
 */
public class Clothing extends Product {
    protected String[] sizes, colors;

    // default constructor
    public Clothing() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the sizes of the product: ");
        String[] sizes = sc.nextLine().split(" ");
        System.out.println("Enter the colors of the product: ");
        String[] colors = sc.nextLine().split(" ");
        setSizes(sizes);
        setColors(colors);
        sc.close();
    }

    // parameterized constructor
    public Clothing(int id, String name, String description, double price, int stockQuantity, String imageUrl,
            String[] sizes,
            String[] colors) {
        super(id, name, description, price, stockQuantity, imageUrl);
        setSizes(sizes);
        setColors(colors);
    }

    // override toString method
    public String toString() {
        return "Clothing , " + super.toString() + " , " + Arrays.toString(this.sizes) + " , "
                + Arrays.toString(this.colors);
    }

    // setters
    public void setSizes(String[] sizes) {
        if (sizes == null || sizes.length == 0)
            throw new IllegalArgumentException("Sizes cannot be empty");
        this.sizes = sizes;
    }

    public void setColors(String[] colors) {
        if (colors == null || colors.length == 0)
            throw new IllegalArgumentException("Colors cannot be empty");
        this.colors = colors;
    }

    // getters
    public String[] getSizes() {
        return this.sizes;
    }

    public String[] getColors() {
        return this.colors;
    }

    // clone method
    public Clothing clone() {
        Clothing clothing = new Clothing(this.id, this.name, this.description, this.price, this.stockQuantity,
                this.imageUrl,
                this.sizes, this.colors);
        return clothing;
    }
}
