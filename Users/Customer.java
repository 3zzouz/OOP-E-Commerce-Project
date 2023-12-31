package Users;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import Cart.Carts;
import Orders.Order;
import PaymentMethod.CreditCard;
import PaymentMethod.PaymentMethod;
import PaymentMethod.Paypal;

public class Customer extends User {
    private HashMap<String, Order> orders;
    private Carts cart;
    private PaymentMethod[] paymentMethods;
    private PaymentMethod preferredPaymentMethod;
    private int[] occurenceCounter = new int[2];

    Customer() {
        super();
        this.orders = new HashMap<>();
        this.paymentMethods = new PaymentMethod[2];
        this.permissionLevel = 2;
        appendFileAccounts(this);
        users.put(username, this);
        System.out.println("Account Created Successfully");
        updateCustomersInformation();
    }

    protected Customer(String username, String name, String email, String address, String Password, int age,
            int phoneNumber, String accountCreationDate, String lastLoginDate, String isBlocked,
            String isLoggedIn) {

        super(username, name, email, address, Password, age, phoneNumber, accountCreationDate, lastLoginDate, isBlocked,
                isLoggedIn);
        this.orders = new HashMap<>();
        this.paymentMethods = new PaymentMethod[2];
        this.permissionLevel = 2;
        updateCustomersInformation();

    }

    public String toString() {
        return super.toString() + " , " + paymentMethods[0].toString() + " , "
                + paymentMethods[1].toString() + " , " + preferredPaymentMethod.toString() + " , "
                + occurenceCounter[0] + " , " + occurenceCounter[1];
    }

    public String toStringOrders() {
        String s = "";
        for (Order order : orders.values()) {
            s += order.toString() + "//";
        }
        return s;
    }

    public void updateCustomersInformation() {
        writeFileAccounts(users);
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
        int productID = sc.nextInt();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.close();
        cart.addProduct(productID, quantity);
    }

    public void removeProductFromCart() {
        if (!isLoggedIn) {
            System.out.println("You must be logged in to remove products from cart");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product id to remove : ");
        int productid = sc.nextInt();
        sc.close();
        cart.removeProduct(productid);
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
        orders.put(order.getOrderID(), order);
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

    public void setCart(Carts carts) {
        this.cart = carts;
    }

}
