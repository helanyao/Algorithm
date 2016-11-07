package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NodeSumAtSpecificLevel {

	public static void main(String[] args) {
		String[] init = new String[]{"1", "[", "2", "[", "4", ",", "5", "]", ",", "3", "[", "6", ",", "]", "]"};	
		BTree bt = new BTree(init);
		System.out.println(getNodeNumN(bt.getRoot(), 3));
		System.out.println(getNodeNum(bt.getRoot(), 3));
	}

	public static int getNodeNumN(BNode root, int level) {
		if (root == null || level <= 0) {
			return -1;
		}
		
		int curLevel = 0;
		Queue<BNode> q = new LinkedList<BNode>();
		q.offer(root);
		BNode cur = null;
		
		while (!q.isEmpty()) {
			curLevel++;
			if (curLevel == level) {
				return q.size();
			}
			int length = q.size(); // important 
			for (int i = 0; i < length; i++) { // important, as q is always changed
				cur = q.poll();
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
		}
		
		return 0;
	}
	
	public static int getNodeNum(BNode root, int level) {
		if (root == null || level < 1) {
			return -1;
		}
		
		List<List<BNode>> levelNodes = new ArrayList<List<BNode>>();
		getNodeNumHelper(root, level, 1, levelNodes);
		
		return levelNodes.get(level - 1).size();
	}
	
	private static void getNodeNumHelper(BNode root, int level, int curLevel, List<List<BNode>> levelNodes) {
		if (root == null || level < curLevel) {
			return;
		} 
		
		List<BNode> nodes = null;
		if (curLevel > levelNodes.size()) {
			nodes = new ArrayList<BNode>();
			levelNodes.add(nodes);
		} else {
			nodes = levelNodes.get(curLevel - 1);
		}
		
		nodes.add(root);
		
		getNodeNumHelper(root.left, level, curLevel + 1, levelNodes);
		getNodeNumHelper(root.right, level, curLevel + 1, levelNodes);
		
	}
	
}
