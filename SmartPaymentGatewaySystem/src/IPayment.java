
public interface IPayment {
	void processPayment(double amount, String recipientId) throws PaymentException;
    String getPaymentType();
}
