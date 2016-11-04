package Strings;


//Intput string: "abcdefg".
//
//offset=0 => "abcdefg"
//offset=1 => "gabcdef"
//offset=2 => "fgabcde"
//offset=3 => "efgabcd"

public class RotateString1 {
	public static void main(String[] args) {
		String s = "abcdefg";
		int offset = 3;
		System.out.println(rotateString1(s.toCharArray(), offset));
		System.out.println(rotateString2(s.toCharArray(), offset));
	}
	
    /*
     * param A: A string
     * param offset: Rotate string with offset.
     * return: Rotated string.
     */
    public static char[] rotateString1(char[] str, int offset) {
        if (str == null || str.length <= 1 || offset <= 0 || offset % str.length == 0) {
            return str;
        }
        
        offset %= str.length;
        char[] newStr = new char[str.length];
        for (int i = 0; i < newStr.length - offset; i++) {
            newStr[offset + i] = str[i];
        }
        for (int i = str.length - offset, j = 0; i < str.length; i++, j++) {
            newStr[j] = str[i];
        }
        
        return newStr;
    }

    // space complexity: O(1)
    public static char[] rotateString2(char[] str, int offset) {
        if (str == null || str.length <= 1 || offset <= 0 || offset % str.length == 0) {
            return str;
        }
        
        offset %= str.length;
        reverse(str, 0, str.length - offset - 1);
        reverse(str, str.length - offset, str.length - 1);
        reverse(str, 0, str.length - 1);
        
        return str;
    }
    
    private static void reverse(char[] str, int start, int end) {
    	if(str == null || end > str.length || start > end || start < 0 || end < 0) {
    		return;
    	}
    	for(int i = start, j = end; i < j; i++, j--) {
    		char temp = str[i];
    		str[i] = str[j];
    		str[j] = temp;
    	}
    }
}

