
public class prime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			for(int i=2;i<500;i++) {
				if(isprime(i)) {
					
					System.out.println(i);
				}
			}
	}
	public static boolean isprime(int i) {
		int c=0;
		for(int j=2;j*j<=i;j++) {
			if(i%j==0) {
				c++;
			}
		} 
		if(c==0) return true;
		else return false;
	}

}
