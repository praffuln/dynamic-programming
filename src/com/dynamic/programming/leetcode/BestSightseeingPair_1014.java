package com.dynamic.programming.leetcode;

import java.util.Arrays;

/**
 * You are given an integer array values where values[i] represents the value of
 * the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i
 * between them.
 * 
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i
 * - j: the sum of the values of the sightseeing spots, minus the distance
 * between them.
 * 
 * Return the maximum score of a pair of sightseeing spots.
 *
 * https://leetcode.com/problems/best-sightseeing-pair/discuss/2441083/Java-or-2-methods-or-Explained
 * 
 * Aim is to maximize MS = values[i] + values[j] + i - j for i < j, which can be
 * rewritten as S + D where S = values[i] + i, D = values[j] - j, MS = max score
 * 
 * So if we maximize both S and D, then MS will be maximized
 * 
 * Use a 1D table to store the maximum value of S seen till any index. Then use
 * this max value to maximize D, thereforce MS
 * 
 */
public class BestSightseeingPair_1014 {
	public static int maxScoreSightseeingPair(int[] values) {
		int maxScore = 0;
		int n = values.length;
		int[] dp = new int[n];
		dp[0] = values[0];

		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], values[i] + i);
			maxScore = Math.max(maxScore, dp[i - 1] + values[i] - i);
		}

		System.out.println(Arrays.toString(dp));

		return maxScore;

	}

	public static void main(String[] args) {
		int[] arr = { 8, 1, 5, 2, 6 };
		System.out.println(maxScoreSightseeingPair(arr));
	}
}
