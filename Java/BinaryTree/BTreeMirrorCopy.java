package BinaryTree;

import java.util.Stack;

public class BTreeMirrorCopy {

	public static void main(String[] args) {
		BNode newRoot = null, root = create();
		//mirrorTransform(root);
		newRoot = mirrorCopy(root);
		newRoot = mirrorCopyN(root);
		System.out.println();
	}
	
	// create test case
	private static BNode create() {
		BNode root = new BNode(1, 3);
		BNode n2 = new BNode(2, 2);
		BNode n3 = new BNode(3, 2);
		BNode n4 = new BNode(4, 1);
		BNode n5 = new BNode(5, 1);
		BNode n6 = new BNode(6, 1);
		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n3.left = n5;
		n3.right = n6;
		
		return root;
	}
	
	// change original data structure
	public static void mirrorTransform(BNode root) {
		if (root != null) {
			BNode n = root.left;
			root.left = root.right;
			root.right = n;
			mirrorTransform(root.left);
			mirrorTransform(root.right);
		}
	}
	
	// copy the mirror tree
	public static BNode mirrorCopy(BNode source) {
		if (source == null) {
			return null;
		}
		BNode target = new BNode();
		target.val = source.val;
		target.right = mirrorCopy(source.left);
		target.left = mirrorCopy(source.right);
		
		return target;
	}
	
	public static BNode mirrorCopyN(BNode sourceRoot) {
		BNode sourceCur = sourceRoot, targetCur = null, targetRoot = null;
		Stack<BNode> stSource = new Stack<BNode>();
		Stack<BNode> stTarget = new Stack<BNode>();
		
		while (!stSource.isEmpty() || sourceCur != null) {
			while (sourceCur != null) {
				if (targetCur == null) {
					targetCur = copyBNode(sourceCur);
				}
				if (targetRoot == null) {
					targetRoot = targetCur;
				}
				if (sourceCur.right != null) {
					targetCur.left = copyBNode(sourceCur.right);
					stSource.push(sourceCur.right);
					stTarget.push(targetCur.left);
				}
				sourceCur = sourceCur.left;
				if (sourceCur != null) {
					targetCur.right = copyBNode(sourceCur);
				}
				targetCur = targetCur.right;
			}
			
			if (!stSource.isEmpty() && !stTarget.isEmpty()) {
				sourceCur = stSource.pop();
				targetCur = stTarget.pop();
			}
		}
		
		return targetRoot;
	}
	
	private static BNode copyBNode(BNode n) {
		if (n == null) {
			return n;
		} else {
			BNode newN = new BNode();
			newN.val = n.val;
			return newN;
		}
	}

}
