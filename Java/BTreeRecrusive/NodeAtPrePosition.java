package BTreeRecrusive;

import java.util.Stack;
import BinaryTree.BNode;

//find the node of position k in pre-roder
public class NodeAtPrePosition {
	
	public BNode findNodeAtPre(BNode root, int k) {
			if(root == null || k < 1) {
				throw new IllegalArgumentException();
			}
			
			int n = 0;
			BNode p = root;
			Stack<BNode> st = new Stack<BNode>();
			
			while(!st.isEmpty() || p != null) {
				while(n != k && p != null) {
					n++;
					if( n == k ) {
						break;
					}
					if(p.getRight() != null) {
						st.push(p.getRight());
					}
					p = p.getLeft();
				}
				
				if(n == k) {
					break;
				}
				
				if(!st.isEmpty()) {
					p = st.pop();
				} else {
					p = null;
				}
			}
			
			if(n == k) {
				return p;
			} else {
				throw new IllegalArgumentException("the number " + k + " exceeds the length of pre-order.");
			}
		}
}
