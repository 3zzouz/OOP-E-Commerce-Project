package Products;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The Electronics class represents an electronic product.
 * It extends the Product class and adds additional specifications for the
 * product.
 */
public class Electronics extends Product {
    protected String[] Specs;

    /**
     * Default constructor for the Electronics class.
     * It prompts the user to enter the specifications of the product and sets them.
     */
    public Electronics() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the specifications of the product: (separate by space)");
        String[] Specs = sc.nextLine().split(" ");
        sc.close();
        setSpecs(Specs);
    }

    /**
     * Parameterized constructor for the Electronics class.
     * It sets the name, description, price, stock quantity, image URL, and
     * specifications of the product.
     *
     * @param name          the name of the product
     * @param description   the description of the product
     * @param price         the price of the product
     * @param stockQuantity the stock quantity of the product
     * @param imageUrl      the image URL of the product
     * @param Specs         the specifications of the product
     */
    public Electronics(int id, String name, String description, double price, int stockQuantity, String imageUrl,
            String[] Specs) {
        super(id, name, description, price, stockQuantity, imageUrl);
        setSpecs(Specs);
    }

    /**
     * Overrides the toString method to include the specifications of the product.
     *
     * @return a string representation of the Electronics object
     */
    @Override
    public String toString() {
        return "Electronics , " + super.toString() + " , " + Arrays.toString(this.Specs);
    }

    /**
     * Retrieves the specifications of the product.
     *
     * @return the specifications of the product
     */
    protected String[] getSpecs() {
        return this.Specs;
    }

    /**
     * Sets the specifications of the product.
     *
     * @param Specs the specifications of the product
     */
    protected void setSpecs(String[] Specs) {
        this.Specs = Specs;
    }

    /**
     * Clones the Electronics object.
     *
     * @return a clone of the Electronics object
     */

    public Electronics clone() {
        Electronics electronics = new Electronics(this.id, this.name, this.description, this.price, this.stockQuantity,
                this.imageUrl,
                this.Specs);
        return electronics;
    }
}
