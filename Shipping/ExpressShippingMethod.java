package Shipping;

import Orders.Order;

public class ExpressShippingMethod extends ShippingMethod {
    public ExpressShippingMethod() {
        super("Express Shipping");
    }

    public double calculateShippingCost(Order order) {
        // Simple simulation of the shipping cost calculation
        return 150;
    }
}
