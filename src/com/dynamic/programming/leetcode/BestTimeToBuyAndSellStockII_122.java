package com.dynamic.programming.leetcode;

/**
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the ith day.
 * 
 * On each day, you may decide to buy and/or sell the stock. You can only hold
 * at most one share of the stock at any time. However, you can buy it then
 * immediately sell it on the same day.
 * 
 * Find and return the maximum profit you can achieve.
 *
		Example 1:
		
		Input: prices = [7,1,5,3,6,4]
		Output: 7
		Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
		Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
		Total profit is 4 + 3 = 7.
		
		Example 2:
		
		Input: prices = [1,2,3,4,5]
		Output: 4
		Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
		Total profit is 4.
		
		
		Example 3:
		
		Input: prices = [7,6,4,3,1]
		Output: 0
		Explanation: There is no way to make a positive profit, so we never buy the stock to 
		achieve the maximum profit of 0.

 * 
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/1569081/Java-Simple-and-Clean-DP-solutions-for-all-6-Buy-and-Sell-Stock-questions-on-LeetCode
 * 
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/2658084/122.-Best-Time-to-Buy-and-Sell-Stock-II-Easiest-approach-4-line-code.
 * 
 * 
 */
public class BestTimeToBuyAndSellStockII_122 {
	public int maxProfit(int[] prices) {
		int profit = 0;
		for(int i=1;i<prices.length;i++)
	    {
	        if(prices[i] > prices[i-1])
	        {
	            profit += prices[i] - prices[i-1];
	        }
	    }
	    return profit;

	}

	public static void main(String[] args) {
		BestTimeToBuyAndSellStockII_122 bestTimeToBuyAndSellStock_121 = new BestTimeToBuyAndSellStockII_122();
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		int profit = bestTimeToBuyAndSellStock_121.maxProfit(prices);
		System.out.println(profit);
	}
}
