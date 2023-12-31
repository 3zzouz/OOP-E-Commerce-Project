package Shipping;

import Orders.Order;

/**
 * The abstract class representing a shipping method.
 */
public abstract class ShippingMethod {
    private String name;

    /**
     * Constructs a shipping method with the specified name.
     * 
     * @param name the name of the shipping method
     */
    public ShippingMethod(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the shipping method.
     * 
     * @return the name of the shipping method
     */
    public String getName() {
        return this.name;
    }

    /**
     * Calculates the shipping cost for the given order.
     * 
     * @param order the order for which to calculate the shipping cost
     * @return the calculated shipping cost
     */
    public abstract double calculateShippingCost(Order order);
}
