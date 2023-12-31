package Exceptions;
/**
 * This exception is thrown when there is not enough money in the account.
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        super("Not enough money in your account.");
    }
}
