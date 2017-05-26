package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @Description
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
 * (each edge is a pair of nodes), check whether these edges make up a valid tree.
 * Assume that no duplicate edges will appear in edges. Since all edges are undirected, 
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * @Example
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * 
 * @Tag Graph, BFS, Google, Facebook, DFS, Union Find
 */
public class ValidTree {
	public static void main(String[] args) {
		int n = 5;
		int[][] edges = new int[][]{{0,1},{0,2},{0,3},{1,4}};
		System.out.println(validTree(n, edges)); // true
		
		edges = new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}};
		System.out.println(validTree(n, edges)); // false
		
		n = 1;
		edges = new int[][]{};
		System.out.println(validTree(n, edges)); // true
		
		n = 2;
		System.out.println(validTree(n, edges)); // false
		
		n = 2;
		edges = new int[][]{{1,0}};
		System.out.println(validTree(n, edges)); // true
		
		n = 8;
		edges = new int[][]{{0,1},{1,2},{3,2},{4,3},{4,5},{5,6},{6,7}};
		System.out.println(validTree(n, edges)); // true
				
	}
	
	/**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public static boolean validTree(int n, int[][] edges) {
        if (n == 0)
            return false;
        else if (edges.length != n - 1) 
            return false;
        
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        queue.offer(0);
        hash.add(0);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor))
                    continue;
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }
        
        return (hash.size() == n);
    }
    
    private static Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new HashSet<Integer>());
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        return graph;
    }
}

// version 2: Union Find
class VTSolution2 {
    class UnionFind{
    	HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
    	
    	UnionFind(int n) {
    		for(int i = 0 ; i < n; i++)
              father.put(i, i); 
    	}
      
    	int compressed_find(int x) {
    		int parent =  father.get(x);
    		while(parent!=father.get(parent))
    			parent = father.get(parent);
          
    		int temp = -1, fa = father.get(x);
    		while(fa!=father.get(fa)) {
    			temp = father.get(fa);
    			father.put(fa, parent) ;
    			fa = temp;
    		}
          
    		return parent;    
    	}
      
    	void union(int x, int y){
    		int fa_x = compressed_find(x);
    		int fa_y = compressed_find(y);
    		if(fa_x != fa_y)
    			father.put(fa_x, fa_y);
    	}
  }
 
  public boolean validTree(int n, int[][] edges) {
      // tree should have n nodes with n-1 edges
      if (n - 1 != edges.length)
          return false;
      
      UnionFind uf = new UnionFind(n);
      
      for (int i = 0; i < edges.length; i++) {
          if (uf.compressed_find(edges[i][0]) == uf.compressed_find(edges[i][1]))
              return false;
          uf.union(edges[i][0], edges[i][1]);
      }
      
      return true;
  }
}