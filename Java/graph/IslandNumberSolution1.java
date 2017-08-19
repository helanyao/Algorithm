package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. 
 * If two 1 is adjacent, we consider  them in the same island. We only consider 
 * up/down/left/right adjacent.
 * 
 * Find the number of islands.
 * 
 * @Example
 * [
 *   [1, 1, 0, 0, 0],
 *   [0, 1, 0, 0, 1],
 *   [0, 0, 0, 1, 1],
 *   [0, 0, 0, 0, 0],
 *   [0, 0, 0, 0, 1]
 * ]
 * return 3.
 * 
 * @Tag Facebook, Zenefits, Google, BFS, To-Do
 */
public class IslandNumberSolution1 {
	public static void main(String[] args) {
		int[][] grid = new int[][]{
			{1,1,0,0,0},
			{0,1,0,0,1},
			{0,0,0,1,1},
			{0,0,0,0,0},
			{0,0,0,0,1}
		};
		
		grid = new int[][]{{1}};
	}
}

// version 1: BFS
class Solution1 {
	class Coordinate {
	    int x, y;
	    public Coordinate(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
	
	public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) 
            return 0;
        
        int n = grid.length, m = grid[0].length, islands = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    markByBFS(grid, i, j);
                    islands++;
                }
            }
        }
               
        return islands;
    }
    
    private void markByBFS(boolean[][] grid, int x, int y) {
        int[] directionX = {0, 1, -1, 0};
        int[] directionY = {1, 0, 0, -1};
        
        Queue<Coordinate> queue = new LinkedList<>();
        
        queue.offer(new Coordinate(x, y));
        grid[x][y] = false;
        
        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate adj = new Coordinate(
                    coor.x + directionX[i],
                    coor.y + directionY[i]
                );
                if (!inBound(adj, grid)) 
                    continue;
                if (grid[adj.x][adj.y]) {
                    grid[adj.x][adj.y] = false;
                    queue.offer(adj);
                }
            }
        }
    }
    
    private boolean inBound(Coordinate coor, boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
    }
}

