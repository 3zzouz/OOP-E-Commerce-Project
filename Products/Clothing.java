package Products;

import java.util.Scanner;

public class Clothing extends Product {
    protected String[] sizes, colors;

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

    public String toString() {
        return this.id + " , " + this.name + " , " + this.description + " , " + this.price + " , " + this.stockQuantity
                + " , "
                + this.imageUrl + " , " + String.join("//", this.sizes) + " , " + String.join("//", this.colors);
    }

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

    public String[] getSizes() {
        return this.sizes;
    }

    public String[] getColors() {
        return this.colors;
    }
}
