package dynamicProgramming;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] A = new int[]{5, 3, 4, 8, 6, 7};
        System.out.println(lis(A));
	}
	
	// Example Input: 5, 3, 4, 8, 6, 7
	// Example Output: 4 (3, 4, 6, 7)
	public static int lis(int[] A) {
		if(A == null) {
			throw new IllegalArgumentException("Input array is null.");
		}
    	int n = A.length;
    	int[] d = new int[n]; // d[i] denotes the length of longest increasing subsequence
    						  // from d[0] to d[i]
    	
    	for(int i = 0; i < n; ++i) {
    		d[i] = 1;
    		for(int j = 0; j < i; ++j) {
    			if(A[j] <= A[i] && d[j] + 1 > d[i]) {
    				d[i] = d[j] + 1;
    			} else {
    				d[i] = d[j];
    			}
    		} // inner for
    	} // out for
    	
    	for(int i = 0; i < n; i++) {
    		System.out.println("d[" + i + "] = " + d[i]);
    	}
    	
    	return d[n - 1];
    }
}
