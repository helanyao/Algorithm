package binaryTree;

import tree.binaryTree.BNode;
import tree.binaryTree.BTree;
import tree.binaryTree.BTreeHelper;

/**
 * @Description
 * Find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path 
 * need to be from parent to child (cannot be the reverse).
 * 
 * @Example
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * 3-4-5, so return 3.
 * 
 *    2
 *     \
 *      3
 *     / 
 *    2    
 *   / 
 *  1
 * 2-3, not3-2-1, so return 2.
 *
 * @Tag Binary Tree, Divide and Conquer, Recursion, Google
 */
public class LongestConsecutiveSeq1 {
	public static void main(String[] args) {
		String[] init = new String[]{"2", "1", "3"};
		BTree bt = new BTree();
		BTreeHelper h = new BTreeHelper();
		bt.root = h.create(init);
		System.out.println(longestConsecutive(bt.getRoot()));
	}
	
	/**
	 * @author jhzhu@outlook.com
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
	public static int longestConsecutive(BNode root) {
        return helper(root, null, 0);
    }
    
	private static int helper(BNode root, BNode par, int max) {
		if (root == null)
			return 0;
		
		int len = 1;
		if (par != null && par.val - root.val == 1)
			 len = ++max;
		int left = helper(root.left, root, len);
		int right = helper(root.right, root, len);
		
		return Math.max(len, Math.max(left, right));
	}
}

//version 2: Another Traverse + Divide Conquer 
class LCS1Solution2 {
	private int longest;
	
	public int longestConsecutive(BNode root) {
		longest = 0;
		helper(root);
		return longest;
	}
 
	private int helper(BNode root) {
		if (root == null) 
         return 0;
     
		int left = helper(root.left);
		int right = helper(root.right);
		int subtreeLongest = 1; // at least we have root
		if (root.left != null && root.val + 1 == root.left.val) 
			subtreeLongest = Math.max(subtreeLongest, left + 1);
		if (root.right != null && root.val + 1 == root.right.val) 
			subtreeLongest = Math.max(subtreeLongest, right + 1);
     
		if (subtreeLongest > longest) 
         longest = subtreeLongest;
     
		return subtreeLongest;
	}
}

//version 3: Divide Conquer
class LCS1Solution3 {
	private class ResultType {
		int maxInSubtree;
		int maxFromRoot;
		public ResultType(int maxInSubtree, int maxFromRoot) {
			this.maxInSubtree = maxInSubtree;
			this.maxFromRoot = maxFromRoot;
		}
	}
 
	public int longestConsecutive(BNode root) {
		return helper(root).maxInSubtree;
	}
 
	private ResultType helper(BNode root) {
		if (root == null) 
			return new ResultType(0, 0);
     
		ResultType left = helper(root.left);
		ResultType right = helper(root.right);
		ResultType result = new ResultType(0, 1); // 1 is the root itself.
     
		if (root.left != null && root.val + 1 == root.left.val) 
			result.maxFromRoot = Math.max(result.maxFromRoot, left.maxFromRoot + 1);
		if (root.right != null && root.val + 1 == root.right.val) 
			result.maxFromRoot = Math.max(result.maxFromRoot, right.maxFromRoot + 1);
     
		result.maxInSubtree = Math.max(result.maxFromRoot, 
				Math.max(left.maxInSubtree, right.maxInSubtree));
     
		return result;
	}
}