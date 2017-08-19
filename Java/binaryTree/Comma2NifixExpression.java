package binaryTree;


//A comma expression "+(2,*(3,4))" is denoted as following:
//    +
//   / \
//  2    *
// / \
//3   4
//
// Transfer it into nifix expression and calculate the value.

class NENode{
	public String val;
	public NENode left;
	public NENode right;
}

public class Comma2NifixExpression {
	// Check the priority order of operation
	public static int precede(String op1, String op2) {
		if(!op1.equals("+") && !op1.equals("-") && !op1.equals("*") && !op1.equals("/")) {
			return -1;
		}
		if(!op2.equals("+") && !op2.equals("-") && !op2.equals("*") && !op2.equals("/")) {
			return -1;
		}
		if(op1.equals("*") || op1.equals("/")) {
			if(op2.equals("*") || op2.equals("/")) {
				return 0;
			}
		}
		return 1;
	}
	
	public static void InorderExp(NENode root) {
		int bracket;
		if(root != null) {
			if(root.left != null) {
				bracket = precede(root.val, root.left.val);
				if(bracket == 1) {
					System.out.print("(");
				}
				InorderExp(root.left);
				if(bracket == 1) {
					System.out.print(")");
				}
			}
			
			System.out.print(root.val);
			
			if(root.right != null) {
				bracket = precede(root.val, root.right.val);
				if(bracket == 1) {
					System.out.print("(");
				}
				InorderExp(root.right);
				if(bracket == 1) {
					System.out.print(")");
				}
			}
		}
	}
}
