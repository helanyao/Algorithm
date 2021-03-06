package array;

/*	
 *  https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *  
 *  Given a sorted array, remove the duplicates in place 
 *  such that each element appear only once and return the new length. 
 *  Do not allocate extra space for another array, 
 *  you must do this in place with constant memory. 
 *  
 *  For example, Given input array A = [1,1,2], 
 *  Your function should return length = 2, and A is now [1,2].
 *  
 */

public class RemoveDuplicatesFromSortedArray {
	public static void main(String[] args) {
		int[] A = new int[]{1, 3, 1, 1, 2};
		int size = removeDuplicates(A);
		for( int i = 0; i < size; i++)
			System.out.print(A[i]);
	}
	
    public static int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) 
            return 0;
        
        int size = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != A[size]) {
                A[++size] = A[i];
            }
        }
        
        return size + 1;
    }
}
