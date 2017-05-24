package search;

/**
 * @author jhzhu@outlook.com
 * 
 * @Description
 * Given a sorted array of n integers, find the starting and ending position of a given target value.
 * If the target is not found in the array, return [-1, -1].
 * http://www.lintcode.com/en/problem/search-for-a-range/
 * 
 * @Example
 * Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 * 
 * @Tag Binary Search
 */
public class SearchRange {
	/** 
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        int[] result = new int[]{-1, -1};
        if (A == null || A.length == 0 || target < A[0] || target > A[A.length - 1])
            return result;
        
        result[0] = searchStart(A, target);
        result[1] = searchEnd(A, target);
            
        return result;
    }
    
    private int searchStart(int[] A, int t) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] >= t)
                end = mid;
            else
                start = mid;
        }
        if (A[start] == t)
            return start;
        else if (A[end] == t)
            return end;
        else
            return -1;
    }
    
    private int searchEnd(int[] A, int t) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] <= t)
                start = mid;
            else
                end = mid;
        }
        if (A[end] == t)
            return end;
        else if (A[start] == t)
            return start;
        else
            return -1;
    }
}
