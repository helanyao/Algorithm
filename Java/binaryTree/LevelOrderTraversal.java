package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
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
	// version 1: one queue BFS
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

//version 2:  DFS
class LevelOrderTraversalSolution2 {
	public ArrayList<ArrayList<Integer>> levelOrder(BNode root) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        
        if (root == null) 
            return results;
        
        int maxLevel = 0;
        while (true) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, maxLevel);
            if (level.size() == 0) {
                break;
            }
            
            results.add(level);
            maxLevel++;
        }
        
        return results;
    }
    
    private void dfs(BNode root,
                     ArrayList<Integer> level,
                     int curtLevel,
                     int maxLevel) {
        if (root == null || curtLevel > maxLevel) 
            return;
        
        if (curtLevel == maxLevel) {
            level.add(root.val);
            return;
        }
        
        dfs(root.left, level, curtLevel + 1, maxLevel);
        dfs(root.right, level, curtLevel + 1, maxLevel);
    }
}

// version 3: two queues based BFS
class LevelOrderTraversalSolution3 {
	public ArrayList<ArrayList<Integer>> levelOrder(BNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) 
            return result;
        
        ArrayList<BNode> Q1 = new ArrayList<BNode>();
        ArrayList<BNode> Q2 = new ArrayList<BNode>();
        Q1.add(root);
        
        while (Q1.size() != 0) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            Q2.clear();
            for (int i = 0; i < Q1.size(); i++) {
                BNode node = Q1.get(i);
                level.add(node.val);
                if (node.left != null) {
                    Q2.add(node.left);
                }
                if (node.right != null) {
                    Q2.add(node.right);
                }
            }
            
            // swap q1 and q2
            ArrayList<BNode> temp = Q1;
            Q1 = Q2;
            Q2 = temp;
            
            // add to result
            result.add(level);
        }
        
        return result;
    }
}

// version 4: BFS with dummy node
class LevelOrderTraversalSolution4 {
	public ArrayList<ArrayList<Integer>> levelOrder(BNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) 
            return result;
        
        Queue<BNode> Q = new LinkedList<BNode>();
        Q.offer(root);
        Q.offer(null); // dummy node
        ArrayList<Integer> level = new ArrayList<Integer>();
        
        while (!Q.isEmpty()) {
            BNode node = Q.poll();
            if (node == null) {
                if (level.size() == 0) 
                    break;
                result.add(level);
                level = new ArrayList<Integer>();
                Q.offer(null); // add a new dummy node
                continue;
            }
            
            level.add(node.val);
            if (node.left != null) 
                Q.offer(node.left);
            if (node.right != null) 
                Q.offer(node.right);
        }
        
        return result;
    }
}