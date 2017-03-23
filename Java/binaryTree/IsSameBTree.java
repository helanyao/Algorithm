package binaryTree;

import java.util.Stack;

import binaryTree.BNode;
import binaryTree.BTree;

/**
 *  check whether BTree B is same with BTree A
 */
public class IsSameBTree {
	public static void main(String[] args) {
		String[] init1 = new String[]{"1", "[", "2", "[", "5", ",", "]", "]"};	
		String[] init2 = new String[]{"1", "[", "2", "[", "4", ",", "]", "]"};	
		BTree bt1 = new BTree(init1, 1);
		BTree bt2 = new BTree(init2, 1);
		System.out.println(isSame(bt1.getRoot(), bt2.getRoot()));
		System.out.println(isSameN(bt1.getRoot(), bt2.getRoot()));
	}
	
	public static boolean isSame(BNode root1, BNode root2) {
		if (root1 == null && root2 == null) 
			return true;
		else if (root1 == null || root2 == null || root1.val != root2.val) 
			return false;
		
		return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
	}
	
	public static boolean isSameN(BNode root1, BNode root2) {
		if (root1 == null && root2 == null) 
			return true;
		else if (root1 == null || root2 == null) 
			return false;
		
		Stack<BNode> st1 = new Stack<BNode>();
		Stack<BNode> st2 = new Stack<BNode>();
		BNode cur1 = root1, cur2 = root2;
		
		while ((cur1 != null || !st1.isEmpty()) && (cur2 != null || !st2.isEmpty())) {
			while (cur1 != null && cur2 != null) {
				if (cur1.val != cur2.val) 
					return false;
				if (cur1.right != null) 
					st1.push(cur1.right);
				if (cur2.right != null) 
					st2.push(cur2.right);
				if (st1.size() != st2.size()) 
					return false;
				cur1 = cur1.left;
				cur2 = cur2.left;
			}
			
			if (cur1 != null || cur2 != null) 
				return false;
			
			if (!st1.isEmpty() && !st2.isEmpty()) {
				cur1 = st1.pop();
				cur2 = st2.pop();
			}
		}
		
		return true;
	}
}
