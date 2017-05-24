package search;

public class BinarySearch {
    public int search(int[] nums, int target, int low, int high) {
        if (high < low)
            return -1;
        else if (nums[high] == target)
            return high;
        else if (nums[low] == target)
            return low;
            
        int mid = (high - low) / 2 + low;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] > target)
            return search(nums, target, low + 1, mid - 1);
        else
            return search(nums, target, mid + 1, high - 1);
    }
	
	public int searchN(int[] nums, int target) {
		if(nums == null || nums.length == 0)
			return -1;
		
		int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
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
        
		return -1;
	}
	
	public int search9N(int[] nums, int target) {
	    if (nums == null || nums.length == 0)
	        return -1;
	        
	    int start = 0, end = nums.length - 1;
	    while (start + 1 < end) {
	        int mid = (end - start) / 2 + start; // (end + start)/2 may exceed the max length of int
	        if (nums[mid] == target)
	            return mid;
	        else if (nums[mid] < target)
	            start = mid;
	        else
	            end = mid;
	    }
	        
	    if (nums[start] == target)
	        return start;
	    else if (nums[end] == target)
	        return end;
	    else
	        return -1;
	 }
	
	public int search9(int[] nums, int target, int low, int high) {
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
