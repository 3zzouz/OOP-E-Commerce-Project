package Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Cart.Carts;
import Orders.Order;
import PaymentMethod.CreditCard;
import PaymentMethod.PaymentMethod;
import PaymentMethod.Paypal;

public class Customer extends User {
    private ArrayList<Order> orders;
    private Carts cart;
    private PaymentMethod[] paymentMethods;
    private PaymentMethod preferredPaymentMethod;
    private int[] occurenceCounter = new int[2];

    Customer() {
        this.orders = new ArrayList<>();
        this.paymentMethods = new PaymentMethod[2];
        this.permissionLevel = 0;
        updateCustomersInformation();
    }

    public void updateCustomersInformation() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Data/Customers.csv", true));
            writer.write(this.username + " , " + paymentMethods[0].toString() + " , "
                    + paymentMethods[1].toString() + " , " + preferredPaymentMethod.toString() + " , "
                    + occurenceCounter[0] + " , " + occurenceCounter[1] + " , " + orders.toString());

            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // supposed that there are 0 to 2 payment methods per Customer
    public void addPaymentMethod() {
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        while (choix != 3) {
            System.out.println("Enter your Payment Method : ");
            System.out.println("1- Credit Card");
            System.out.println("2- Paypal");
            System.out.println("3- Exit");
            switch (choix) {
                case 1:
                    try {
                        System.out.println("Enter your Credit Card Number : ");
                        int cardNumber = sc.nextInt();
                        System.out.println("Enter your Credit Card Holder Name : ");
                        String name = sc.next();
                        System.out.println("Enter your Credit Card Expiry Date (format: dd/MM/yyyy) : ");
                        String expiryDateString = sc.next();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date expiryDate = formatter.parse(expiryDateString);
                        System.out.println("Enter your Credit Card CVV : ");
                        int cvv = sc.nextInt();
                        this.paymentMethods[0] = new CreditCard(cardNumber, name, expiryDate, cvv);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                        choix = 0;
                    }

                case 2:
                    try {
                        System.out.println("Enter your Paypal Email : ");
                        String email = sc.next();
                        System.out.println("Enter your Paypal Password : ");
                        String password = sc.next();
                        this.paymentMethods[1] = new Paypal(email, password);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                        choix = 0;
                    }
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
    }

    public void login(String password) {
        super.login(password);
        this.cart = new Carts(this);
    }

    public void logout() {
        super.logout();
        this.cart.emptyCart();
    }

    public void setPreferredPaymentMethod() {
        if (occurenceCounter[0] > occurenceCounter[1]) {
            this.preferredPaymentMethod = paymentMethods[0];
        } else {
            this.preferredPaymentMethod = paymentMethods[1];
        }
    }

    public PaymentMethod getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void addProductToCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to add products to cart");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String productName = sc.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.close();
        cart.addProduct(productName, quantity);
    }

    public void removeProductFromCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to remove products from cart");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String productName = sc.nextLine();
        sc.close();
        cart.removeProduct(productName);
    }

    public void clearCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to clear cart");
            return;
        }
        cart.emptyCart();
    }

    public Carts getCart() {

        return cart;
    }

    public int getNumberofOrders() {
        return orders.size();
    }

    public void placeOrder() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to place an order");
            return;
        }
        Order order = new Order(this);
        orders.add(order);
    }

    public void incrementOccurenceCounter(int index) {
        if (index > 1) {
            throw new IndexOutOfBoundsException();
        }
        occurenceCounter[index] += 1;
    };

    public PaymentMethod getPaymentMethod(int index) {
        if (index > 1)
            throw new IndexOutOfBoundsException();
        return paymentMethods[index];
    }

}
