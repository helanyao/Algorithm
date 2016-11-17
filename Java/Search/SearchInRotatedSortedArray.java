package Search;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Given a target value. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * 
 * Example
 * For [4, 5, 1, 2, 3] and target=1, return 2.
 * For [4, 5, 1, 2, 3] and target=0, return -1.
 *
 * http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/
 */

public class SearchInRotatedSortedArray {
	public static void main(String[] args) {
		int[] A = new int[]{0,1,2,-10,-9,-8,-7,-6,-5,-4,-3,-2,-1};
		int key = -9;
		System.out.println(search(A, key));
	}
	
    /** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public static int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int low = 0, high = A.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[low] > A[high]) { 
            	// the array between low and high is consisted of two sub-arrays.
            	// like [0, 1, 2, 3] + [-5, -4, -3, -2]
                if (A[mid] > target && target > A[low]) { // target and mid in first array, 
                										  // like target = 1, A[mid] = 2
                	high = mid;
                } else if (A[mid] > target && A[mid] < A[high]) { // target and mid in second array
                												  // target = -4, A[mid] = -3
                	high = mid;
                } else if (A[mid] < target && A[mid] < A[high] && A[low] < target) { // target in 1st, mid in 2nd
                																	 // target = 2, A[mid] = -3
                	high = mid;
                } else if (A[mid] < target && A[mid] > A[low]) { // target and mid in 1st
                												 // target = 3, A[mid] = 2
                	low = mid;
                } else if (A[mid] > target && A[mid] > A[low] && A[high] > target) { // mid in 1st, target in 2nd
                																	 // A[mid] = 2, target = -3
                	low = mid;
                } else { // the last case
                	low = mid;
                }
            } else { // in this case, the low-high-mid-target are in the ascending array
                if (A[mid] < target) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
        }
        
        if (A[low] == target) {
            return low;
        } else if (A[high] == target) {
            return high;
        } else {
            return -1;
        }
    }

}
