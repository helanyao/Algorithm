package binaryTree;

// A comma expression "+(2,*(3,4))" is denoted as following:
//     +
//    / \
//  2    *
//      / \
//     3   4
//
// Calculate the value.

class CENode {
	public String value;
	public CENode left;
	public CENode right;
}

public class CommaExpression {
	public static int commaExpression(CENode root) throws Exception {
		int result = 0;
		if(root != null) {
			if(isNumber(root.value)) {
				return Integer.parseInt(root.value);
			} else {
				int lv = commaExpression(root.left);
				int rv = commaExpression(root.right);
				if(root.value.equals("+")) {
					result = lv + rv;
				} else if(root.value.equals("-")) {
					result = lv - rv;
				} else if(root.value.equals("*")) {
					result = lv * rv;
				} else if(root.value.equals("/")) {
					if(rv == 0) {
						throw new Exception("The right value is 0 when dealing with operation \"/\"");
					} else {
						result = lv / rv;
					}
				}
			}
		}
		
		return result;
	}
	
	public static boolean isNumber(String s) {
		if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
			return true;
		} else {
			return false;
		}
	}
}
