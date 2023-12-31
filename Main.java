import java.util.HashMap;
import java.util.Scanner;

import Products.Books;
import Products.Clothing;
import Products.Electronics;
import Products.Product;
import Products.SportsAndOutDoor;
import Users.Admin;
import Users.Customer;
import Users.ProductManager;
import Users.User;

public class Main {
    public static void main(String[] args) {
        // this section simulates the database
        Scanner sc = new Scanner(System.in);
        App app = new App();
        {

            ProductManager.products = new HashMap<>();
            User.users = new HashMap<>();
            // create an object admin and a customer and a product manager
            // and then add them to the hashmap

            Customer customer1 = new Customer("customer1", "customer1@gmail.com", "customer1address", "customer1", 20,
                    12345678, "customer1password");
            Admin admin1 = new Admin("admin1", "admin1@gmail.com", "admin1address", "admin1", 25, 98765432,
                    "admin1password");
            ProductManager productManager1 = new ProductManager("productManager1", "productmanager1@gmail.com",
                    "productmanager1address", "productmanager1", 30, 15935764, "productmanager1password");

            User.users.put(customer1.getUsername(), customer1);
            User.users.put(admin1.getUsername(), admin1);
            User.users.put(productManager1.getUsername(), productManager1);
            // add a book to the products list
            String[] genres = { "genre1", "genre2" };
            Books book1 = new Books(1, "book1name", "book1description", 100.50, 10, "book1imgurl", "book1author",
                    "book1publisher", genres);
            ProductManager.products.put(book1.getId(), book1);

            Clothing clothing1 = new Clothing(2, "clothing1name", "clothing1description", 200.50, 20, "clothing1imgurl",
                    new String[] { "S", "M", "L" }, new String[] { "red", "blue", "green" });
            ProductManager.products.put(clothing1.getId(), clothing1);

            Electronics electronics1 = new Electronics(3, "electronics1name", "electronics1description", 300.50, 30,
                    "electronics1imgurl", new String[] { "spec1", "spec2", "spec3" });
            ProductManager.products.put(electronics1.getId(), electronics1);

            SportsAndOutDoor sportsAndOutDoor1 = new SportsAndOutDoor(4, "sportsAndOutDoor1name",
                    "sportsAndOutDoor1description", 400.50, 40, "sportsAndOutDoor1imgurl", "sportsAndOutDoor1type",
                    "sportsAndOutDoor1brand", "sportsAndOutDoor1material", "sportsAndOutDoor1weight",
                    "sportsAndOutDoor1sportType");
            for (int i = 0; i < 4; i++) {
                Product.incrementIdCounter();
            }
            ProductManager.products.put(sportsAndOutDoor1.getId(), sportsAndOutDoor1);

            User.users.put(customer1.getUsername(), customer1);
            User.users.put(admin1.getUsername(), admin1);
            User.users.put(productManager1.getUsername(), productManager1);
        }

        System.out.println("Welcome to our online store");
        System.out.println("Please choose one of the following options : ");

        int choix = 0;
        User user = null;
        while (choix != 4) {
            System.out.println("1- Login ");
            System.out.println("2- Create a new account ");
            System.out.println("3- Reset password ");
            System.out.println("4- Exit ");
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Please enter an integer");
            }
            switch (choix) {
                case 1:
                    try {
                        user = app.login();
                        choix = 4;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 2:
                    app.createUser();
                    break;
                case 3:
                    app.changePassword();
                    break;
                case 4:
                    System.out.println("Closing Menu !");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        if (user != null && user.getIsLoggedIn()) {
            int permissionLevel = user.getPermissionLevel();
            if (permissionLevel == 0) {
                choix = 0;
                while (choix != 10) {
                    System.out.println("Welcome Admin");
                    System.out.println("Please choose one of the following options : ");
                    System.out.println("1- View all users");
                    System.out.println("2- View a user");
                    System.out.println("3- Block a user");
                    System.out.println("4- View all products");
                    System.out.println("5- Filter products");
                    System.out.println("6- Add a product");
                    System.out.println("7- Edit a product");
                    System.out.println("8- Delete a product");
                    System.out.println("9- Logout");
                    System.out.println("10- Exit");
                    choix = sc.nextInt();
                    sc.nextLine();
                    switch (choix) {
                        case 1:
                            app.viewAllUsers((Admin) user);
                            break;
                        case 2:
                            app.viewUser((Admin) user);
                            break;
                        case 3:
                            app.blockUser((Admin) user);
                            break;
                        case 4:
                            app.viewAllProducts();
                            break;
                        case 5:
                            app.searchProduct();
                            break;
                        case 6:
                            app.addProducts((Admin) user);
                            break;
                        case 7:
                            app.updateProductInventory((Admin) user);
                            break;
                        case 8:
                            app.removeProduct((Admin) user);
                            break;
                        case 9:
                            app.logout(user);
                            System.out.println("Logging out !");
                            choix = 10;
                            break;
                        case 10:
                            System.out.println("Closing Menu !");
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
            } else if (permissionLevel == 1) {
                choix = 0;
                while (choix != 11) {
                    System.out.println("Welcome Product Manager");
                    System.out.println("Please choose one of the following options : ");
                    System.out.println("1- View all products");
                    System.out.println("2- Filter products");
                    System.out.println("3- Add a product");
                    System.out.println("4- Edit a product");
                    System.out.println("5- Delete a product");
                    System.out.println("6- AddDiscount");
                    System.out.println("7- RemoveDiscount");
                    System.out.println("8- UpdateDiscount");
                    System.out.println("9- Show all discounts");
                    System.out.println("10- Logout");
                    System.out.println("11- Exit");
                    choix = sc.nextInt();
                    sc.nextLine();
                    switch (choix) {
                        case 1:
                            app.viewAllProducts();
                            break;
                        case 2:
                            app.searchProduct();
                            break;
                        case 3:
                            app.addProducts((ProductManager) user);
                            break;
                        case 4:
                            app.updateProductInventory((ProductManager) user);
                            break;
                        case 5:
                            app.removeProduct((ProductManager) user);
                            break;
                        case 6:
                            app.addDiscount((ProductManager) user);
                            break;
                        case 7:
                            app.removeDiscount((ProductManager) user);
                            break;
                        case 8:
                            app.updateDiscount((ProductManager) user);
                            break;
                        case 9:
                            app.printDiscounts((ProductManager) user);
                            break;
                        case 10:
                            app.logout(user);
                            System.out.println("Logging out !");
                            choix = 11;
                            break;
                        case 11:
                            System.out.println("Closing Menu !");
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
            } else if (permissionLevel == 2) {
                choix = 0;
                while (choix != 12) {
                    System.out.println("Welcome Customer");
                    System.out.println("Please choose one of the following options : ");
                    System.out.println("1- View all products");
                    System.out.println("2- Filter products");
                    System.out.println("3- Add to cart");
                    System.out.println("4- View cart");
                    System.out.println("5- Remove from cart");
                    System.out.println("6- Checkout");
                    System.out.println("7- View orders");
                    System.out.println("8- Clear Chart");
                    System.out.println("9- Add Payment Method");
                    System.out.println("10- View Payment Methods");
                    System.out.println("11- Logout");
                    System.out.println("12- Exit");
                    if (sc.hasNextInt()) {
                        choix = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("No integer to read");
                    }
                    switch (choix) {
                        case 1:
                            app.viewAllProducts();
                            break;
                        case 2:
                            app.searchProduct();
                            break;
                        case 3:
                            app.addProductToCart((Customer) user);
                            break;
                        case 4:
                            app.viewCart((Customer) user);
                            break;
                        case 5:
                            app.removeProductFromCart((Customer) user);
                            break;
                        case 6:
                            app.checkout((Customer) user);
                            break;
                        case 7:
                            app.viewOrders((Customer) user);
                            break;
                        case 8:
                            app.clearCart((Customer) user);
                            break;
                        case 9:
                            app.addPaymentMethod((Customer) user);
                            break;
                        case 10:
                            app.viewPaymentMethods((Customer) user);
                            break;
                        case 11:
                            app.logout(user);
                            System.out.println("Logging out !");
                            choix = 12;
                            break;
                        case 12:
                            System.out.println("Closing Menu !");
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
            }
        }
        sc.close();
    }
}
