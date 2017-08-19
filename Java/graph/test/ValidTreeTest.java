package graph.test;

import static org.junit.Assert.*;
import org.junit.Test;
import graph.ValidTree;

public class ValidTreeTest {
	private int n;
	private int[][] edges;

	@Test
	public void testBFS() {
		n = 5;
		edges = new int[][]{{0,1},{0,2},{0,3},{1,4}};
		assertTrue(ValidTree.validTree(n, edges));
			
		edges = new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}};
		assertFalse(ValidTree.validTree(n, edges));
			
		n = 1;
		edges = new int[][]{};
		assertTrue(ValidTree.validTree(n, edges));
			
		n = 2;
		assertFalse(ValidTree.validTree(n, edges));
			
		edges = new int[][]{{1,0}};
		assertTrue(ValidTree.validTree(n, edges));
			
		n = 8;
		edges = new int[][]{{0,1},{1,2},{3,2},{4,3},{4,5},{5,6},{6,7}};
		assertTrue(ValidTree.validTree(n, edges));
	}
}
