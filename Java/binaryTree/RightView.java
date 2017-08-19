package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import tree.binaryTree.BNode;

public class RightView {
	public static String viewBFS(BNode root) {
		if(root == null) {
			System.out.println("Empty input tree.");
			return null;
		}
		
		Queue<BNode> qu = new LinkedList<BNode>();
		StringBuilder st = new StringBuilder();
		BNode n = root;
		qu.add(n);
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			while(size > 0) {
				n = qu.poll();
				if(n.hasLeft()) 
					qu.add(n.getLeft());
				if(n.hasRight()) 
					qu.add(n.getRight());
				if(size == 1) 
					st.append(n.getVal());
				size--;
			}
		}
		
		return st.toString();
	}
	
	public static List<Integer> viewDFS(BNode root) {
		List<Integer> l = new ArrayList<Integer>();
		helperDFS(root, l, 0);
		return l;
	}
	
	public static void helperDFS(BNode root, List<Integer> l, int level) {
		if(root == null) 
			return;
		
		if(level < 0) 
			throw new IllegalArgumentException("Levle value is illegal.");
		
		if(level == l.size()) 
			l.add(root.getVal());
		
		helperDFS(root.getLeft(), l, level + 1);
		helperDFS(root.getRight(), l, level + 1);
	}
}
