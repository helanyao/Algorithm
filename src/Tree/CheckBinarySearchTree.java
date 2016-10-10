package Tree;

import java.util.ArrayList;

public class CheckBinarySearchTree {
	Node root;
	
	public static void main(String args[]){
		CheckBinarySearchTree tree = new CheckBinarySearchTree();
        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);
 
        if (tree.isBST2(tree.root)){
        	System.out.println("IS BST");
        } else {
        	System.out.println("Not a BST");
        }       
    }
	
	
	boolean isBST1(Node cur)  {
		return isBSTUtil(cur, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	 
	    
	boolean isBSTUtil(Node node, int min, int max){
	    if (node == null) {
	    	return true;
	    }
	 
	    if (node.data < min || node.data > max){
	    	return false;
	    }
		
	    return (isBSTUtil(node.left, min, node.data-1) && isBSTUtil(node.right, node.data+1, max));
	}
	
	
	boolean isBST2(Node cur) {
		ArrayList<Node> al = new ArrayList<Node>();
		nodesToList(cur, al);
		for(int i = 1; i < al.size(); i++) {
			if(al.get(i).data <= al.get(i - 1).data) {
				return false;
			}
		}
		return true;
	}
	
	void nodesToList(Node cur, ArrayList<Node> al) {
		if(cur != null) {
			nodesToList(cur.left, al);
			al.add(cur);
			nodesToList(cur.right, al);
		}
	}
}

class Node
{
    public int data;
    public Node left, right;
 
    public Node(int item)
    {
        data = item;
        left = right = null;
    }
}