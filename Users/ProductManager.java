package Users;

import Products.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Discount.Discount;

/**
 * The ProductManager class represents a user with the role of a product manager
 * in an e-commerce system.
 * It extends the User class and provides functionality to manage products,
 * including adding, removing, updating,
 * and searching for products. It also allows the product manager to manage
 * discounts for products.
 */
public class ProductManager extends User {

    public static HashMap<Integer, Product> products;

    // Constructor
    public ProductManager() {
        super();
        permissionLevel = 1;
        users.put(username, this);
        System.out.println("Account Created Successfully");
    }

    // add parameterized constructor
    public ProductManager(String name, String email, String address, String username, int age, int phoneNumber,
            String password) {
        super(name, email, address, username, age, phoneNumber, password);
        permissionLevel = 1;
    }

    // method to add a product to the list
    public void addProducts() {
        Product product;
        int choix = 0;
        while (choix != 5) {
            System.out.println("Enter your Product Category : ");
            System.out.println("1- Clothes");
            System.out.println("2- Electronics");
            System.out.println("3- Books");
            System.out.println("4- SportsAndOutdoor");
            System.out.println("5- Exit");
            Scanner sc = new Scanner(System.in);
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    product = new Clothing();
                    products.put(product.getId(), product);
                    System.out.println("Product added successfully");
                    break;
                case 2:
                    product = new Electronics();
                    products.put(product.getId(), product);
                    System.out.println("Product added successfully");
                    break;
                case 3:
                    product = new Books();
                    products.put(product.getId(), product);
                    System.out.println("Product added successfully");
                    break;
                case 4:
                    product = new SportsAndOutDoor();
                    products.put(product.getId(), product);
                    System.out.println("Product added successfully");
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    // method to check if a product exists
    public boolean existProduct(int id) {

        return products.containsKey(id);
    }

    // method to get a product by id
    public static Product getProduct(int id) {
        if (!products.containsKey(id)) {
            return null;
        }
        return products.get(id);
    }

    // method to get product price
    public static double getProductPrice(int id) {
        if (!products.containsKey(id)) {
            return -1;
        }
        return products.get(id).getPrice();
    }

    // method to get product stock quantity
    public static int getProductStockQuantity(int id) {
        if (!products.containsKey(id)) {
            return -1;
        }
        return products.get(id).getStockQuantity();
    }

    // method to remove a product from the list
    public void removeProduct() {

        System.out.println(
                "Enter the product id you want to remove : ");
        Scanner sc = new Scanner(System.in);
        int productId = sc.nextInt();
        sc.nextLine();
        if (!existProduct(productId)) {
            System.out.println("Product does not exist");
            return;
        }
        products.remove(productId);
    }

    // method to update a product in the list
    public void updateProduct() {

        System.out.println(
                "Enter the id of product you want to update : ");
        Scanner sc = new Scanner(System.in);
        int prodId = sc.nextInt();
        sc.nextLine();
        ;
        if (!existProduct(prodId)) {
            System.out.println("Product does not exist");
            return;
        }
        Product product = products.get(prodId);
        int choix = 0;
        while (choix != 6) {
            System.out.println("Enter the field you want to update : ");
            System.out.println("1- Name");
            System.out.println("2- Description");
            System.out.println("3- Price");
            System.out.println("4- Stock Quantity");
            System.out.println("5- Image URL");
            System.out.println("6- Exit");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Enter the new name : ");
                    String name = sc.nextLine();
                    product.setName(name);
                    System.out.println("Product Name updated successfully");
                    break;
                case 2:
                    System.out.println("Enter the new description : ");
                    String description = sc.nextLine();
                    product.setDescription(description);
                    System.out.println("Product Description updated successfully");
                    break;
                case 3:
                    System.out.println("Enter the new price : ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    product.setPrice(price);
                    System.out.println("Product Price updated successfully");
                    break;
                case 4:
                    System.out.println("Enter the new stock quantity : ");
                    int stockQuantity = sc.nextInt();
                    sc.nextLine();
                    product.setStockQuantity(stockQuantity);
                    System.out.println("Product Stock Quantity updated successfully");
                    break;
                case 5:
                    System.out.println("Enter the new image URL : ");
                    String imageUrl = sc.nextLine();
                    product.setImageUrl(imageUrl);
                    System.out.println("Product Image URL updated successfully");
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    break;
            }
        }
    }

    // method to print all products
    public static void printProducts() {
        System.out.println("Products : ");

        for (Product product : products.values()) {
            System.out.println(product.toString());
        }
    }

    // method to search for products using a search term and filters
    public static ArrayList<Product> searchProducts(String searchTerm, Map<String, Object> filters) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : products.values()) {

            // Check if the product name contains the search term
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                boolean match = true;

                // Check each filter
                for (String filterField : filters.keySet()) {
                    Object filterValue = filters.get(filterField);
                    // Match the filter field with the corresponding product info field
                    switch (filterField) {
                        case "price":
                            double price = product.getPrice();
                            Double[] priceRange = (Double[]) filterValue;
                            if ((price < priceRange[0] && priceRange[0] != 0)
                                    || (price > priceRange[1] && priceRange[1] != 0)) {
                                match = false;
                            }
                            break;
                        case "quantity":
                            if (product.getStockQuantity() < Integer.parseInt(filterValue.toString())
                                    && product.getStockQuantity() != 0) {
                                match = false;
                            }
                            break;
                    }

                    if (!match) {
                        break;
                    }
                }

                if (match) {
                    results.add(product);
                }
            }
        }

        return results;
    }

    // method to receive notifications
    public static void receiveNotifications(String s) {
        System.out.println("Notification received : " + s);
    }

    // method to update product quantity
    public static void updateProductQuantity(int id, int quantity) {
        if (!products.containsKey(id)) {
            return;
        }
        Product product = products.get(id);
        product.setStockQuantity(product.getStockQuantity() + quantity);

    }

    // method to add discount
    public void addDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter discount code: ");
        String code = sc.nextLine();
        while (Discount.getDiscount(code) != null) {
            System.out.println("Discount code already exists");
            System.out.println("Enter discount code: ");
            code = sc.nextLine();
        }
        System.out.println("Enter discount percentage: (0-100)");
        double percentage = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter discount expiry date: (dd/mm/yyyy hh:mm:ss)");
        String expiryDate = sc.nextLine();
        Discount.addDiscount(code, percentage, expiryDate);
    }

    // method to remove discount
    public void removeDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter discount code: ");
        String code = sc.nextLine();
        if (Discount.getDiscount(code) == null) {
            System.out.println("Discount not found");
            return;
        }
        Discount.removeDiscount(code);
    }

    // method to print discounts
    public void printDiscounts() {
        if (Discount.discounts == null || Discount.discounts.isEmpty()) {
            System.out.println("No discounts available");
            return;
        }
        Discount.printDiscounts();
    }

    // method to update discount
    public void updateDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter discount code: ");
        String code = sc.nextLine();
        if (Discount.discounts == null) {
            System.out.println("There are no discounts");
            return;
        }
        Discount discount = Discount.getDiscount(code);
        if (discount == null) {
            System.out.println("Discount not found");
            return;
        }
        int choix = 0;
        while (choix != 3) {
            System.out.println("Enter the field you want to update : ");
            System.out.println("1- Percentage");
            System.out.println("2- Expiry Date");
            System.out.println("3- Exit");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Enter the new percentage : ");
                    double percentage = sc.nextDouble();
                    sc.nextLine();
                    discount.setPercentage(percentage);
                    System.out.println("Discount Percentage updated successfully");
                    break;
                case 2:
                    System.out.println("Enter the new expiry date : ");
                    String expiryDate = sc.nextLine();
                    discount.setExpiryDate(expiryDate);
                    System.out.println("Discount Expiry Date updated successfully");
                    break;
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    break;
            }
        }
    }

}
