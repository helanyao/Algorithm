package LinkedList;

import singleLinkedList.LinkedList;
import singleLinkedList.ListNode;

/**
 * 
 *  LC24: https://leetcode.com/problems/swap-nodes-in-pairs/
 *  Given a linked list, swap every two adjacent nodes and return its head.
 *  For example,
 *  Given 1->2->3->4, return the list as 2->1->4->3.
 *
 */
 
public class SwapNodeInPair {
  public static void main(String[] args) {
      ListNode head = new ListNode(1);
      LinkedList l = new LinkedList(head);
      for (int i = 2; i < 8; i++) {
          l.add(i);
      }
      l.printAll();
      swapPairs(l.getHead());
      l.printAll();
  }
  
    public static ListNode swapPairs(ListNode head) {
        ListNode prev = null, cur = head;
        
        while (cur != null) {
            if(cur.next == null) {
                break;
            }
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = cur;
            if (prev == null) {
                head = next;  
            } else {
                prev.next = next;
            }
            prev = cur;
            cur = cur.next;
            
        }
        
        return head;
    }

}