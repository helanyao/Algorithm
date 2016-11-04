package LinkedList;

import SingleLinkedList.LinkedList;
import SingleLinkedList.ListNode;

/*
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example, given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * 
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */

public class RemoveNthFromEnd {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(0);
		LinkedList ll = new LinkedList(n1);
		//ll.add(1);
		//ll.add(3);
		ll.printAll();
		ll.head = removeEnd(ll.getHead(), 1);
		ll.printAll();
	}
	
	public static ListNode removeEnd(ListNode head, int n) {
		if(n <= 0 || head == null) {
			return head;
		}
		
		ListNode pre = null, p = head, q = head;
		
		for(int i = 0; i < n - 1; i++) {
			if(q == null) {
				throw new IllegalArgumentException("n exceeds the length of linked list.");
			}
			q = q.next;
		}
		
		while(q.next != null) {
			pre = p;
			p = p.next;
			q = q.next;
		}
		
		if(pre == null) { // if remove the head node
			pre = head;
			head = head.next;
			pre.next = null;
		} else {
			pre.next = p.next;
			p.next = null;
		}
		
		return head;
	}

}
