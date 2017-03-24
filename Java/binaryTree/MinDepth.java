package binaryTree;

import java.util.Stack;

import binaryTree.BNode;

/**
 * 
 * @author jhzhu@outlook.com
 * 
 * @Description
 * The min depth is the number of nodes along the shortest path from 
 * root node down to the nearest leaf node.
 *
 * @Tag Binary Tree, DFS
 */
public class MinDepth {
	/**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
	public int minDepth(BNode root) {
        // Corner case. Should never be hit unless the code is
        // called on root = NULL
        if (root == null)
            return 0;

        // Base case : Leaf Node. This accounts for height = 1.
        if (root.left == null && root.right == null)
            return 1;

        // If left subtree is NULL, recur for right subtree
        if (root.left == null)
            return minDepth(root.right) + 1;

        // If right subtree is NULL, recur for right subtree
        if (root.right == null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left),
                        minDepth(root.right)) + 1;
    }
	
	/**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
	public int minDepthN(BNode root) {
        int height = -1;
        if(root == null) 
            return 0;
        
        BNode n = root;
		Stack<BNode> st = new Stack<BNode>();
		
		while(n != null || !st.isEmpty()) {
			while(n != null) {
				st.push(n);
				if(n.left != null) 
					n = n.left;
				else 
					n = n.right;
			} // inner while
			
			n = st.pop();
			if(n.left == null && n.right == null) 
			    if(height == -1 || height > st.size()) 
			        height = 1 + st.size();
			
			while(!st.isEmpty() && st.peek().right == n) 
				n = st.pop();
			
			if(!st.empty()) 
				n = st.peek().right;
			else 
				n = null;
		} // out while
		
		return height;
    }
}
