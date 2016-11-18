package Arrays;

/**
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *  
 *  Have an array for which the ith element is the price of stock on day i.
 *  If only permitted to complete at most one transaction 
 *  (buy one and sell one share of the stock), 
 *  design an algorithm to find the maximum profit.
 *  
 *  Example 1:
 *  Input: [7, 1, 5, 3, 6, 4] Output: 5
 *  max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 *  
 *  Example 2:
 *  Input: [7, 6, 4, 3, 1] Output: 0
 *  In this case, no transaction is done, i.e. max profit = 0.
 *
 */
public class BuySellStock1 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        
        int maxProfit = 0;
        int curMin = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            curMin = Math.min(curMin, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - curMin);
        }
        
        return maxProfit;
    }
}
