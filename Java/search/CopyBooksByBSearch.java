package search;

/**
 *  @author jhzhu@outlook.com
 *  
 *  Given n books and the i-th book has A[i] pages. You are given k people to copy the n books.
 *  n books list in a row and each person can claim a continous range of n books. 
 *  For example one copier can copy the books from i-th to j-th continously, 
 *  but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).
 *  
 *  They start copying books at same time and they all cost 1 minute to copy 1 page. 
 *  What's the best strategy to assign books so that the slowest copier can finish at earliest time?
 *  
 *  Example:
 *  Given array A = [3,2,4], k = 2.
 *  Return 5 (First person spends 5 minutes to copy book 1 and book 2 and 
 *  second person spends 4 minutes to copy book 3. )
 */

// O(n log m) where n is the number of books and m is the sum of the pages.
public class CopyBooksByBSearch {
	/**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages.length == 0) 
            return 0;  
        
        int total = 0, max = pages[0];
        for (int i = 0; i < pages.length; i++) {
            total += pages[i];
            if (max < pages[i]) 
                max = pages[i];
        }
        
        int start = max, end = total;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (countCopiers(pages, mid) > k) 
                start = mid;
            else 
                end = mid;
        }
        
        if (countCopiers(pages, start) <= k) 
            return start;
        
        return end;
    }
    
    private int countCopiers(int[] pages, int limit) {
        if (pages.length == 0) 
            return 0;
        
        int copiers = 1, sum = pages[0]; // limit is always >= pages[0]
        for (int i = 1; i < pages.length; i++) {
            if (sum + pages[i] > limit) {
                copiers++;
                sum = 0;
            }
            sum += pages[i];
        }
        
        return copiers;
    }
}
