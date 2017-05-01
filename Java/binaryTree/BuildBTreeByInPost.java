package binaryTree;

import tree.binaryTree.BNode;

public class BuildBTreeByInPost {
	BNode buildBTreeInPost(String[] in, int inSta, int inEnd, String[] post, int postSta, int postEnd) {
		if ( in == null || inSta > inEnd || post == null || postSta > postEnd ) {
			return null;
		}
		
		int i = inSta;
		String s = post[postEnd];
		BNode bn = new BNode(Integer.parseInt(s));
		
		for ( ; i <= inEnd; i++ ) {
			if( in[i].equals(s)) {
				break;
			}
		}
		
		BNode left = buildBTreeInPost(in, inSta, i - 1, post, postSta, postSta + i - (inSta + 1));
		bn.setLeft(left);
		BNode right = buildBTreeInPost(in, i + 1, inEnd, post, postSta + i - inSta, postEnd - 1);
		bn.setRight(right);
		
		return bn;
	}
}
