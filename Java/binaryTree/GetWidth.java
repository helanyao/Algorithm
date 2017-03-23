package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

import binaryTree.BNode;

public class GetWidth {
	public int getWidth(BNode root) {
		if(root == null) {
			return 0;
		}
		
		class WNode {
			public BNode node;
			public int level;
			
			public WNode(BNode n, int l) {
				node = n;
				level = l;
			}
		}
		
		Queue<WNode> qu = new LinkedList<WNode>();
		
		qu.add(new WNode(root, 1));
		int maxCount = 0, curLevel = 1, count = 0;
		WNode p = null;
		
		while(!qu.isEmpty()) {
			p = qu.poll();
			if(p.level != curLevel) {
				curLevel = p.level;
				if(count > maxCount) {
					maxCount = count;
				}
				count = 1;
			} else {
				count++;
			}
			
			if(p.node.getLeft() != null) {
				qu.add(new WNode(p.node.getLeft(), p.level + 1));
			}
			if(p.node.getRight() != null) {
				qu.add(new WNode(p.node.getRight(), p.level + 1));
			}
		}
		
		// important while if the most width level is the leaf node level
		if(count > maxCount) {
			maxCount = count;
		}
		return maxCount;
	}
}
