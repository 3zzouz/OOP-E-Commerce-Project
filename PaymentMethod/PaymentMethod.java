package PaymentMethod;

import java.util.Random;

public class PaymentMethod {
    private String name;
    private double balance;

    PaymentMethod(String name) {
        this.name = name;
        // this simulates the balance of the payment method
        this.balance = generateRandomBalance();
    }

    String getName() {
        return name;
    }

    double getBalance() {
        return balance;
    }

    private double generateRandomBalance() {
        Random random = new Random();
        return 1000.0 + (5000.0 - 1000.0) * random.nextDouble();
    }

    public void reduceBalance(double amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Not enough balance");
        }
        this.balance -= amount;
    }

}
