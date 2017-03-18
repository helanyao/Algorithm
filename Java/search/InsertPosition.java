package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu@outlook.com
 * 
 * Given a sorted array and a target value, 
 * return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * You may assume NO duplicates in the array.
 * 
 * Example
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 */
public class InsertPosition {
	public static void main(String[] args) {
		int[] nums = new int[]{1, 3, 5, 6};
		int target = 0;
		System.out.println(findInsertPos(nums, target));

	}
	
	public static int findInsertPos(int[] nums, int target) {
		if (nums == null || nums.length == 0) 
			return 0;
		
		List<Integer> range = search(nums, target);
		if (range.size() == 1) 
			return range.get(0);
		else if (range.size() != 2) 
			return -1; // error
		
		int low = range.get(0), high = range.get(1);
		if (target < nums[low]) 
			return low;
		else if (target > nums[high]) 
			return high + 1;
		else 
			return high;
	}
	
	// for the return List<Integer>:
	// if we find the target, list(0) is its position
	// if the target dosen't exist, list(0) and list(1) stand for the range closer to it
	private static List<Integer> search(int[] nums, int target) {
		List<Integer> range = new ArrayList<Integer>();
		int low = 0, high = nums.length - 1;
		
		while (low + 1 < high) {
			int mid = (low + high) / 2;
			if (nums[mid] == target) {
				range.add(mid);
				return range;
			} else if (nums[mid] < target) {
				low = mid;
			} else {
				high = mid;
			}
		}
		
		if (nums[low] == target) {
			range.add(low);
		} else if (nums[high] == target) {
			range.add(high);
		} else {
			range.add(low);
			range.add(high);
		}
		
		return range;
	}
	
	// A simple and clean algorithm to implement it.
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) 
            return 0;
  
        int start = 0, end = A.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) 
                return mid;
            else if (A[mid] < target) 
                start = mid;
            else 
                end = mid;
        }
        
        if (A[start] >= target) 
            return start;
        else if (A[end] >= target) 
            return end;
        else 
            return end + 1;
    }
}
