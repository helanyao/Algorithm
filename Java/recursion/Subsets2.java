package recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author jhzhu@outlook.com
 *
 * @Description
 * Given a set of distinct integers, return all possible subsets.
 * Note:
 * 1. Elements in a subset must be in non-descending order.
 * 2. The solution set must not contain duplicate subsets.
 * 
 * @Example
 * If S = [1,2,3], a solution is:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 * 
 * @Tag
 * Facebook, Uber, Recursion
 * 
 */
public class Subsets2 {
	public static void main(String[] args) {
		int[] nums1 = new int[]{1, 2, 2};
		ArrayList<ArrayList<Integer>> result1 = subsets(nums1);
		for (ArrayList<Integer> v : result1)
			System.out.println(v.toString());
		
		System.out.println("-----");
		
		int[] nums2 = new int[]{2, 2, 2, 2, 2};
		ArrayList<ArrayList<Integer>> result2 = subsets(nums2);
		for (ArrayList<Integer> v : result2)
			System.out.println(v.toString());
	}
	
	/**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
	
	/* 
	 * Non-recursion. For [1,2,3]，it starts with empty set. When dealing with 1,
	 * it equals to add 1 into empty set, [1]. Now, there are two elements in result: [] and [1].
	 * Now, let's deal with 2. We could easily add 2 into each subset([] and [1]) in the result.
	 * The result is [2]，[1, 2]. So, the result is [], [1], [2], and [1, 2] now. 
	 * We could follow the same idea to deal with 3 which is to add 3 into each subset.
	 */
    public static ArrayList<ArrayList<Integer>> subsetsN1(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> value = new ArrayList<Integer>();
        result.add(value);
        if (nums == null || nums.length == 0)
            return result;
            
        Arrays.sort(nums);
        int size = 1, last = nums[0];
        for (int i = 0; i < nums.length; i++) {
        	if (last != nums[i]) {
        		last = nums[i];
        		size = result.size();
        	}
        	int newSize = result.size();
        	for (int j = newSize - size; j < newSize; j++) {
        		ArrayList<Integer> temp = (ArrayList<Integer>) result.get(j).clone();
        		temp.add(nums[i]);
            	result.add(temp);
        	}
        }
        
        return result;
    }
    
    public static ArrayList<ArrayList<Integer>> subsetsN2(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> value = new ArrayList<Integer>();
        result.add(value);
        if (nums == null || nums.length == 0)
            return result;
            
        Arrays.sort(nums);
        value = new ArrayList<Integer>();
        value.add(nums[0]);
        result.add(value);
        int lastSize = 1;
        Integer last = nums[0];
        for (int i = 1; i < nums.length; i++) {
        	int start = 0, size = result.size();
        	if (last != null && last == nums[i]) {
        		start = result.size() - lastSize;
        		start = result.size() - start;
        	}
        	for (int j = start; j < size; j++) {
        		ArrayList<Integer> temp = (ArrayList<Integer>) result.get(j).clone();
        		temp.add(nums[i]);
            	result.add(temp);
        	}
        	last = nums[i];
        	lastSize = size; 
        }
        
        return result;
    }
    
    /*
     *  Recursion version. Like Subset1, we could build similar binary tree. The only difference is
     *  to add one more line (if (i != pos && num[i] == num[i - 1]) continue;), 
     *  which is to skip duplicate number to avoid add duplicate subset.
     *                         []        
     *                    /          \        
     *                   /            \     
     *                  /              \
     *               [1]                []
     *            /       \           /    \
     *           /         \         /      \          
     *        [1 2]       [1]       [2]     []
     *       /     \     /   \     /   \    / \
     *   [1 2 2] [1 2]  X   [1]  [2 2] [2] X  []
     */
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public static ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) 
            return result;
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0);

        return result;
    }


    private static void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {
        result.add(new ArrayList<Integer>(list));

        for (int i = pos; i < num.length; i++) {
        	if (i != pos && num[i] == num[i - 1])
        		continue;
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
    

}
