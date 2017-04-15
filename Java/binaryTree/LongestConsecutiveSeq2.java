package binaryTree;

/**
 * @Description
 * Find the length of the longest consecutive sequence path.
 * The path could be start and end at any node in the tree
 * 
 * @Example
 *     1
 *    / \
 *   2   0
 *  /
 * 3
 * Return 4 // 0-1-2-3-4
 *
 * @Tag DFS, Binary Tree, Recursion
 */

public class LongestConsecutiveSeq2 {
	public static void main(String[] args) {
		String[] init = new String[]{"1", "2", "0", "3"};
		BTree bt = new BTree(init, 2);
		System.out.println(longestSeq(bt.getRoot()));
	}
	
	/**
	 * @author jhzhu@outlook.com
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
	public static int longestSeq(BNode root) {
		return helper(root).max;
	}
	
	private static LCS2ResultType helper(BNode root) {
		if (root == null)
			return new LCS2ResultType(0, 0, 0);
		
		LCS2ResultType left = helper(root.left);
		LCS2ResultType right = helper(root.right);
		
		int maxSubLen = Math.max(left.max, right.max);
		int max = 1, down = 1, up = 1;
		int leftDown = 0, leftUp = 0, rightDown = 0, rightUp = 0;
		
		if (root.left != null && root.val - root.left.val == 1)
			leftDown = left.down;
		if (root.right != null && root.val - root.right.val == 1)
			rightDown = right.down;
		down = Math.max(leftDown, rightDown) + 1;
		
		if (root.left != null && root.val - root.left.val == -1)
			leftUp = left.up ;
		if (root.right != null && root.val - root.right.val == -1)
			rightUp = right.up;
		up = Math.max(leftUp, rightUp) + 1;
		
		// Minus 1 is because it adds two 1 when calculating down and up.
		max = Math.max(max, down + up - 1);
		max = Math.max(max, maxSubLen);
		
		return new LCS2ResultType(down, up, max);
	}
}

class LCS2ResultType {
	int down;
	int up;
	int max;
	
	LCS2ResultType(int d, int u, int m) {
		down = d;
		up = u;
		max = m;
	}
}