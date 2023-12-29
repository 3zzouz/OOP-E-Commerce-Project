package Products;

import java.util.Scanner;

public class Electronics extends Product {
    protected String[] Specs;

    public Electronics() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the specifications of the product: ");
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
        this.Specs = Specs;
    }

}
