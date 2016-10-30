package LinkedList;

public class DetectCycle {
	boolean hasCycle(Node head) {
	    Node slow = head;
	    Node fast = head;
	    while(fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	        if(slow == fast) {
	            return true;
	        }
	    }
	    return false;
	}
}

class Node {
	public Node next;
	public int value;
}
