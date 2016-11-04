package BTreeRecrusive;

import BinaryTree.BNode;

// get the number of parent node which meets (1) has left child (2) has right child
public class ParentNodeNum {

	public static void main(String[] args) {
	}
	
	public static int getParentNodeNum(BNode root) {
		if(root == null) {
			return 0;
		} else if(root.getLeft() != null || root.getRight() != null) {
			return 0;
		} else {
			return 1 + getParentNodeNum(root.getLeft()) + getParentNodeNum(root.getRight());
		}
		
	}

}
