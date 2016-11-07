package BinaryTree;

import java.util.Iterator;
import java.util.Stack;

import BinaryTree.BNode;
import BinaryTree.BTree;

public class GetPathFromLeafToRoot {
	public static void main(String [] args) {
		String[] init = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};		
		BTree bt = new BTree(init);
		getPathFromLeafToRoot(bt.getRoot());
	}
	
	public static void getPathFromLeafToRoot(BNode root) {
		Stack<BNode> s = new Stack<BNode>();
		BNode p = root;
		
		while(!s.isEmpty() || p != null) {
			while(p != null) {
				s.push(p);
				if(p.getLeft() != null) {
					p = p.getLeft();
				}else {
					p = p.getRight();
				}//if
			}//while
			
			p = s.pop();
			if(p.getLeft() == null && p.getRight() == null) {
				System.out.print("\nPath of " + p.getVal() + ": ");
				Iterator<BNode> it = s.iterator();
				while(it.hasNext()) {
					System.out.print(it.next().toString());
				}
			}		
			
			while(!s.isEmpty() && s.peek().getRight() == p) {
				p = s.pop();
				if(p.getLeft() == null && p.getRight() == null) {
					System.out.print("\nPath of " + p.getVal() + ": ");
					Iterator<BNode> it = s.iterator();
					while(it.hasNext()) {
						System.out.print(it.next().toString());
					}
				}
			}
			
			if(!s.isEmpty()) {
				p = s.peek().getRight();
			}else{
				p = null;
			}
		}
	}
}
