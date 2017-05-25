package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import graph.undirectedGraph.UGNode;

/**
 * @author jhzhu@outlook.com
 *
 * @Description
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * Nodes are labeled uniquely. Use # as a separator for each node, and , as a separator for node label 
 * and each neighbor of the node.
 * 
 * @Example
 * Consider the serialized graph {0,1,2#1,2#2,2}, which has three nodes, and therefore contains three parts 
 * as separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * 
 * Visually, the graph looks like the following:
 *    1
 *   / \
 *  /   \
 * 0 --- 2
 *      / \
 *      \_/
 *
 * @Tag BFS, Graph, Facebook
 */
public class Clone {
	/**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
	public CUndirectedGraphNode cloneGraph(CUndirectedGraphNode node) {
        if (node == null)
            return null;
        
        Queue<CUndirectedGraphNode> q = new LinkedList<CUndirectedGraphNode>();
        HashMap<Integer, CUndirectedGraphNode> nodes = new HashMap<Integer, CUndirectedGraphNode>();
        HashSet<CUndirectedGraphNode> visited = new HashSet<CUndirectedGraphNode>();
        q.add(node);
        while (!q.isEmpty()) {
        	CUndirectedGraphNode n = q.poll();
            if (visited.contains(n))
                continue;
            else
                visited.add(n);
            CUndirectedGraphNode newNode = null;
            if (!nodes.containsKey(n.label)) {
                newNode = new CUndirectedGraphNode(n.label);
                nodes.put(n.label, newNode);
            } else {
                newNode = nodes.get(n.label);
            }
            for (CUndirectedGraphNode neighbor : n.neighbors) {
                q.add(neighbor);
                if (newNode.neighbors.contains(neighbor))
                    continue;
                CUndirectedGraphNode newNeighbor = null;
                if (!nodes.containsKey(neighbor.label)) {
                    newNeighbor = new CUndirectedGraphNode(neighbor.label);
                    nodes.put(neighbor.label, newNeighbor);
                } else {
                    newNeighbor = nodes.get(neighbor.label);
                }
                newNode.neighbors.add(newNeighbor);
            }
        }
        
        return nodes.get(node.label);
    }
}

class CUndirectedGraphNode {
     int label;
     ArrayList<CUndirectedGraphNode> neighbors;
     CUndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<CUndirectedGraphNode>(); }
};
