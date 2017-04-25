package graph;

// version 3: DFS
public class IslandNumberSolution3 {
	private int m, n;
	
    public void dfs(boolean[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) 
        	return;
        
        if (grid[i][j]) {
            grid[i][j] = false;
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }
    }

    public int numIslands(boolean[][] grid) {
        m = grid.length; n = grid[0].length;
        if (m == 0 || n == 0) 
        	return 0;
        
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!grid[i][j]) continue;
                ans++;
                dfs(grid, i, j);
            }
        }
        
        return ans;
    }
}
