package binaryTree.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import binaryTree.BT2LinkedList;
import tree.binaryTree.BNode;
import tree.binaryTree.BTree;
import tree.binaryTree.BTreeHelper;

public class BT2LinkedListTest {
	
	private static final String[] data = new String[]{"1","2","5","3","4"};
	private static final int[] result = new int[]{1,2,3,4,5};
	private BNode root;
	private BT2LinkedList test = new BT2LinkedList();
	
	@Before
	public void init() {
		BTree t = new BTree();
		BTreeHelper h = new BTreeHelper();
		t.root = h.create(data);
		root = t.getRoot();
	}

	@Test
	public void testFlattenN() {
		test.flattenN(root);
		assertNotNull(root);
		BNode n = root;
		int i = 0;
		while (n != null) {
			assertEquals(n.val, result[i++]);
			assertNull(n.left);
			n = n.right;
		}
		assertEquals(i, data.length);
		root = null;
		test.flattenN(root);
		assertNull(root);
	}

	@Test
	public void testFlatten() {
		test.flatten(root);
		assertNotNull(root);
		BNode n = root;
		int i = 0;
		while (n != null) {
			assertEquals(n.val, result[i++]);
			assertNull(n.left);
			n = n.right;
		}
		assertEquals(i, data.length);
		root = null;
		test.flatten(root);
		assertNull(root);
	}

}
