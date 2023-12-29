package Shipping;

import Orders.Order;

public class StandardShipping extends ShippingMethod {
    public StandardShipping() {
        super("Standard Shipping");
    }

    public double calculateShippingCost(Order order) {
        //Simple simulation of the shipping cost calculation
        return 50;
    }
}
