package binaryTree;

import tree.binaryTree.BNode;

// swap all left child nodes and right ones
public class SwapChild {
	public BNode swap(BNode root) {
		if(root == null) 
			return null;
		
		swap(root.getLeft());
		swap(root.getRight());
		BNode n = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(n);
		
		return root;
	}
}
