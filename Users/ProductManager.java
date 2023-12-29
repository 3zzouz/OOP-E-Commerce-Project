package Users;

import Products.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Discount.Discount;
import Interfaces.ProductRW;

public class ProductManager extends User implements ProductRW {

    public static ArrayList<String> products;

    public ProductManager() {
        super();
        products = readfile(ProductRW.fileProducts);
        this.permissionLevel = 1;
    }

    public void addProducts() {
        String product;
        int choix = 0;
        while (choix != 6) {
            System.out.println("Enter your Product Category : ");
            System.out.println("1- Clothes");
            System.out.println("2- Electronics");
            System.out.println("3- Books");
            System.out.println("4- HomeAndKitchen");
            System.out.println("5- SportsAndOutdoor");
            switch (choix) {
                case 1:
                    product = (new Clothing()).toString();
                    writefile(product, ProductRW.fileProducts);
                    products.add(product);
                    break;
                case 2:
                    product = (new Electronics()).toString();
                    writefile(product, ProductRW.fileProducts);
                    products.add(product);
                    break;
                case 3:
                    product = (new Books()).toString();
                    writefile(product, ProductRW.fileProducts);
                    products.add(product);
                    break;
                case 4:
                    product = (new HomeAndKitchen()).toString();
                    writefile(product, ProductRW.fileProducts);
                    products.add(product);
                    break;
                case 5:
                    product = (new SportsAndOutDoor()).toString();
                    writefile(product, ProductRW.fileProducts);
                    products.add(product);
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public boolean existProduct(int id) {
        setProducts();
        for (String product : ProductManager.products) {
            String[] productInfo = product.split(" , ");
            if (Integer.parseInt(productInfo[0]) == id)
                return true;
        }
        return false;
    }

    public static double getProductPrice(String productName) {
        for (String product : ProductManager.products) {
            String[] productInfo = product.split(" , ");
            if (productInfo[1].equals(productName))
                return Double.parseDouble(productInfo[3]);
        }
        return -1;
    }

    public static int getProductStockQuantity(String productName) {
        for (String product : ProductManager.products) {
            String[] productInfo = product.split(" , ");
            if (productInfo[1].equals(productName))
                return Integer.parseInt(productInfo[4]);
        }
        return -1;
    }

    public void removeProduct() {
        setProducts();
        System.out.println(
                "Enter the product id you want to remove : ");
        Scanner sc = new Scanner(System.in);
        int productId = sc.nextInt();
        sc.close();
        if (!existProduct(productId)) {
            System.out.println("Product does not exist");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            String[] produc = products.get(i).split(" , ");
            if (Integer.parseInt(produc[0]) == productId) {
                products.remove(i);
                System.out.println("Product removed successfully");
            }
        }
        updateProductsLists();
    }

    protected void setProducts() {
        products = readfile("./Data/Products.csv");
    }

    protected void updateProuct() {
        System.out.println(
                "Enter the id of product you want to update : ");
        Scanner sc = new Scanner(System.in);
        int prodId = sc.nextInt();
        sc.close();
        if (!existProduct(prodId)) {
            System.out.println("Product does not exist");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            String[] produc = products.get(i).split(" , ");
            if (Integer.parseInt(produc[0]) == prodId) {
                int choix = 0;
                while (choix != 6) {
                    System.out.println("Enter the field you want to update : ");
                    System.out.println("1- Name");
                    System.out.println("2- Description");
                    System.out.println("3- Price");
                    System.out.println("4- Stock Quantity");
                    System.out.println("5- Image URL");
                    switch (choix) {
                        case 1:
                            System.out.println("Enter the new name : ");
                            String name = sc.next();
                            produc[1] = name;
                            break;
                        case 2:
                            System.out.println("Enter the new description : ");
                            String description = sc.next();
                            produc[2] = description;
                            break;
                        case 3:
                            System.out.println("Enter the new price : ");
                            double price = sc.nextDouble();
                            produc[3] = String.valueOf(price);
                            break;
                        case 4:
                            System.out.println("Enter the new stock quantity : ");
                            int stockQuantity = sc.nextInt();
                            produc[4] = String.valueOf(stockQuantity);
                            break;
                        case 5:
                            System.out.println("Enter the new image URL : ");
                            String imageUrl = sc.next();
                            produc[5] = imageUrl;
                            break;
                        case 6:
                            System.out.println("Exit");
                            break;
                        default:
                            break;
                    }
                }
            }
            String product = "";
            for (int j = 0; j < produc.length; j++) {
                product = product + produc[j];
            }
            products.set(i, product);
            System.out.println("Product updated successfully");
        }
        updateProductsLists();
    }

    protected static void updateProductsLists() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ProductRW.fileProducts));
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printProducts() {
        System.out.println("Products : ");
        setProducts();
        for (String product : products) {
            System.out.println(product);
        }
    }

    public static List<String> searchProducts(String searchTerm, Map<String, Object> filters) {
        List<String> results = new ArrayList<>();

        for (String product : products) {
            String[] productInfo = product.split(" , ");

            // Check if the product name contains the search term
            if (productInfo[1].toLowerCase().contains(searchTerm.toLowerCase())) {
                boolean match = true;

                // Check each filter
                for (Map.Entry<String, Object> filter : filters.entrySet()) {
                    String filterField = filter.getKey();
                    Object filterValue = filter.getValue();

                    // Match the filter field with the corresponding product info field
                    switch (filterField) {
                        case "price":
                            double price = Double.parseDouble(productInfo[3]);
                            double[] priceRange = (double[]) filterValue;
                            if (price < priceRange[0] && priceRange[0] != 0
                                    || price > priceRange[1] && priceRange[1] != 0) {
                                match = false;
                            }
                            break;
                        case "quantity":
                            if (!productInfo[4].equals(filterValue.toString())
                                    && Integer.parseInt(productInfo[4]) != 0) {
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

    public static void receiveNotifications(String s) {
        System.out.println("Notification received : " + s);
    }

    public static void updateProductQuantity(String productName, int quantity) {
        ArrayList<String> products = ProductManager.products;
        for (int i = 0; i < products.size(); i++) {
            String[] produc = products.get(i).split(" , ");
            if (produc[1].equals(productName)) {
                produc[4] = String.valueOf(Integer.parseInt(produc[4]) - quantity);
                String product = "";
                for (int j = 0; j < produc.length; j++) {
                    product = product + produc[j];
                }
                products.set(i, product);
            }
        }
        updateProductsLists();
    }

    public void addDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter discount code: ");
        String code = sc.nextLine();
        System.out.println("Enter discount percentage: ");
        double percentage = sc.nextDouble();
        System.out.println("Enter discount expiry date: ");
        String expiryDate = sc.next();
        sc.close();
        Discount.addDiscount(code, percentage, expiryDate);
    }

    public void removeDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter discount code: ");
        String code = sc.nextLine();
        sc.close();
        Discount.removeDiscount(code);
    }

    public void printDiscounts() {
        Discount.printDiscounts();
    }

    public void updateDiscount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter discount code: ");
        String code = sc.nextLine();
        sc.close();
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
            switch (choix) {
                case 1:
                    System.out.println("Enter the new percentage : ");
                    double percentage = sc.nextDouble();
                    discount.setPercentage(percentage);
                    break;
                case 2:
                    System.out.println("Enter the new expiry date : ");
                    String expiryDate = sc.next();
                    discount.setExpiryDate(expiryDate);
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
