

public class Beneficiary {
    private String name;
    private String accountId;

    public Beneficiary(String name, String accountId) {
        this.name = name;
        this.accountId = accountId;
    }

    public String getName() { return name; }
    public String getAccountId() { return accountId; }
}