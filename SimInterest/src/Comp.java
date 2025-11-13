import java.util.Scanner;

public class Comp {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		double principal = scanner.nextDouble();

        
        double rate = scanner.nextDouble();
       

        
        double time = scanner.nextDouble();

        
        double n = scanner.nextDouble();

        double base = 1 + (rate / n);
        double exponent = n * time;
        double amount = principal * Math.pow(base, exponent);
        double compoundInterest = amount - principal;
        
        System.out.println(compoundInterest);
        System.out.println(amount);
	}
	
	

}
