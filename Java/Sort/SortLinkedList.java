package Sort;

import singleLinkedList.LinkedList;
import singleLinkedList.ListNode;

public class SortLinkedList {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(3);
		LinkedList L = new LinkedList(n1);
		L.add(0);
		L.add(1);
		
		LinkedList result = OrderLinkedList(L);
		
		result.print();
	}
	
	public static LinkedList OrderLinkedList(LinkedList L){
		ListNode tmpH = null, cur = L.getHead();
		LinkedList tmpL = new LinkedList(tmpH);
		while(cur != null){
			OrderInsertList(tmpL, cur.val);
			cur = cur.getNext();
		}
		
		return tmpL;
	}
	
	public static void OrderInsertList(LinkedList L, int item){
		ListNode newN = new ListNode(item);
		ListNode prev = null, cur = L.getHead();

	    while(cur != null){
	        if(cur.val > item){
	            break;
	        }
	        prev = cur;
	        cur = cur.getNext();
	    }

	    if(prev == null){ //1. empty linked list; 2. inserted into head
	        newN.setNext(L.getHead());
	        L.setHead(newN);
	    }else{
	        prev.setNext(newN);
	        newN.setNext(cur);
	    }
	}
}
