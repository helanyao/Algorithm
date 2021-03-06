package binaryTree;

import java.util.Stack;
import tree.binaryTree.BNode;

// Link all leaf nodes from left to right 
// by using their right child.
public class LinkLeafNode {
	class InnerLinkedList {
		public BNode head = null;
		public BNode tail = null;
	}
	
	public static BNode linkNodeN(BNode root) {
		Stack<BNode> st = new Stack<BNode>();
		BNode start = null, cur = null, n = root;
		
		while(n != null || !st.isEmpty()) {
			while(n != null) {
				if(n.isLeaf()) {
					if(start == null) 
						start = n;
					else 
						cur.setRight(n);
					cur = n;
				}
				if(n.getRight() != null) 
					st.push(n.getRight());
				n = n.getLeft();
			}// inner while
			
			if(!st.isEmpty()) 
				n = st.pop();
		} // while
		
		return start;
	}
	
	public static void linkNode(BNode root, InnerLinkedList ll) {
		if(root != null) {
			if(root.isLeaf()) {
				if(ll.head == null) 
					ll.head = root;
				else 
					ll.tail.setRight(root);
				ll.tail = root;
			}
			
			if(root.getLeft() != null) 
				linkNode(root.getLeft(), ll);
			
			if(root.getRight() != null) 
				linkNode(root.getRight(), ll);
		}
	}
	
	public static void printLink(BNode start) {
		while(start != null) {
			System.out.println(start.getVal());
			start = start.getRight();
		}
	}
}
