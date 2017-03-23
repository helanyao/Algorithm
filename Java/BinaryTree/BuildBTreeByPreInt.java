package BinaryTree;

import binaryTree.BNode;

public class BuildBTreeByPreInt {
	BNode buildBTreePreIn(String[] pre, int preSta, int preEnd, String[] in, int inSta, int inEnd) {
		if ( pre == null || in == null || preSta > preEnd || inSta > inEnd) {
			return null;
		}
		
		int i = inSta;
		String s = pre[preSta];
		BNode bn = new BNode(Integer.parseInt(s));
		
		for (; i <= inEnd; i++ ) {
			if (in[i].equals(s)) {
				break;
			}
		}
		
		BNode left = buildBTreePreIn(pre, preSta + 1, preSta + (i - inSta), in, inSta, i - 1);
		bn.setLeft(left);
		BNode right = buildBTreePreIn(pre, preSta + (i - inSta) + 1, preEnd, in, i + 1, inEnd);
		bn.setRight(right);
		
		return bn;
	}
}
