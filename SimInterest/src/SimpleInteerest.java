import java.util.*;
public class SimpleInteerest {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		float r=sc.nextFloat();
		int p=sc.nextInt();
		int t=sc.nextInt();
		
		int si=(int)(p*t*r)/100;
		System.out.println("per month emi would be "+ si/(t*12));
		System.out.println("Total interest you need to pay for "+t+"years "+si);
		System.out.println("Total amount need to pay is  " +(p+si));
		

	}

}
