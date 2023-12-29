package Cart;

import java.util.ArrayList;

import Users.Customer;
import Users.ProductManager;

public class Carts {
    private ArrayList<String> productsName;
    private ArrayList<Integer> quantities;
    private ArrayList<Double> prices;
    private double totalPrice;

    public Carts(Customer customer) {
        productsName = new ArrayList<>();
        quantities = new ArrayList<>();
        prices = new ArrayList<>();
        totalPrice = 0;
    }

    public boolean existsProduct(String productName) {
        int index = productsName.indexOf(productName);
        if (index == -1) {
            System.out.println("Product not in cart");
            return false;
        }
        System.out.println("Product in cart");
        return true;
    }

    public void addProduct(String productName, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return;
        }
        double price;
        if (existsProduct(productName)) {
            for (int i = 0; i < quantity; i++)
                incrementQuantity(productName);
            price = prices.get(productsName.indexOf(productName));
        } else {
            productsName.add(productName);
            quantities.add(quantity);
            price = ProductManager.getProductPrice(productName);
            prices.add(price * quantity);
        }
        totalPrice = totalPrice + price * quantity;
    }

    public void removeProduct(String productName) {
        int index = productsName.indexOf(productName);
        if (index == -1) {
            System.out.println("Product not in cart");
            return;
        }
        productsName.remove(index);
        quantities.remove(index);
        prices.remove(index);
    }

    public void emptyCart() {
        productsName.clear();
        quantities.clear();
        prices.clear();
        totalPrice = 0;
    }

    public void incrementQuantity(String productName) {
        int index = productsName.indexOf(productName);
        if (index == -1) {
            System.out.println("Product not in cart");
            return;
        }
        int oldQuantity = quantities.get(index);
        double uPrice = prices.get(index) / oldQuantity;
        double price = uPrice * (oldQuantity + 1);
        quantities.set(index, oldQuantity + 1);
        prices.set(index, price);
        totalPrice = totalPrice + uPrice;
    }

    public void decrementQuantity(String productName) {
        int index = productsName.indexOf(productName);
        if (index == -1) {
            System.out.println("Product not in cart");
            return;
        }
        int oldQuantity = quantities.get(index);
        if (oldQuantity == 1) {
            removeProduct(productName);
            return;
        }
        double uPrice = prices.get(index) / oldQuantity;
        double price = uPrice * (oldQuantity - 1);
        quantities.set(index, oldQuantity - 1);
        prices.set(index, price);
        totalPrice = totalPrice - uPrice;
    }

    public boolean isChartEmpty() {
        return productsName.isEmpty();
    }

    public ArrayList<String> getProductsName() {
        return productsName;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
