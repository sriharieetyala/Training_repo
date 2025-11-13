import java.util.*;
public class Main {
    public static void main(String[] args) {
        
    	Scanner sc=new Scanner(System.in);
    	int p=sc.nextInt();
    	int r=sc.nextInt();
    	int t=sc.nextInt();
    	
        Loan myLoan = new Loan(p,r,t);
        
        EmiCalculator calculator = new EmiCalculator();
        
        double monthlyPayment = calculator.calculateSimpleInterestEmi(myLoan);
               
        System.out.println("The monthly payment (EMI) is: " + monthlyPayment);
    }
}