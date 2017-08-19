package hash;

import java.util.ArrayList;
import java.util.List;

public class ConsistentHash1 {
	// assume the range of consistent hashing virtual nodes is 360
	private static int cycleLen = 360;
	
	public static void main(String[] args) {
		List<List<Integer>> machines = consistentHashing(4);
		System.out.println("\n\nResult:");
		printMachine(machines);
	}
	
	// for n machines, this function will shard the hashing range from
	// the case in which there is only 1 machine.
	// Then add a new machine(2 machines in hashing range) and continue.
    public static List<List<Integer>> consistentHashing(int n) {
        if(n < 1 || n > cycleLen) {
            throw new IllegalArgumentException();
        }
        
        ArrayList<List<Integer>> myHashSet = new ArrayList<List<Integer>>();
        init(myHashSet);
        printMachine(myHashSet);
        for(int i = 2; i <= n; i++) {
        	System.out.println("\nadding the " + myHashSet.size() + "th machine:");
        	int index = findTarget(myHashSet);
            updateList(myHashSet, index);
            printMachine(myHashSet);
        }
        
        return myHashSet; 
    }
    
    // for each ArrayList of machine, the meaning of its values in order are
    // bottom value, top value, machine Id
    public static void init(List<List<Integer>> myHashSet) {
    	ArrayList<Integer> machine = new ArrayList<Integer>();
    	machine.add(0);
    	machine.add(cycleLen - 1);
    	machine.add(1); // first machine id
    	myHashSet.add(machine);
    }
    
    public static int findTarget(List<List<Integer>> myHashSet) {
    	int max = 0, index = 0, id = cycleLen;
    	for(int i = 0; i < myHashSet.size(); i++) {
    		List<Integer> item = myHashSet.get(i);
    		int range = item.get(1) - item.get(0);
    		int itemId = item.get(2);
    		if(range > max) {
    			max = range;
    			index = i;
    			id = itemId;
    		} else if(range == max && itemId < id) {
    			id = itemId;
    			index = i;
    		}
    	}
    	
    	return index;
    }
    
    public static void updateList(List<List<Integer>> myHashSet, int index) {
    	int newMachineId = getMaxId(myHashSet) + 1;
    	List<Integer> splitItem = myHashSet.get(index);
    	int low = splitItem.get(0), high = splitItem.get(1);
    	int splitPoint = (high - low) / 2 + low;
    	splitItem.set(1, splitPoint);
    	ArrayList<Integer> newItem = new ArrayList<Integer>();
    	newItem.add(splitPoint + 1);
    	newItem.add(high);
    	newItem.add(newMachineId);
    	myHashSet.add(newItem);
    }
    
    public static int getMaxId(List<List<Integer>> myHashSet) {
    	if(myHashSet == null || myHashSet.isEmpty()) {
    		throw new IllegalArgumentException();
    	}
    	
    	int maxId = 0;
    	for(int i = 0; i < myHashSet.size(); i++) {
    		List<Integer> machine = myHashSet.get(i);
    		if(machine.get(2) > maxId) {
    			maxId = machine.get(2);
    		}
    	}
    	
    	return maxId;
    }
    
    public static void printMachine(List<List<Integer>> myHashSet) {
    	if(myHashSet == null) {
    		System.out.println("The machine hashing set object is null.");
    		return;
    	} else if(myHashSet.isEmpty()) {
    		System.out.println("There is 0 machine in this hashing set.");
    		return;
    	}
    	
    	System.out.println("There are " + myHashSet.size() + " machines in this set.");
    	for(int i = 0; i < myHashSet.size(); i++) {
    		List<Integer> machine = myHashSet.get(i);
    		System.out.print("Machine ID: " + machine.get(2) + " bottom: " + machine.get(0) + " top: " + machine.get(1));
    		System.out.println("\n----------------");
    	}
    }
}
