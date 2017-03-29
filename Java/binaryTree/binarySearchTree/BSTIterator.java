package binaryTree.binarySearchTree;

import java.util.Stack;

import binaryTree.BNode;

/**
 * Design an iterator over a binary search tree with following rules:
 * 1. Elements are visited in ascending order (i.e. an in-order traversal)
 * 2. next() and hasNext() queries run in O(1) time in average.
 * 
 * @Example
 * For following tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]
 *
 *    10
 *  /    \
 * 1      11
 *  \       \
 *   6       12
 * 
 * @Tag Binary Search Tree, Google, LinkedIn, Facebook
 */
public class BSTIterator {
	private Stack<BNode> stack = new Stack<>();
    private BNode curt;
    
    // @param root: The root of binary tree.
    public BSTIterator(BNode root) {
        curt = root;
    }

    // @return: True if there has next node, or false
    public boolean hasNext() {
        return (curt != null || !stack.isEmpty());
    }
    
    //@return: return next node. If no next node, return null.
    public BNode next() {
        while (curt != null) {
            stack.push(curt);
            curt = curt.left;
        }
        
        BNode node = null;
        if (!stack.isEmpty()) {
        	curt = stack.pop();
            node = curt;
            curt = curt.right;
        }
        
        return node;
    }
}