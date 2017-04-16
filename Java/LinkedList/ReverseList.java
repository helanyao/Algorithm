package LinkedList;

import singleLinkedList.ListNode;

public class ReverseList {
	public ListNode reverseListN(ListNode head) {
		ListNode dummy = new ListNode();
        dummy.next = null;
        
        while(head != null) {
        	ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        
        return dummy.next;
	}
	
	public ListNode reverseList(ListNode head, ListNode pre) {
		if(head == null) {
			return pre;
		}
		
		ListNode next = head.getNext();
		head.setNext(pre);
		
		return reverseList(next, head);
	}
	
	public void reversePrint(ListNode head) {
		  if(head == null){
		      return;
		  } else{
		    reversePrint(head.getNext());
		    System.out.println(head.val);
		  }
	}
}
