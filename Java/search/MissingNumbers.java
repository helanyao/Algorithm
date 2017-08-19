package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *  @author jhzhu@outlook.com
 *  
 *  @Description
 *  There are two lists A and B, such that B was a permutation of A. 
 *	Unfortunately, while transporting them from one exhibition to another, 
 *	some numbers were left out of A. Can you find the missing numbers?
 *
 *	@Notes
 *	1. If a number occurs multiple times in the lists, 
 *     the frequency of that number in both lists should be same. 
 *     Otherwise, it is a missing number.
 *
 *  2. Print all the missing numbers in ascending order.
 *	
 *	3. Print each missing number once, even if it is missing multiple times.
 *
 *	4. The difference between maximum and minimum number in B is no larger than 100.
 *
 * 	@InputFormat 
 *	There will be four lines of input:
 *  1. n - the size of the first list 
 *	2. This is followed by n space-separated integers that make up the first list. 
 * 	3. m - the size of the second list 
 *  4. This is followed by m space-separated integers that make up the second list.
 *
 *	@Constraints
 *	1 <= n, m <= 1000010
 *	n <= m
 *	1 <= x <= 10000, x is one of the numbers of B
 *	Xmax - Xmin < 101
 *
 *	@OutputFormat 
 *	Output the missing numbers in ascending order.
 * 
 *	@SampleInput
 *	10
 *	203 204 205 206 207 208 203 204 205 206
 *  13
 *	203 204 204 205 206 207 205 208 203 206 205 206 204
 *
 *	@SampleOutput
 *  204 205 206
 *
 *	@Explanation
 *	204 is present in both arrays. 
 * 	Its frequency in A is 2, while its frequency in B is 3. 
 * 
*/


public class MissingNumbers {
	public static void main(String[] args) {
		Integer[] A = new Integer[]{203, 204, 205, 206, 207, 208, 203, 204, 205, 206};
		Integer[] B = new Integer[]{203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204};
//		Integer[] A = new Integer[]{1, 2, 4};
//		Integer[] B = new Integer[]{1, 2, 2, 3, 4, 5, 2};
		
		Scanner sc = new Scanner(System.in);
        int aLen = sc.nextInt();
        Integer[] a = new Integer[aLen];
        for(int i = 0; i < a.length; i++) 
            a[i] = sc.nextInt();
        
        int bLen = sc.nextInt();
        Integer[] b = new Integer[bLen];
        for(int i = 0; i < b.length; i++) 
            b[i] = sc.nextInt();
        
        sc.close();
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		addToHM(hm, B);
		checkHM(hm, A);
		printResult(hm);
	}
	
	public static void addToHM(HashMap<Integer, Integer> hm, Integer[] b) {
		if( hm == null || b == null) 
			throw new IllegalArgumentException();
		
		for(int i = 0; i < b.length; i++) {
			if(hm.containsKey(b[i])) {
				Integer v = (Integer) hm.get(b[i]);
				hm.replace(b[i], v + 1);
			} else {
				hm.put(b[i], 1);
			}
		}
	}
	
	public static void checkHM(HashMap<Integer, Integer> hm, Integer[] a) {
		if( hm == null) 
			throw new IllegalArgumentException();
	    else if ( a == null) 
			return;
		
		for(int i = 0; i < a.length; i++) {
			if(hm.containsKey(a[i])) {
				Integer v = (Integer) hm.get(a[i]);
				hm.replace(a[i], v - 1);
			}
		}
	}
	
	public static void printResult(HashMap<Integer, Integer> hm) {
		if( hm == null ) 
			throw new IllegalArgumentException();
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(Map.Entry<Integer, Integer> e : hm.entrySet()){
			int v = e.getValue();
			for(int i = 0; i < v; i++) 
				al.add(e.getKey());
		}
		
		al.sort(null);
		
		Iterator<Integer> it = al.iterator();
		while(it.hasNext()) 
			System.out.print(it.next() + " ");
	}
}
