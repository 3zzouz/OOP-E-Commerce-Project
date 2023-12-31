package PaymentMethod;

import java.util.Date;

/**
 * Represents a credit card payment method.
 * Extends the PaymentMethod class.
 */
public class CreditCard extends PaymentMethod {
    private int cardNumber, cvv;
    private Date expiryDate;
    private String cardHolderName;

    // parameterized constructor
    public CreditCard(int cardNumber, String name, Date expiryDate, int cvv) {
        super("Credit Card");
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardHolderName = name;
    }

    // getters
    protected int getCardNumber() {
        return cardNumber;
    }

    protected Date getExpiryDate() {
        return expiryDate;
    }

    protected int getCvv() {
        return cvv;
    }

    protected String getCardHolderName() {
        return cardHolderName;
    }

    // override toString method
    public String toString() {
        return super.toString() + " , " + cardNumber + " , " + cardHolderName + " , " + expiryDate + " , " + cvv;
    }

    // setters
    protected void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    protected void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    protected void setCvv(int cvv) {
        this.cvv = cvv;
    }

    protected void setCardHolderName(String name) {
        this.cardHolderName = name;
    }

}
