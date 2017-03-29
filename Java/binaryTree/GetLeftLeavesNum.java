package binaryTree;

import java.util.Stack;

/*
Get the sum of all left leaves in a given binary tree.

Example:
    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/

public class GetLeftLeavesNum {
	public int sumOfLeftLeaves(BNode root) {
        if(root == null || (root.left == null && root.right == null)) 
            return 0;
        
        int result = 0;
        Stack<BNode> s = new Stack<BNode>();
		BNode p = root;
		boolean right = false;
		while(!s.isEmpty() || p != null) {
			while(p != null) {
			    if((p.left == null && p.right == null) && !right) 
			        result += p.val;
			    if(right) 
			        right = false;
				if(p.right != null) 
					s.push(p.right);
				p = p.left;
			}//while
			if(!s.isEmpty()) {
			    right = true;
				p = s.pop();
			}
		}
		
		return result; 
    }
}