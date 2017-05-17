package linkedList;

import singleLinkedList.ListNode;


/**
 * @Description
 * Given a linked list, return the node where the cycle begins. 
 * If there is no cycle, return null.
 * 
 * @Example
 * Given -21->10->4->5, tail connects to node index 1，return 10
 *
 * @Tag Linked List, Two Pointers, Greedy
 */
public class GetCycleHead {
	public ListNode detectCycle(ListNode head) {
        if (head == null || head.next==null)
            return null;

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return null;
            
            fast = fast.next.next;
            slow = slow.next;
        } 
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        
        return head;
    }
}
