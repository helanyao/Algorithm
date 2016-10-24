package Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

// Compared with ConsistentHash1 & 2, this class employs SortedMap & TreeMap to improve performance.
// Besides, it implements the function of remove machine.

public class ConsistentHash {
	public static void main(String[] args) {

	}

}

class CstntHash {
	private int cycleLen = 0;
	private int microSharding = 0;
	private TreeMap<Integer, Integer> nodesToMachine = null;
	private Map<Integer, List<Integer>> machineToNodes = null;
	private Map<Integer, List<Integer>> nodeToValues = null;
	
	public static CstntHash create(int n, int k) {
		if(n <= 0 || k <= 0 || n < k) {
			throw new IllegalArgumentException("Cyclet Length: " + n + " Micro-Sharding: " + k);
		}
		
		CstntHash ch = new CstntHash();
		ch.cycleLen = n;
		ch.microSharding = k;
		ch.nodesToMachine = new TreeMap<Integer, Integer>();
		ch.machineToNodes = new HashMap<Integer, List<Integer>>();
		ch.nodeToValues = new HashMap<Integer, List<Integer>>();
		
		return ch;
	}
	
	// In the returned list, the element starts from 0 to cycleLen - 1 is the virtual nodes.
	// The element at position cycleLen is the machine id.
	public List<Integer> addMachine(int machineId) {
		if(machineId <= 0 || machineToNodes.containsKey(machineId)) {
			System.out.println("Machine ID = " + machineId + " is less than 0 or there exists machine with same id.");
			return null;
		} else if(cycleLen - nodesToMachine.size() < microSharding) {
			System.out.println("no enough nodes to add new machine.");
			return null;
		}
		
		List<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < microSharding; i++) {
			int node = getNewNodeId();
			nodesToMachine.put(node, machineId);
			nodes.add(node);
		}
		machineToNodes.put(machineId, nodes);
		nodes.add(machineId);
		
		return nodes;
	}
	
	// Actually, this function should be replaced with some hash algorithm.
	private int getNewNodeId() {
		Random ra =new Random();
		int node = ra.nextInt(cycleLen);
		while(nodesToMachine.containsKey(node)) {
			node = ra.nextInt(cycleLen);
		}
		return node;
	}
	
	public void addValue(int val) {
		int hashcode = val % cycleLen;
		int node = getNodeByHashCode(hashcode);
		List<Integer> values = null;
		if(nodeToValues.containsKey(node)) {
			values = nodeToValues.get(node);
		} else {
			values = new ArrayList<Integer>();
		}
		values.add(val);
		nodeToValues.put(node, values);
	}
	
	public int getNodeByHashCode(int hashcode) {
		if(hashcode < 0 || hashcode > cycleLen) {
			throw new IllegalArgumentException("hashcode : " + hashcode);
		}
		
		SortedMap<Integer, Integer> subNodesMachine = nodesToMachine.tailMap(hashcode);
		
		return subNodesMachine.firstKey();
	}
	
	public int getMachineByHashCode(int hashcode) {
		if(hashcode < 0 || hashcode > cycleLen) {
			throw new IllegalArgumentException("hashcode : " + hashcode);
		}
		
		int node = getNodeByHashCode(hashcode);
		
		return nodesToMachine.get(node);
	}
	
	public void removeMachine(int machineId) {
		if(machineId < 0 || !machineToNodes.containsKey(machineId)) {
			System.out.println("does not have this machine " + machineId);
			return;
		}
		
		List<Integer> nodes = machineToNodes.get(machineId);
		List<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < nodes.size(); i++) {
			values.addAll(getValuesAtNode(nodes.get(i)));
		}

		removeData(machineId, nodes);
		
		// re-allocate the values to other virtual nodes
		for(int i = 0; i < values.size(); i++) {
			addValue(values.get(i));
		}
	}
	
	public List<Integer> getValuesAtNode(int node) {
		List<Integer> values = new ArrayList<Integer>();
		if(node >= 0 && nodeToValues.containsKey(node)) {
			values.addAll(nodeToValues.get(node));
		}
		
		return values;
	}
	
	private void removeData(int machineId, List<Integer> nodes) {
		if(machineId < 0 || nodes == null) {
			return;
		}
		for(int i = 0; i < nodes.size(); i++) {
			int node = nodes.get(i);
			nodesToMachine.remove(node);
			nodeToValues.remove(node);
		}
		machineToNodes.remove(machineId);
	}

}
