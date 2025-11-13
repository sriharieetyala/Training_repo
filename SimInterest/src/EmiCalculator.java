public class EmiCalculator {

    public double calculateSimpleInterestEmi(Loan loan) {
        
        double simpleInterest = loan.principal * (loan.annualRate / 100) * loan.tenureYears;
        
        System.out.println("Interest for "+loan.tenureYears +"  years "+ simpleInterest);
        
        double totalAmount = loan.principal + simpleInterest;
        
        int totalMonths = loan.tenureYears * 12;
        
        double emi = totalAmount / totalMonths;
        
        return emi;
    }
}