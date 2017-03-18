package search;

/**
 * @author jinghuaz
 *
 * Everything is the same as in GetMinInRotatedSortedArray1, except it allows duplicate element
 * 
 * Example: given [4,4,5,6,7,0,1,2] return 0.
 */

/*
 * This question is not to test binary search. If you could get the worst case ([1, 0, 1, 1]),
 * then you could hit the answer. Because in that case, it is not able to write a binary search.
 * If employing for to solve it, its time complexity is O(n). So, just go with for.
 */
public class GetMinInRotatedSortedArray2 {
	/**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        int min = num[0];
        for (int i : num)
            if (i < min)
                min = i;
        
        return min;
    }
}
