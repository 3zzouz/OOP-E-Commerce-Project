package Shipping;

import Orders.Order;

public abstract class ShippingMethod {
    private String name;

    public ShippingMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract double calculateShippingCost(Order order);

}
