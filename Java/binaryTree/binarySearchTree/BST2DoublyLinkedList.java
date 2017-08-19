package binaryTree.binarySearchTree;

import java.util.Stack;
import tree.binaryTree.BNode;
import tree.binaryTree.BTree;
import tree.binaryTree.BTreeHelper;

/**
 * @Description
 * Convert a binary search tree to doubly linked list with in-order traversal.
 * 
 * @Example
 * Given a binary search tree:
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 * return 1<->2<->3<->4<->5
 *
 * @Tag Binary Search Tree, Linked List
 */
public class BST2DoublyLinkedList {
	public static void main(String[] args) {
		String[] init = new String[]{"4", "2", "5", "1", "3"};
		BTree bt = new BTree();
		BTreeHelper h = new BTreeHelper();
		bt.root = h.create(init);
		DoublyListNode list = bst2DoublyList(bt.getRoot());
		while (list != null) {
			System.out.print(list.val + " ");
			list = list.next;
		}		
	}
	
	/**
	 * @author jhzhu@outlook.com
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public static DoublyListNode bst2DoublyList(BNode root) {  
        DoublyListNode head = null, last = null;
        Stack<BNode> st = new Stack<BNode>();
        BNode n = root;
        
        while (n != null || !st.isEmpty()) {
        	while (n != null) {
        		st.push(n);
        		n = n.left;
        	}
        	if (!st.isEmpty())
        		n = st.pop();
        	DoublyListNode dln = new DoublyListNode(n.val);
        	if (head == null) {
        		head = dln;
        	} else {
        		last.next = dln;
            	dln.prev = last;
        	}
        	last = dln;
        	n = n.right; 	
        }
        
        return head;
    }
}

class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}