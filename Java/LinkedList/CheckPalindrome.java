package LinkedList;

import singleLinkedList.LinkedList;
import singleLinkedList.ListNode;

public class CheckPalindrome {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(0);
		LinkedList ll = new LinkedList(n1);
		ll.add(1);
		ll.add(3);
		ll.add(1);
		ll.add(0);
		ll.print();
	}
	
	// Time: O(n), Space: O(1)
	public boolean isPalindrome(ListNode head) {
		if(head == null) 
			return true;
		
		ListNode middle = findMiddle(head);
		middle.next = reverseList(middle.next);
		ListNode p1 = head, p2 = middle.next;
		while(p1 != null && p2 != null) {
			if(p1.val != p2.val) {
				return false;
			} else {
				p1 = p1.next;
				p2 = p2.next;
			}
		}
		
		return true;
	}
	
	public ListNode findMiddle(ListNode head) {
		if(head == null) 
			return null;
		
		ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
	}
	
	public ListNode reverseList(ListNode head) {
		ListNode newH = null, cur = head, next = null;
		
		while(cur != null) {
			next = cur.next;
			cur.next = newH;
			newH = cur;
			cur = next;
		}
		
		return newH;
	}
}
