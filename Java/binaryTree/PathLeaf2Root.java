package binaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import binaryTree.BNode;
import binaryTree.BTree;

public class PathLeaf2Root {
	public static void main(String [] args) {
		String[] init = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};		
		BTree bt = new BTree(init, 1);
		ArrayList<List<Integer>> results = getPathFromLeafToRoot1(bt.getRoot());
		Iterator<List<Integer>> it = results.iterator();
		System.out.println();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	
	// version 1: non-recursion
	public static ArrayList<List<Integer>> getPathFromLeafToRoot1(BNode root) {
		ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
		Stack<BNode> s = new Stack<BNode>();
		BNode p = root;
		
		while(!s.isEmpty() || p != null) {
			while(p != null) {
				s.push(p);
				if(p.getLeft() != null) 
					p = p.getLeft();
				else 
					p = p.getRight();
			}//while
			
			p = s.pop();
			if(p.isLeaf()) {
				System.out.print("\nPath of " + p.getVal() + ": ");
				List<Integer> path = new ArrayList<Integer>();
				Iterator<BNode> it = s.iterator();
				while(it.hasNext()) {
					BNode n = it.next();
					System.out.print(n.val + " ");
					path.add(n.val);
				}
				path.add(p.val);
				results.add(path);
			}		
			
			while(!s.isEmpty() && s.peek().getRight() == p) 
				p = s.pop();
			
			if(!s.isEmpty()) 
				p = s.peek().getRight();
			else
				p = null;
		}
		
		return results;
	}
	
	// version 2: Divide and Conquer
	public List<String> getPathFromLeafToRoot2(BNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null)
            return paths;
        
        List<String> leftPaths = getPathFromLeafToRoot2(root.left);
        List<String> rightPaths = getPathFromLeafToRoot2(root.right);
        for (String path : leftPaths)
            paths.add(root.val + "->" + path);
        for (String path : rightPaths)
            paths.add(root.val + "->" + path);
        
        // root is a leaf
        if (paths.size() == 0)
            paths.add("" + root.val);
        
        return paths;
    }
	
	// version 3: traverse
	public List<String> getPathFromLeafToRoot3(BNode root) {
        List<String> result = new ArrayList<String>();
        if (root == null)
            return result;
        helper(root, String.valueOf(root.val), result);
        return result;
    }
    
    private void helper(BNode root, String path, List<String> result) {
        if (root == null)
            return;
        
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        
        if (root.left != null)
            helper(root.left, path + "->" + String.valueOf(root.left.val), result);
        if (root.right != null)
            helper(root.right, path + "->" + String.valueOf(root.right.val), result);
    }
}
