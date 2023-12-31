import java.util.Scanner;

import Exceptions.WrongPasswordException;
import Users.Admin;
import Users.Customer;
import Users.ProductManager;
import Users.User;

public class App {
    public User createUser() {
        System.out.println("Enter the new account type : ");
        System.out.println("1- Admin");
        System.out.println("2- Product Manager");
        System.out.println("3- Customer");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        sc.nextLine();
        ;
        User user = null;
        if (choix == 1) {
            user = new Admin();
        } else if (choix == 2) {
            user = new ProductManager();
        } else if (choix == 3) {
            user = new Customer();
        } else {
            System.out.println("Invalid choice");
        }
        return user;
    }

    public User login() throws WrongPasswordException {
        System.out.println("Enter your username : ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.nextLine();
        User.login(username, password);
        return User.users.get(username);
    }

    public void logout(User user) {
        User.logout(user.getUsername());
    }

    public void changePassword() {
        System.out.println("Enter your username : ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.println("Enter your old password : ");
        String oldPassword = sc.nextLine();
        System.out.println("Enter your new password : ");
        String newPassword = sc.nextLine();
        ;
        User.changePassword(username, oldPassword, newPassword);
    }

    public void searchProduct() {
        User.searchProduct();
    }

    public void viewAllProducts() {
        ProductManager.printProducts();
    }

    public void viewAllUsers(Admin admin) {
        admin.viewAllUsers();
    }

    public void viewUser(Admin admin) {
        admin.viewUser();
    }

    public void blockUser(Admin admin) {
        admin.blockUser();
    }

    public void unblockUser(Admin admin) {
        admin.unblockUser();
    }

    public void addProducts(ProductManager productManager) {
        productManager.addProducts();
    }

    public void removeProduct(ProductManager pm) {
        pm.removeProduct();
    }

    public void updateProductInventory(ProductManager pm) {
        pm.updateProduct();
    }

    public void addDiscount(ProductManager productManager) {
        productManager.addDiscount();
    }

    public void removeDiscount(ProductManager productManager) {
        productManager.removeDiscount();
    }

    public void updateDiscount(ProductManager productManager) {
        productManager.updateDiscount();
    }

    public void printDiscounts(ProductManager productManager) {
        productManager.printDiscounts();
    }

    public void addProductToCart(Customer customer) {
        customer.addProductToCart();
    }

    public void removeProductFromCart(Customer customer) {
        customer.removeProductFromCart();
    }

    public void viewCart(Customer customer) {
        System.out.println(customer.getCart().toString());
    }

    public void checkout(Customer customer) {
        customer.placeOrder();
    }

    public void removeProductInCart(Customer customer) {
        customer.removeProductFromCart();
    }

    public void clearCart(Customer customer) {
        customer.clearCart();
    }

    public void viewPaymentMethods(Customer customer) {
        customer.viewPaymentMethods();
    }

    public void viewOrders(Customer customer) {
        System.out.println(customer.toStringOrders());
    }

    public void addPaymentMethod(Customer customer) {
        customer.addPaymentMethod();
    }
}
