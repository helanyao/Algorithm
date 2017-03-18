package search;

/**
 * @author jhzhu@outlook.com
 * 
 * There is an integer array which has the following features:
 * (1) The numbers in adjacent positions are different.
 * (2) A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
 * 
 * We define a position P is a peek if:
 * A[P] > A[P-1] && A[P] > A[P+1]
 * 
 * Find a peak element in this array. Return the index of the peak.
 * The array may contains multiple peeks, find any of them.
 * 
 * Example:
 * Given [1, 2, 1, 3, 4, 5, 7, 6]
 * Return index 1 (which is number 2) or 6 (which is number 7)
 *
 */
public class FindPeakElement {
	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 1, 3, 4, 5, 7, 6};
		System.out.println(findPeak(nums));
	}
	
	public static int findPeak(int[] nums) {
		if (nums == null || nums.length < 3) 
			return -1;
		
		int low = 1, high = nums.length - 2;
		while (low + 1 < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] > nums[mid - 1])  // mid左侧数据呈上峰趋势
				low = mid;
			else // mid左侧数据呈下峰趋势
				high = mid;
		}
		
		if (nums[low] < nums[high]) 
			return high;
		else 
			return low;
	}
}
