package Discount;

import java.util.ArrayList;

public class Discount {
    private String code;
    private double percentage;
    private String expiryDate;
    public static ArrayList<Discount> discounts;

    public Discount(String code, double percentage, String expiryDate) {
        this.code = code;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
        discounts.add(this);
    }

    public String getCode() {
        return this.code;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public static void addDiscount(String code, double percentage, String expiryDate) {
        discounts.add(new Discount(code, percentage, expiryDate));
    }

    public static void removeDiscount(String code) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getCode().equals(code)) {
                discounts.remove(i);
                return;
            }
        }
        System.out.println("Discount not found");
    }

    public static Discount getDiscount(String code) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getCode().equals(code)) {
                return discounts.get(i);
            }
        }
        System.out.println("Discount not found");
        return null;
    }

    public static void printDiscounts() {
        for (int i = 0; i < discounts.size(); i++) {
            System.out.println(discounts.get(i).toString());
        }
    }

    public String toString() {
        return "Code: " + this.code + "\nPercentage: " + this.percentage + "\nExpiry Date: " + this.expiryDate;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
