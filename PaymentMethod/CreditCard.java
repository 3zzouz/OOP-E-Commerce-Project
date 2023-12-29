package PaymentMethod;

import java.util.Date;

public class CreditCard extends PaymentMethod {
    private int cardNumber, cvv;
    private Date expiryDate;
    private String name;

    public CreditCard(int cardNumber, String name, Date expiryDate, int cvv) {
        super(name);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.name = name;
    }

    protected int getCardNumber() {
        return cardNumber;
    }

    protected Date getExpiryDate() {
        return expiryDate;
    }

    protected int getCvv() {
        return cvv;
    }

    protected String getName() {
        return name;
    }

    public String toString() {
        return cardNumber + " , " + name + " , " + expiryDate + " , " + cvv;
    }
}
