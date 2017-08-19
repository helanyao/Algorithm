package sort;

public class SelectionSort {
	public void sort(int[] A) {
		if (A == null)
			return;
		
		for (int i = 0; i < A.length; i++) {
			int pos = i, min = A[i];
			for (int j = i + 1; j < A.length; j++) {
				if (A[j] < min) {
					min = A[j];
					pos = j;
				}
			}
			int temp = A[i];
			A[i] = A[pos];
			A[pos] = temp;
		}
	}
}
