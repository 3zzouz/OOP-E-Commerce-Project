package Orders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import Discount.Discount;
import Shipping.ShippingMethod;
import Users.Customer;
import Users.ProductManager;

public class Order {

    private ShippingMethod shippingMethod;
    private int idCounter;
    Customer customer;
    Date orderDate;
    String orderStatus;
    String orderID;
    ArrayList<String> productsNames;
    ArrayList<Integer> productsQuantities;
    ArrayList<Double> productsPrices;
    double totalPrice;

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
        productsNames = customer.getCart().getProductsName();
        productsQuantities = customer.getCart().getQuantities();
        productsPrices = customer.getCart().getPrices();
        for (int i = 0; i < productsNames.size(); i++) {
            int quantityInStock = ProductManager.getProductStockQuantity(productsNames.get(i));
            if (quantityInStock < productsQuantities.get(i)) {
                System.out.println("Not enough quantity in stock for product " + productsNames.get(i));
                productsQuantities.set(i, quantityInStock);
                productsPrices.set(i, quantityInStock * productsPrices.get(i) / productsQuantities.get(i));
            }
            PushNotificationToProductManager(productsNames.get(i), quantityInStock);
        }
        totalPrice = 0;
        for (int i = 0; i < productsNames.size(); i++) {
            totalPrice += productsPrices.get(i);
        }
        totalPrice = calculateTotalPrice();
        // Applying discount
        System.out.println("Do you have a discount code ? (y/n)");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equals("y")) {
            System.out.println("Enter your discount code : ");
            String code = sc.nextLine();
            Discount discount = Discount.getDiscount(code);
            if (discount != null) {
                totalPrice -= totalPrice * discount.getPercentage();
            }
        }
        try {
            orderStatus = "Pending";
            setOrderID();
            PayOrder();
            orderStatus = "Processing";
            shipOrder();
            for (int i = 0; i < productsNames.size(); i++) {
                ProductManager.updateProductQuantity(productsNames.get(i), productsQuantities.get(i));
            }
            customer.getCart().emptyCart();
            orderDate = new Date(System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            orderStatus = "Failed";
            return;
        }
    }

    public void setOrderID() {
        orderID = customer.getUsername() + idCounter;
        idCounter++;
    }

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
        if (orderDate.getTime() - System.currentTimeMillis() > 72 * 60 * 60 * 1000) {
            System.out.println("Cannot Cancel Order after 72 hours");
            return;
        }
        orderStatus = "Canceled";
    }

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
            throw new IllegalArgumentException("Payment failed");
        }
    }

    public String toString() {
        String res = "";
        res += orderID + " , ";
        res += orderDate + " , ";
        res += orderStatus + " , ";
        for (int i = 0; i < productsNames.size(); i++) {
            res += productsNames.get(i) + " , " + productsQuantities.get(i) + " , " + productsPrices.get(i) + " , ";
        }
        res += totalPrice;
        return res;
    }

    public void PushNotificationToProductManager(String productName, int quantityInStock) {
        if (quantityInStock < 10)
            ProductManager.receiveNotifications("Product " + productName + " is running out of stock");
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public double calculateTotalPrice() {
        double shippingCost = this.shippingMethod.calculateShippingCost(this);
        return this.totalPrice + shippingCost;
    }
}
