package Orders;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import Discount.Discount;
import Products.Product;
import Shipping.ExpressShippingMethod;
import Shipping.ShippingMethod;
import Shipping.StandardShipping;
import Users.Customer;
import Users.ProductManager;
import Utility.DateFormat;

/**
 * Represents an order made by a customer.
 * 
 * This class handles the order process, including calculating the total price,
 * applying discounts, setting the order ID, canceling the order, shipping the
 * order,
 * and simulating the checkout process.
 * 
 * The order status can be one of the following: "Pending", "Processing",
 * "Shipped",
 * "Delivered", or "Canceled".
 * 
 * The order ID is generated based on the customer's username and a unique
 * counter.
 * 
 * The total price of the order is calculated by summing the prices of the
 * products
 * in the customer's cart and adding the shipping cost.
 * 
 * The order can be canceled within 72 hours of placing the order.
 * 
 * The order can be shipped, which changes the order status to "Shipped".
 * 
 * The order can be paid, which reduces the customer's balance and updates the
 * preferred payment method.
 * 
 * Notifications can be pushed to the product manager when a product is running
 * out of stock.
 */
public class Order {

    private ShippingMethod shippingMethod;
    private int idCounter;
    Customer customer;
    DateFormat orderDate;
    String orderStatus;
    String orderID;
    HashMap<Integer, Product> products;
    double totalPrice;

    // Constructor (simulates the order process)
    public Order(Customer customer) {
        this.customer = customer;
        idCounter = this.customer.getNumberofOrders();
        if (!customer.isLoggedIn()) {
            System.out.println("You must be logged in to make an order");
            return;
        }
        if (customer.getCart().isChartEmpty()) {
            System.out.println("Your cart is empty");
            return;
        }
        products = customer.getCart().getProducts();
        totalPrice = 0;
        // to calculate the total price of the order
        for (Product product : products.values()) {
            int quantityInStock = ProductManager.getProductStockQuantity(product.getId());
            if (quantityInStock < product.getStockQuantity()) {
                System.out.println("Not enough quantity in stock for product " + product.getName());
                product.setStockQuantity(quantityInStock);
            }
            totalPrice += product.getPrice() * product.getStockQuantity();
            PushNotificationToProductManager(product.getName(), quantityInStock);
        }
        // to choose the shipping method
        System.out.println("Choose the shipping method : ");
        System.out.println("1- Standard Shipping");
        System.out.println("2- Express Shipping");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        sc.nextLine();
        while (choix != 1 && choix != 2) {
            System.out.println("Invalid choice");
            System.out.println("Choose the shipping method : ");
            System.out.println("1- Standard Shipping");
            System.out.println("2- Express Shipping");
            choix = sc.nextInt();
            sc.nextLine();
        }
        if (choix == 1) {

            setShippingMethod(new StandardShipping());
        } else {

            setShippingMethod(new ExpressShippingMethod());
        }
        // to apply shipping fees
        totalPrice = calculateTotalPrice();
        // Applying discount
        System.out.println("Do you have a discount code ? (y/n)");
        String choice = sc.nextLine();
        if (choice.equals("y")) {
            System.out.println("Enter your discount code : ");
            String code = sc.nextLine();
            Discount discount = Discount.getDiscount(code);
            // if the discount is valid and not expired then apply it
            if (discount != null && discount.getExpiryDate().getDate().getTime() > System.currentTimeMillis()) {
                totalPrice *= (1 - discount.getPercentage() / 100);
            }
        }
        try {
            orderStatus = "Pending";
            setOrderID();
            // checkout
            PayOrder();
            orderStatus = "Processing";
            // update the stock quantity of the products
            for (Product product : products.values())
                ProductManager.updateProductQuantity(product.getId(), -product.getStockQuantity());
            // empty the cart
            customer.getCart().emptyCart();
            shipOrder();
            orderDate = new DateFormat(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            orderStatus = "Failed";

        } finally {
            // update the total price of the cart based on the discount and shipping cost
            // and whether the order was successful or not
            customer.getCart().setTotalPrice(totalPrice);
            customer.setPreferredPaymentMethod();
        }
    }

    // setting the order ID
    public void setOrderID() {
        orderID = customer.getUsername() + "//" + idCounter;
        idCounter++;
    }

    // to cancel an order
    public void ReturnOrder() {
        if (orderStatus.equals("Canceled")) {
            System.out.println("Order already canceled");
            return;
        }
        if (orderStatus.equals("Delivered")) {
            System.out.println("Order already delivered");
            return;
        }
        if (orderStatus.equals("Shipped")) {
            System.out.println("Order already shipped");
            return;
        }
        // cannot cancel an order after 72 hours
        if (orderDate.getDate().getTime() - System.currentTimeMillis() > 72 * 60 * 60 * 1000) {
            System.out.println("Cannot Cancel Order after 72 hours");
            return;
        }
        orderStatus = "Canceled";
    }

    // simulates the shipping process
    public void shipOrder() {
        if (orderStatus.equals("Canceled")) {
            System.out.println("Order already canceled");
            return;
        }
        if (orderStatus.equals("Delivered")) {
            System.out.println("Order already delivered");
            return;
        }
        if (orderStatus.equals("Shipped")) {
            System.out.println("Order already shipped");
            return;
        }
        // here goes the logic of shipping the order
        orderStatus = "Shipped";
    }

    // simulates checkout process
    public void PayOrder() {
        if (orderStatus.equals("Canceled")) {
            System.out.println("Order already canceled");
            return;
        }
        if (orderStatus.equals("Delivered")) {
            System.out.println("Order already delivered");
            return;
        }
        if (orderStatus.equals("Shipped")) {
            System.out.println("Order already shipped");
            return;
        }
        if (orderStatus.equals("Processing")) {
            System.out.println("Order is being processed");
            return;
        }
        try {
            int choix = 0;
            while (choix != 3) {
                System.out.println("Enter the payment Method : ");
                System.out.println("1- PaCredit Card");
                System.out.println("2- Paypal");
                System.out.println("3- Exit");
                Scanner sc = new Scanner(System.in);
                choix = sc.nextInt();
                sc.nextLine();
                switch (choix) {
                    case 1:
                        customer.getPaymentMethod(0).reduceBalance(totalPrice);
                        customer.incrementOccurenceCounter(0);
                        break;
                    case 2:
                        customer.getPaymentMethod(1).reduceBalance(totalPrice);
                        customer.incrementOccurenceCounter(1);
                        break;
                    case 3:
                        System.out.println("Exit !");
                        break;
                    default:
                        break;
                }
            }
            customer.setPreferredPaymentMethod();
        } catch (Exception e) {
            throw new RuntimeException("Payment Failed");
        }
    }

    // overriding toString method
    public String toString() {
        String res = "";
        res += orderID + ",";
        res += orderDate + ",";
        res += orderStatus + ",";
        for (Product product : products.values())
            res += product.toString() + ",";
        res += totalPrice;
        return res;
    }

    // to push notifications to the product manager (simulates the notification
    // process when a product is running out of stock)
    public void PushNotificationToProductManager(String productName, int quantityInStock) {
        if (quantityInStock < 10)
            ProductManager.receiveNotifications("Product " + productName + " is running out of stock");
    }

    // setter for shipping method
    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    // to calculate the total price of the order (including shipping cost)
    public double calculateTotalPrice() {
        double shippingCost = this.shippingMethod.calculateShippingCost(this);
        return this.totalPrice + shippingCost;
    }

    // getter of the order ID
    public String getOrderID() {
        return orderID;
    }

}
