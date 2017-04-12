package binaryTree;

/**
 * @Description
 * Find the subtree with minimum sum. Return the root of the subtree.
 *
 * @Example
 *      1
 *    /   \
 *  -5     2
 *  / \   /  \
 * 0   2 -4  -5 
 * return the node 1.
 * 
 * @Tag Microsoft, Yelp, DFS, Binary Tree, Divide and Conquer, Recursion
 */
public class MinSubtree {
	private static int MIN_VALUE = Integer.MAX_VALUE;
	private static BNode SUB_ROOT = null;
	public static void main(String[] args) {
		String[] init = new String[]{"1", "-5", "2", "0", "2", "-4", "-5"};
		BTree bt = new BTree(init, 2);
		System.out.println(findSubtree(bt.getRoot()).val);
	}
	
	/**
	 * @author jhzhu@outlook.com
     * @param root the root of binary tree
     * @return the root of the minimum subtree
     */
	public static BNode findSubtree(BNode root) {
		helper(root);
		return SUB_ROOT;
	}
	
	private static int helper(BNode root) {
		if (root == null)
			return 0;
		
		int left = helper(root.left);
		int right = helper(root.right);
		int sum = left + right + root.val;
		if (sum < MIN_VALUE) {
			MIN_VALUE = sum;
			SUB_ROOT = root;
		}
		
		return sum;
	}
	
	class ResultType {
		int val;
		BNode subRoot;
		
		public ResultType(int v, BNode n) {
			val = v;
			subRoot = n;
		}
	}
}

//version 2: Pure divide conquer
class Solution2 {
	class ResultType {
		 public BNode minSubtree;
		 public int sum, minSum;
		 public ResultType(BNode minSubtree, int minSum, int sum) {
		     this.minSubtree = minSubtree;
		     this.minSum = minSum;
		     this.sum = sum;
		 }
	}
	
	public BNode findSubtree(BNode root) {
		ResultType result = helper(root);
		
		return result.minSubtree;
	}
 
	public ResultType helper(BNode node) {
		if (node == null)
			return new ResultType(null, Integer.MAX_VALUE, 0);
     
		ResultType leftResult = helper(node.left);
		ResultType rightResult = helper(node.right);
		ResultType result = new ResultType(
				node,
				leftResult.sum + rightResult.sum + node.val,
				leftResult.sum + rightResult.sum + node.val
		);
     
		if (leftResult.minSum < result.minSum) {
			result.minSum = leftResult.minSum;
			result.minSubtree = leftResult.minSubtree;
		}
		if (rightResult.minSum < result.minSum) {
			result.minSum = rightResult.minSum;
			result.minSubtree = rightResult.minSubtree;
		}
     
		return result;
	}
}