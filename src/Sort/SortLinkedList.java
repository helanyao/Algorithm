package Sort;

import SingleLinkedList.LinkedList;
import SingleLinkedList.Node;

public class SortLinkedList {
	public static void main(String[] args) {
		Node n1 = new Node(3);
		LinkedList L = new LinkedList(n1);
		L.add(0);
		L.add(1);
		
		LinkedList result = OrderLinkedList(L);
		
		result.printAll();
	}
	
	public static LinkedList OrderLinkedList(LinkedList L){
		Node tmpH = null, cur = L.getHead();
		LinkedList tmpL = new LinkedList(tmpH);
		while(cur != null){
			OrderInsertList(tmpL, cur.getValue());
			cur = cur.getNext();
		}
		
		return tmpL;
	}
	
	public static void OrderInsertList(LinkedList L, int item){
	    Node newN = new Node(item);
	    Node prev = null, cur = L.getHead();

	    while(cur != null){
	        if(cur.getValue() > item){
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
