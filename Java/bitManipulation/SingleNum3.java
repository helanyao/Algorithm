package bitManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * Given 2*n + 2 numbers, every numbers occurs twice except two, find them.
 * 
 * @Example
 * Given [1,2,2,3,4,4,5,3] return 1 and 5
 *
 */
public class SingleNum3 {
	/**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) 
            xor ^= A[i];
        
        int lastBit = xor - (xor & (xor - 1)), group0 = 0, group1 = 0;
        for (int i = 0; i < A.length; i++) {
            if ((lastBit & A[i]) == 0) 
                group0 ^= A[i];
            else 
                group1 ^= A[i];
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(group0);
        result.add(group1);
        
        return result;
    }
}
