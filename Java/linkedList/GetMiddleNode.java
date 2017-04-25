package linkedList;

import singleLinkedList.ListNode;

public class GetMiddleNode {
	public ListNode findMiddle(ListNode head) {
		if(head == null) {
			return null;
		}
		
		ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
	}
}
