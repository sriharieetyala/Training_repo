
public interface Refundable {
    boolean initiateRefund(String transactionId, double amount);
}