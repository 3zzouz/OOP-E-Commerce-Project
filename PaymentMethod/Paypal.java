package PaymentMethod;

/**
 * The Paypal class represents a payment method using Paypal.
 * It extends the PaymentMethod class and provides additional functionality
 * specific to Paypal.
 */
public class Paypal extends PaymentMethod {
    private String PaypalEmail, PaypalPassword;

    // parameterized constructor
    public Paypal(String PaypalEmail, String PaypalPassword) {
        super("Paypal");
        this.PaypalEmail = PaypalEmail;
        this.PaypalPassword = PaypalPassword;
    }

    // override toString method
    public String toString() {
        return super.toString() + " , " + this.PaypalEmail + " , " + this.PaypalPassword;
    }

    // getters
    protected String getPaypalEmail() {
        return PaypalEmail;
    }

    protected String getPaypalPassword() {
        return PaypalPassword;
    }

    // setters
    protected void setPaypalEmail(String PaypalEmail) {
        if (PaypalEmail == null || PaypalEmail.isEmpty())
            throw new IllegalArgumentException("PaypalEmail cannot be empty");
        this.PaypalEmail = PaypalEmail;
    }

    protected void setPaypalPassword(String PaypalPassword) {
        if (PaypalPassword == null || PaypalPassword.isEmpty())
            throw new IllegalArgumentException("PaypalPassword cannot be empty");
        this.PaypalPassword = PaypalPassword;
    }
}
