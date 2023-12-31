package Shipping;

import Orders.Order;

/**
 * The StandardShipping class represents a standard shipping method for an order.
 * It extends the ShippingMethod class and provides a default constructor and a method to calculate the shipping cost.
 */
public class StandardShipping extends ShippingMethod {

    /**
     * Constructs a StandardShipping object with the name "Standard Shipping".
     */
    public StandardShipping() {
        super("Standard Shipping");
    }

    /**
     * Calculates the shipping cost for the given order.
     * This method provides a simple simulation of the shipping cost calculation.
     *
     * @param order the order for which to calculate the shipping cost
     * @return the calculated shipping cost
     */
    public double calculateShippingCost(Order order) {
        return 50;
    }
}
