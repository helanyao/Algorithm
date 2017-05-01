package binaryTree;

import java.util.ArrayList;

import tree.binaryTree.BNode;

/**
 * @Description
 * Find all paths that sum of the nodes in the path equals to a given number target.
 * A valid path is from root node to any of the leaf nodes.
 * 
 * @Example
 * Target = 5:
 *      1
 *     / \
 *    2   4
 *   / \
 *  2   3
 * return
 * 
 * [
 *   [1, 2, 2],
 *   [1, 4]
 * ] 
 *
 * @Tag Binary Tree, Recursion
 */

public class PathSumList1 {
	/**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public ArrayList<ArrayList<Integer>> binaryTreePathSum(BNode root, int target) {
        // Algorithm: Traverse
        // Use recursion to traverse the tree in pre-order, pass with a parameter
        // `sum` as the sum of each node from root to current node.
        // Put the whole path into result if the leaf has a sum equal to target.
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) 
            return result;
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        helper(root, path, root.val, target, result);
        
        return result;
    }
    
    private void helper(BNode root,
                        ArrayList<Integer> path,
                        int sum,
                        int target,
                        ArrayList<ArrayList<Integer>> result) {
                            
        // meet leaf
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }
        
        // go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, sum + root.left.val, target, result);
            path.remove(path.size() - 1);
        }
        
        // go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, sum + root.right.val, target, result);
            path.remove(path.size() - 1);
        }
    }
}
