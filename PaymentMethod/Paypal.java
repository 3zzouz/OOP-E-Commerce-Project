package PaymentMethod;

public class Paypal extends PaymentMethod {
    private String PaypalEmail, PaypalPassword;

    public Paypal(String PaypalEmail, String PaypalPassword) {
        super("Paypal");
        this.PaypalEmail = PaypalEmail;
        this.PaypalPassword = PaypalPassword;
    }

    protected String getPaypalEmail() {
        return PaypalEmail;
    }

    protected String getPaypalPassword() {
        return PaypalPassword;
    }

    public String toString() {
        return PaypalEmail + " , " + PaypalPassword;
    }
}
