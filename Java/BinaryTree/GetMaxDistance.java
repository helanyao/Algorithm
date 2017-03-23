package BinaryTree;

import binaryTree.BNode;
import binaryTree.BTree;

public class GetMaxDistance {

	public static void main(String[] args) {
		String[] init = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "]"};	
		BTree bt = new BTree(init);
		setDepth(bt.getRoot());
		System.out.println(getMaxDistance(bt.getRoot()));
		BNode root = create();
		System.out.println(getMaxDistance(root));
	}
	
	public static int getMaxDistance(BNode root) {
		int[] max = new int[1];
		getMaxHelper(root, max);
		
		return max[0];
	}
	
	private static void getMaxHelper(BNode root, int[] max) {
		if (root == null) {
			return;
		} 
		
		getMaxHelper(root.left, max);
		getMaxHelper(root.right, max);
		
		int maxTmp;
		if (root.left != null && root.right != null) {
			maxTmp = root.left.deepth + root.right.deepth;
		} else {
			maxTmp = root.deepth - 1;
		}
		max[0] = Math.max(maxTmp, max[0]);
	}
	
	private static void setDepth(BNode root) {
		if (root != null) {
			root.deepth = getDeepth(root);
			setDepth(root.left);
			setDepth(root.right);
		}
	}
	
	private static int getDeepth(BNode root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max( getDeepth(root.getLeft()), getDeepth(root.getRight()) );
		}
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
	private static BNode create() {
		BNode root = new BNode(1, 4);
		BNode n2 = new BNode(2, 3);
		BNode n3 = new BNode(3, 2);
		BNode n4 = new BNode(4, 1);
		BNode n5 = new BNode(5, 1);
		root.left = n2;
		n2.left = n3;
		n3.left = n4;
		n3.right = n5;
		
		return root;
	}
}
