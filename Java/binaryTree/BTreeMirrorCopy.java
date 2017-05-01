package binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import tree.binaryTree.BNode;


public class BTreeMirrorCopy {

	public static void main(String[] args) {
		List<BNode> roots = create();
		BNode newRoot = null, root1 = roots.get(0), root2 = roots.get(1), root3 = roots.get(2);
//		mirrorTransform(root);
//		mirrorTransformN(root);
		newRoot = mirrorCopy(root1);
//		newRoot = mirrorCopyN(root);
		System.out.println(isMirror(root1, newRoot));
		System.out.println(isMirrorN(root1, newRoot));
		System.out.println(isMirrorN(root2, root2));
		System.out.println(isMirrorN(root2, root3));
	}
	
	// create test case
	private static List<BNode> create() {
		List<BNode> roots = new LinkedList<BNode>();
		
		// test case 1:
		BNode root1 = new BNode(1, 3);
		BNode n2 = new BNode(2, 2);
		BNode n3 = new BNode(3, 2);
		BNode n4 = new BNode(4, 1);
		BNode n5 = new BNode(5, 1);
		BNode n6 = new BNode(6, 1);
		root1.left = n2;
		root1.right = n3;
		n2.left = n4;
		n3.left = n5;
		n3.right = n6;
		roots.add(root1);
		
		// test case 2:
		BNode root2 = new BNode(1);
		root2.left = new BNode(2);
		roots.add(root2);
		
		// test case 3:
		BNode root3 = new BNode(1);
		root3.right = new BNode(2);
		roots.add(root3);
		
		return roots;
	}
	
	public static boolean isMirror(BNode root1, BNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		} else if (root1 == null || root2 == null || root1.val != root2.val) {
			return false;
		} else {
			return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
		}
	}
	
	public static boolean isMirrorN(BNode root1, BNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		} else if (root1 == null || root2 == null) {
			return false;
		}
		Stack<BNode> st1 = new Stack<BNode>();
		Stack<BNode> st2 = new Stack<BNode>();
		BNode n1 = root1, n2 = root2;
		
		while ((n1 != null || !st1.isEmpty()) && (n2 != null || !st2.isEmpty())) {
			while (n1 != null && n2 != null) {
				if (n1.val != n2.val) {
					return false;
				}
				if (n1.right != null) {
					st1.push(n1.right);
				}
				if (n2.left != null) {
					st2.push(n2.left);
				}
				n1 = n1.left;
				n2 = n2.right;
			}
			if (n1 != null || n2 != null || st1.size() != st2.size()) {
				return false;
			} else if (!st1.isEmpty()) {
				n1 = st1.pop();
				n2 = st2.pop();
			}
		}
		
		return true;
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
	
	public static void mirrorTransformN(BNode root) {
		Stack<BNode> st = new Stack<BNode>();
		BNode cur = root, temp = null;
		
		while (!st.isEmpty() || cur != null) {
			while (cur != null) {
				temp = cur.left;
				cur.left = cur.right;
				cur.right = temp;
				if (cur.right != null) {
					st.push(cur.right);
				}
				cur = cur.left;
			}
			if (!st.isEmpty()) {
				cur = st.pop();
			}
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
