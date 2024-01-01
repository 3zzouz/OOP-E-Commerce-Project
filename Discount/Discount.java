package Discount;

import java.util.ArrayList;

import Utility.DateFormat;

/**
 * The Discount class represents a discount that can be applied to products in
 * an e-commerce system.
 * It stores the discount code, percentage, and expiry date.
 * Discounts can be added, removed, retrieved, and printed.
 */
public class Discount {
    private String code;
    private double percentage;
    private DateFormat expiryDate;
    public static ArrayList<Discount> discounts = new ArrayList<Discount>();

    /**
     * Constructs a Discount object with the specified code, percentage, and expiry
     * date.
     * The discount is added to the list of discounts.
     *
     * @param code       the discount code
     * @param percentage the discount percentage
     * @param expiryDate the expiry date of the discount
     */
    public Discount(String code, double percentage, String expiryDate) {
        this.code = code;
        this.percentage = percentage;
        DateFormat df = new DateFormat(expiryDate);
        this.expiryDate = df;
    }

    /**
     * Returns the discount code.
     *
     * @return the discount code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the discount percentage.
     *
     * @return the discount percentage
     */
    public double getPercentage() {
        return this.percentage;
    }

    /**
     * Returns the expiry date of the discount.
     *
     * @return the expiry date of the discount
     */
    public DateFormat getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * Adds a new discount with the specified code, percentage, and expiry date to
     * the list of discounts.
     *
     * @param code       the discount code
     * @param percentage the discount percentage
     * @param expiryDate the expiry date of the discount
     */
    public static void addDiscount(String code, double percentage, String expiryDate) {
        if (percentage < 0 || percentage > 100) {
            System.out.println("Invalid percentage");
            return;
        }
        if (expiryDate == null || expiryDate.isEmpty()) {
            System.out.println("Invalid expiry date");
            return;
        }
        discounts.add(new Discount(code, percentage, expiryDate));
    }

    /**
     * Removes the discount with the specified code from the list of discounts.
     * If the discount is not found, a message is printed.
     *
     * @param code the discount code
     */
    public static void removeDiscount(String code) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getCode().equals(code)) {
                discounts.remove(i);
                return;
            }
        }
        System.out.println("Discount not found");
    }

    /**
     * Returns the discount with the specified code.
     * If the discount is not found, a message is printed and null is returned.
     *
     * @param code the discount code
     * @return the discount with the specified code, or null if not found
     */
    public static Discount getDiscount(String code) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getCode().equals(code)) {
                return discounts.get(i);
            }
        }
        return null;
    }

    /**
     * Prints all the discounts in the list.
     */
    public static void printDiscounts() {
        for (int i = 0; i < discounts.size(); i++) {
            System.out.println(discounts.get(i).toString());
        }
    }

    /**
     * Returns a string representation of the discount.
     *
     * @return a string representation of the discount
     */
    public String toString() {
        return "Code: " + this.code + "\nPercentage: " + this.percentage + "\nExpiry Date: "
                + this.expiryDate.getSDate();
    }

    /**
     * Sets the discount percentage.
     *
     * @param percentage the discount percentage
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Sets the expiry date of the discount.
     *
     * @param expiryDate the expiry date of the discount
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = new DateFormat(expiryDate);
    }

}
