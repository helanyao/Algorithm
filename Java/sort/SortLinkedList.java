package sort;

import singleLinkedList.ListNode;

public class SortLinkedList {
	public ListNode sort(ListNode head) {
		if (head == null)
			return head;
		
		ListNode newH = null, oldH = head;
		while (oldH != null) {
			ListNode n = oldH.next;
			newH = insert(newH, oldH);
			oldH = n;
		}
		
		return newH;
	}
	
	private ListNode insert(ListNode head, ListNode n) {
		if (n == null)
			return null;
		
		if (head == null) {
			head = n;
			// key point: must take care of next field for each case
			n.next = null; 
			return head;
		}
		
		ListNode pre = null, cur = head;
		while (cur != null && cur.val < n.val) {
			pre = cur;
			cur = cur.next;
		}
		
		if (pre == null) {
			n.next = head;
			head = n;
		} else if (cur == null) {
			n.next = null;
			pre.next = n;
		} else {
			n.next = cur;
			pre.next = n;
		}
		
		return head;
	}
}
