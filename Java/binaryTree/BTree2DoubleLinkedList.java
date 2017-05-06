package binaryTree;

import java.util.Stack;
import tree.binaryTree.BNode;


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
