

public abstract class AbstractPayment implements IPayment {
    protected String transactionId;
    protected double amount;

    protected void log(String message) {
        System.out.println("[LOG] " + getPaymentType() + ": " + message);
    }

    public abstract void processPayment(double amount, String recipientId) throws PaymentException;
}