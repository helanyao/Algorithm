package LinkedList;

import SingleLinkedList.ListNode;

public class DetectCycle {
	// for a given linked list, check whether there exists a cycle
	boolean hasCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
	    while(fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	        if(slow == fast) {
	            return true;
	        }
	    }
	    return false;
	}

	// LC160: https://leetcode.com/problems/intersection-of-two-linked-lists/
	// For example, the following two linked lists:
	// A:          a1 ¡ú a2
	//                    ¨K
    //                     c1 ¡ú c2 ¡ú c3
	//					     ¨J            
	// B:     b1 ¡ú b2 ¡ú b3
	// begin to intersect at node c1.
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        if (headA == null || headB == null) {
	            return null;
	        }
	        
	        // get the tail of list A.
	        ListNode node = headA;
	        while (node.next != null) {
	            node = node.next;
	        }
	        node.next = headB;
	        ListNode result = listCycleII(headA);
	        node.next = null;
	        return result;
	}
	    
	private ListNode listCycleII(ListNode head) {
	        ListNode slow = head, fast = head.next;
	        
	        while (slow != fast) {
	            if (fast == null || fast.next == null) {
	                return null;
	            }
	            
	            slow = slow.next;
	            fast = fast.next.next;
	        }
	        
	        slow = head;
	        fast = fast.next;
	        while (slow != fast) {
	            slow = slow.next;
	            fast = fast.next;
	        }
	        
	        return slow;
	}
}
