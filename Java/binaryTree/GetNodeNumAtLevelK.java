package binaryTree;

import java.util.LinkedList;
import java.util.Queue;
import tree.binaryTree.BNode;

public class GetNodeNumAtLevelK {
	public static int getNodeNumN(BNode root, int level) {
		if (root == null || level <= 0) 
			return -1;
		
		int curLevel = 0;
		Queue<BNode> q = new LinkedList<BNode>();
		q.offer(root);
		BNode cur = null;
		
		while (!q.isEmpty()) {
			curLevel++;
			if (curLevel == level) 
				return q.size();
			int length = q.size(); // important 
			for (int i = 0; i < length; i++) { // important, as q is always changed
				cur = q.poll();
				if (cur.left != null) 
					q.offer(cur.left);
				if (cur.right != null) 
					q.offer(cur.right);
			}
		}
		
		return 0;
	}
	
	public static int getNodeNum(BNode root, int level) {
        if (root == null || level <= 0) 
            return 0;
        else if (level == 1) 
            return 1;
        
        // 将左子树及右子树在K层的节点个数相加.
        return getNodeNum(root.left, level - 1) + getNodeNum(root.right, level - 1);
	}
}
