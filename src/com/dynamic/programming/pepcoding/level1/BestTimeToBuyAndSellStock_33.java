package com.dynamic.programming.pepcoding.level1;

import java.util.Arrays;

/**
 * 1. You are given a number n, representing the number of days.
 * 
 * 2. You are given n numbers, where ith number represents price of stock on ith
 * day.
 * 
 * 3. You are required to print the maximum profit you can make if you are
 * allowed infinite transactions.
 * 
 * Note - There can be no overlapping transaction. One transaction needs to be
 * closed (a buy followed by a sell) before opening another transaction (another
 * buy)
 * 
 * 
 */
public class BestTimeToBuyAndSellStock_33 {
	public static void main(String[] args) {
		int[] arr = {1,3,2,8,4,9};
		int transactionFees = 2;
				
		BestTimeToBuyAndSellStock_33 obj = new BestTimeToBuyAndSellStock_33();
		obj.bestTimeToBuyAndSellStock(arr, transactionFees);
	}

	private void bestTimeToBuyAndSellStock(int[] arr, int transactionFees) {
		int profit = bestTimeToBuyAndSellStockRecursionUtil(0, 1, transactionFees, arr);
		System.out.println("profit by recursion - " + profit);

		int[][] dp = getDp(arr);
		profit = bestTimeToBuyAndSellStockRecursionDpUtil(0, 1, transactionFees, arr, dp);
		System.out.println("profit by RecursionDp - " + profit);

		profit = bestTimeToBuyAndSellStockDpUtil(0, 1, transactionFees, arr);
		System.out.println("profit by Dp - " + profit);

		profit = bestTimeToBuyAndSellStockDpOptimizedUtil(0, 1, transactionFees, arr);
		System.out.println("profit by DpOptimized - " + profit);
	}

	private int bestTimeToBuyAndSellStockRecursionUtil(int index, int buy, int transactionFees, int[] arr) {
		if (arr.length == index)
			return 0;

		int profit = 0;
		if (buy == 1) {
			// pay transaction fees while buying or selling whereever.
			profit = Integer.max(-arr[index] -transactionFees + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, transactionFees, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, transactionFees, arr));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, transactionFees, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, transactionFees, arr));
		}

		return profit;
	}

	private int bestTimeToBuyAndSellStockRecursionDpUtil(int index, int buy, int transactionFees, int[] arr, int[][] dp) {
		if (arr.length == index)
			return 0;

		if (dp[index][buy] != -1) {
			return dp[index][buy];
		}

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] - transactionFees + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 0,transactionFees, arr, dp),
					0 + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 1, transactionFees, arr, dp));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 1, transactionFees, arr, dp),
					0 + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 0, transactionFees, arr, dp));
		}

		return dp[index][buy] = profit;
	}

	private int bestTimeToBuyAndSellStockDpUtil(int i, int j, int transactionFees, int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n + 1][2];
		dp[n][0] = 0;
		dp[n][1] = 0;

		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				int profit = 0;
				if (buy == 1) {
					profit = Integer.max(-arr[index] -transactionFees + dp[index + 1][0], 0 + dp[index + 1][1]);
				} else {
					profit = Integer.max(arr[index] + dp[index + 1][1], 0 + dp[index + 1][0]);
				}

				dp[index][buy] = profit;
			}
		}
		return dp[0][1];
	}

	private int bestTimeToBuyAndSellStockDpOptimizedUtil(int i, int j, int transactionFees, int[] arr) {
		int n = arr.length;
		int[] ahead = new int[2];
		int[] curr = new int[2];
		ahead[0] = 0;
		ahead[1] = 0;

		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				int profit = 0;
				if (buy == 1) {
					profit = Integer.max(-arr[index] + ahead[0], 0 + ahead[1]);
				} else {
					profit = Integer.max(arr[index] -transactionFees + ahead[1], 0 + ahead[0]);
				}

				curr[buy] = profit;
			}
			curr = ahead;
		}
		return curr[1];
	}

	private int[][] getDp(int[] arr) {
		int[][] dp = new int[arr.length][2];
		Arrays.setAll(dp, i -> {
			Arrays.fill(dp[i], -1);
			return dp[i];
		});
		return dp;
	}
}