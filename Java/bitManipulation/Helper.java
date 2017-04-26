package bitManipulation;

public class Helper {
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
	
	public boolean isTwoPower(int x) {
		return ((x & (x - 1)) == 0) && (x != 0);
	}
	
	public int getAvg(int x, int y) {
		return ((x & y) + (x ^ y)) >> 1;
	}
}
