
public class TransactionFailedException extends PaymentException {
    public TransactionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}