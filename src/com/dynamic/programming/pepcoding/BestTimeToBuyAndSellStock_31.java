package com.dynamic.programming.pepcoding;

import java.util.Arrays;

/**
 * 1. You are given a number n, representing the number of days.
 * 
 * 2. You are given n numbers, where ith number represents price of stock on ith
 * day.
 * 
 * 3. You are required to print the maximum profit you can make if you are
 * allowed a single transaction.
 *
 * 
 */
public class BestTimeToBuyAndSellStock_31 {
	public static void main(String[] args) {
		int[] arr = { 7, 1, 5, 3, 6, 4 };

		BestTimeToBuyAndSellStock_31 obj = new BestTimeToBuyAndSellStock_31();
		obj.bestTimeToBuyAndSellStock(arr);
	}

	private void bestTimeToBuyAndSellStock(int[] arr) {
		int profit = bestTimeToBuyAndSellStockRecursionUtil(0, 1, arr);
		System.out.println("profit by recursion - " + profit);

		int[][] dp = getDp(arr);
		profit = bestTimeToBuyAndSellStockRecursionDpUtil(0, 1, arr, dp);
		System.out.println("profit by RecursionDp - " + profit);

		profit = bestTimeToBuyAndSellStockDpUtil(0, 1, arr);
		System.out.println("profit by Dp - " + profit);

		profit = bestTimeToBuyAndSellStockDpOptimizedUtil(0, 1, arr);
		System.out.println("profit by DpOptimized - " + profit);
	}

	private int bestTimeToBuyAndSellStockRecursionUtil(int index, int buy, int[] arr) {
		if (arr.length == index)
			return 0;

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, arr));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, arr));
		}

		return profit;
	}

	private int bestTimeToBuyAndSellStockRecursionDpUtil(int index, int buy, int[] arr, int[][] dp) {
		if (arr.length == index)
			return 0;

		if (dp[index][buy] != -1) {
			return dp[index][buy];
		}

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, arr));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, arr));
		}

		return dp[index][buy] = profit;
	}

	private int bestTimeToBuyAndSellStockDpUtil(int i, int j, int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n + 1][2];
		dp[n][0] = 0;
		dp[n][1] = 0;

		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				int profit = 0;
				if (buy == 1) {
					profit = Integer.max(-arr[index] + dp[index + 1][0], 0 + dp[index + 1][1]);
				} else {
					profit = Integer.max(arr[index] + dp[index + 1][1], 0 + dp[index + 1][0]);
				}

				dp[index][buy] = profit;
			}
		}
		return dp[0][1];
	}

	private int bestTimeToBuyAndSellStockDpOptimizedUtil(int i, int j, int[] arr) {
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
					profit = Integer.max(arr[index] + ahead[1], 0 + ahead[0]);
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
