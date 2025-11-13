
public class Fibno {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=0;
		int b=1;
		System.out.println(a);
		System.out.println(b);
		while(true) {
			int c=a+b;
			if(c>500) break;
			System.out.println(c);
			a=b;
			b=c;
		}
	}
	
	

}
