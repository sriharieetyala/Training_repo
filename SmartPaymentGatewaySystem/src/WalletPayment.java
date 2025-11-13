
public class WalletPayment extends AbstractPayment implements Refundable {
    private double currentBalance = 100.00;

    @Override
    public String getPaymentType() { return "Wallet"; }

    @Override
    public void processPayment(double amount, String recipientId) throws PaymentException {
        BeneficiaryService.findBeneficiary(recipientId);
        
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive.");
        }
        
        // Scenario 2: Insufficient Funds
        if (amount > currentBalance) {
            throw new InsufficientBalanceException(
                "Wallet payment failed. Required: ₹" + amount + ", Available: ₹" + currentBalance
            );
        }

        log("Initiating payment of ₹" + amount + " from Wallet.");
        this.currentBalance -= amount;
        this.amount = amount;
        this.transactionId = "WALLET-" + System.currentTimeMillis();
        log("Payment successful. New Wallet Balance: ₹" + currentBalance);
    }
    
    @Override
    public boolean initiateRefund(String transactionId, double amount) {
        log("Processing wallet refund. Amount credited: " + amount);
        this.currentBalance += amount;
        return true;
    }
}