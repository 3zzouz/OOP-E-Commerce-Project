package Users;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import Cart.Carts;
import Exceptions.WrongPasswordException;
import Orders.Order;
import PaymentMethod.CreditCard;
import PaymentMethod.PaymentMethod;
import PaymentMethod.Paypal;

/**
 * Represents a customer in the e-commerce system.
 * Inherits from the User class.
 */
public class Customer extends User {
    public HashMap<String, Order> orders;
    private Carts cart;
    private PaymentMethod[] paymentMethods = new PaymentMethod[2];
    private PaymentMethod preferredPaymentMethod;
    private int[] occurenceCounter = new int[2];

    public Customer() {
        super();
        this.orders = new HashMap<>();
        this.permissionLevel = 2;
        users.put(username, this);
        System.out.println("Account Created Successfully");
    }

    // parameterized constructor
    public Customer(String name, String email, String address, String username, int age,
            int phoneNumber, String password) {
        super(name, email, address, username, age, phoneNumber,
                password);
        this.orders = new HashMap<>();
        this.cart = new Carts(this);
        this.paymentMethods = new PaymentMethod[2];
        this.preferredPaymentMethod = null;
        this.occurenceCounter = new int[2];
    }

    // override toString method
    public String toString() {
        return super.toString() + " , " + (paymentMethods[0] != null ? paymentMethods[0].toString() : "") + " , "
                + (paymentMethods[1] != null ? paymentMethods[1].toString() : "") + " , "
                + (preferredPaymentMethod != null ? preferredPaymentMethod.toString() : "") + " , "
                + occurenceCounter[0] + " , " + occurenceCounter[1];
    }

    // toString method for orders of the customer
    public String toStringOrders() {
        String s = "";
        for (Order order : orders.values()) {
            s += order.toString() + "//";
        }
        return s;
    }

    // supposed that there are 0 to 2 payment methods per Customer the first one is
    // credit card and the second one is paypal
    public void addPaymentMethod() {
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        while (choix != 3) {
            System.out.println("Enter your Payment Method : ");
            System.out.println("1- Credit Card");
            System.out.println("2- Paypal");
            System.out.println("3- Exit");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    try {
                        System.out.println("Enter your Credit Card Number : ");
                        int cardNumber = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter your Credit Card Holder Name : ");
                        String name = sc.nextLine();
                        System.out.println("Enter your Credit Card Expiry Date (format: dd/MM/yyyy hh:mm:ss) : ");
                        String expiryDateString = sc.nextLine();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                        Date expiryDate = formatter.parse(expiryDateString);
                        System.out.println("Enter your Credit Card CVV : ");
                        int cvv = sc.nextInt();
                        sc.nextLine();
                        this.paymentMethods[0] = new CreditCard(cardNumber, name, expiryDate, cvv);
                        System.out.println("Credit Card Added Successfully");
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                        choix = 0;
                        break;
                    }

                case 2:
                    try {
                        System.out.println("Enter your Paypal Email : ");
                        String email = sc.nextLine();
                        System.out.println("Enter your Paypal Password : ");
                        String password = sc.nextLine();
                        this.paymentMethods[1] = new Paypal(email, password);
                        System.out.println("Paypal Added Successfully");
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                        choix = 0;
                        break;
                    }
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        ;
    }

    // method to login
    public void login(String password) throws WrongPasswordException {
        super.login(password);
        this.cart = new Carts(this);

    }

    // method to logout
    public void logout() {
        super.logout();
        this.cart.emptyCart();
    }

    // method to set preferred payment method
    public void setPreferredPaymentMethod() {
        if (occurenceCounter[0] > occurenceCounter[1]) {
            this.preferredPaymentMethod = paymentMethods[0];
        } else {
            this.preferredPaymentMethod = paymentMethods[1];
        }
    }

    // method to get preferred payment method
    public PaymentMethod getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    // method to add product to cart
    public void addProductToCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to add products to cart");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int productID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        cart.addProduct(productID, quantity);
    }

    // method to remove product from cart
    public void removeProductFromCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to remove products from cart");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product id to remove : ");
        int productid = sc.nextInt();
        sc.nextLine();
        cart.removeProduct(productid);
    }

    // method to clear cart
    public void clearCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to clear cart");
            return;
        }
        cart.emptyCart();
    }

    // method to get the cart
    public Carts getCart() {

        return cart;
    }

    // to get the number of orders per customer
    public int getNumberofOrders() {
        return orders.size();
    }

    // method to place order
    public void placeOrder() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to place an order");
            return;
        }
        Order order = new Order(this);
    }

    // to update the occurence counter (to know which payment method is preferred)
    public void incrementOccurenceCounter(int index) {
        if (index > 1) {
            throw new IndexOutOfBoundsException();
        }
        occurenceCounter[index] += 1;
    };

    // method to get payment method
    public PaymentMethod getPaymentMethod(int index) {
        if (index > 1)
            throw new IndexOutOfBoundsException();
        if (index == 0 && paymentMethods[0] == null) {
            throw new IllegalArgumentException("No Credit Card added");
        }
        if (index == 1 && paymentMethods[1] == null) {
            throw new IllegalArgumentException("No Paypal added");
        }
        return paymentMethods[index];
    }

    // setter for cart
    public void setCart(Carts carts) {
        this.cart = carts;
    }

    // method to view payment methods
    public void viewPaymentMethods() {
        System.out.println("Payment Methods : ");
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod != null)
                System.out.println(paymentMethod.toString());
        }
    }

}
