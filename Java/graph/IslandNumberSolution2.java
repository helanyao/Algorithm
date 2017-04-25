package graph;

// version 2: Union Find
public class IslandNumberSolution2 {
	public int numIslands(boolean[][] grid) {
        int n = grid.length, m = grid[0].length, total = 0;
        if (n == 0 || m == 0)
            return 0;
        INS2_UnionFind union_find = new INS2_UnionFind(n * m);
        
        for(int i = 0;i < grid.length; ++i)
            for(int j = 0;j < grid[0].length; ++j)
            if (grid[i][j])
                total ++;
    
        union_find.set_count(total);
        for(int i = 0;i < grid.length; ++i)
            for(int j = 0;j < grid[0].length; ++j)
            if (grid[i][j]) {
                if (i > 0 && grid[i - 1][j])
                    union_find.connect(i * m + j, (i - 1) * m + j);
                if (i <  n - 1 && grid[i + 1][j])
                    union_find.connect(i * m + j, (i + 1) * m + j);
                if (j > 0 && grid[i][j - 1])
                    union_find.connect(i * m + j, i * m + j - 1);
                if (j < m - 1 && grid[i][j + 1])
                    union_find.connect(i * m + j, i * m + j + 1);
            }
        
        return union_find.query();
    }
}

class INS2_UnionFind { 
    private int[] father = null;
    private int count;

    private int find(int x) {
        if (father[x] == x)
            return x;
        
        return father[x] = find(father[x]);
    }

    public INS2_UnionFind(int n) {
        // initialize data structure
        father = new int[n];
        for (int i = 0; i < n; ++i)
            father[i] = i;
    }

    public void connect(int a, int b) {
        int root_a = find(a), root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            count --;
        }
    }
        
    public int query() {
        return count;
    }
    
    public void set_count(int total) {
        count = total;
    }
}