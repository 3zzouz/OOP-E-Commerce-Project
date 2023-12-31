package Exceptions;

/**
 * This exception is thrown when an incorrect password is provided.
 */
public class WrongPasswordException extends Exception {
    /**
     * Constructs a new WrongPasswordException with a default error message.
     */
    public WrongPasswordException(){
        super("Wrong password. Try again.");
    }
}
