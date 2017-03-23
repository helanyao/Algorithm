package binaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import binaryTree.BNode;
import binaryTree.BTree;

public class GetPathFromLeafToRoot {
	public static void main(String [] args) {
		String[] init = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};		
		BTree bt = new BTree(init, 1);
		ArrayList<List<Integer>> results = getPathFromLeafToRoot(bt.getRoot());
		Iterator<List<Integer>> it = results.iterator();
		System.out.println();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	
	public static ArrayList<List<Integer>> getPathFromLeafToRoot(BNode root) {
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
}
