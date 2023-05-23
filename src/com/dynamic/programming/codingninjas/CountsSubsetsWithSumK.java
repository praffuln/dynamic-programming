package com.dynamic.programming.codingninjas;

import java.util.Arrays;

/**
 * We are given an array ‘ARR’ with N positive integers and an integer K. We
 * need to find the number of subsets whose sum is equal to K.
 * 
 * {1,2,3} and sum is 3
 * 
 * answer is 2.
 * 
 * {1,2} {3}
 *
 */
public class CountsSubsetsWithSumK {
	/**
	 * Time Complexity: O(N*K)
	 * 
	 * Space Complexity: O(N*K) + O(N)
	 * 
	 */
	static int findWaysUtilRecursion(int ind, int target, int[] arr, int[][] dp) {
		if (target == 0)
			return 1;

		if (ind == 0)
			return arr[0] == target ? 1 : 0;

		if (dp[ind][target] != -1)
			return dp[ind][target];

		int notTaken = findWaysUtilRecursion(ind - 1, target, arr, dp);

		int taken = 0;
		if (arr[ind] <= target)
			taken = findWaysUtilRecursion(ind - 1, target - arr[ind], arr, dp);

		return dp[ind][target] = notTaken + taken;
	}

	static int findWays(int[] num, int k) {
		int n = num.length;
		int dp[][] = new int[n][k + 1];
		for (int row[] : dp)
			Arrays.fill(row, -1);

		return findWaysUtilRecursion(n - 1, k, num, dp);
	}

	public static void main(String args[]) {

		int arr[] = { 1, 2, 2, 3 };
		int k = 3;

		System.out.println("The number of subsets found are " + findWays(arr, k));

		System.out.println("The number of subsets found are " + findWaysDp(arr, k));

	}

	/**
	 * Time Complexity: O(N*K)
	 * 
	 * Space Complexity: O(N*K)
	 * 
	 */
	static int findWaysDp(int[] num, int k) {
		int n = num.length;

		int[][] dp = new int[n][k + 1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}

		if (num[0] <= k)
			dp[0][num[0]] = 1;

		for (int ind = 1; ind < n; ind++) {
			for (int target = 1; target <= k; target++) {

				int notTaken = dp[ind - 1][target];

				int taken = 0;
				if (num[ind] <= target)
					taken = dp[ind - 1][target - num[ind]];

				dp[ind][target] = notTaken + taken;
			}
		}

		return dp[n - 1][k];
	}
}