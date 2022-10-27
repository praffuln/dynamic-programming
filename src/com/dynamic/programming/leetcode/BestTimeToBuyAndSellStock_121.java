package com.dynamic.programming.leetcode;

/**
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day.
 * 
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you
 * cannot achieve any profit, return 0.
 *
 * 
		Example 1:
		
		Input: prices = [7,1,5,3,6,4]
		Output: 5
		Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
		Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
		Example 2:
		
		Input: prices = [7,6,4,3,1]
		Output: 0
		Explanation: In this case, no transactions are done and the max profit = 0.
 * 
 * 
 */
public class BestTimeToBuyAndSellStock_121 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <2)
            return 0;
        int profit=0, minPriceSoFar=prices[0];
        
        for(int i=1; i<prices.length;i++) {
           profit = Math.max(profit, prices[i]-minPriceSoFar);
           minPriceSoFar = Math.min(minPriceSoFar, prices[i]);
        }
        
        return profit;
    }

    
    public static void main(String[] args) {
    	BestTimeToBuyAndSellStock_121 bestTimeToBuyAndSellStock_121 = new BestTimeToBuyAndSellStock_121();
    	int[] prices = {7,1,5,3,6,4};
		int profit = bestTimeToBuyAndSellStock_121.maxProfit(prices );
		System.out.println(profit);
    }
}
