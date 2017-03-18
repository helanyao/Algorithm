package search;

import java.util.Scanner;

// The same problem as mentioned in MissingNumbers1

public class MissingNumbers2 {
	public static void main(String[] args) {
//		int[] A = new int[]{203, 204, 205, 206, 207, 208, 203, 204, 205, 206};
//		int[] B = new int[]{203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204};
		int[] A = new int[]{1, 2, 4};
		int[] B = new int[]{1, 2, 2, 3, 4, 5, 2};
		/*		
		Scanner sc = new Scanner(System.in);
        int aLen = sc.nextInt();
        int[] a = new int[aLen];
        for(int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        
        int bLen = sc.nextInt();
        int[] b = new int[bLen];
        for(int i = 0; i < b.length; i++) {
            b[i] = sc.nextInt();
        }
        
        sc.close();
        */
		
		int[] result = new int[201];
		int pivot = A[0];
		
		for(int i = 0; i < A.length; i++) {
			int distance = A[i] - pivot;
			result[100 + distance]--;
		}
		
		for(int i = 0; i < B.length; i++) {
			int distance = B[i] - pivot;
			result[100 + distance]++;
		}
		
		for(int i = 0; i < result.length; i++) {
			for(int j = 0; j < result[i]; j++) {
				int value = i - 100 + pivot;
				System.out.print(value + " ");
			}
		}
		
	}
	
	
}