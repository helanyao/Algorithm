package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jinghuaz
 * 
 * @Description 
 * Given a binary tree, return the level order traversal of its nodes' values. 
 * 
 * @Example
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ] 
 * 
 * @Tag Linkedin, Facebook, Binary Tree, BFS, Uber
 */
public class LevelOrderTraversal {
	/**
	 * @author jhzhu@outlook.com
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(BNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null)
        	return result;
        Queue<BNode> q = new LinkedList<BNode>();
        q.add(root);
        
        while (!q.isEmpty()) {
        	int size = q.size();
        	ArrayList<Integer> nodes = new ArrayList<Integer>();
        	for (int i = 0; i < size; i++) {
        		BNode n = q.poll();
        		nodes.add(n.val);
        		if (n.left != null)
        			q.add(n.left);
        		if (n.right != null)
        			q.add(n.right);
        	}
        	result.add(nodes);
        }
        
        return result;
    }

}
