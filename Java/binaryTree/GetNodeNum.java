package binaryTree;

import java.util.Stack;
import tree.binaryTree.BNode;

public class GetNodeNum {
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
