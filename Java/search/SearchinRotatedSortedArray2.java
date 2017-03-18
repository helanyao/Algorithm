package search;

/**
 * @author jinghuaz
 *
 * Follow up for Search in Rotated Sorted Array:
 * 1. What if duplicates are allowed?
 * 2. Would this affect the run-time complexity? How and why?
 * 3. Write a function to determine if a given target is in the array.
 * 
 * Example:
 * 1. Given [1, 1, 0, 1, 1, 1] and target = 0, return true.
 * 2. Given [1, 1, 1, 1, 1, 1] and target = 0, return false.
 */

/*
 * This question is not to test binary search. If you could get the worst case ([1, 1,...,1] - 0),
 * then you could hit the answer. Because in that case, it is not able to write a perfect binary search.
 * If employing for to solve it, its time complexity is O(n). So, just go with for.
 */
public class SearchinRotatedSortedArray2 {
	public boolean search(int[] A, int target) {
        if (A == null || A.length == 0)
             return false;
        for (int i : A)
            if (i == target)
                return true;
        
        return false;
    }
}
