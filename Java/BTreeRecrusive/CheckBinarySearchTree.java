package BTreeRecrusive;

import java.util.ArrayList;
import BinaryTree.BNode;

public class CheckBinarySearchTree {
	BNode root;
	
	public static void main(String args[]){
		CheckBinarySearchTree tree = new CheckBinarySearchTree();
        tree.root = new BNode(4);
        tree.root.left = new BNode(2);
        tree.root.right = new BNode(5);
        tree.root.left.left = new BNode(1);
        tree.root.left.right = new BNode(3);
 
        if (tree.isBST2(tree.root)){
        	System.out.println("IS BST");
        } else {
        	System.out.println("Not a BST");
        }       
    }
	
	
	boolean isBST1(BNode cur)  {
		return isBSTUtil(cur, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	 
	    
	boolean isBSTUtil(BNode node, int min, int max){
	    if (node == null) {
	    	return true;
	    }
	 
	    if (node.val < min || node.val > max){
	    	return false;
	    }
		
	    return (isBSTUtil(node.left, min, node.val - 1) && isBSTUtil(node.right, node.val + 1, max));
	}
	
	
	boolean isBST2(BNode cur) {
		ArrayList<BNode> al = new ArrayList<BNode>();
		nodesToList(cur, al);
		for(int i = 1; i < al.size(); i++) {
			if(al.get(i).val <= al.get(i - 1).val) {
				return false;
			}
		}
		return true;
	}
	
	void nodesToList(BNode cur, ArrayList<BNode> al) {
		if(cur != null) {
			nodesToList(cur.left, al);
			al.add(cur);
			nodesToList(cur.right, al);
		}
	}
}
