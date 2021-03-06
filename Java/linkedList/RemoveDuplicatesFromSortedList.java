package linkedList;

import singleLinkedList.LinkedList;
import singleLinkedList.ListNode;

public class RemoveDuplicatesFromSortedList {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(0);
		LinkedList ll = new LinkedList(n1);
		ll.add(0);
		ll.add(0);
		//ll.add(3);
		ll.print();
		ll.head = deleteDuplicates(ll.getHead());
		ll.print();
	}
	
    public static ListNode deleteDuplicates(ListNode head) {
    	ListNode dummy = new ListNode();
		dummy.next = head;
		
		while(head != null) {
			ListNode n = head.next;
			if(n != null && n.val == head.val) {
				head.next = n.next;
				n.next = null;
			} else {
			    head = head.next;
			}
		}
		
		return dummy.next;
    }

}
