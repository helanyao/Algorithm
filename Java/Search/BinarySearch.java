package Search;

public class BinarySearch {
	public static void main(String[] args) {
		// int[] a = new int[]{1, 2, 2, 4, 6, 7};
		int[] a = new int[]{1, 3, 3, 3, 5};
		int key = 3;
		System.out.println(findPosition(a, key));
		System.out.println(searchN(a, key));
		System.out.println(search9N(a, key));
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
        
        //return search(nums, target, 0, nums.length - 1);
        return search9(nums, target, 0, nums.length - 1);
    }
    
    private static int search(int[] nums, int target, int low, int high) {
        if (high < low) {
            return -1;
        } else if (nums[high] == target) {
            return high;
        } else if (nums[low] == target) {
            return low;
        }
        int mid = (high - low) / 2 + low;
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
        	System.out.println(low + " " + high);
            mid = (high - low) / 2 + low;
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
        System.out.println(low + " " + high);
		return -1;
	}
	
	public static int search9N(int[] nums, int target) {
	    if (nums == null || nums.length == 0) {
	        return -1;
	    }
	        
	    int start = 0, end = nums.length - 1;
	    while (start + 1 < end) {
	    	System.out.println(start + " " + end);
	        int mid = (end - start) / 2 + start; // (end + start)/2 may exceed the max of int
	        if (nums[mid] == target) {
	            return mid;
	        } else if (nums[mid] < target) {
	            start = mid;
	        } else {
	            end = mid;
	        }
	    }
	    System.out.println(start + " " + end);
	        
	    if (nums[start] == target) {
	        return start;
	    } else if (nums[end] == target) {
	        return end;
	    } else {
	        return -1;
	    }
	 }
	
	public static int search9(int[] nums, int target, int low, int high) {
		if (low + 1 > high) 
			return -1;
		
		int mid = (high - low) / 2 + low;
		if (nums[mid] == target)
			return mid;
		else if (nums[mid] < target)
			return search9(nums, target, mid, high);
		else
			return search9(nums, target, low, mid);
	}

}
