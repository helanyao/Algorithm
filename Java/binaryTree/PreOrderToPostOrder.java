package binaryTree;

/**
 * @author jhzhu@outlook.com
 * 
 * @Description
 * In a full binary tree, given its pre-order, calculate its post-order.
 * 
 * @Example
 *      1
 *     / \
 *   2     3
 *  / \   / \
 * 4  5  6   7
 * PreOrder:  1 2 4 5 3 6 7
 * PostOrder: 4 5 2 6 7 3 1
 * 
 * @Tag Binary Tree, Recursion
 */

public class PreOrderToPostOrder {
	public void PreToPost(int[] pre, int preLow, int preHigh, int[] post, int postLow, int postHigh) {
		if (pre == null || post == null) 
			throw new IllegalArgumentException("Array is null.");
		
		if (preHigh >= preLow) {
			post[postHigh] = pre[preLow];
			int half = (preHigh - preLow) >> 1;
			// left child tree
			PreToPost(pre, preLow + 1, preLow + half, post, postLow, postLow + half - 1);
			// right child tree
			PreToPost(pre, preLow + half + 1, preHigh, post, postLow + half, postHigh -1);
		}
	}
}
