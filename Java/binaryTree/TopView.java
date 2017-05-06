package binaryTree;

import java.util.Stack;
import tree.binaryTree.BNode;

public class TopView {
//	public static void main(String[] args) {
//		String[] init1 = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};	
//		String[] init2 = new String[]{"1"};
//		String[] init3 = new String[]{"1", "[", ",", "3", "[", "6", ",", "]", "]"};
//		printTopViewN(null);
//	}
	
	public void printTopViewN(BNode root) {
		if(root == null) {
			System.out.println("printTopView: empty tree.");
			return;
		}
		
		Stack<BNode> st = new Stack<BNode>();
		BNode n = root.getLeft();
		
		// left
		while(n != null) {
			st.push(n);
			n = n.getLeft();
		}
		
		while(!st.isEmpty()) 
			System.out.print(st.pop().getVal() + " ");
		
		System.out.print(root.getVal() + " ");
		
		// right
		n = root.getRight();
		while(n != null) {
			System.out.print(n.getVal() + " ");
			n = n.getRight();
		}
	}
	
	public void top_view(BNode root) {
	    if(root != null) {
	        top_view(root.getLeft(), true);
	        System.out.print(root.getVal() + " ");
	        top_view(root.getRight(), false);
	    }
	}

	public void top_view(BNode node, boolean goLeft) {
	    if(node != null) {
	        if(goLeft) {
	            top_view(node.getLeft(), goLeft);
	            System.out.print(node.getVal() + " ");
	        } else {
	            System.out.print(node.getVal() + " ");
	            top_view(node.getRight(), goLeft);
	        }
	    } 
	}
}
