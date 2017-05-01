package binaryTree;

import java.util.Iterator;
import java.util.Stack;

import tree.binaryTree.BNode;
import tree.binaryTree.BTree;

/**
 * @author jhzhu@outlook.com
 * 
 * @Description
 * Find the longest path sum from root. The path may end at any node and contain at least one node in it.
 *
 * @Tag Binary Tree, Recursion, Divide and Conquer
 */
public class LongestPath1 {
	public static void main(String [] args) {
		String[] init = new String[]{"-191","563","664","-875","988","198","-505","-420","795","957","#","536","-995","-681","821","381","-852","-506","-786","964","392","-675","-654","-717","479","-402","-223","-666","#","249","#","#","#","714","164","#","#","#","#","-580","#","#","652","-153","144","142","-776","906","-665","194","#","#","#","#","#","382","#","25","118","#","433","-754","53","701","#","-496","837","#","#","994","-440","#","-98","-456","852","-237","-159","#","92","628","-681","#","#","547","#","#","600","#","#","#","#","#","#","#","#","#","#","237","-127","795","#","#","#","316","53","-191","628","3","357","474","#","#","#","#","#","#","#","#","#","-746","-650","#","805","-201","750","#","#","#","#","-925","-237","#","-147","629","-769","#","#","#","-846","#","-776","#","#","#","#","#","#","#","#","#","#","-906","552","#","#","#","#","675","-832","594","#","-61","#","#","#","-291","-783","#","#","-660","#","789","#","#","-786","-853","#","-534","-321","-564","-398","358","-874","#","#","#","#","#","#","#","#","#","#","#","#","#","-598","#","#","#","#","#","593"};
		BTree bt = new BTree(init, 2);
		System.out.println(getMaxPathN(bt.getRoot()));
		System.out.println(getMaxPath(bt.getRoot()));
	}
	
	/**
     * @param root the root of binary tree.
     * @return an integer
     */
    public static int getMaxPath(BNode root) {
        if (root == null) 
            return Integer.MIN_VALUE;
        
        int left = getMaxPath(root.left);
        int right = getMaxPath(root.right);
        
        /*
         * The number 0 plays an important role:
         * 1. to deal with leaf node case: 0 > Integer.MIN_VALUE
         * 2. for any subtree root node n, if root.left.val < 0, just make ti 
         */
        return root.val + Math.max(0, Math.max(left, right));
    }
	
    /**
     * @param root the root of binary tree.
     * @return an integer
     */
	public static int getMaxPathN(BNode root) {
		if (root == null)
			return Integer.MIN_VALUE;
		int max = root.val;
		BNode cur = root;
		Stack<BNode> st = new Stack<BNode>();
		
		while (cur != null || !st.isEmpty()) {
			while (cur != null) {
				st.push(cur);
				if (cur.left != null)
					cur = cur.left;
				else
					cur = cur.right;
			}
			
			cur = st.pop();
			max = helper(st, cur.val, max);
			
			while (!st.isEmpty() && st.peek().right == cur) {
				cur = st.pop();
				max = helper(st, cur.val, max);
			}
				
			if (!st.isEmpty())
				cur = st.peek().right;
			else
				cur = null;
		}
		
		return max;
	}
	
	private static int helper(Stack<BNode> s, int cur, int max) {
		Iterator<BNode> it = s.iterator();
		while (it.hasNext())
			cur += it.next().val;
		
		return cur > max ? cur : max;
	}
}
