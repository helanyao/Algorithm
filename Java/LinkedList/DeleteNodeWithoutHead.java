package LinkedList;

import singleLinkedList.LinkedList;
import singleLinkedList.ListNode;

/*
 * Write a function to delete a node (except the tail) in a singly linked list, 
 * given only access to that node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 
 * and given the third node with value 3, 
 * the linked list should become 1 -> 2 -> 4.
 * 
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */

public class DeleteNodeWithoutHead {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(0);
		LinkedList ll = new LinkedList(n1);
		ll.add(1);
		ll.add(2);
		//ll.add(3);
		ll.print();
		ListNode n = ll.head.next;
		deleteNode(n);
		ll.print();
	}
	
	// test case: [1, 2, 3] remove 2; [1, 2] remove 1;
    public static void deleteNode(ListNode node) {
    	ListNode pre = null, p = node, q = node.next;
        while(q != null) {
            p.val = q.val;
            pre = p;
            p = p.next;
            q = q.next;
        }
        pre.next = null;
    }
}
