package com.dynamic.programming.pepcoding;

/**
 * 1. You are given a number n, representing the number of days.
 * 
 * 2. You are given n numbers, where ith number represents price of stock on ith
 * day.
 * 
 * 
 * 3. You are required to print the maximum profit you can make if you are
 * allowed infinite transactions, but have to cooldown for 1 day after 1
 * transaction
 * 
 * i.e. you cannot buy on the next day after you sell, you have to cooldown for
 * a day at-least before buying again.
 *
 * 
 * 
 */
public class BestTimeToBuyAndSellStock_32 {
	public static void main(String[] args) {
		//int[] arr = { 3, 3, 5, 0, 0, 3, 1, 4 };
		int[] arr = {1,2,3,0,2};

		BestTimeToBuyAndSellStock_32 obj = new BestTimeToBuyAndSellStock_32();
		obj.bestTimeToBuyAndSellStock(arr);
	}

	private void bestTimeToBuyAndSellStock(int[] arr) {
		int profit = bestTimeToBuyAndSellStockRecursionUtil(0, 1, arr);
		System.out.println("profit by recursion - " + profit);

		int[][] dp = get2Dp(arr);
		profit = bestTimeToBuyAndSellStockRecursionDpUtil(0, 1, arr, dp);
		System.out.println("profit by RecursionDp - " + profit);
		
		 profit = bestTimeToBuyAndSellStockDpUtil(0, 1, arr);
		 System.out.println("profit by Dp - " + profit);
		
		 profit = bestTimeToBuyAndSellStockDpOptimizedUtil(0, 1, arr);
		 System.out.println("profit by DpOptimized - " + profit);
 
	}

	 

	private int bestTimeToBuyAndSellStockRecursionUtil(int index, int buy, int[] arr) {
		if (index >= arr.length)
			return 0;

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 1, arr));
		} else {
			// cap changes [Cool down] [index + 2 for next buy]
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionUtil(index + 2, 1, arr),
					0 + bestTimeToBuyAndSellStockRecursionUtil(index + 1, 0, arr));
		}

		return profit;
	}

	private int bestTimeToBuyAndSellStockRecursionDpUtil(int index, int buy, int[] arr, int[][] dp) {
		if (arr.length <= index)
			return 0;

		if (dp[index][buy] != -1) {
			return dp[index][buy];
		}

		int profit = 0;
		if (buy == 1) {
			profit = Integer.max(-arr[index] + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 0, arr, dp),
					0 + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 1, arr, dp));
		} else {
			profit = Integer.max(arr[index] + bestTimeToBuyAndSellStockRecursionDpUtil(index + 2, 1, arr, dp),
					0 + bestTimeToBuyAndSellStockRecursionDpUtil(index + 1, 0, arr, dp));
		}

		return dp[index][buy] = profit;
	}

	private int bestTimeToBuyAndSellStockDpUtil(int i, int j, int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n+2][2];

		for (int index = n-1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {

				int profit = 0;
				if (buy == 1) {
					profit = Integer.max(-arr[index] + dp[index + 1][0], 0 + dp[index + 1][1]);
				} else {
					profit = Integer.max(arr[index] + dp[index + 2][1], 0 + dp[index + 1][0]);
				}
				dp[index][buy] = profit;
			}
		}
		return dp[0][1];
	}

	//same code but not working
	private int bestTimeToBuyAndSellStockDpOptimizedUtil(int i, int j, int[] arr) {
		int n = arr.length;
		int[] front2 = new int[2];
		int[] front1 = new int[2];
		int[] current = new int[2];
		

		for (int index = n - 1; index >= 0; index--) {
			current[1] = Integer.max(-arr[index] + front1[0], 
									0 + front1[1]);
			current[0] = Integer.max(arr[index] + front2[1], 
								0 + front1[0]);
			front2 = front1;
			front1 = current;
		}
		return current[1];
	}

	private int[][] get2Dp(int[] arr) {
		// N --> index (0 to n-1)
		// buy --> 0/1
		int[][] dp = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j <= 1; j++) {
				dp[i][j] = -1;
			}
		}
		return dp;
	}
}