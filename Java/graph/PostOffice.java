package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * http://www.jiuzhang.com/solutions/build-post-office-ii/
 * 
 * @Description
 * Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0. 
 * Find a place to build a post office so that the sum of the distance 
 * from the post office to all the houses is smallest.
 * 
 * Return the smallest sum of distance. Return -1 if it is not possible.
 *
 * You cannot pass through wall and house, but can pass through empty.
 * You only build post office on an empty.
 * 
 * Solve this problem within O(n^3) time.
 *
 * @Example
 * Given a grid:
 * 	0 1 0 0 0
 *	1 0 0 2 1
 *	0 1 0 0 0
 * return 8, build at (1,1).
 *
 * @Tag Google, BFS
 */
public class PostOffice {
	public final int EMPTY = 0, HOUSE = 1, WALL = 2;
    public int[][] grid;
    public int n, m;
    public final int[] deltaX = {0, 1, -1, 0};
    public final int[] deltaY = {1, 0, 0, -1};
    
    private List<POCoordinate> getCoordinates(int type) {
        List<POCoordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == type)
                    coordinates.add(new POCoordinate(i, j));
        
        return coordinates;
    }
    
    private void setGrid(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        this.grid = grid;
    }
    
    private boolean inBound(POCoordinate coor) {
        if (coor.x < 0 || coor.x >= n)
            return false;
        if (coor.y < 0 || coor.y >= m)
            return false;
        
        return grid[coor.x][coor.y] == EMPTY;
    }

    /**
     * @param grid a 2D grid
     * @return an integer
     */
    /*
     * 1. Need a matrix to record which one is visited and how many times there are. 
     * 2. And another matrix to record how many steps are needed to get that point. 
     * 3. Find one point whose number of visited is equal to the number of house and 
     *    have minimum steps.
     */
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return -1;
        
        // set n, m, grid
        setGrid(grid);
        
        List<POCoordinate> houses = getCoordinates(HOUSE);
        int[][] distanceSum = new int[n][m];;
        int[][] visitedTimes = new int[n][m];;
        for (POCoordinate house : houses)
            bfs(house, distanceSum, visitedTimes);
        
        int shortest = Integer.MAX_VALUE;
        List<POCoordinate> empties = getCoordinates(EMPTY);
        for (POCoordinate empty : empties) {
            if (visitedTimes[empty.x][empty.y] != houses.size())
                continue;
            shortest = Math.min(shortest, distanceSum[empty.x][empty.y]);
        }
        
        if (shortest == Integer.MAX_VALUE)
            return -1;
        
        return shortest;
    }
    
    private void bfs(POCoordinate start, int[][] distanceSum, int[][] visitedTimes) {
        Queue<POCoordinate> queue = new LinkedList<>();
        boolean[][] hash = new boolean[n][m];
        
        queue.offer(start);
        hash[start.x][start.y] = true;
        
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int temp = 0; temp < size; temp++) {
            	POCoordinate coor = queue.poll();
                for (int i = 0; i < 4; i++) {
                	POCoordinate adj = new POCoordinate(
                        coor.x + deltaX[i],
                        coor.y + deltaY[i]
                    );
                    if (!inBound(adj))
                        continue;
                    if (hash[adj.x][adj.y])
                        continue;
                    queue.offer(adj);
                    hash[adj.x][adj.y] = true;
                    distanceSum[adj.x][adj.y] += steps;
                    visitedTimes[adj.x][adj.y]++;
                } // direction
            } // for temp
        } // while
    }
}

class POCoordinate {
    int x, y;
    
    public POCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
