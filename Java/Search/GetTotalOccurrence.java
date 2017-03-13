package Search;

/* Given a target number and an integer array sorted in ascending order. 
 * Find the total number of occurrences of target in the array.
 * 
 * Example: given [1, 3, 3, 4, 5] and target = 3, return 2.
 */

public class GetTotalOccurrence {
	public static void main(String[] args) {
		int[] a1 = new int[]{};
		System.out.println(totalOccurrence(a1, 5)); // 0
		
		int[] a2 = new int[]{4};
		int t2 = 3;
		System.out.println(totalOccurrence(a2, t2)); // 0
		
		int[] a3 = new int[]{1, 3, 3, 4, 5};
		int t3 = 3;
		System.out.println(totalOccurrence(a3, t3)); // 2
		
		int[] a4 = new int[]{2, 2, 3, 4, 6};
		int t4 = 4;
		System.out.println(totalOccurrence(a4, t4)); // 1
		
		int[] a5 = new int[]{1, 2, 3, 4, 5};
		int t5 = 6;
		System.out.println(totalOccurrence(a5, t5)); // 0
		
		int[] a6 = new int[]{1,1,1,1,1,1,1,1,1,1,1};
		int t6 = 1;
		System.out.println(totalOccurrence(a6, t6)); // 11
	}
	
	/**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public static int totalOccurrence(int[] A, int target) {
        if (A.length == 0 || A[0] > target || A[A.length - 1] < target)
            return 0;
        
        int low = 0, high = A.length - 1, mid = 0;
        while (low + 1 < high) {
            mid = (high - low) / 2 + low;
            if (target == A[mid])
                break;
            else if (target > A[mid])
                low = mid;
            else 
                high = mid;
        }
        if (A[mid] != target)
        	return 0;
        
        int start = mid, mid1 = 0;
        low = 0;
        high = mid;
        while (low + 1 < high) {
        	mid1 = (high + low) >> 1;
        	if (A[mid1] < target)
        		low = mid1;
        	else {
        		high = mid1;
        		start = mid1;
        	}
        }
        if (A[low] == target) // important because it is low + 1 < high, not low <= high
        	start = low;
        
        int end = mid, mid2 = 0;
        low = mid;
        high = A.length - 1;
        while (low + 1 < high) {
        	mid2 = (low + high) >> 1;
        	if (A[mid2] > target)
        		high = mid2;
        	else {
        		low = mid2;
        		end = mid2;
        	}
        }
        if (A[high] == target) 
        	end = high;
        
        return end - start + 1;
    }
}
