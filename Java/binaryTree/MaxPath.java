package binaryTree;

import tree.binaryTree.BNode;
import tree.binaryTree.BTree;

/**
 * @Description
 * Find the max path sum. The path may start and end at any node.
 * 
 * @Example
 *   1
 *  / \
 * 2   3
 * return 6.
 * 
 * @Tag Binary Tree, Divide and Conquer, DP, Recursion
 */
public class MaxPath {
	public static void main(String[] args) {
		int[] max = new int[]{Integer.MIN_VALUE};
		// case1
		String[] init = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
		BTree bt = new BTree();
		bt.create(init);
		int result = getMaxPath(bt.getRoot(), max); 
		print(result, max); // 56
		
		max[0] = Integer.MIN_VALUE;
		// case2
		init = new String[]{"-1"};
		bt.create(init);
		result = getMaxPath(bt.getRoot(), max); 
		print(result, max); // -1
		
		max[0] = Integer.MIN_VALUE;
		// case3
		init = new String[]{"1","2","-5","4","#","5","6"};
		bt.create(init);
		result = getMaxPath(bt.getRoot(), max); 
		print(result, max); // 8
	}
	
	private static void print(int result, int[] max) {
		if (result > max[0])
			System.out.println(result);
		else
			System.out.println(max[0]);
	}
	
	// *************
	// Solution 1
	// *************
	/**
	 * @author jhzhu@outlook.com
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum1(BNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        int result = getMaxPath(root, max);
        if (result > max[0])
            return result;
        else
            return max[0];
    }
    
	private static int getMaxPath(BNode root, int[] max) {
		if (root == null)
			return Integer.MIN_VALUE;
		
		int left = getMaxPath(root.left, max);
		int right = getMaxPath(root.right, max);
		
		if (max[0] == Integer.MIN_VALUE)
			max[0] = root.val;
		if (left > max[0])
			max[0] = left;
		if (right > max[0])
			max[0] = right;
		if (root.val > max[0])
			max[0] = root.val;
		// we need to check it for case. Otherwise, -1 + Integer.MIN_VALUE causes issues.
		if (right != Integer.MIN_VALUE && left != Integer.MIN_VALUE && root.val + right + left > max[0])
			max[0] = root.val + right + left;
		
		int result = root.val;
		if (right != Integer.MIN_VALUE)
			result = Math.max(result, root.val + right);
		if (left != Integer.MIN_VALUE)
			result = Math.max(result, root.val + left);
			
		return result;
	}
	
	// *************
	// Solution 2
	// *************
	public int maxPathSum2(BNode root) {
        ResultType2 result = helper2(root);
        return result.maxPath;
    }
	
	private class ResultType2 {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath; 
        ResultType2(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType2 helper2(BNode root) {
        if (root == null) 
            return new ResultType2(0, Integer.MIN_VALUE);
        
        // Divide
        ResultType2 left = helper2(root.left);
        ResultType2 right = helper2(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType2(singlePath, maxPath);
    }
    
    // *************
 	// Solution 3
 	// *************
    public int maxPathSum3(BNode root) {
        ResultType3 result = helper3(root);
        return result.maxPath;
    }
    
    private class ResultType3 {
        int singlePath, maxPath;
        ResultType3(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType3 helper3(BNode root) {
        if (root == null) 
            return new ResultType3(Integer.MIN_VALUE, Integer.MIN_VALUE);
        
        // Divide
        ResultType3 left = helper3(root.left);
        ResultType3 right = helper3(root.right);

        // Conquer
        int singlePath =
            Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath,
                           Math.max(left.singlePath, 0) + 
                           Math.max(right.singlePath, 0) + root.val);

        return new ResultType3(singlePath, maxPath);
    } 
}
