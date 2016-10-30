package LinkedList;

import SingleLinkedList.LinkedList;
import SingleLinkedList.Node;

public class CheckPalindrome {

	public static void main(String[] args) {
		Node n1 = new Node(0);
		LinkedList ll = new LinkedList(n1);
		ll.add(1);
		ll.add(3);
		ll.add(1);
		ll.add(0);
		ll.printAll();
		
	}
	
	// Time: O(n), Space: O(1)
	public boolean isPalindrome(Node head) {
		if(head == null) {
			return true;
		}
		
		Node middle = findMiddle(head);
		middle.next = reverseList(middle.next);
		Node p1 = head, p2 = middle.next;
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
	
	public Node findMiddle(Node head) {
		if(head == null) {
			return null;
		}
		
		Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
	}
	
	public Node reverseList(Node head) {
		Node newH = null, cur = head, next = null;
		
		while(cur != null) {
			next = cur.next;
			cur.next = newH;
			newH = cur;
			cur = next;
		}
		
		return newH;
	}

}
