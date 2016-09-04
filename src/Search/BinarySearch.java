package Search;

public class BinarySearch {

	public static void main(String[] args) {
		int[] a = new int[]{1, 2, 3, 4};
		System.out.println(search(a, 0, a.length - 1, 5));
	}
	
	public static int search(int[] arr, int low, int high, int val) {
		if(arr == null || low < 0 || high < 0) {
			throw new IllegalArgumentException("Argument value is illegal.");
		}
		
		if(high < low) {
			System.out.println("Value " + val + " does not exist in input array.");
			return -1;
		}
		
		// try to improve performance
		if(arr[low] == val) {
			return low;
		} else if (arr[high] == val) {
			return high;
		}
		
		int mid = (low + high)/2;
		if(arr[mid] == val) {
			return mid;
		} else if(arr[mid] < val) {
			return search(arr, mid + 1, high, val);
		} else {
			return search(arr, low, mid - 1, val);
		}
	}
	
	public static int searchN(int[] arr, int val) {
		if(arr == null) {
			throw new IllegalArgumentException("Array is null.");
		}
		
		int low = 0, high = arr.length - 1, mid;
		
		while(low <= high) {
			mid = (low + high) / 2;
			if(arr[mid] == val) {
				return mid;
			} else if(arr[mid] < val) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		System.out.println("Value " + val + " does not exist in input array.");
		return -1;
	}

}
