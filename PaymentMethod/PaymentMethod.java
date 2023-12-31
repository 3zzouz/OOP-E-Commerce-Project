package PaymentMethod;

import java.util.Random;

import Exceptions.NotEnoughMoneyException;

/**
 * The PaymentMethod class represents a payment method with a name and a
 * balance.
 * It provides methods to retrieve the name and balance, as well as to reduce
 * the balance.
 */
public class PaymentMethod {
    private String name;
    private double balance;

    // parameterized constructor
    PaymentMethod(String name) {
        this.name = name;
        // this simulates the balance of the payment method
        this.balance = generateRandomBalance();
    }

    // getters
    String getName() {
        return name;
    }

    double getBalance() {
        return balance;
    }

    // simulate the balance of the payment method
    private double generateRandomBalance() {
        Random random = new Random();
        return 1000.0 + (5000.0 - 1000.0) * random.nextDouble();
    }

    // simultaneously check if the balance is enough and reduce the balance
    public void reduceBalance(double amount) throws NotEnoughMoneyException {
        if (amount > this.balance) {
            throw new NotEnoughMoneyException();
        }
        this.balance -= amount;
    }

    // override toString method
    public String toString() {    
        return name + " , " + balance;
    }
}
