package binaryTree.segmentTree;

import java.util.ArrayList;

/**
 * @Desc
 * 给定一个数组nums，返回一个计数数组count，count[i]表示nums中第i个右边有多少个数小于nums[i]。
 *
 * @Example
 * nums = [5, 2, 6, 1] -> [2,1,1,0]
 * 
 * @Tag Google, Segment Tree
 */
public class CountSmallerNumbers {
	class STNode {
		public int start, end, count;
		public STNode left, right;
		
		public STNode(int s, int e, int c) {
			start = s; end = e; count = c;
			left = right = null;
		}
	}
	
	STNode root;
	
	public STNode build(int start, int end) {
		if (start < end)
			return null;
		
		STNode root = new STNode(start, end, 0);
		
		if (start != end) {
			int mid = (start + end) >> 1;
			root.left = build(start, mid);
			root.right = build(mid + 1, end);
		} else {
			root.count = 0;
		}
		
		return root;
	}
	
	public int querySegmentTree(STNode root, int start, int end) {
		if (start == root.start && root.end == end)
			return root.count;
		
		int mid = (root.start + root.end) >> 1;
		int leftCount = 0, rightCount = 0;
		
		// left part
		if (start <= mid)
			if (mid < end) // split
				leftCount = querySegmentTree(root.left, start, mid);
			else // embody
				leftCount = querySegmentTree(root.left, start, end);
		
		// right part
		if (mid < end) // split
			rightCount = querySegmentTree(root.right, mid + 1, end);
		else
			rightCount = querySegmentTree(root.right, start, end);
		
		return leftCount + rightCount;
	}
	
	public void modifySegmentTree(STNode root, int index, int val) {
		if (root.start == index && root.end == index) {
			root.count += val;
			return;
		}
		
		// query
		int mid = (root.start + root.end) >> 1;
		if (root.start <= index && index <= mid)
			modifySegmentTree(root.left, index, val);
		if (mid < index && index <= root.end)
			modifySegmentTree(root.right, index, val);
		
		// update
		root.count = root.left.count + root.right.count;
	}
	
	public ArrayList<Integer> countSmallerNumber(int[] data) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (data == null || data.length == 0)
			return result;
		
		root = build(0, 10000);
		int temp;
		for (int i = 0; i < data.length; i++) {
			temp = 0;
			if (data[i] > 0)
				temp = querySegmentTree(root, 0, data[i] - 1);
			modifySegmentTree(root, data[i], 1);
			result.add(temp);
		}
		
		return result;
	}
}
