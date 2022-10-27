package com.dynamic.programming.leetcode;

/**
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day, and an integer fee representing a transaction fee.
 * 
 * Find the maximum profit you can achieve. You may complete as many
 * transactions as you like, but you need to pay the transaction fee for each
 * transaction.
 * 
 * Note: You may not engage in multiple transactions simultaneously (i.e., you
 * must sell the stock before you buy again).
 *
 * 	Example 1:
	
	Input: prices = [1,3,2,8,4,9], fee = 2
	Output: 8
	Explanation: The maximum profit can be achieved by:
	- Buying at prices[0] = 1
	- Selling at prices[3] = 8
	- Buying at prices[4] = 4
	- Selling at prices[5] = 9
	The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
	Example 2:
	
	Input: prices = [1,3,7,5,10,3], fee = 3
	Output: 6
 * 
 * 
 * 
 * 
 */
public class BestTimetoBuyAndSellStockWithTransactionFee_714 {
	
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int buy = -prices[0], sell = 0;
        
        for (int i = 1; i < len; i++) {
            int tmpbuy = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, tmpbuy + prices[i] - fee);
        }
        
        return Math.max(buy, sell);
    }

	
    public int maxProfitDP(int[] prices, int fee) {
        if(prices == null || prices.length ==0)
            return 0;
        
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        
        buy[0] = prices[0]*-1;
        sell[0] = 0;
        
        for(int i=1; i<prices.length; i++){
            buy[i] = Math.max(buy[i-1], sell[i-1]-prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1]+prices[i]-fee); 
        }
        
        return sell[prices.length-1];
    }    

	public int maxProfitWithOtherApproach(int[] prices, int fee) {
		int n = prices.length;
		if (n < 2) {
			return 0;
		}
		int ans = 0;
		int minimum = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < minimum)
				minimum = prices[i];
			else if (prices[i] > minimum + fee) {
				ans += prices[i] - fee - minimum;
				minimum = prices[i] - fee;
			}
		}
		return ans;
	}
    
	public static void main(String[] args) {
		BestTimetoBuyAndSellStockWithTransactionFee_714 bestTimetoBuyAndSellStockWithTransactionFee_714 = new BestTimetoBuyAndSellStockWithTransactionFee_714();
		int[] prices = {1,3,2,8,4,9}; 
		int fee = 2;
		int profit = bestTimetoBuyAndSellStockWithTransactionFee_714.maxProfit(prices, fee);
		System.out.println(profit);
		profit = bestTimetoBuyAndSellStockWithTransactionFee_714.maxProfitDP(prices, fee);
		System.out.println(profit);
		profit = bestTimetoBuyAndSellStockWithTransactionFee_714.maxProfitWithOtherApproach(prices, fee);
		System.out.println(profit);
		
		System.out.println("\n\n");
		
		int[] prices2 = {1,3,7,5,10,3};
	    fee = 3;
		int profit2 = bestTimetoBuyAndSellStockWithTransactionFee_714.maxProfit(prices2, fee);
		System.out.println(profit2);
		profit2 = bestTimetoBuyAndSellStockWithTransactionFee_714.maxProfitDP(prices2, fee);
		System.out.println(profit2);
		profit2 = bestTimetoBuyAndSellStockWithTransactionFee_714.maxProfitWithOtherApproach(prices2, fee);
		System.out.println(profit2);
	}
}
