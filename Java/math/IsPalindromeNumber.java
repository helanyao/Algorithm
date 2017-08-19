package math;

/*  https://leetcode.com/problems/palindrome-number/?tab=Description
 * 
 *  Determine whether an integer is a palindrome. Do this without extra space.
 */

public class IsPalindromeNumber {
	public boolean isPalindrome(int x) {
		// negative number is not palindrome
        if(x < 0)
            return false;
        
        return x == reverse(x);
    }
    
    public static int reverse(int data) {
		int r_data = 0;
		while(data != 0) {
			int i = data % 10;
			r_data = r_data * 10 + i;
			data /= 10;
		}
		
		return r_data;
	}
}
