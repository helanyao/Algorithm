package Math;

/* 
 *  https://leetcode.com/problems/reverse-integer/?tab=Description
 *  
 *  Reverse digits of an integer. Example: 
 *  x = 123, return 321; x = -123, return -321
 *  The input is a 32-bit signed integer. Return 0 when the reversed integer overflows.
 */

public class ReverseInteger {
	public static void main(String[] args) {
		int n1 = 1000000003, n2 = 123, n3 = -123;
		System.out.println(reverse1(n1) + " " + reverse1(n2) + " " + reverse1(n3));
		System.out.println(reverse2(n1) + " " + reverse2(n2) + " " + reverse2(n3));
		System.out.println(reverse3(n1) + " " + reverse3(n2) + " " + reverse3(n3));
	}
	
	// 用一个变量temp存储变化后的值，每次做反向操作，如果和之前的值一样就更新，不一样，说明溢出了。 
    public static int reverse1(int n) {
        int reversed_n = 0;
        
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;
            if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
        return reversed_n;
    }
    
    // tricky solution, employ long to help to deal with the overflow case
    public static int reverse2(int x) {
        long tmp=0;
        while(x != 0) {
            tmp *=10;
            tmp += x%10;
            if(tmp > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE)
                return 0;
            x /= 10;
        }
        
        return (int)tmp;
    }
    
    // Key idea is if the result calculated last time is larger than Integer.MAX_VALUE / 10,
    // then there is no need to move forward.
    public static int reverse3(int n) {
        int result = 0;

        while (n != 0) {
            if (result > Integer.MAX_VALUE / 10 || 
            		((result == Integer.MAX_VALUE / 10) && (n % 10 > Integer.MAX_VALUE % 10))) {
                result = 0;
                break;
            }
            
            if (result < Integer.MIN_VALUE / 10 || 
            		((result == Integer.MIN_VALUE / 10) && (n % 10 < Integer.MIN_VALUE % 10))){
                result = 0;
                break;
            }
            
            result = result * 10 + n % 10;
            n = n / 10;
        }

        return result;
    }
}
