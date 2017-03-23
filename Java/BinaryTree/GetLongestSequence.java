package BinaryTree;

import binaryTree.BNode;

/**
 *  找出二叉树中最长连续子串
 *  即全部往左的连续节点，或是全部往右的连续节点
 */

public class GetLongestSequence {
	
	// For node n, firstly get the length for path1 and path2
	// where path1 stands for the length of sequence when only traveling in left from n
	// and paths means the right one.
	// 
	// After that, get the value of its children in recursive.
	// In the end, find the max one among the 4 values;
	public int findLS1(BNode root) {
		if (root == null) {
			return 0;
		}
		
		int curLeftLS = 0, curRightLS = 0, leftChildLS, rightChildLS, max;
		BNode n = root.left;
		while (n != null) {
			curLeftLS++;
			n = n.left;
		}
		n = root.right;
		while (n != null) {
			curRightLS++;
			n = n.right;
		}
		
		leftChildLS = findLS1(root.left);
		rightChildLS = findLS1(root.right);
		
		max = Math.max(leftChildLS, rightChildLS);
		max = Math.max(max, curLeftLS);
		max = Math.max(max, curRightLS);
		
		return max;
	}
	
	public int findLS2(BNode root) {
		int[] max = new int[1]; // why uses int[1] is the parameter is pass by value
		findLS2Helper(root, max);
		
		return max[0];
	}
	
	private int[] findLS2Helper(BNode root, int[] max) { 
		int[] childMax = new int[2]; // childMax[0] for left child
		if (root == null) {
			childMax[0] = -1;
			childMax[0] = -1;
			return childMax;
		}
		
		childMax[0] = findLS2Helper(root.left, max)[0] + 1; // left child
		childMax[1] = findLS2Helper(root.right, max)[0] + 1; // right child
		max[0] = Math.max(childMax[0], max[0]);
		max[0] = Math.max(childMax[1], max[0]);
		
		return childMax;
	}
}
