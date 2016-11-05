package BitManipulation;
//Given a base-10 integer, n, convert it to binary (base-2). 
//Then find and print the base-10 integer denoting the maximum number of consecutive 1's in n's binary representation.
//Sample Input 13
//Sample Output 2
//The binary representation of 13 is 1101, so the maximum number of consecutive 1's is 2.


public class BinaryNumber {

	public static void main(String[] args) {
		int n = 13, count = 0, max = 0;
		
		while(n > 0){
		    if ((n & 1) > 0){
		        count++;
		    } else {
		        count = 0;
		    }
		    if (max < count)
		        max = count;
		    n>>=1;
		}
		
		System.out.println(max);
	

	}
	
	

}
