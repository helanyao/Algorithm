package search;

/**
 * Searche for a value in an m x n matrix, return the occurrence of it.
 * 
 * This matrix has the following properties:
 * 1. Integers in each row are sorted from left to right.
 * 2. Integers in each column are sorted from up to bottom.
 * 3.No duplicate integers in each row or column.
 * 
 * Example:
 * [
 *  [1, 3, 5, 7],
 *  [2, 4, 7, 8],
 *  [3, 5, 9, 10]
 * ]
 *  
 *  Given target = 3, return 2.
 *  
 *  Tag: Google
 *  
 */

public class Search2DMatrix2 {
	public static void main(String[] args) {
		int[][] nums1 = new int[][]{{1,3,5,7}, {2,4,7,8}, {3,5,9,10}};
		int target1 = 3;
		System.out.println(getOccurence(nums1, target1)); // 2
		
		int[][] nums2 = new int[][]{{62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80}, 
			{63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81}, 
			{64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82},
			{65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83},
			{66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84},
			{67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85}};
		int target2 = 81;
		System.out.println(getOccurence(nums2, target2)); // 5
	}
	
	/**
     * @param nums: A list of lists of integers
     * @param target: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
	public static int getOccurence(int[][] nums, int target) {
		if (nums == null || nums.length == 0 || target < nums[0][0] 
				|| target > nums[nums.length - 1][nums[0].length - 1])
			return 0;
		
		int row = searchRow(nums, target);
		int occurence = 0;
		
		for (int i = 0; i <= row; i++)
			if (exist(nums[i], target))
				occurence++;
		
		return occurence;
	}
	
	private static int searchRow(int[][] nums, int target) {
		int low = 0, high = nums.length - 1;
		while (low + 1 < high) {
			int mid = ((high - low) >> 1) + low;
			if (nums[mid][0] == target) 
				return mid;
			else if (nums[mid][0] < target) 
				low = mid;
			else
				high = mid;
		}
		
		if (nums[high][0] <= target) 
			return high;
		else
			return low;
	}
	
	private static boolean exist(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		while (low + 1 < high) {
			int mid = ((high - low) >> 1) + low;
			if (nums[mid] == target)
				return true;
			else if (nums[mid] < target)
				low = mid;
			else
				high = mid;
		}
		
		if (nums[low] == target || nums[high] == target)
			return true;
		else
			return false;
	}

}
