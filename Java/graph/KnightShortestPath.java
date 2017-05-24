package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * Given a knight in a chess board (a binary matrix with 0 as empty and 1 as barrier) 
 * with a source position, find the shortest path to a destination position, return the 
 * length of the route. Return -1 if knight can not reached.
 * 
 * Clarification
 * If the knight is at (x, y), he can get to the following positions in one step:
 * (x + 1, y + 2), (x + 1, y - 2), (x - 1, y + 2), (x - 1, y - 2), (x + 2, y + 1)
 * (x + 2, y - 1), (x - 2, y + 1), (x - 2, y - 1)
 * 
 * @Notice
 * 1. source and destination must be empty.
 * 2. Knight can not enter the barrier
 * 
 * @Example
 * [[0,0,0],
 *  [0,0,0],
 *  [0,0,0]]
 * source = [2, 0] destination = [2, 2] return 2
 * 
 * [[0,1,0],
 *  [0,0,0],
 *  [0,0,0]]
 * source = [2, 0] destination = [2, 2] return 6
 *
 * [[0,1,0],
 *  [0,0,1],
 *  [0,0,0]]
 * source = [2, 0] destination = [2, 2] return -1
 * 
 * @Tag BFS
 */
public class KnightShortestPath {
	private static final int[][] direction = new int[][]{{1,2},{1,-2},
														{-1,2},{-1,-2},
														{2,1},{2,-1},
														{-2,1},{-2,-1}};
														
	public static void main(String[] args) {
		boolean[][] grid = new boolean[][]{{false,false,false},{false,false,false},{false,false,false}};
		KSPPoint source = new KSPPoint(2, 0), dest = new KSPPoint(2, 2);
		System.out.println(shortestPath(grid, source ,dest)); // 2
		
	}
	
	 /**
     * @param grid a chess board included 0 (false) and 1 (true)
     * @param source, destination a point
     * @return the shortest path 
     */
    public static int shortestPath(boolean[][] grid, KSPPoint source, KSPPoint destination) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || 
        		grid[source.x][source.y] == true || 
        		grid[destination.x][destination.y] == true)
        	return -1;
        
        Queue<KSPPoint> q = new LinkedList<KSPPoint>();
        q.add(source);
        int step = 0;
        while (!q.isEmpty()) {
        	int size = q.size();
        	for (int j = 0; j < size; j++) {
        		KSPPoint p = q.poll();
            	if (p.x == destination.x && p.y == destination.y)
            		return step;
            	for (int i = 0; i < direction.length; i++) {
            		int x_new = p.x + direction[i][0], y_new = p.y + direction[i][1];
            		if (!isValid(x_new, y_new, grid))
            			continue;
            		grid[x_new][y_new] = true;
            		q.add(new KSPPoint(x_new, y_new));
            	}
        	}
        	step++;
        }
        
        return -1;
    }
    
    private static boolean isValid(int x, int y, boolean[][] grid) {
    	if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length)
    		return false;
    	return grid[x][y] == false;
    }

}

class KSPPoint {
	 public int x, y;
	 public KSPPoint() { x = 0; y = 0; }
	 public KSPPoint(int a, int b) { x = a; y = b; }
}