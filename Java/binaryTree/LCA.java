package binaryTree;

import java.util.ArrayList;
import java.util.Stack;
import tree.binaryTree.BNode;
import tree.binaryTree.BTree;

/**
 * @Tag LinkedIn, Binary Tree, Facebook
 */
public class LCA {
	public static void main(String[] args) {
		String[] init = new String[]{"1","#","2","#","3","#","4","#","5"};
		BTree bt = new BTree();
		bt.create(init);
		System.out.println(getLCANonR(bt.getRoot(), 3, 8) == null);
	}
	
	/**
	 * @author jhzhu@outlook.com
     * @param root: The root of the binary search tree.
     * @param a and b: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
	public static BNode getLCANonR(BNode root, int a, int b) {
		if(root == null) 
			return null;
		
		Stack<BNode> s = new Stack<BNode>();
		BNode p = root;
		BNode[] aPath = null;
		BNode[] bPath = null;
		int i = 0;
		
		while(!s.isEmpty() || p != null) {
			while(p != null) {
				s.push(p);
				if(p.getVal() == a) {
					int length = s.size();
					aPath = new BNode[length];
					for(i = 0; i < aPath.length; i++) 
						aPath[i] = s.get(i);
				}
				
				if(p.getVal() == b) {
					int length = s.size();
					bPath = new BNode[length];
					for(i = 0; i < bPath.length; i++) 
						bPath[i] = s.get(i);
				}
				
				if(aPath != null && bPath != null) 
					break;
				if(p.getLeft() != null) 
					p = p.getLeft();
				else 
					p = p.getRight();
			}//while
			
			if(aPath != null && bPath != null) 
				break;
			
			p = s.pop();
			
			while(!s.isEmpty() && s.peek().getRight() == p) 
				p = s.pop();
			
			if(!s.isEmpty()) 
				p = s.peek().getRight();
			else
				p = null;
		}// out while
		
		if (aPath == null || bPath == null)
			return null;
		
		for(i = 0; i < aPath.length && i < bPath.length; i++) 
			if(aPath[i].getVal() != bPath[i].getVal()) 
				break;
		
		return aPath[i - 1];	
	}
	
	// the key idea to visit all nodes in depth-first
	// for each time of visiting this node, including visiting it from its parent node
	// or from its children nodes, just push it into array
	// for the inputting data a and b, get its last position in this array
	// Assume the sub-array starts from s and ends in e,
	// the node with the smallest depth in [s, e] is the LCA node.
	/**
	 * @author jhzhu@outlook.com
     * @param root: The root of the binary search tree.
     * @param a and b: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
	public static BNode getLCAByRMQ(BNode root, int a, int b) {
		if(root == null) 
			return null;
		
		class DNode {
			public int depth;
			public BNode node;
			
			public DNode(BNode n, int d) {
				depth = d;
				node = n;
			}
		}
		
		ArrayList<DNode> al = new ArrayList<DNode>();
		Stack<BNode> s = new Stack<BNode>();
		BNode p = root;
		int depth = 0;
		
		while(!s.isEmpty() || p != null) {
			while(p != null) {
				s.push(p);
				depth++;
				al.add(new DNode(p, depth));
				if(p.getLeft() != null) 
					p = p.getLeft();
				else 
					p = p.getRight();
			}// inner while
			
			p = s.pop();
			
			while(!s.isEmpty() && s.peek().getRight() == p) {
				p = s.pop();
				al.add(new DNode(p, --depth));
			}
			
			if(!s.isEmpty()) {
				p = s.peek().getRight();
				al.add(new DNode(s.peek(), --depth));
			}else{
				p = null;
			}
		}// out while
		
//		Iterator<DNode> it = al.iterator();
//		while(it.hasNext()) {
//			it.next().display();;
//		}
		int aPos = 0, bPos = 0, start = 0, end = 0, maxDepth = 0;
		BNode result = null;
		for(int i = 0; i < al.size(); i++) {
			BNode n = al.get(i).node;
			if(n.getVal() == a) 
				aPos = i;
			if(n.getVal() == b) 
				bPos = i;
		}
		if(aPos < bPos) {
			start = aPos;
			end = bPos;
		} else {
			start = bPos;
			end = aPos;
		}
		
		maxDepth = al.get(start).depth;
		for(int i = start; i < end; i++) {
			if(al.get(i).depth < maxDepth) {
				result = al.get(i).node;
				maxDepth = al.get(i).depth;
			}
		}
		
		return result;
	}
	
	/**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public BNode getLCA(BNode root, BNode A, BNode B) {
        ResultType rt = helper(root, A, B);
        if (rt.a_exist && rt.b_exist)
            return rt.node;
        else
            return null;
    }

    private ResultType helper(BNode root, BNode A, BNode B) {
        if (root == null)
            return new ResultType(false, false, null);
            
        ResultType left_rt = helper(root.left, A, B);
        ResultType right_rt = helper(root.right, A, B);
        
        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;
        
        if (root == A || root == B)
            return new ResultType(a_exist, b_exist, root);

        if (left_rt.node != null && right_rt.node != null)
            return new ResultType(a_exist, b_exist, root);
        if (left_rt.node != null)
            return new ResultType(a_exist, b_exist, left_rt.node);
        if (right_rt.node != null)
            return new ResultType(a_exist, b_exist, right_rt.node);

        return new ResultType(a_exist, b_exist, null);
    }
    
    class ResultType {
        public boolean a_exist, b_exist;
        public BNode node;
        
        ResultType(boolean a, boolean b, BNode n) {
            a_exist = a;
            b_exist = b;
            node = n;
        }
    }
}