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
		System.out.println(rotateString(s.toCharArray(), 3));
	}
	
    /*
     * param A: A string
     * param offset: Rotate string with offset.
     * return: Rotated string.
     */
    public static char[] rotateString(char[] str, int offset) {
        if (str == null || str.length <= 1 || offset <= 0 || offset % str.length == 0) {
            return str;
        }
        
        offset %= str.length;
        char[] newStr = new char[str.length];
        for (int i = 0; i < newStr.length - offset; i++) {
            newStr[offset + i] = str[i];
        }
        for (int i = str.length - offset, j = 0; i < str.length; i++) {
            newStr[j++] = str[i];
        }
        
        return newStr;
    }

}

