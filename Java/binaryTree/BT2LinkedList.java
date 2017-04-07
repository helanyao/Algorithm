package binaryTree;

import java.util.Stack;

/**
 * @Description
 * Flatten a binary tree to a fake "linked list" in pre-order traversal.
 * Here we use the right pointer in node as the next pointer in ListNode.
 * Don't forget to mark the left child of each node to null. 
 * 
 * @Example
 *               1
 *                \
 *      1          2
 *     / \          \
 *    2   5    =>    3
 *   / \   \          \
 *  3   4   6          4
 *                      \
 *                       5
 *                        \
 *                         6
 * 
 * @Tag Binary Tree
 */
public class BT2LinkedList {
	public static void main(String[] args) {
		BTreeHelper helper = new BTreeHelper();
		String[] init = new String[]{"1", "2", "5", "3", "4", "#", "6"};
		BTree bt = new BTree(init, 2);
		flatten(bt.getRoot());
		helper.print(bt.getRoot());
	}
	
	/**
	 * @author jhzhu@outlook.com
     * @param root: the root of the binary tree
     * @return: nothing
     */
    public static void flatten(BNode root) {
        Stack<BNode> st = new Stack<BNode>();
        BNode cur = root, pre = null;
        
        while (!st.isEmpty() || cur != null) {
        	while (cur != null) {
        		if (cur.right != null) {
        			st.push(cur.right);
        			cur.right = null;
        		}
        		if (pre != null) {
            		pre.right = cur;
            		pre.left = null;
            	}
        		pre = cur;
        		cur = cur.left;
        	}
        	
        	if (!st.isEmpty())
        		cur = st.pop();
        	pre.right = cur;
        }
    }
    
 // version 2: Divide & Conquer
    public void flattenDC(BNode root) {
        helper(root);
    }
    
    private BNode helper(BNode root) {
        if (root == null) 
            return null;
        
        BNode leftLast = helper(root.left);
        BNode rightLast = helper(root.right);
        
        // connect leftLast to root.right
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if (rightLast != null) 
            return rightLast;
        if (leftLast != null) 
            return leftLast;
        
        return root;
    }
}
