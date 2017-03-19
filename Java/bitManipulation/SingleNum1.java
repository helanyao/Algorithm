package bitManipulation;

/**
 * 
 *  @author jhzhu@outlook.com
 *  
 *  @Description
 *  Given 2*n + 1 numbers, every numbers occurs twice except one, find it.
 *  
 *  @Example
 *  Given [1,2,2,1,3,4,3], return 4
 *  
 */
public class SingleNum1 {
	/**
     *@param A : an integer array
     *return : an integer 
     */
   public int singleNumber(int[] A) {
       if (A.length == 0)
           return 0;
           
       int result = A[0];
       for (int i = 1; i < A.length; i++)
           result ^= A[i];
       
       return result;
   }
}
