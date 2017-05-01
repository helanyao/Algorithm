package sort;

/*
 * 插入排序就是把数组分成两个子序列：已排好序和待排序。每次从待排序数组中取第一个元素，
 * 插入到已排好序的子数组中。
 */
public class InsertionSort {
	public void sort(int[] data) {
		if (data == null)
			return;
	 
	    for (int i = 1; i < data.length; i++) {
	        int j = i;
	        int target = data[i];
	 
	        while (j > 0 && target < data[j - 1]) {
	        	data[j] = data[j - 1];
	            j--;
	        }
	 
	        data[j] = target;
	    }
	}
}
