
public class CreditCardPayment extends AbstractPayment implements Refundable, Retryable {
    @Override
    public String getPaymentType() { return "Credit Card"; }

    @Override
    public void processPayment(double amount, String recipientId) throws PaymentException {
        Beneficiary recipient = BeneficiaryService.findBeneficiary(recipientId);

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive.");
        }
        
        log("Paying ₹" + amount + " to " + recipient.getName() + " via Credit Card.");
        
        // Simulation of internal NullPointerException (System Failure)
        if (Math.random() < 0.1) {
            String s = null;
            s.length(); // Throws NullPointerException
        }

        // Simulated Timeout (20% chance)
        if (Math.random() < 0.2) {
            throw new PaymentGatewayTimeoutException("Credit Card payment gateway unresponsive.");
        }
        
        this.amount = amount;
        this.transactionId = "CC-" + System.currentTimeMillis();
        log("Payment successful. Tx ID: " + this.transactionId);
    }

    @Override
    public boolean initiateRefund(String transactionId, double amount) {
        log("Refunding " + amount + " for transaction " + transactionId);
        return true; 
    }

    @Override
    public boolean canRetry(PaymentException e) {
        return e instanceof PaymentGatewayTimeoutException;
    }
}