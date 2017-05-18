package string;

/**
 * @Description
 * Give two strings, s1 and s2. Check whether s1 is the sub sequence of s2.
 * 
 * @Example
 * s1 = "abc", s2 = "ahbgdc" -> true
 * s1 = "axc", s2 = "ahbgdc" -> false
 * 
 * @Tag Google, Two Pointers
 */
public class IsSubSequence {
	// O(s1.length * s2.length)
	public boolean isSubSequence1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() > s2.length())
			return false;
		
		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();
		int k = 0;
		while (k < ch2.length - ch1.length + 1) {
			if (ch1[0] != ch2[k]) {
				k++;
				continue;
			}
			int i = 1, j = k + 1;
			while (i < ch1.length && j < ch2.length) {
				while (j < ch2.length && ch2[j] != ch1[i])
					j++;
				if (j >= ch2.length) {
					break;
				} else {
					i++;
					j++;
				}
			}
			if (i == ch1.length)
				return true;
			else
				k++;
		}
		
		return false;
	}
	
	// O(s1.length + s2.length)
	public boolean isSubSequence(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() > s2.length())
			return false;
		
		int i = 0, j = 0;
		while (i < s1.length() && j < s2.length()) {
			if (s1.charAt(i) == s1.charAt(j))
				i++;
			j++;
		}
		
		return i == s1.length();
	}

}
