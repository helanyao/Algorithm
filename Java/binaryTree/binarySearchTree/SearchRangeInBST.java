package binaryTree.binarySearchTree;

import java.util.ArrayList;

import tree.binaryTree.BNode;

/**
 * @Description
 * Given two values k1 and k2 (k1 < k2) and a root of BST. Find all keys of tree in range k1 to k2. 
 * i.e. print all x such that k1<=x<=k2 in BST. Return all the keys in ascending order.
 * 
 * @Example
 * If k1 = 10 and k2 = 22, then should return [12, 20, 22].
 *     20
 *    /  \
 *   8   22
 *  / \
 * 4   12
 * 
 * @Tag Binary Search Tree, Recursion
 */
public class SearchRangeInBST {
	private ArrayList<Integer> results;
	
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in increasing order.
     */
    public ArrayList<Integer> searchRange(BNode root, int k1, int k2) {
        results = new ArrayList<Integer>();
        helper(root, k1, k2);
        
        return results;
    }
    
    private void helper(BNode root, int k1, int k2) {
        if (root == null) 
            return;
        if (root.val > k1) 
            helper(root.left, k1, k2);
        if (root.val >= k1 && root.val <= k2) 
            results.add(root.val);
        if (root.val < k2) 
            helper(root.right, k1, k2);
    }
}
