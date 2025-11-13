
public class PaymentCredit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.println("This is test");
      print();
      add(3,4);
	}

public static void print() {
	
	System.out.println("inside print");
	
	int i = 0;
	
	  i =i+2;   //=+2;
	  System.out.println("inside print - "+i);
	  i = i-1;
	  
	  i = i*2;
	  
	  i = i/3;
	  
	  i = i%2;
}


public static void add(int i,int j) {
	 
	int result = i+j;
	
	int arr[] = {1,2,5,7,9,10};
	
	for (int k=0;k<arr.length;k++) {
		System.out.println("value of k in for loop with array  "+arr[k]);
	}
	
	System.out.println("result - "+result);
	
	for (int k=0;k<10;k++) {
		System.out.println("value of k in for loop  "+k);
	}
	
	while(result<10 ) {
		
		System.out.println("value of result in while - "+ result);
		result = result+1;
	}
	
	do {
		result++;
		System.out.println("value of result in do while = "+result);
	}
	while(result<20);
}


}
	



	