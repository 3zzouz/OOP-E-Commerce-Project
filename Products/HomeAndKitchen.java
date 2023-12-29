package Products;

import java.util.Scanner;

public class HomeAndKitchen extends Product {
    protected String[] Specs;

    public HomeAndKitchen() {
        System.out.println("Enter the specifications of the product: ");
        Scanner sc = new Scanner(System.in);
        String[] Specs = sc.nextLine().split(" ");
        sc.close();
        setSpecs(Specs);
    }

    protected String[] getSpecs() {
        return this.Specs;
    }

    public String toString() {
        return this.id + " , " + this.name + " , " + this.description + " , " + this.price + " , " + this.stockQuantity
                + " , "
                + this.imageUrl + " , " + String.join("//", this.Specs);
    }

    protected void setSpecs(String[] Specs) {
        if (Specs == null || Specs.length == 0)
            throw new IllegalArgumentException("Specs cannot be empty");
        this.Specs = Specs;
    }
}
