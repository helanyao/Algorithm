package BTreeRecrusive;

import BinaryTree.BNode;
import BinaryTree.BTree;
import BinaryTree.BTreeTraversal;

// swap all left child nodes and right ones
public class SwapChild {
	public static void main(String[] args) {
		String[] init1 = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};	
		BTree bt1 = new BTree(init1);
		BNode root = swap(bt1.getRoot());
		BTreeTraversal tra = new BTreeTraversal();
		tra.LevelOrderN(root);
	}
	
	public static BNode swap(BNode root) {
		if(root == null) {
			return null;
		}
		swap(root.getLeft());
		swap(root.getRight());
		BNode n = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(n);
		
		return root;
	}
}
