package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

import binaryTree.BNode;
import binaryTree.BTree;
import binaryTree.BTreeTraversal;

//https://leetcode.com/problems/symmetric-tree/
public class SymmetricTree {
	public static void main(String[] args) {
		String[] init = new String[]{"1", "[", "2", "[", "3", ",", "]", ",", "3", "[", "2", ",", "]", "]"};
		BTree bt = new BTree(init, 1);
		BTreeTraversal tra = new BTreeTraversal();
		tra.InOrder(bt.getRoot());	
		System.out.println();
		System.out.println(isSymmetric(bt.getRoot()));
	}
	
	public static boolean isSymmetric(BNode root) {
        if(root==null) 
        	return true;
        
        return isSym(root.left,root.right);
    }
	
    public static boolean isSym(BNode left,BNode right){
    	if (left==null && right==null) 
    		return true;
    	
    	if (left!=null && right==null) 
    		return false;
    	
    	if (left==null && right!=null) 
    		return false;
    	
    	if (left.val != right.val) 
    		return false;
    	else 
    		return isSym(left.right,right.left)&&isSym(left.left,right.right);
    }
    
    public boolean isSymmetricN(BNode root) {
        if (root==null)  
            return true;  
        
        Queue<BNode> ql=new LinkedList<BNode>();  
        Queue<BNode> qr=new LinkedList<BNode>();  
        ql.offer(root.left);  
        qr.offer(root.right);  
        while (!ql.isEmpty()){  
        	BNode left=ql.poll();  
        	BNode right=qr.poll();  
            if (left==null && right==null)
                continue;  
            if (left==null || right==null || left.val != right.val) 
                return false;  
            
            ql.offer(left.left);  
            ql.offer(left.right);  
            qr.offer(right.right);  
            qr.offer(right.left);  
        }  
            
        return true;  
    }
}
