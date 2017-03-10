package Search;

/*
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * Example: given dividend = 100 and divisor = 9, return 11.
 * 
 * Tag: Binary Search
 */

public class DivideTwoIntegers {
	public static void main(String[] args) {
		int dividend = -2147483648, divisor = 2;
		System.out.println(divide(dividend, divisor));
	}
	
	public static int divide(int dividend, int divisor) {
		if (divisor == 0) 
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        else if (dividend == 0) 
            return 0;
        else if (dividend == Integer.MIN_VALUE && divisor == -1) 
            return Integer.MAX_VALUE;
            
		boolean isNegative = (dividend < 0 && divisor > 0) || 
                (dividend > 0 && divisor < 0);
		// important to convert them into Long type before calling Math.abs().
		// Otherwise if x = -2147483648, the result is 2147483647 because of overflow issue.
		long a = Math.abs((long)dividend), b = Math.abs((long)divisor);
		long low = 0, high = a;
		int result = 0 ; // must be initialized to meet grammar
		while (low + 1 < high) {
			long mid = (high - low) / 2 + low;
			if (mid * b == a){
				result = (int)mid;
				break;
			} else if (mid * b > a)
				high = mid;
			else
				low = mid;
		}
		
		if (low + 1 >= high && high *b <= a)
			result = (int)high;
		else if (low + 1 >= high)
			result = (int)low;
		
		return isNegative ? -result: result;
	}

}
