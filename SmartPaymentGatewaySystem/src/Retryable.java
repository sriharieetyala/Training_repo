
public interface Retryable {
	boolean canRetry(PaymentException e);
}
