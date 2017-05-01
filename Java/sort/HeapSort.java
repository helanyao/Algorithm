package sort;

/*
 * 堆排序首先为原始数组data建立大根堆（假设排序目标为升序），然后进行排序。
 * 
 * 建堆过程就是从数组尾部开始对每个parent节点进行筛选。把它与其孩子节点进行比较，将较大的值移上去。
 * 以此反复，最终建立大根堆。
 * 
 * 排序过程就是每次把data[0]与data[n-i]（第一次i为1）互换，这样当前的最大值就放到尾部。然后对新的
 * data[0]调用建堆过程的算法重新筛选。这样做n-1次，就完成了。
 */
public class HeapSort {
	/**
	 * Time complexity is O(n * log_2 * n). Heap sort firstly creates a heap. 
	 * After that, it will change the value between data[0] and data[n-1]. Then 
	 * sort the heap again. So, for i-th iteration, it changes the value between 
	 * data[0] and data[n-i] (which means the data between data[n-i+1]...data[n]
	 * is already sorted).
	 *  
	 * @param data The array to be sorted
	 * @param n Denote the range of array ([0, n]) to be sorted
	 */
	public static void sort(int[] data, int n) {
		if (data == null || n < 0 || n > data.length)
			return;
		
		for (int i = n / 2 - 1; i >= 0; i--)
			sift(data, n, i); // create heap
		for (int i = 1; i < n; i++) { // only perform iteration in n-1 times
			// change the value of the first and last element 
			// in current unsorted sub array
			int x = data[0];
			data[0] = data[n - i];
			data[n - i] = x; 
			sift(data, n - i, 0); // filter node, "new" data[0]
		}
	}
	
	/**
	 * Sort the heap for element at a given index. Assume a head is already created
	 * for sub array of [i+1, n-1].
	 * 
	 * @param data The array to be sorted
	 * @param n Denote the range of array ([0, n]) to be sorted
	 * @param index The target index in this array to be sorted
	 */
	private static void sift(int[] data, int n, int index) {
		if (data == null || n < 0 || index < 0 || n > data.length || index > n)
			return;
		
		int childIndex = index * 2 + 1, v = data[index];
		while (childIndex < n) {
			if (childIndex + 1 < n && data[childIndex] < data[childIndex + 1])
				childIndex++; // now, right child
			if (v > data[childIndex]) 
				break;
			data[index] = data[childIndex];
			index = childIndex;
			childIndex = index * 2 + 1;
		}
		data[index] = v; 
	}
}
