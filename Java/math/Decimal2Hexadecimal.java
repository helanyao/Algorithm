package math;

/**
 * @Description
 * 给出一个整型(int)的数，将其转换为十六进制，负数用二进制补码形式表示
 * 
 * @Example
 * 26 -> 1a, -1 -> ffffffff
 * 
 * @Tag 
 * Facebook, Math, Bit Mainpulation
 * 
 */

public class Decimal2Hexadecimal {
	// 负数的存储方式是补码的形式，负数的补码等于其正数反码+1
	// 2的反码表示形式就是1101，所以-2的表示形式就是其反码+1，是1110
	public String dec2Hex1(int num) {
		if (num == 0)
			return "0";
		
		long n = num > 0 ? num : -num;
		int[] bit = new int[10];
		int len = 0, leader0 = 1; // leader0 is to local first 0 in hex expression
		String ans = "";
		for (int v : bit)
			v = 0;
		
		while (n > 0) {
			bit[len++] = (int)n % 16;
			n /= 16;
		}
		
		if (num < 0) { // translate its true form into complement form 
			for (int i = 0; i < 8; i++)
				bit[i] = 15 - bit[i];
			int pos = 0;
			while (bit[pos] == 15) 
				bit[pos++] = 0;
			bit[pos]++;
		}
		
		for (int i = 7; i >= 0; i--) {
			if (bit[i] != 0)
				leader0 = 0;
			if (leader0 == 1)
				continue;
			if (bit[i] < 10)
				ans += (char)('0' + bit[i]);
			else
				ans += (char)('a' + bit[i] - 10);
		}
		
		return ans;
	}
	
	/*
	 *  整型数在计算机内部是二进制形式存储，那么不管的是正数还是负数，其实计算机已有了完整的存储，
	 *  那么其实不需要再去计算反码补码，只需对其进行2进制转换成16进制就可以了。
	 *  
	 *  因为二进制一位表示的是2,16进制一位表示的是16，那么16进制下一位表示的是2进制下的4位(16 = 2^4)。
	 *  在转换过程中可以每四位转换成一位，这里有个小技巧：用到位运算&和>>来提高运行速度，&15相当于%16
	 */
	public String dec2Hex2(int num) {
		if (num == 0)
			return "0";
		
		String ans = "";
		int len = 0;
		while (num != 0 && len < 8) {
			int bit = num & 15;
			if (bit < 10)
				ans += (char)('0' + bit);
			else
				ans += (char)('a' + bit - 10);
			num >>= 4;
			len++;
		}
		
		return ans;
	}
}
