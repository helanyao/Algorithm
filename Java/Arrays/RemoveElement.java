package Arrays;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * 
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 * 
 * https://leetcode.com/problems/remove-element/
 */

public class RemoveElement {
	// test case: nums = [1], val = 1
	public static void main(String[] args) {
		int[] nums = new int[]{3, 2, 2, 3};
		int len1 = removeElement1(nums, 3);
		System.out.println("\n" + len1);
		int len2 = removeElement2(nums, 3);
		System.out.println(len2);
//		for (int i = 0; i < len; i++) {
//			System.out.print(nums[i] + " ");
//		}
	}
	
    public static int removeElement1(int[] A, int elem) {
        int i = 0, pointer = A.length - 1;
        while(i <= pointer){
            if(A[i] == elem){
                A[i] = A[pointer];
                pointer--;
            } else {
                i++;
            }
        }
        
        for (int j = 0; j < pointer + 1; j++)
        	System.out.print(A[j]);
        
        return pointer + 1;
    }
	
    public static int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int low = 0, high = nums.length - 1, count = 0;;
        while (low <= high) {
            while (low < nums.length && nums[low] != val) {
            	count++;
                low++;
            }
            while (high >= 0 && nums[high] == val) {
                high--;
            }
            if (low <= high) {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
            }
        }
        
        return count;
    }

}
