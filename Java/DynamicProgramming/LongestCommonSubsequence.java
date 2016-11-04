package DynamicProgramming;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		char[] chArr1 = new char[]{'a', 'c', 'd', 'e'};
		char[] chArr2 = new char[]{'r', 'c', 'a', 'e'};
		
		System.out.println(LCS(chArr1, chArr2));
	}
	
	public static int LCS(char[] arr1, char[] arr2) {
		if(arr1 == null || arr2 == null) {
			throw new IllegalArgumentException();
		}
		
		int i, j, len1 = arr1.length, len2 = arr2.length;
		int[][] maxLen = new int[len1][len2];
		
		for(i = 0; i < len1; i++) {
			maxLen[i][0] = 0;
		}
		for(j = 0; j < len2; j++) {
			maxLen[0][j] = 0;
		}
		
		for(i = 1; i < len1; i++) {
			for(j = 1; j < len2; j++) {
				if(arr1[i] == arr2[j]) {
					maxLen[i][j] = maxLen[i - 1][j - 1] + 1;
				} else {
					maxLen[i][j] = Math.max(maxLen[i][j - 1], maxLen[i - 1][j]);
				}
			}
		}
		
		return maxLen[len1 - 1][len2 - 1];
	}

}
