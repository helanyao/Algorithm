package LinkedList;

import SingleLinkedList.Node;

public class ReverseList {
	public Node reverseListN(Node head) {
        Node dummy = new Node();
        dummy.next = null;
        
        while(head != null) {
            Node next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        
        return dummy.next;
	}
	
	public Node reverseList(Node head, Node pre) {
		if(head == null) {
			return pre;
		}
		
		Node next = head.getNext();
		head.setNext(pre);
		
		return reverseList(next, head);
	}
	
	public void reversePrint(Node head) {
		  if(head == null){
		      return;
		  } else{
		    reversePrint(head.getNext());
		    System.out.println(head.getValue());
		  }
	}
}
