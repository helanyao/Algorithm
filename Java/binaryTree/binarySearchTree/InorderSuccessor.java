package binaryTree.binarySearchTree;

import java.util.Stack;
import tree.binaryTree.BNode;
import tree.binaryTree.BTree;
import tree.binaryTree.BTreeHelper;

/**
 * @author jhzhu@outlook.com
 *
 * @Description
 * Given a BST and a node, find the in-order successor of that node in BST.
 * If the given node has no in-order successor in the tree, return null.
 * 
 * @Tag Binary Search Tree
 */
public class InorderSuccessor {
	public static void main(String[] args) {
		String[] init = new String[]{"7","2","8","1","6","#","10","#","#","3","#","9","11","#","5","#","#","#","#","4"};
		BTree bt = new BTree();
		BTreeHelper h = new BTreeHelper();
		bt.root = h.create(init);
		BNode p = new BNode(3);
		System.out.println(inorderSuccessor1(bt.getRoot(), p)); // 4
	}
	
	public static BNode inorderSuccessor1(BNode root, BNode p) {
        if (root == null || p == null)
            return null;
        
        BNode n = root;
        Stack<BNode> st = new Stack<BNode>();
        
        while (n != null || !st.isEmpty()) {
            while (n != null && n.val != p.val) {
                st.push(n);
                n = n.left;
            }
            if (n != null && n.val == p.val)
                break;
            else if (!st.isEmpty()) 
                n = st.pop().right;
        }
        
        if (st.isEmpty()) {
            if (n.left != null || n.right != null)
                return n.right;
            else
                return null;
        } else if (n.right == null) {
            return st.pop();
        } else {
            n = n.right;
            while (n != null && n.left != null)
                n = n.left;
            return n;
        }
    }
	
	// better solution
	public BNode inorderSuccessor2(BNode root, BNode p) {
        BNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        if (root == null) 
            return null;
        else if (root.right == null) 
            return successor;
        
        root = root.right;
        while (root.left != null) 
            root = root.left;
        
        return root;
    }
}
