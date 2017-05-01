package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.binaryTree.BNode;
import tree.binaryTree.BTree;

public class RightView {
	public static void main(String[] args) {
		String[] init1 = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};	
		BTree bt1 = new BTree(init1, 1);
		System.out.println(viewBFS(bt1.getRoot()));
	}
	
	public static String viewBFS(BNode root) {
		if(root == null) {
			System.out.println("RightView::viewBFS, empty input tree.");
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
			throw new IllegalArgumentException("helperDFS: levle value is illegal.");
		
		if(level == l.size()) 
			l.add(root.getVal());
		
		helperDFS(root.getLeft(), l, level + 1);
		helperDFS(root.getRight(), l, level + 1);
	}
}
