public class BeneficiaryNotFoundException extends PaymentException {
    public BeneficiaryNotFoundException(String identifier) { 
        super("Beneficiary not found with identifier: " + identifier); 
    }
}