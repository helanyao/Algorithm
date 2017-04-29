package bitManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu@outlook.com
 *
 * @Description
 * Given an array, most elements of this array occur twice except two numbers.
 * Find these two numbers.
 * 
 * @Example
 * For {2,3,6,8,3,2,7,7}, should return 8 and 6.
 * 
 * @Tag Bit Manipulation
 */
public class DoubleNum {
	public static void main(String[] args) {
		int[] data = new int[]{2,3,6,8,3,2,7,7};
		System.out.println(getNums(data).toString()); // 8,6
		data = new int[]{0,3,-6,8,3,0,-7,-7};
		System.out.println(getNums(data).toString()); // 8,-6
		data = new int[]{2,-3,-6,-8,-3,2,7,7};
		System.out.println(getNums(data).toString()); // -6,-8
	}
	
	public static List<Integer> getNums(int[] data) {
		List<Integer> result = new ArrayList<Integer>();
		if (data == null)
			return result;
		
		int mask = 0, num1 = 0, num2 = 0;
		for (int i = 0; i < data.length; i++)
			mask ^= data[i];
		int index = getMagicNum(mask);
		for (int i = 0; i < data.length; i++) {
			if (check(data[i], index))
				num1 ^= data[i];
			else
				num2 ^= data[i];
		}
		result.add(num1);
		result.add(num2);
		
		return result;
	}
	
	private static boolean check(int data, int mask) {
		return (data & mask) == mask;
	}
	
	/*
	 * int型的最大值：2^{31} - 1 = 2147483647    16进制：0x7FFF FFFF
	 * int型的最小值：-2^{31} = -2147483648   16进制：0x8000 0000
	 */
	private static int getMagicNum(int num) {
		if (num == 0)
			return 0;
		int indexBit = 1 << 30;
		while ((num & indexBit) == 0)
			indexBit = indexBit >> 1;
		
		return indexBit;
	}
}
