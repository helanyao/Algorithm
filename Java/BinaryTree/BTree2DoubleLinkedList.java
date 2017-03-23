package BinaryTree;

import java.util.Stack;

import binaryTree.BNode;
import binaryTree.BTree;

/**
 *  For a given Binary Search Tree,
 *       4
 *     /   \
 *    2      6
 *   / \    / \
 *  1   3  5   7
 * 
 *  change it into double linked list 
 *  1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6 <-> 7
 */
public class BTree2DoubleLinkedList {

	public static void main(String[] args) {
		String[] init = new String[]{"4", "[", "2", "[", "1", ",", "3", "]", ",", "6", "[", "5", ",", "7", "]", "]"};	
		BTree bt = new BTree(init);
		BNode head;
//		head = btree2listN(bt.getRoot());
//		for (int i = 0; i < init.length; i++) {
//			System.out.print(head.left.val + " " + head.right.val + "\n");
//			head = head.right;
//		}
//		System.out.println();
		btree2list(bt.getRoot());
		System.out.println();
	}
	
    private static BNode previous;  
    public static void btree2list(BNode root){  
        if (root != null){  
        	btree2list(root.getLeft());  
            if ( previous != null ){  
                previous.setRight(root);  
                root.setLeft(previous);  
            }  
            previous = root;  
            btree2list(root.getRight());  
        }  
    }  
	
	private static void btree2listHelper(BNode cur, BNode pre, BNode head) {
		if (cur == null) {
			return;
		}
		btree2listHelper(cur.left, pre, head);
		if (pre == null) {
			head = cur;
		} else {
			pre.right = cur;
			cur.left = pre;
		}
		pre = cur;
		btree2listHelper(cur.right, pre, head);
	}
	
	public static BNode btree2listN(BNode root) {
		BNode head = new BNode(), pre = head;
		Stack<BNode> st = new Stack<BNode>();
		BNode cur = root;
		while (cur != null || !st.isEmpty()) {
			while (cur != null) {
				st.push(cur);
				cur = cur.left;
			}
			
			if (!st.isEmpty()) {
				cur = st.pop();
				pre.right = cur;
				cur.left = pre;
				pre = cur;
				cur = cur.right;
			}
		}
		
		// since it is double linked list,
		// don't forget to deal with the first and last node.
		if (head.right != null) { // in case, it is empty
			head.right.left = pre;
			pre.right = head.right;
		}
		return pre.right;
	}

}
