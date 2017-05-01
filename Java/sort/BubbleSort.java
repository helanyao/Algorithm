package sort;

public class BubbleSort {
	public void sort(int[] A) {
		if (A == null)
			return;
		
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < A.length; i++) {
				if (i < A.length - 1 && A[i] > A[i + 1]) {
					int temp = A[i];
					A[i] = A[i + 1];
					A[i + 1] = temp;
					flag = true;
				}
			}
		}
	}
}
