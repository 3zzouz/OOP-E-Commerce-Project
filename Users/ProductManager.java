package Users;

import Products.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Discount.Discount;
import Interfaces.ProductRW;

public class ProductManager extends User implements ProductRW {

    public static HashMap<Integer, Product> products;

    public ProductManager() {
        super();
        setProducts();
        permissionLevel = 1;
        appendFileAccounts(this);
        users.put(username, this);
        System.out.println("Account Created Successfully");

    }

    public ProductManager(String username, String name, String email, String address, String Password, int age,
            int phoneNumber,
            String accountCreationDate, String lastLoginDate, String isBlocked, String isLoggedIn) {

        super(username, name, email, address, Password, age, phoneNumber, accountCreationDate, lastLoginDate, isBlocked,
                isLoggedIn);
        setProducts();
        this.permissionLevel = 1;
        users.put(username, this);
        System.out.println("Account Created Successfully");
        updateUsersLists();
    }

    // to synchronize the products list with the file
    public void setProducts() {
        products.clear();
        ArrayList<String> productsArray = readFileProducts();
        for (String product : productsArray) {
            String[] productInfo = product.split(" , ");
            if (productInfo[0].equals("Clothing")) {
                String[] sizes = productInfo[7].substring(1, productInfo[7].length() - 1).split(", ");
                String[] colors = productInfo[8].substring(1, productInfo[8].length() - 1).split(", ");
                Clothing clothing = new Clothing(Integer.parseInt(productInfo[1]), productInfo[2], productInfo[3],
                        Double.parseDouble(productInfo[4]), Integer.parseInt(productInfo[5]), productInfo[6], sizes,
                        colors);
                products.put(clothing.getId(), clothing);
            } else if (productInfo[0].equals("Electronics")) {
                String[] specs = productInfo[7].substring(1, productInfo[7].length() - 1).split(", ");
                Electronics electronics = new Electronics(Integer.parseInt(productInfo[1]), productInfo[2],
                        productInfo[3], Double.parseDouble(productInfo[4]), Integer.parseInt(productInfo[5]),
                        productInfo[6], specs);
                products.put(electronics.getId(), electronics);
            } else if (productInfo[0].equals("Books")) {
                String author = productInfo[7];
                String publisher = productInfo[8];
                String[] genres = productInfo[9].substring(1, productInfo[9].length() - 1).split(", ");
                Books books = new Books(Integer.parseInt(productInfo[1]), productInfo[2], productInfo[3],
                        Double.parseDouble(productInfo[4]), Integer.parseInt(productInfo[5]), productInfo[6], author,
                        publisher, genres);
                products.put(books.getId(), books);
            } else if (productInfo[0].equals("SportsAndOutDoor")) {
                String type, brand, material, weight, sportType;
                type = productInfo[7];
                brand = productInfo[8];
                material = productInfo[9];
                weight = productInfo[10];
                sportType = productInfo[11];
                SportsAndOutDoor sportsAndOutDoor = new SportsAndOutDoor(Integer.parseInt(productInfo[1]),
                        productInfo[2], productInfo[3], Double.parseDouble(productInfo[4]),
                        Integer.parseInt(productInfo[5]), productInfo[6], type, brand, material, weight, sportType);
                products.put(sportsAndOutDoor.getId(), sportsAndOutDoor);
            }
        }
    }

    // method to add a product to the list
    public void addProducts() {
        setProducts();
        Product product;
        int choix = 0;
        while (choix != 5) {
            System.out.println("Enter your Product Category : ");
            System.out.println("1- Clothes");
            System.out.println("2- Electronics");
            System.out.println("3- Books");
            System.out.println("4- SportsAndOutdoor");
            System.out.println("5- Exit");
            switch (choix) {
                case 1:
                    product = new Clothing();
                    appendFileProducts(product);
                    products.put(product.getId(), product);
                    break;
                case 2:
                    product = new Electronics();
                    appendFileProducts(product);
                    products.put(product.getId(), product);
                    break;
                case 3:
                    product = new Books();
                    appendFileProducts(product);
                    products.put(product.getId(), product);
                    break;
                case 4:
                    product = new SportsAndOutDoor();
                    appendFileProducts(product);
                    products.put(product.getId(), product);
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
        setProducts();
        return products.containsKey(id);
    }

    // method to get a product by id
    public static Product getProduct(int id) {
        if (!products.containsKey(id)) {
            return null;
        }
        return products.get(id);
    }

    public static double getProductPrice(int id) {
        if (!products.containsKey(id)) {
            return -1;
        }
        return products.get(id).getPrice();
    }

    public static int getProductStockQuantity(int id) {
        if (!products.containsKey(id)) {
            return -1;
        }
        return products.get(id).getStockQuantity();
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
        products.remove(productId);
        updateProductsLists();
    }

    protected void updateProduct() {
        setProducts();
        System.out.println(
                "Enter the id of product you want to update : ");
        Scanner sc = new Scanner(System.in);
        int prodId = sc.nextInt();
        sc.close();
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
            switch (choix) {
                case 1:
                    System.out.println("Enter the new name : ");
                    String name = sc.next();
                    product.setName(name);
                    break;
                case 2:
                    System.out.println("Enter the new description : ");
                    String description = sc.next();
                    product.setDescription(description);
                    break;
                case 3:
                    System.out.println("Enter the new price : ");
                    double price = sc.nextDouble();
                    product.setPrice(price);
                    break;
                case 4:
                    System.out.println("Enter the new stock quantity : ");
                    int stockQuantity = sc.nextInt();
                    product.setStockQuantity(stockQuantity);
                    break;
                case 5:
                    System.out.println("Enter the new image URL : ");
                    String imageUrl = sc.next();
                    product.setImageUrl(imageUrl);
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    break;
            }
        }
        System.out.println("Product updated successfully");
        if (choix < 6) {
            updateProductsLists();
        }

    }

    protected static void updateProductsLists() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ProductRW.fileProducts));
            for (Product product : products.values()) {
                writer.write(product.toString());
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
        for (Product product : products.values()) {
            System.out.println(product.toString());
        }
    }

    public static List<Product> searchProducts(String searchTerm, Map<String, Object> filters) {
        List<Product> results = new ArrayList<>();

        for (Product product : products.values()) {
            // Check if the product name contains the search term
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                boolean match = true;

                // Check each filter
                for (Map.Entry<String, Object> filter : filters.entrySet()) {
                    String filterField = filter.getKey();
                    Object filterValue = filter.getValue();

                    // Match the filter field with the corresponding product info field
                    switch (filterField) {
                        case "price":
                            double price = product.getPrice();
                            double[] priceRange = (double[]) filterValue;
                            if (price < priceRange[0] && priceRange[0] != 0
                                    || price > priceRange[1] && priceRange[1] != 0) {
                                match = false;
                            }
                            break;
                        case "quantity":
                            if (product.getStockQuantity() == Integer.parseInt(filterValue.toString())
                                    || product.getStockQuantity() == 0) {
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

    public static void updateProductQuantity(int id, int quantity) {
        if (!products.containsKey(id)) {
            return;
        }
        Product product = products.get(id);
        product.setStockQuantity(product.getStockQuantity() + quantity);
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
