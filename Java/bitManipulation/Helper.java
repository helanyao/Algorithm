package bitManipulation;

public class Helper {
	/**
	 * Switch each number of two input arrays in same position with
	 * out using any temporary variable.
	 * 
     * @param a: an array of integers
     * @param b: an array of integers
     */
	public void switchNum(int[] a, int[] b) {
		if (a == null || b == null)
			return;
		
		int i = 0, j = 0;
		while(i < a.length && j < b.length) {
			a[i] ^= a[j];
			a[j] ^= a[i];
			a[i] ^= a[j];
		}
	}
	
	/**
	 * Check whether the input data can be dividend by two.
	 * 
     * @param x: an int data
     * @return a boolean type data
     */
	public boolean isTwoPower(int x) {
		return ((x & (x - 1)) == 0) && (x != 0);
	}
	
	/**
	 * Calculate the average value for any two numbers without worrying
	 * about the limitation of input data type.
	 * 
     * @param x: an int data
     * @param y: an int data
     * @return the average value of x and y
     */
	public int getAvg(int x, int y) {
		return ((x & y) + (x ^ y)) >> 1;
	}
}
