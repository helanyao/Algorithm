package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jhzhu@outlook.com
 * 
 * @Description
 * Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0.
 * Zombies can turn the nearest people(up/down/left/right) into zombies 
 * every day, but can not through wall. How long will it take to turn all 
 * people into zombies? Return -1 if can not turn all people into zombies.
 * 
 * @Example
 * Given a matrix:
 * 	0 1 2 0 0
 * 	1 0 0 2 1
 *	0 1 0 0 0
 * 
 * return 2
 *
 * @Tag BFS
 */
public class Zombie {
	private final static int STEPS = 4;
	private final static int[] X = new int[]{-1, 0, 1, 0};
	private final static int[] Y = new int[]{0, -1, 0, 1};
	
	/**
     * @param g  a 2D integer grid
     * @return an integer
     */
	public int zombie(int[][] g) {
		if (g == null || g.length == 0)
			return -1;
		
		Queue<ZNode> q = new LinkedList<ZNode>();
		int num = 0, day = 0;
		for (int i = 0; i < g.length; i++)
			for (int j = 0; j < g[0].length; j++)
				if (g[i][j] == 1) 
					q.offer(new ZNode(i, j));
				else if (g[i][j] == 0)
					num++;
		
		while (!q.isEmpty()) {
			if (num == 0)
				break;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				ZNode n = q.poll();
				int x = n.x, y = n.y;
				for (int j = 0; j < STEPS; j++) {
					if (isValid(x + X[j], y + Y[j], g)) {
						g[x + X[j]][y + Y[j]] = 1;
						num--;
						q.offer(new ZNode(x + X[j], y + Y[j]));
					}	
				}
			}
			day++;
		}
		
		if (num == 0)
			return day;
		else
			return -1;
	}
	
	private boolean isValid(int x, int y, int[][] g) {
		if (x < 0 || y < 0 || x >= g.length || y >= g[0].length 
				|| g[x][y] == 2 || g[x][y] == 1)
			return false;
		else
			return true;	
	}
}

class ZNode {
	public int x;
	public int y;
	
	public ZNode(int x1, int y1) {
		x = x1;
		y = y1;
	}
}
