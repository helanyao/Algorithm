package BitManipulation;

/*
 * 字符长度为能被3整除时：比如“Tom” ：
 *             T           o           m
 * ASCII:      84         111         109
 * 8bit字节: 01010100    01101111    01101101
 * 6bit字节:  010101      000110      111101      101101
 * 十进制:     21          6           61          45
 * 对应编码:   V           G           9           t  
 * 
 * 字符串长度不能被3整除时，比如“Lucy”：
 *             L           u           c            y
 * ASCII:      76          117         99          121
 * 8bit字节:  01001100    01110101    01100011    01111001     
 * 6bit字节:   010011      000111      010101      100011      011110  010000  000000  000000
 * 十进制:       19          7          21           35           30      16    (异常)   (异常)      
 * 对应编码:     T           H           V           j             e       Q       =       =
 * 
 * 由于Lucy只有4个字母，按3个一组，第二组还有两个空位，需用0来补齐。
 * 因为是需要补齐而出现的0，所以转化成十进制的时候就不能按常规用base64编码表来对应，所以不是a
 * 可以理解成为一种特殊的“异常”，编码应该对应“=”。
 */

public class Base64 {
	private static final String CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	
	public static void main(String[] args) {
		String s1 = "Tom", s2 = "Lucy";
		System.out.println(encode(s1)); // VG9t
		System.out.println(encode(s2)); // THVjeQ==
		System.out.println(decode("VG9t"));
		System.out.println(decode("THVjeQ=="));
	}
	
	public static String encode(String s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		byte[] in = s.getBytes();
		StringBuilder out = new StringBuilder(in.length * 4 / 3); // inByte.length * 8 = outByte.length * 6
		int newChar;
		for (int curByte = 0; curByte < in.length; curByte += 3) {
			newChar = (in[curByte] & 0xFC) >> 2; // 第一个6位只需要让8位二进制右移两位
			out.append(CODES.charAt(newChar));
			newChar = (in[curByte] & 0x03) << 4; // 第二个6位 = 第一个8位的后两位 + 第二个8位的前4位
												 // 然后取拼出的8位的后6位转换，所以只左移4位
			if (curByte + 1 < in.length) {
				newChar |= ((in[curByte + 1] & 0xF0) >> 4);
				out.append(CODES.charAt(newChar));
				newChar = (in[curByte + 1] & 0x0F) << 2;
			} else { // 第一个为最后一个8位的最后两位后面补4个0。另外两个6位对应的是异常的“=”；
				out.append(CODES.charAt(newChar));
				out.append("==");
				continue; // don't forget continue, otherwise it will run next else.
			}
			
			if (curByte + 2 < in.length) { //第三个6位 = 第二个8位的后4位 + 第三个8位的前2位			  
				newChar |= (in[curByte + 2] & 0xC0) >> 6;
				out.append(CODES.charAt(newChar));
				newChar = in[curByte + 2] & 0x3F;  //第4个6位 = 第三个8位的后6位
				out.append(CODES.charAt(newChar));
			} else { // 还需补两个6位，一个是最后一个8位的后4位补两个0，另一个对应异常的“=”
				out.append(CODES.charAt(newChar));
				out.append("=");
			}
		}
		
		return out.toString();
	}
	
	public static String decode(String input) {
		if (input == null || input.length() % 4 != 0) {
			throw new IllegalArgumentException();
		}
		
		StringBuilder out = new StringBuilder(((input.length() * 3) / 4) - (input.indexOf('=') > 0 ? (input.length() - input.indexOf('=')) : 0));
		char[] inChars = input.toCharArray();
		byte[] inBytes = new byte[4];
		byte b;
		char ch;
		for(int inIndex = 0; inIndex < inChars.length; inIndex += 4) {
			inBytes[0] = (byte) CODES.indexOf(inChars[inIndex]);
			inBytes[1] = (byte) CODES.indexOf(inChars[inIndex + 1]);
			inBytes[2] = (byte) CODES.indexOf(inChars[inIndex + 2]);
			inBytes[3] = (byte) CODES.indexOf(inChars[inIndex + 3]);
			b = (byte) ((inBytes[0] << 2) | (inBytes[1] >> 4));
			ch = (char) b;
			out.append(ch);
			if (inBytes[2] < 64) {
                b = (byte) ((inBytes[1] << 4) | (inBytes[2] >> 2));
                ch = (char) b;
                out.append(ch);
                if (inBytes[3] < 64) {
                    b = (byte) ((inBytes[2] << 6) | inBytes[3]);
                    ch = (char) b;
                    out.append(ch);
                }
            }
		}
		
		return out.toString();
	}

}
