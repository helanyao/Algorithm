package hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ConsistentHash2 {
	
	private int cycleLen = 0;
	private int microShards = 0;
	private HashSet<Integer> nodeStatus = null;
	private List<List<Integer>> machines = null;
	private int minNodeId;

	public static void main(String[] args) {
		ConsistentHash2 ch = ConsistentHash2.create(10, 5);
		
		List<Integer> item1 = ch.addMachine(1);
//		
//		System.out.println("*******");
//		for(int i = 0; i < item1.size(); i++) {
//			System.out.print(item1.get(i) + " ");
//		}
//		System.out.println("\n*******");
		ch.printStatus();
		System.out.println();
		
		System.out.println("get machie id for 4:" + ch.getMachineIdByHashCode(4));
		
		List<Integer> item2 = ch.addMachine(2);
//		
//		System.out.println("*******");
//		for(int i = 0; i < item2.size(); i++) {
//			System.out.print(item2.get(i) + " ");
//		}
//		System.out.println("\n*******");
		ch.printStatus();
		System.out.println();
		
		for(int i = 0; i < 10; i++) {
			System.out.println("get machie id for " + i + ": " + ch.getMachineIdByHashCode(i));
		}
	}
	
	public void printStatus() {
		System.out.println("There are " + this.machines.size() + " machines.");
		for(int i = 0; i < this.machines.size(); i++) {
			List<Integer> machine = this.machines.get(i);
			System.out.println("\n-------");
			System.out.println("The " + (i + 1) + " machine:");
			System.out.print("The nodes in machine No." + machine.get(microShards) + ": ");
			for(int j = 0; j < machine.size(); j++) {
				System.out.print(machine.get(j) + " ");
			}
		}
	}
	
    // @param n a positive integer, which is the range of consistent hashing cycle
    // @param k a positive integer, which is the number of micro-shards
    // @return a ConsistentHash2 object
	// In real NoSQL, n and k usually equal to 2^64 and 1000 respectively.
    public static ConsistentHash2 create(int n, int k) {
        if(n <= 0 || k <= 0 || k >= n) {
        	throw new IllegalArgumentException("Cycle length: " + n + " Micro-shards: " + k);
        }
        
        ConsistentHash2 ch = new ConsistentHash2();
        ch.cycleLen = n;
        ch.microShards = k;
        ch.nodeStatus = new HashSet<Integer>();
        ch.machines = new ArrayList<List<Integer>>();
        ch.minNodeId = n;
        
        return ch;
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        if(machine_id < 0) { // assume machine_id starts from 0
        	throw new IllegalArgumentException("Machine ID: " + String.valueOf(machine_id));
        } else if(nodeStatus == null) {
        	System.out.println("node status is null.");
        	return null;
        }
        // check whether it has enough space to add new machine
        int availableNodes = cycleLen - nodeStatus.size();
        if(availableNodes < microShards) {
        	System.out.println("there is no enough space to add new machine.");
        	return null;
        }
        
        ArrayList<Integer> machine = new ArrayList<Integer>(microShards + 1);
        Random ra =new Random();
        for(int i = 0; i < microShards; i++) {
        	int index = ra.nextInt(cycleLen);
        	while(nodeStatus.contains(index)) {
        		index = ra.nextInt(cycleLen);
        	}
        	if(index < minNodeId) {
        		minNodeId = index;
        	}
        	nodeStatus.add(index);
        	machine.add(i, index);
        }
        machine.add(machine_id); // in the last position, it sets the machine id
        machines.add(machine);
        
        return machine;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        if(hashcode < 0 || hashcode > cycleLen) {
        	throw new IllegalArgumentException("Hashcode: " +String.valueOf(hashcode));
        }
        
        int node = 0, distance = cycleLen;
        
        for(int i = 0; i < machines.size(); i++) {
        	List<Integer> machine = machines.get(i);
        	for(int j = 0; j < microShards; j++) {
        		int nodeId = machine.get(j);
        		if(nodeId == hashcode) {
        			return getMachineByNode(nodeId);
        		}
        		if(nodeId > hashcode && (nodeId - hashcode) < distance) {
        			node = nodeId;
        			distance = nodeId - hashcode;
        		}
        	}
        }
        if(node == 0) {
        	return getMachineByNode(minNodeId);
        } else {
        	return getMachineByNode(node);
        }  
    }
    
    public int getMachineByNode(int nodeId) {
    	if(nodeId <0 || nodeId > cycleLen) {
        	throw new IllegalArgumentException();
        }
    	
    	for(int i = 0; i < machines.size(); i++) {
    		List<Integer> machine = machines.get(i);
    		for(int j = 0; j < microShards; j++) {
    			if(machine.get(j) == nodeId) {
    				return machine.get(microShards);
    			}
    		}
    	}
    	
    	System.out.println("failed to find machine id for node: " + nodeId);
    	return -1;
    }

}
