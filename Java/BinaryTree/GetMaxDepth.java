package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import binaryTree.BNode;
import binaryTree.BTree;

/**
 * 
 * @author jhzhu@outlook.com
 * 
 * @Description
 * The max depth is the number of nodes along the longest path from 
 * root node down to the farthest leaf node.
 * 
 * @Tag Uber, Divide and Conquer, Recursion, Binary Tree
 */
public class GetMaxDepth {
	public static void main(String[] args) {
		/*   
		 * init1:
		 *          1
		 *        /   \
		 *      2       3
		 *    /   \    /
		 *   4     5  6
		 */
		String[] init1 = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};
		BTree bt = new BTree(init1);
		
		System.out.println(getDeepth(bt.getRoot()));
		System.out.println(getDeepthN1(bt.getRoot()));
		System.out.println(getDeepthN2(bt.getRoot()));
	}
	
	// The height of a binary tree is the number of edges 
	// between root and its farthest leaf. 
	// A null tree has a depth of 0.
	/**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
	public static int getDeepth(BNode root) {
		if (root == null) 
			return 0;
		else 
			return 1 + Math.max( getDeepth(root.getLeft()), getDeepth(root.getRight()) );
	}
	
	public static int getDeepthN1(BNode root) {
		int deepth = 0;
		if (root == null) 
			return deepth;
		
		Stack<BNode> st = new Stack<BNode>();
		BNode cur = root;
		
		while (!st.isEmpty() || cur != null) {
			while (cur != null) {
				st.push(cur);
				if (cur.left != null) 
					cur = cur.left;
				else 
					cur = cur.right;
			}
			
			if (st.size() > deepth) 
				deepth = st.size();
			
			if (!st.isEmpty()) 
				cur = st.pop();
			
			while (!st.isEmpty() && st.peek().right == cur) 
				cur = st.pop();
			
			if (!st.isEmpty()) 
				cur = st.peek().right;
			else 
				cur = null;
		}
		
		return deepth;
	}
	
    /*
     * We can also call LevelOrderTraversal to do it.
     * The key is to create a dummyNode to help us detect different level.
     *      1  
           / \  
          2   3  
         / \   \  
        4   5   6
     * 
     * In queue： 1, dummy, 2, 3, dummy, 4, 5, 5, dummy  
     * 
    */  
	public static int getDeepthN2(BNode root) {
		int deepth = 0;
		if (root == null) 
			return deepth;
		
		Queue<BNode> q = new LinkedList<BNode>();
		BNode dummy = new BNode(), cur;
		q.offer(root);
		q.offer(dummy);
		
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur == dummy) { // use dummy node to detect the last node of each level
				deepth++;
				if (!q.isEmpty())  // finish BFS, need to exist
					q.offer(dummy);
			} 
			if (cur.left != null) 
				q.offer(cur.left);
			if (cur.right != null) 
				q.offer(cur.right);
		}
		
		return deepth;
	}
}
