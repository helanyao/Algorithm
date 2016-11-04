package Search;

public class InsertionSearch {
	public static void main(String[] args) {
		int[] a = new int[]{1, 2, 3, 4};
		System.out.println(search(a, 0, a.length - 1, 2));
	}

	// Considering such scenario, if the values of an sorted array start from 0 to 10000 and
	// we would like to search 5 or 900, what is the performance of Binary Search?
	// So, Inseration Search is developed to improve it.
	// In Binary Search, it calcutes mid point as mid=(low+high)/2, 即mid=low+1/2*(high-low)
	// In Inseration Search, mid=low+(key-a[low])/(a[high]-a[low])*(high-low)
	// In this case, the mid point could be more closer to the destination.
	// Please note that if the value is not uniform distribution, please employ Binary Search.
	// Time Complexity: O(log2(log2n))
	public static int search(int[] arr, int low, int high, int val) {
		if(arr == null) {
			throw new IllegalArgumentException();
		}
		
		int mid = low+(val-arr[low])/(arr[high]-arr[low])*(high-low);
	    if(arr[mid] == val) {
	    	return mid;
	    } else if(arr[mid] > val) {
	    	return search(arr, low, mid - 1, val);
	    } else {
	    	return search(arr, mid + 1, high, val);
	    }     
	}
}
