package LinkedList;

import SingleLinkedList.LinkedList;
import SingleLinkedList.Node;

public class TestOnly {

	public static void main(String[] args) {
		Node n1 = new Node(0);
		LinkedList ll = new LinkedList(n1);
		ll.add(0);
		ll.add(0);
		//ll.add(3);
		ll.printAll();
		ll.head = removeDup(ll.getHead());
		ll.printAll();

	}
	
	public static Node removeDup(Node head) {
		Node dummy = new Node();
		dummy.next = head;
		
		while(head != null) {
			Node n = head.next;
			if(n != null && n.val == head.val) {
				head.next = n.next;
				n.next = null;
			} else { // else is used for case [0, 0, 0]
				head = head.next;
			}	
		}
		
		return dummy.next;
	}

}
