
import java.util.Scanner;

public class SmartPaymentGatewaySystem {

    private static final Scanner scanner = new Scanner(System.in);
    
    // Static payment objects
    private static final IPayment creditCard = new CreditCardPayment();
    private static final IPayment wallet = new WalletPayment();

    private static IPayment getPaymentMethod(int choice) {
        switch (choice) {
            case 1: return creditCard;
            case 2: 
                System.out.print("Enter UPI PIN (e.g., 1234 for success, or 9999 for invalid): ");
                String upiPin = scanner.nextLine();
                return new UPIPayment(upiPin);
            case 3: return wallet;
            case 4:
                return new NetBankingPayment();
            default: return null;
        }
    }

    public static void main(String[] args) {
        
        System.out.println("--- Welcome to the SmartPayment Gateway System ---");
        boolean continueTransaction = true;

        while (continueTransaction) {
            System.out.println("\n=================================================");
            System.out.println("             NEW TRANSACTION REQUEST             ");
            System.out.println("=================================================");
            
            IPayment payment = null;
            double amount = -1;
            String recipientId = null;

            // Step 1: Get Payment Method
            try {
                System.out.println("Choose Payment Method:");
                System.out.println("1. Credit Card");
                System.out.println("2. UPI");
                System.out.println("3. Wallet (Current Balance: ~100.00)");
                System.out.println("4. NetBanking");
                System.out.print("Enter choice (1-4): ");
                
                String choiceLine = scanner.nextLine().trim();
                if (choiceLine.isEmpty()) continue; 
                
                int choice = Integer.parseInt(choiceLine);
                payment = getPaymentMethod(choice);
                
                if (payment == null) {
                    System.err.println("ERROR: Invalid selection. Please enter a number between 1 and 4.");
                    continue;
                }

                // Step 2: Get Amount
                System.out.print("Enter Transaction Amount (e.g., -500, 250, 10): ");
                amount = Double.parseDouble(scanner.nextLine().trim());

                // Step 3: Get Recipient ID
                System.out.print("Enter Recipient ID (Use 'UPI12345678' for success, 'NONEXISTENT' for failure, or leave blank for Null ID test): ");
                recipientId = scanner.nextLine().trim();

                // Step 4: Process and Handle
                System.out.println("\n--- Processing " + payment.getPaymentType() + " payment for " + amount + " ---");
                handlePayment(payment, amount, recipientId.isEmpty() ? null : recipientId);

            } catch (NumberFormatException e) {
                System.err.println("\nINPUT ERROR: Please ensure you enter valid numbers for choices and amount.");
            } catch (Exception e) {
                System.err.println("\nUNEXPECTED ERROR: An issue occurred during input. Details: " + e.getMessage());
            }
            
            // Step 5: Check for another transaction
            System.out.print("\nDo you want to initiate another transaction? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (!response.equals("yes")) {
                continueTransaction = false;
            }
        }
        
        System.out.println("\n--- System Exit ---");
        System.out.println("Thank you for using the SmartPayment Gateway. Exiting.");
        scanner.close();
    }
    
    public static void handlePayment(IPayment payment, double amount, String recipientId) {
        try {
            payment.processPayment(amount, recipientId);
            System.out.println("\n[STATUS: SUCCESS] Transaction completed.");
            
        } catch (InvalidAmountException e) {
            System.err.println("\n[STATUS: FAILED] USER ERROR: " + e.getMessage());
            System.out.println("ACTION: Please correct the amount and try again.");
            
        } catch (InsufficientBalanceException e) {
            System.err.println("\n[STATUS: FAILED] INSUFFICIENT FUNDS: " + e.getMessage());
            System.out.println("ACTION: Please recharge or choose an alternate method.");

        } catch (InvalidCredentialsException e) {
            String maskedMessage = e.getMessage().replaceAll("PIN|password", "XXXXX");
            System.err.println("\n[STATUS: FAILED] CREDENTIALS ERROR: " + maskedMessage);
            System.out.println("ACTION: Please re-enter credentials.");
            
        } catch (BeneficiaryNotFoundException e) {
            System.err.println("\n[STATUS: FAILED] BENEFICIARY ERROR: " + e.getMessage());
            System.out.println("ACTION: Verify the recipient ID and ensure it is correct.");

        } catch (PaymentGatewayTimeoutException e) {
            System.err.println("\n[STATUS: FAILED] GATEWAY TIMEOUT: " + e.getMessage());
            
            if (payment instanceof Retryable && ((Retryable) payment).canRetry(e)) {
                System.out.println("ACTION: System supports retry. Please attempt the transaction again.");
            } else {
                System.out.println("ACTION: Please wait and check payment status later.");
            }
            
        } catch (TransactionFailedException e) {
            System.err.println("\n[STATUS: FATAL FAILED] SYSTEM LEVEL ERROR: " + e.getMessage());
            if (e.getCause() != null && e.getCause() instanceof NullPointerException) {
                System.err.println("SYSTEM ALERT: Critical Null Error detected. Check beneficiary data pipeline.");
            }
        } catch (NullPointerException e) {
             System.err.println("\n[STATUS: CRITICAL FAILURE] UNHANDLED NULL POINTER: " + e.getMessage());
        } catch (PaymentException e) {
            System.err.println("\n[STATUS: FAILED] UNSPECIFIC PAYMENT ERROR: " + e.getMessage());
        }
        System.out.println("\n-------------------------------------------------");
    }
}