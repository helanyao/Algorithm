package BTreeRecrusive;

import java.util.Stack;

/*
Find the sum of all left leaves in a given binary tree.

Example:
    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/

public class SumLeftLeaves {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int sumOfLeftLeaves(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return 0;
        } 
        
        int result = 0, temp;
        Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode p = root;
		boolean right = false;
		while(!s.isEmpty() || p != null) {
			while(p != null) {
			    if((p.left == null && p.right == null) && !right) {
			        result += p.val;
			    }
			    if(right) {
			        right = false;
			    }
				if(p.right != null) {
					s.push(p.right);
				}//if
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

class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
}
