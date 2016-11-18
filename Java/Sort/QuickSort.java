package Sort;

import java.util.Stack;

public class QuickSort {
	public static void main(String[] args) {
		int[] A = new int[]{3, 2 ,1};
		int[] B = new int[]{3, 2, 1};
		quickSort(A, 0, A.length - 1);
		quickSortN(B);
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : B) {
			System.out.print(i + " ");
		}
	}
	
	private static void quickSort(int[] A, int start, int end) {
        if (A == null || A.length < 2 || start >= end) {
            return;
        }
        
        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        int pivot = A[(start + end) / 2];

        // key point 2: every time you compare left & right, it should be 
        // left <= right not left < right
        while (left <= right) {
            // key point 3: A[left] < pivot not A[left] <= pivot
            while (left <= right && A[left] < pivot) {
                left++;
            }
            // key point 3: A[right] > pivot not A[right] >= pivot
            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                
                left++;
                right--;
            }
        }
        
        quickSort(A, start, right);
        quickSort(A, left, end);
    }
	
	public static void quickSortN(int[] A) {
		if (A == null || A.length < 2) {
			return;
		}
		Stack<Integer> leftSt = new Stack<Integer>();
		Stack<Integer> rightSt = new Stack<Integer>();
		leftSt.push(0);
		rightSt.push(A.length - 1);
		
		while (!leftSt.isEmpty() && !rightSt.isEmpty()) {
			// key point 1: need to remember the position of start and end
			int start = leftSt.pop(), end = rightSt.pop();
			// key point 2: to exist infinite loop 
			if (start > end) {
				continue;
			}
			int left = start, right = end, pivot = A[(left + right) / 2];
			
			while (left <= right) {
				while (left <= right && A[left] < pivot) {
					left++;
				}
				while (left <= right && A[right] > pivot) {
					right--;
				}
	            if (left <= right) {
	                int temp = A[left];
	                A[left] = A[right];
	                A[right] = temp;
	                
	                left++;
	                right--;
	            }
			} // while (left <= right)
			
			// key point 3: need to push start and end
			leftSt.push(start);
			rightSt.push(right);
			leftSt.push(left);
			rightSt.push(end);
		}
	}

}
