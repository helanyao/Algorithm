package Search;

public class BinarySearch {
	public static void main(String[] args) {
		int[] a = new int[]{1, 2};
		int key = 2;
		System.out.println(findPosition(a, key));
		System.out.println(searchN(a, key));
	}
	
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public static int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        return search(nums, target, 0, nums.length - 1);
    }
    
    private static int search(int[] nums, int target, int low, int high) {
        if (high < low) {
            return -1;
        } else if (nums[high] == target) {
            return high;
        } else if (nums[low] == target) {
            return low;
        }
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return search(nums, target, low + 1, mid - 1);
        } else {
            return search(nums, target, mid + 1, high - 1);
        }
        
    }
	
	public static int searchN(int[] nums, int target) {
		if(nums == null || nums.length == 0) {
			return -1;
		}
		
		int low = 0, high = nums.length - 1, mid;
		
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[low] == target) {
                return low;
            } else if (nums[high] == target) {
                return high;
            } else if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
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
