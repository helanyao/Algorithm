package binaryTree;

import binaryTree.BNode;

public class GetMaxAndMinVal {
	int getMaxVal(BNode root) {
		if(root == null) {
			throw new IllegalArgumentException();
		}
		int max = root.getVal();
		if(root.getLeft() != null) {
			max = Math.max(max, getMaxVal(root.getLeft()));
		}
		if(root.getRight() != null) {
			max = Math.max(max, getMaxVal(root.getRight()));
		}
		
		return max;
	}
	
	int getMinVal(BNode root) {
		if(root == null) {
			throw new IllegalArgumentException();
		}
		int min = root.getVal();
		if(root.getLeft() != null) {
			min = Math.min(min, getMaxVal(root.getLeft()));
		}
		if(root.getRight() != null) {
			min = Math.min(min, getMaxVal(root.getRight()));
		}
		
		return min;
	}
}
