package binaryTree;

/**
 * 
 * @Description
 * Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
 * It's guaranteed that there is only one subtree with maximum average.
 * 
 * @Example
 *      1
 *    /   \
 *  -5     11
 *  / \   /  \
 * 1   2 4    -2 
 * return the node 11.
 * 
 * @Tag DFS, Amazon, Binary Tree
 */
public class MaxAvgSubtree {
	private class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }
	private BNode subtree = null;
    private ResultType subtreeResult = null;
 
	
	/**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public BNode findSubtree(BNode root) {
    	helper(root);
        return subtree;
    }
    
    private ResultType helper(BNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        ResultType result = new ResultType(
            left.sum + right.sum + root.val,
            left.size + right.size + 1
        );
        
        // 乘法比除法的鲁棒性更好
        if (subtree == null ||
            subtreeResult.sum * result.size < result.sum * subtreeResult.size
        ) {
            subtree = root;
            subtreeResult = result;
        }
        return result;
    }
}
