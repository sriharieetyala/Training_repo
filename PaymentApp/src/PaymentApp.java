
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

// IMPORTING CLASSES FROM BANKING APP JAR:
// We can use any class found inside the imported JAR file.
// Based on the JAR contents, we'll try to import and use the Entrypoint class.
// Note: You must update this import path if the actual class you want to use is different.
import com.app.process.Entrypoint; 

/**
 * Account Class: Implements Comparable for sorting by account holder name 
 * and overrides equals/hashCode based on holder name and account number.
 */
class Account implements Comparable<Account> {
    private String accountholdername;
    private long accountno;
    private String transcode;
    private String country;
    private String ifsccode;
    private double balance;

    public Account(String accountholdername, long accountno, String transcode, String country, String ifsccode, double balance) {
        this.accountholdername = accountholdername;
        this.accountno = accountno;
        this.transcode = transcode;
        this.country = country;
        this.ifsccode = ifsccode;
        this.balance = balance;
    }

    // Getters
    public String getAccountholdername() { return accountholdername; }
    public long getAccountno() { return accountno; }
    public double getBalance() { return balance; }

    // Comparable Implementation: Default sort by accountholdername
    @Override
    public int compareTo(Account other) {
        return this.accountholdername.compareTo(other.accountholdername);
    }

    // equals and hashCode: Objects are the same if accountholdername AND accountno are same.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountno == account.accountno && Objects.equals(accountholdername, account.accountholdername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountholdername, accountno);
    }

    @Override
    public String toString() {
        return String.format("Account{Holder='%s', No=%d, Balance=%.2f, IFSC='%s'}", 
                             accountholdername, accountno, balance, ifsccode);
    }
}

/**
 * Comparator 1: Sorts Account objects based on accountno (Long).
 */
class AccountNoComparator implements Comparator<Account> {
    @Override
    public int compare(Account acc1, Account acc2) {
        return Long.compare(acc1.getAccountno(), acc2.getAccountno());
    }
}

/**
 * Comparator 2: Sorts Account objects based on balance (Double).
 */
class BalanceComparator implements Comparator<Account> {
    @Override
    public int compare(Account acc1, Account acc2) {
        return Double.compare(acc1.getBalance(), acc2.getBalance());
    }
}

/**
 * Main application class.
 */
public class PaymentApp {
    public static void main(String[] args) {
        
        System.out.println("--- PaymentApp Initialized ---");
        
        // =========================================================
        // 1. DEMONSTRATION OF JAR LIBRARY USAGE
        // =========================================================
        try {
            // Instantiate and use a class from the imported banking_jar.jar
            // This proves the external JAR dependency is linked correctly.
            // Note: If Entrypoint doesn't have a public constructor or method, 
            // you may need to use a different class from your JAR.
            Entrypoint bankingEntry = new Entrypoint();
            // Assuming Entrypoint has a public method (e.g., runService)
            // bankingEntry.runService(); // Uncomment and adjust if needed
            System.out.println("SUCCESS: Successfully instantiated Entrypoint class from banking_jar.jar.");
            
        } catch (Exception e) {
            System.err.println("ERROR: Could not use class from banking_jar.jar. Ensure the class name and methods are public.");
            e.printStackTrace();
        }
        
        // =========================================================
        // 2. ACCOUNT CLASS DEMONSTRATION
        // =========================================================
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Bob", 100345L, "T1", "US", "IFSC001", 5000.50));
        accounts.add(new Account("Alice", 100123L, "T2", "CA", "IFSC002", 1500.75));
        accounts.add(new Account("Charlie", 100567L, "T3", "UK", "IFSC003", 8000.00));
        // Duplicate object based on accountholdername and accountno for equals/hashCode test
        accounts.add(new Account("Alice", 100123L, "T4", "DE", "IFSC004", 9999.99)); 

        System.out.println("\n--- Original List (Size: " + accounts.size() + ") ---");
        accounts.forEach(System.out::println);
        
        // A. Sort by Account Holder Name (Comparable - Natural Order)
        System.out.println("\n--- A. Sorted by Holder Name (Comparable) ---");
        Collections.sort(accounts); 
        accounts.forEach(System.out::println);

        // B. Sort by Account Number (AccountNoComparator)
        System.out.println("\n--- B. Sorted by Account Number (Comparator 1) ---");
        Collections.sort(accounts, new AccountNoComparator());
        accounts.forEach(System.out::println);

        // C. Sort by Balance (BalanceComparator)
        System.out.println("\n--- C. Sorted by Balance (Comparator 2) ---");
        Collections.sort(accounts, new BalanceComparator());
        accounts.forEach(System.out::println);
        
        // D. Test HashCode and Equals
        System.out.println("\n--- D. Testing hashCode() and equals() with HashSet ---");
        Set<Account> uniqueAccounts = new HashSet<>(accounts); 
        System.out.println("List Size: " + accounts.size()); // 4
        // The size should be 3 because the two 'Alice' accounts are considered equal
        // based on the custom equals() method.
        System.out.println("HashSet Size (Unique Objects): " + uniqueAccounts.size()); 
    }
}
