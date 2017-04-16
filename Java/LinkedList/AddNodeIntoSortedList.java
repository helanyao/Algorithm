package LinkedList;

import singleLinkedList.LinkedList;
import singleLinkedList.ListNode;

public class AddNodeIntoSortedList {
	public void insertList(LinkedList HL, int item){
	    ListNode newN = new ListNode(item);
	    ListNode prev = null, cur = HL.head;

	    while(cur != null && cur.val <= item){
	        prev = cur;
	        cur = cur.next;
	    }

	    if(prev == null){  //1. empty linked list; 2. inserted into head
	        newN.next = HL.head;
	        HL.head = newN;
	    }else{
	        prev.next = newN;
	        newN.next = cur;
	    }
	}
}
