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
public class Subsets1 {
	public static void main(String[] args) {
		int[] nums = new int[]{2, 3, 1};
		ArrayList<ArrayList<Integer>> result = subsetsN1(nums);
		for (ArrayList<Integer> v : result)
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
        if (nums == null || nums.length == 0)
            return result;
            
        Arrays.sort(nums);
        ArrayList<Integer> value = new ArrayList<Integer>();
        result.add(value);
        for (int i = 0; i < nums.length; i++) {
        	int size = result.size();
        	for (int j = 0; j < size; j++) {
        		ArrayList<Integer> temp = (ArrayList<Integer>) result.get(j).clone();
        		value = result.get(j);
        		value.add(nums[i]);
        		result.add(temp);
        	}
        }
        
        return result;
    }
    
    /*
     *  Recursion version. 
     *  It is like DFS. Because there are only 2 states for each element in the array: it exists in the 
     *  subset of result or not. So, when dealing the subset, there are only two choices for each element:
     *  pick it or not. Then we can build a binary tree in that way.
     *  The left child means we choose it while the right child means the other side. And all of the leaf
     *  nodes are the result.
     *                      []        
     *                  /          \        
     *                 /            \     
     *                /              \
     *             [1]                []
     *          /       \           /    \
     *         /         \         /      \        
     *      [1 2]       [1]       [2]     []
     *     /     \     /   \     /   \    / \
     * [1 2 3] [1 2] [1 3] [1] [2 3] [2] [3] []
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) 
            return result;
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0);

        return result;
    }


    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {
        result.add(new ArrayList<Integer>(list));

        for (int i = pos; i < num.length; i++) {
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    /*
     * The last solution from CareerCup.
     * 把数组中所有的数分配一个状态，true表示这个数在子集中出现，false表示在子集中不出现。
     * 那么对于一个长度为n的数组，每个数字有出现与不出现两种情况，所以共2n情况。把每种情况都转换出来就是子集。
     * [1 2 3]共有8个子集，每个子集序号的二进制表示，把是1的位对应原数组中的数字取出来就是一个子集，八种情况都取出来就是所有的子集。
 	 *      1	2	3	Subset
     * 0	F	F	F	[]
	 * 1	F	F	T	3
	 * 2	F	T	F	2
	 * 3	F	T	T	23
	 * 4	T	F	F	1
	 * 5	T	F	T	13
	 * 6	T	T	F	12
	 * 7	T	T	T	123
     */
    public ArrayList<ArrayList<Integer>> subsetsN2(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int max = 1 << nums.length;
        Arrays.sort(nums);
        
        for (int k = 0; k < max; k++) {
        	ArrayList<Integer> value = convertIntToSet(nums, k);
        	result.add(value);
        }
        
        return result;
    }
    
    private ArrayList<Integer> convertIntToSet(int[] nums, int k) {
    	ArrayList<Integer> value = new ArrayList<Integer>();
    	int index = 0;
    	for (int i = k; i > 0; i >>= 1) {
    		if ((i & 1) == 1)
    			value.add(nums[index]);
    		index++;
    	}
    	
    	return value;
    }
}
