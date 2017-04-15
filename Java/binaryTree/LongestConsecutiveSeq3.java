package binaryTree;

import java.util.List;

/**
 * @Description
 * Given a k-ary tree, find the length of the longest consecutive sequence path.
 * The path could be start and end at any node in the tree
 * 
 * @Example
 *      5
 *    /   \
 *   6     4
 *  /|\   /|\
 * 7 5 8 3 5 3
 * 
 * Return 5, // 3-4-5-6-7
 *
 * @Tag DFS, Recursion
 */
public class LongestConsecutiveSeq3 {
	/**
	 * @author jhzhu@outlook.com
     * @param root the root of k-ary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive3(MultiTreeNode root) {
        return helper(root).max;
    }
    
    private LCS3ResultType helper(MultiTreeNode root) {
        if (root == null)
            return new LCS3ResultType(0, 0, 0);
        
        int down = 1, up = 1, max = 1;
        
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i ++) {
                MultiTreeNode child = root.children.get(i);
                LCS3ResultType temp = helper(child);
                if (root.val - child.val == 1 && temp.down + 1> down) 
                    down = temp.down + 1;
                else if (root.val - child.val == -1 && temp.up + 1> up) 
                    up = temp.up + 1;
                max = Math.max(max, temp.max);
            }
        }
        
        max = Math.max(max, down + up - 1);
            
        return new LCS3ResultType(down, up, max);
    }
}

class LCS3ResultType {
    int down;
    int up;
    int max;
    
    LCS3ResultType(int d, int u, int m) {
        down = d;
        up = u;
        max = m;
    }
}

class MultiTreeNode {
	int val;
	List<MultiTreeNode> children;
	MultiTreeNode(int x) { val = x; }
}