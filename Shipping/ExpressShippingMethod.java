package Shipping;

import Orders.Order;

/**
 * Represents an express shipping method for an order.
 */
public class ExpressShippingMethod extends ShippingMethod {
    // default constructor
    public ExpressShippingMethod() {
        super("Express Shipping");
    }

    public double calculateShippingCost(Order order) {
        // Simple simulation of the shipping cost calculation
        return 150;
    }
}
