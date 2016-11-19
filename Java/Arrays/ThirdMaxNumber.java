package Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Given a non-empty array of integers, return the third maximum number. 
 *  If it does not exist, return the maximum number. 
 *  The time complexity must be in O(n).
 *  
 *  Example 1:
 *  Input: [3, 2, 1]  Output: 1
 *  
 *  Example 2:
 *  Input: [1, 2]  Output: 2
 *  Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 *  
 *  Example 3:
 *  Input: [2, 2, 3, 1]  Output: 1
 *  Explanation: Note that the third maximum here means the third maximum distinct number.
 *  Both numbers with value 2 are both considered as second maximum.
 * 
 *  https://leetcode.com/problems/third-maximum-number/
 */

public class ThirdMaxNumber {
    public int thirdMax(int[] nums) {
        List<Integer> maxNums = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (maxNums.size() < 3 || nums[i] > maxNums.get(0)) {
                addMax(maxNums, nums[i]);
            }
        }
        
        if (maxNums.size() < 3) {
            return maxNums.get(maxNums.size() - 1);
        } else {
            return maxNums.get(0);
        }
    }
    
    private void addMax(List<Integer> maxNums, int data) {
        for (Integer i : maxNums) {
            if (i == data) {
                return;
            }
        }
        if (maxNums.size() < 3) {
            maxNums.add(data);
        } else {
            maxNums.set(0, data);
        }
        
        Collections.sort(maxNums);
    }
}
