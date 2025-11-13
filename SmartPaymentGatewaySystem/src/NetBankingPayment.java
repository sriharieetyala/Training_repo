
public class NetBankingPayment extends AbstractPayment {
    @Override
    public String getPaymentType() { return "NetBanking"; }

    @Override
    public void processPayment(double amount, String recipientId) throws PaymentException {
        BeneficiaryService.findBeneficiary(recipientId);
        
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive.");
        }

        log("Redirecting to Bank portal for payment of ₹" + amount + ".");
        this.amount = amount;
        this.transactionId = "NB-" + System.currentTimeMillis();
        log("Payment successful. Tx ID: " + this.transactionId);
    }
}