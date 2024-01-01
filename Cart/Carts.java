package Cart;

import java.util.HashMap;

import Products.Product;
import Users.Customer;
import Users.ProductManager;

/**
 * The Carts class represents a shopping cart for a customer in an e-commerce
 * system.
 * It allows adding and removing products, calculating the total price, and
 * displaying the cart contents.
 */
public class Carts implements Cloneable {
    private HashMap<Integer, Product> products;
    Customer customer;
    private double totalPrice;

    // Constructor
    public Carts(Customer customer) {
        products = new HashMap<Integer, Product>();
        totalPrice = 0;
        this.customer = customer;
        customer.setCart(this);
    }

    // verifies if a product exists in the cart or not
    public boolean existsProduct(int productId) {
        return products.containsKey(productId);
    }

    // add a product to the cart ( if it exists, it will update the quantity and the
    // total price)
    // if the quantity is greater than the stock quantity, it will not be added
    // if the product doesn't exist in the chart, it (a clone of the object) will be
    // added to the cart
    // if the quantity is negative, it will not be added
    // if the product ID doesn't exist among products in the inventory, it will not
    // be added
    public void addProduct(int prodID, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return;
        }
        if (quantity > ProductManager.getProductStockQuantity(prodID)) {
            System.out.println("Not enough quantity in stock");
            return;
        }
        if (!ProductManager.products.containsKey(prodID)) {
            System.out.println("Product ID doesn't exist");
            return;
        }
        double price;
        if (existsProduct(prodID)) {
            Product product = products.get(prodID);
            int oldQuantity = product.getStockQuantity();
            if (oldQuantity + quantity > ProductManager.getProductStockQuantity(prodID)) {
                System.out.println("Not enough quantity in stock");
                return;
            }
            product.setStockQuantity(oldQuantity + quantity);
            products.put(prodID, product);
            price = product.getPrice();
        } else {
            Product product = ProductManager.getProduct(prodID).clone();
            product.setStockQuantity(quantity);
            products.put(prodID, product);
            price = product.getPrice();
        }
        totalPrice = totalPrice + price * quantity;
    }

    // remove a product from the cart if it exists already
    public void removeProduct(int id) {
        if (!existsProduct(id)) {
            System.out.println("Product not in cart");
            return;
        }
        Product product = products.get(id);
        double price = product.getPrice();
        int quantity = product.getStockQuantity();
        totalPrice = totalPrice - price * quantity;
        products.remove(id);
    }

    // to empty the cart after successful checkout
    public void emptyCart() {
        products.clear();
        totalPrice = 0;
    }

    // to display the cart contents
    public String toString() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty");
            return "";
        }
        String result = "Products : ";
        for (Product product : products.values()) {
            result += product.toString() + "//";
        }
        return result + " ,Total Price : " + totalPrice;
    }

    // to verify if the cart is empty or not
    public boolean isChartEmpty() {
        return products.isEmpty();
    }

    // to return the products in the cart
    public HashMap<Integer, Product> getProducts() {
        return this.products;
    }

    public void changeProductQuantityInChart(int id, int quantity) {
        if (!existsProduct(id)) {
            System.out.println("Product not in cart");
            return;
        }
        Product product = products.get(id);
        double price = product.getPrice();
        int oldQuantity = product.getStockQuantity();
        if (quantity > ProductManager.getProductStockQuantity(id)) {
            System.out.println("Not enough quantity in stock");
            return;
        }
        totalPrice = totalPrice - price * oldQuantity;
        product.setStockQuantity(quantity);
        totalPrice = totalPrice + price * quantity;
        products.put(id, product);
    }

    // to calculate the total price of the products in the cart (without
    // discount)(useful for the checkout)
    public double getTotalPrice() {
        return totalPrice;
    }

    // setter for the total price
    public void setTotalPrice(double price) {
        totalPrice = price;
    }

}
