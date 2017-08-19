package binaryTree;

import java.util.PriorityQueue;

/**
 * @author jhzhu@outlook.com
 *
 * @Tag Binary Tree
 */
public class Huffman {
	public HTreeNode build(char[] chars, int[] freq) {
		if (chars == null || freq == null || chars.length == 0
			|| freq.length == 0 || chars.length != freq.length)
			return null;
		
		PriorityQueue<HTreeNode> nodes = new PriorityQueue<HTreeNode>();
		for(int i = 0; i < chars.length; i++)
			nodes.offer(new HTLeaf(freq[i], chars[i]));
		
		while(nodes.size() > 1) {
			HTreeNode a = nodes.poll();
			HTreeNode b = nodes.poll();
			nodes.offer(new HTNode(a, b));
		}
		
		return nodes.poll();
	}
}

abstract class HTreeNode implements Comparable<HTreeNode> {
	public int freq;
	
	public HTreeNode(int f) {
		freq = f;
	}
	
	public int compareTo(HTreeNode n) {
		return freq - n.freq;
	}
}

class HTLeaf extends HTreeNode {
	public char ch;
	
	public HTLeaf(int f, char c) {
		super(f);
		ch = c;
	}
}

class HTNode extends HTreeNode {
	public HTreeNode left = null;
	public HTreeNode right = null;
	
	public HTNode(HTreeNode l, HTreeNode r) {
		super(l.freq + r.freq);
		left = l;
		right = r;
	}
}