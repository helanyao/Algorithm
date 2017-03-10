package Search;


/*
 * Implement int sqrt(int x). Compute and return the square root of x. sqrt(3) = 1
 * 
 * Tag: Binary Search, Math, Facebook
 * 
 */

public class GetSqrt1 {
	public static void main(String[] args) {
		int limit = 10;
		for (int i = 0; i < limit; i++) {
			System.out.println(i);
			System.out.println(sqrt(i) + "\n");
		}
		int x = 999999999;
		System.out.println(sqrt(x));
	}
	
	public static int sqrt(int x) {
		if (x == 0)
			return 0;
		
		long low = 1, high = x; // long is very important
		
		while (low + 1 < high) {
			long mid = (high - low) / 2 + low;
			if (x == mid * mid)
				return (int)mid;
			else if (mid * mid > x) 
				high = mid;
			else
				low = mid;
		}
		
		return (int)low;
	}
}
