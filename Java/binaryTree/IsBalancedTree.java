package binaryTree;

import tree.binaryTree.BNode;

/**
 * @Description
 * Given a binary tree, determine if it is height-balanced.
 * Height-balanced binary tree is defined as a binary tree,
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * 
 * @Tag Divide and Conquer, Recursion
 */
public class IsBalancedTree {
	/**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
	// Version 1: using ResultType
    public boolean isBalanced1(BNode root) {
        return helper(root).isBalanced;
    }
    
    private ResultType helper(BNode root) {
        if (root == null) 
            return new ResultType(true, 0);
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        // subtree not balance
        if (!left.isBalanced || !right.isBalanced) 
            return new ResultType(false, -1);
        
        // root not balance
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) 
            return new ResultType(false, -1);
        
        return new ResultType(true, Math.max(left.maxDepth, right.maxDepth) + 1);
    }
    
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    // Version 2: without ResultType
    public boolean isBalanced2(BNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(BNode root) {
        if (root == null) 
            return 0;

        int left = maxDepth(root.left), right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) 
            return -1;
        
        return Math.max(left, right) + 1;
    }
    
    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }
}

