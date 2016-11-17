package Search;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * 
 * Example: Given [4, 5, 6, 7, 0, 1, 2] return 0
 *
 * http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/
 */
public class GetMinInRotatedSortedArray {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[low] && nums[mid] < nums[high]) { // for the case: 0, 1, 2
                return nums[low];
            } else if (nums[mid] > nums[low]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        if (nums[low] < nums[high]) {
            return nums[low];
        } else {
            return nums[high];
        }
    }
}
