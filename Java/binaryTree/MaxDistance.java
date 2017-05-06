package binaryTree;

import tree.binaryTree.BNode;

public class MaxDistance {
	public int getMaxDistance(BNode root) {
		int[] max = new int[1];
		getMaxHelper(root, max);
		
		return max[0];
	}
	
	private void getMaxHelper(BNode root, int[] max) {
		if (root == null) 
			return;
		
		getMaxHelper(root.left, max);
		getMaxHelper(root.right, max);
		
		int maxTmp;
		if (root.left != null && root.right != null)
			maxTmp = root.left.deepth + root.right.deepth;
		else
			maxTmp = root.deepth - 1;
		
		max[0] = Math.max(maxTmp, max[0]);
	}
	
	/**
	 *   test case 1:
	 *       1
	 *     /   \
	 *    2      3
	 *   / \    /
	 *  4   5  6
	 *  
	 *  test case 2:
	 *        1
	 *       /  
	 *      2
	 *     /
	 *    3
	 *   / \
	 *  4   5
	 */
}
