package search;

/**
 * 
 * @author jhzhu@outlook.com
 *
 * @description
 * Given a target number, a non-negative integer k and an integer array A sorted in ascending order, 
 * find k closest numbers to target, sorted in ascending order by the difference between the number and target. 
 * Otherwise, sorted in ascending order by number if the difference is same.
 *
 * @example
 * Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
 * Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].
 * 
 * @tag
 * Binary Search
 * 
 */

public class KClosestNumbersInSortedArray {
	/**
     * @param A an integer array
     * @param target an integer
     * @param k a non-negative integer
     * @return an integer array
     */
	public int[] kClosestNumbers(int[] A, int target, int k) { 
        if (A == null || A.length == 0 || k > A.length) 
            return A;
        
        int[] result = new int[k];
        
        int index = firstIndex(A, target);
        
        int start = index - 1, end = index;
        for (int i = 0; i < k; i++) {
            if (start < 0) {
                result[i] = A[end++];
            } else if (end >= A.length) {
                result[i] = A[start--];
            } else {
                if (target - A[start] <= A[end] - target) 
                    result[i] = A[start--];
                else 
                    result[i] = A[end++];
            }
        }
        
        return result;
    }
    
    private int firstIndex(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) >> 1;
            if (A[mid] < target) 
                start = mid;
            else if (A[mid] > target) 
                end = mid;
            else 
                end = mid;
        }
        
        if (A[start] >= target) 
            return start;
        if (A[end] >= target) 
            return end;
        
        return A.length; // case: A = [1, 2], target = 3
    }
}
