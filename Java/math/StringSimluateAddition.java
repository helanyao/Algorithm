package math;

/**
 * @author jhzhu@outlook.com
 *
 * @Descritpion
 * Given two strings whose length is less than 5000. They only contain number 0-9 and does
 * not include any 0 in the beginning position. Calculate their sum without using any math method.
 * @Example
 * Input: "5" and "619"
 * Output: "624"
 * 
 * @Tag Facebook, String, Math
 */
public class StringSimluateAddition {
	public static void main(String[] args) {
		String str1 = "5", str2 = "619";
		System.out.println(solution(str1, str2));
	}
	
	public static String solution(String str1, String str2) {
		if (str1 == null || str2 == null)
			return str1 + str2;
		
		String str3 = new String();
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int i = ch1.length - 1, j = ch2.length - 1, mark = 0;
		
		while (i >= 0 && j >= 0) {
			int data = ch1[i--] - '0' + ch2[j--] - '0' + mark;
			if (data > 9) {
				mark = 1;
				data -= 10;
			} else {
				mark = 0;
			}
			str3 = String.valueOf(data) + str3;
		}
		
		while (i >= 0) { 
			int data = ch1[i--] - '0' + mark; // pay attention to the boundary condition. mark == 1 ?
			if (data > 9) {
				data -= 10;
				mark = 1;
			} else {
				mark = 0;
			}
			str3 = String.valueOf(data) + str3;		
		}
			
		while (j >= 0) {
			int data = ch2[j--] - '0' + mark;
			if (data > 9) {
				data -= 10;
				mark = 1;
			} else {
				mark = 0;
			}
			str3 = String.valueOf(data) + str3;	
		}
			
		return str3;
	}
}
