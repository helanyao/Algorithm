package Search;

/**
 * 	Find the last position of a target number in a sorted array. 
 *  Return -1 if target does not exist.
 *  Example
 *  Given [1, 2, 2, 4, 5, 5].
 *  For target = 2, return 2.
 *  For target = 5, return 5.
 *  For target = 6, return -1.
 */
public class LastPosition1 {

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 2, 4, 5, 5};
		int key = 5;
		System.out.println(findLastPosition(nums, key));
	}
	
	// this is the best solution
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end + start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        } else {
        	return -1;
        }
    }
    
	// this algorithm could work but will too time-consuming
	// when the data set it too large.
	public static int findLastPosition(int[] nums, int key) {
		if (nums == null || nums.length == 0 || key < nums[0] || key > nums[nums.length - 1]) {
			return -1;
		}
		
		int index = findKey(nums, key), i;
		if (index == -1) {
			return index;
		}
		
		for (i = index; i < nums.length && nums[i] == key; i++) {}
		
		return i - 1;
	}
	
	private static int findKey(int[] nums, int key) {
		int low = 0, high = nums.length - 1;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			if (nums[low] == key) {
				return low;
			} else if (nums[high] == key) {
				return high;
			} else if (nums[mid] == key) {
				return mid;
			} else if (nums[mid] < key) {
				low = mid + 1;
				high--;
			} else {
				low++;
				high = mid - 1;
			}
		}
		
		return -1;
	}
}
