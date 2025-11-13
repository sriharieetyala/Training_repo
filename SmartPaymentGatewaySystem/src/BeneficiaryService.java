
import java.util.Arrays;

public class BeneficiaryService {
    private static Beneficiary[] beneficiaries = new Beneficiary[3];
    private static int count = 0;

    static {
        addBeneficiary(new Beneficiary("Aashish Choudhary", "UPI12345678"));
        addBeneficiary(new Beneficiary("Bank Account Holder", "NB987654321"));
    }

    public static void addBeneficiary(Beneficiary b) {
        if (count < beneficiaries.length) {
            beneficiaries[count++] = b;
        } else {
            System.err.println("Warning: Beneficiary array is full. Cannot add " + b.getName());
        }
    }

    public static Beneficiary findBeneficiary(String id) throws BeneficiaryNotFoundException {
        if (id == null || id.trim().isEmpty()) {
            throw new BeneficiaryNotFoundException("NULL ID provided");
        }
        
        for (Beneficiary b : beneficiaries) {
            if (b != null && id.equals(b.getAccountId())) {
                return b;
            }
        }
        throw new BeneficiaryNotFoundException(id);
    }
}