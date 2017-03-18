package search;

/*
 *  Given a mountain sequence of n integers which increase firstly 
 *  and then decrease, find the mountain top.
 *  
 *  Example:
 *  Given nums = [1, 2, 4, 8, 6, 3] return 8
 *  Given nums = [10, 9, 8, 7], return 10
 */

public class GetPeakInMountainSeq {
	public static void main(String[] args) {
		int[] n1 = new int[]{1, 2, 4, 8, 6, 3};
		System.out.println(mountainSequence(n1)); // 8
		int[] n2 = new int[]{10, 9, 8, 7};
		System.out.println(mountainSequence(n2)); // 10
	}
	
	/**
     * @param nums a mountain sequence which increase firstly and then decrease
     * @return then mountain top
     */
    public static int mountainSequence(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        
        int low = 0, high = nums.length - 1, mid = 0;
        while (low + 1 < high) {
            mid = (high - low) / 2 + low;
            int prev = mid - 1, next = mid + 1;
            if (nums[prev] < nums[mid] && nums[mid] > nums[next])
            	return nums[mid];
            else if (nums[prev] < nums[mid] && nums[mid] < nums[next]) 
            	low = mid;
            else 
            	high = mid;
        }
        
        return nums[low] > nums[high] ? nums[low]: nums[high];
    }

}
