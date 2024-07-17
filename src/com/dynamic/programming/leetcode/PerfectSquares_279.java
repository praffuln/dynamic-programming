package com.dynamic.programming.leetcode;

import java.util.Arrays;

public class PerfectSquares_279 {

	public static int numSquares(int n) {
		return numSquareHelper(1, n);
	}

	private static int numSquareHelper(int i, int n) {
		System.out.println(i + "  " + n);

		if (n == 0)
			return 0;

		if (i > n)
			return 1000000;

		int select = 1 + numSquareHelper(i, n - (i * i));
		System.out.println("select - " + select);
		int notSelect = 0 + numSquareHelper(i + 1, n);
		System.out.println("notSelect - " + notSelect);
		return Math.min(select, notSelect);
	}

	public static void main(String[] args) {
		int answer = numSquares(12);
		System.out.println(answer);

		answer = numSquaresDp(12);
		System.out.println(answer);

		answer = numSquaresMemoryOptimize(12);
		System.out.println(answer);
	}

	private static int numSquaresDp(int n) {
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// Initialize base cases
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (j >= i * i) {
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - i * i] + 1);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		printDP(dp);
		return dp[n][n];
	}

	private static void printDP(int[][] dp) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "  ");
			}
			System.out.println("\n");
		}
	}

	public static int numSquaresMemoryOptimize(int n) {

		int[] prev = new int[n + 1];
		int[] curr = new int[n + 1];

		Arrays.fill(prev, Integer.MAX_VALUE);
		prev[0] = 0;

		for (int i = 1; i <= n; i++) {
			curr = new int[n + 1];
			for (int j = 1; j <= n; j++) {
				if (j >= i * i) {
					curr[j] = Math.min(prev[j], curr[j - i * i] + 1);
				} else {
					curr[j] = prev[j];
				}
			}
			prev = curr;
		}

		return prev[n];

	}

}
