package com.dynamic.programming.pepcoding.level1;

/**
 * 1. You are given a number n, representing the number of days.
 * 
 * 2. You are given n numbers, where ith number represents price of stock on ith
 * day.
 * 
 * 3. You are required to print the maximum profit you can make if you are
 * allowed  2 transactions.
 *
 * 
 */
public class BestTimeToBuyAndSellStock_34 {
	public static void main(String[] args) {
		int[] arr = {3,3,5,0,0,3,1,4};
		int cap= 2;

		BestTimeToBuyAndSellStock_34 obj = new BestTimeToBuyAndSellStock_34();
		obj.bestTimeToBuyAndSellStock(arr, cap);
	}

	private void bestTimeToBuyAndSellStock(int[] arr, int cap) {
		int profit = bestTimeToBuyAndSellStockRecursionUtil(0, 1,cap, arr);
		System.out.println("profit by recursion - " + profit);

		int[][][] dp = get3Dp(arr);
		profit = bestTimeToBuyAndSellStockRecursionDpUtil(0, 1, cap, arr, dp);
		System.out.println("profit by RecursionDp - " + profit);

		profit = bestTimeToBuyAndSellStockDpUtil(0, 1, cap, arr);
		System.out.println("profit by Dp - " + profit);

		profit = bestTimeToBuyAndSellStockDpOptimizedUtil(0, 1, cap, arr);
		System.out.println("profit by DpOptimized - " + profit);
		
		profit = bestTimeToBuyAndSellStockDpNCrossFourUtil(0,0,arr);
		System.out.println("profit by NCrossFour - " + profit);
	}

	private int bestTimeToBuyAndSellStockDpNCrossFourUtil(int index, int transaction, int[] arr) {
		if(index == arr.length || transaction ==4)
			return 0;
		int profit = 0;
		if(transaction%2 == 0) { //buy
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockDpNCrossFourUtil(index + 1, transaction+1, arr),
					0 + bestTimeToBuyAndSellStockDpNCrossFourUtil(index + 1, transaction, arr));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockDpNCrossFourUtil(index + 1, transaction +1, arr),
					0 + bestTimeToBuyAndSellStockDpNCrossFourUtil(index + 1, transaction, arr));
		}
		
		return profit;
	}

	private int bestTimeToBuyAndSellStockRecursionUtil(int index, int buy, int cap, int[] arr) {
		if (arr.length == index)
			return 0;
		if (cap == 0)
			return 0;

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, cap, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, cap, arr));
		} else {
			// cap changes
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, cap - 1, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, cap, arr));
		}

		return profit;
	}

	private int bestTimeToBuyAndSellStockRecursionDpUtil(int index, int buy, int cap, int[] arr, int[][][] dp) {
		if (arr.length == index || cap == 0)
			return 0;

		if (dp[index][buy][cap] != -1) {
			return dp[index][buy][cap];
		}

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 0, cap, arr, dp),
					0 + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 1, cap, arr, dp));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 1, cap-1, arr, dp),
					0 + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 0, cap, arr, dp));
		}

		return dp[index][buy][cap] = profit;
	}

	private int bestTimeToBuyAndSellStockDpUtil(int i, int j, int cap, int[] arr) {
		int n = arr.length;
		int[][][] dp = new int[n+1][2][cap+1];
		

		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				for (int capIndex = 1; capIndex <=cap; capIndex++) {
					int profit = 0;
					if (buy == 1) {
						profit = Integer.max(-arr[index] +dp[index + 1][0][capIndex],
								0 + dp[index + 1][1][capIndex]);
					} else {
						profit = Integer.max(arr[index] + dp[index + 1][1][capIndex-1],
								0 + dp[index + 1][0][capIndex]);
					}

					dp[index][buy][capIndex] = profit;
				}
			}
		}
		return dp[0][1][2];
	}

	private int bestTimeToBuyAndSellStockDpOptimizedUtil(int i, int j, int cap, int[] arr) {

		int n = arr.length;
		int[][] ahead = new int[2][cap+1];
		int[][] curr = new int[2][cap+1];

		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				for (int capIndex = 1; capIndex <=cap; capIndex++) {
					int profit = 0;
					if (buy == 1) {
						profit = Integer.max(-arr[index] +ahead[0][capIndex],
								0 + ahead[1][capIndex]);
					} else {
						profit = Integer.max(arr[index] + ahead[1][capIndex-1],
								0 + ahead[0][capIndex]);
					}

					curr[buy][capIndex] = profit;
				}
			}
			ahead = curr;
		}
		return ahead[1][2];
	
	}
	
	private int[][][] get3Dp(int[] arr) {
		// N --> index (0 to n-1)
		// buy --> 0/1
		// cap --> 0/1/2
		int[][][] dp = new int[arr.length][2][3];
		 for(int i=0; i<arr.length; i++) {
			 for(int j=0; j<=1; j++) {
				 for(int k=0; k<3; k++) {
					 dp[i][j][k] = -1;
				 }
			 }
		 }
		return dp;
	}

	 
}
