
public class UPIPayment extends AbstractPayment implements Retryable {
    private String upiPin;

    public UPIPayment(String upiPin) { this.upiPin = upiPin; }

    @Override
    public String getPaymentType() { return "UPI"; }

    @Override
    public void processPayment(double amount, String recipientId) throws PaymentException {
        BeneficiaryService.findBeneficiary(recipientId);
        
        if (amount <= 0) {
            throw new InvalidAmountException("Negative or zero amount is not allowed.");
        }
        
        // Scenario 4: Invalid Credentials
        if (!"1234".equals(upiPin)) {
            throw new InvalidCredentialsException("UPI PIN is incorrect.");
        }

        log("Paying ₹" + amount + " via UPI.");
        this.amount = amount;
        this.transactionId = "UPI-" + System.currentTimeMillis();
        log("Payment successful. Tx ID: " + this.transactionId);
    }
    
    @Override
    public boolean canRetry(PaymentException e) {
        return e instanceof PaymentGatewayTimeoutException;
    }
}