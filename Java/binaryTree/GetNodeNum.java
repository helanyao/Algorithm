package binaryTree;

import java.util.Stack;

import tree.binaryTree.BNode;
import tree.binaryTree.BTree;

public class GetNodeNum {
	public static void main(String[] args) {
		String[] init = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};
		BTree bt = new BTree(init, 1);
		System.out.println(getNodeNum(bt.getRoot()));
		System.out.println(getNodeNumN(bt.getRoot()));
	}
	
	public static int getNodeNum(BNode root) {
		if (root == null)
			return 0;
		else 
			return 1 + getNodeNum(root.left) + getNodeNum(root.right);	
	}
	
	public static int getNodeNumN(BNode root) {
		int num = 0;
		
		if (root == null) 
			return num;
		
		Stack<BNode> st = new Stack<BNode>();
		BNode cur = root;
		
		while (cur != null || !st.isEmpty()) {
			while (cur != null) {
				num++;
				if (cur.right != null) 
					st.push(cur.right);
				cur = cur.left;
			}
			
			if (!st.isEmpty()) 
				cur = st.pop();
		}
		
		return num;
	}
}
