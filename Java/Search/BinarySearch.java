package Search;

public class BinarySearch {
	public static void main(String[] args) {
		int[] a = new int[]{1, 2};
		int key = 2;
		System.out.println(search(a, 0, a.length - 1, key));
		System.out.println(searchN(a, key));
	}
	
	public static int search(int[] arr, int low, int high, int val) {
		if(arr == null || low < 0 || high < 0) {
			return -1;
		}
		
		if(high < low) {
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
			return search(arr, mid + 1, high - 1, val);
		} else {
			return search(arr, low + 1, mid - 1, val);
		}
	}
	
	public static int searchN(int[] arr, int val) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		int low = 0, high = arr.length - 1, mid;
		
		while(low <= high) {
			if(arr[low] == val) { // improve performance
				return low;
			} else if(arr[high] == val) {
				return high;
			}
			mid = (low + high) / 2;
			if(arr[mid] == val) {
				return mid;
			} else if(arr[mid] < val) {
				low = mid + 1;
				high--;
			} else {
				high = mid - 1;
				low++;
			}
		}
		
		return -1;
	}

}
