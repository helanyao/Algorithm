package Arrays;

/**
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, 
 * the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 
 * https://leetcode.com/problems/rotate-array/
 */

public class RotateArray {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,6,7};
		int k = 3;
		rotate1(nums, k);
		for (int i : nums) {
			System.out.print(i + " ");
		}
	}
	
	// Space is O(n) and time is O(n)
    public static void rotate1(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k <= 0 || k % nums.length == 0) {
            return;
        }
        k %= nums.length;
        
    	int[] result = new int[nums.length];
    	for(int i=0; i < k; i++){
    		result[i] = nums[nums.length - k + i];
    	}
    	int j=0;
    	for(int i=k; i<nums.length; i++){
    		result[i] = nums[j];
    		j++;
    	}
    	System.arraycopy( result, 0, nums, 0, nums.length );
    }
    
    // This solution is like a bubble sort.
    // Space is O(1) and time is O(n*k)
    public static void rotate2(int[] arr, int k) {
    	if (arr == null || k < 0) {
    	    return;
    	}
     
    	for (int i = 0; i < k; i++) {
    		for (int j = arr.length - 1; j > 0; j--) {
    			int temp = arr[j];
    			arr[j] = arr[j - 1];
    			arr[j - 1] = temp;
    		}
    	}
    }
    
    // Assuming given {1,2,3,4,5,6} and k is 2. The basic idea is:
    // 1. Divide the array two parts: 1,2,3,4 and 5, 6
    // 2. Rotate first part: 4,3,2,1,5,6
    // 3. Rotate second part: 4,3,2,1,6,5
    // 4. Rotate the whole array: 5,6,1,2,3,4
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++; end--;
        }
    }
    
    public void rotate3(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        
        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }
}
