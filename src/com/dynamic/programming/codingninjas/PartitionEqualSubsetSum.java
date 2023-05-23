package com.dynamic.programming.codingninjas;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 * 
 * Given an integer array nums, return true if you can partition the array into
 * two subsets such that the sum of the elements in both subsets is equal or
 * false otherwise.
 *
 * 
 * Example 1:
		Input: nums = [1,5,11,5]
		Output: true
		Explanation: The array can be partitioned as [1, 5, 5] and [11].
		Example 2:
		
		Input: nums = [1,2,3,5]
		Output: false
		Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 *  Question states that : can we get a subset of sum = Total/2.
 */
public class PartitionEqualSubsetSum {

	// Overlapping subProblem.
	public static void main(String[] args) {
		int[] set = {1,5,11,5};
		int index = set.length;
		int sum = Arrays.stream(set).sum();
		//if sum is false then false
		if(sum%2 != 0) {
			System.out.println(false); // false
			return;
		}
		int target = sum/2;
		System.out.println(subsetSumEqualsToTargetRecurssion(set, target)); // true
		int[][] dp = new int[index + 1][target + 1];
		System.out.println(subsetSumEqualsToTargetRecurssionMemorization(dp, set, target)); // true

		System.out.println(subsetSumEqualsToTargetDp(set, target)); // true
		System.out.println(subsetSumEqualsToTargetDpSpaceOptimization(set, target));
	}

	/**
	 * Time Complexity - O(N*target) Space Complexity - O(N*target) + O(N)
	 */
	private static boolean subsetSumEqualsToTargetRecurssionMemorization(int[][] dp, int[] set, int target) {
		return subsetSumEqualsToTargetRecurssionMemorizationUtil(dp, set, set.length - 1, target);
	}

	private static boolean subsetSumEqualsToTargetRecurssionMemorizationUtil(int[][] dp, int[] set, int index,
			int target) {

		if (target == set[index])
			return true;

		if (index == 0)
			return set[index] == target;

		if (dp[index][target] != 0)
			return dp[index][target] == 1 ? true : false;

		boolean notTake = subsetSumEqualsToTargetRecurssionUtil(set, index - 1, target);
		boolean take = false;
		if (set[index] <= target)
			take = subsetSumEqualsToTargetRecurssionUtil(set, index - 1, target - set[index]);
		dp[index][target] = (notTake || take) ? 1 : 2;
		return notTake || take;

	}

	/**
	 * Time Complexity = 2(N) Space complexity = O(N)
	 */
	private static boolean subsetSumEqualsToTargetRecurssion(int[] set, int target) {

		return subsetSumEqualsToTargetRecurssionUtil(set, set.length - 1, target);
	}

	private static boolean subsetSumEqualsToTargetRecurssionUtil(int[] set, int index, int target) {
		if (target == set[index])
			return true;

		if (index == 0)
			return set[index] == target;

		boolean notTake = subsetSumEqualsToTargetRecurssionUtil(set, index - 1, target);
		boolean take = false;
		if (set[index] <= target)
			take = subsetSumEqualsToTargetRecurssionUtil(set, index - 1, target - set[index]);

		return notTake || take;
	}

	/**
	 * Time Complexity - O(N*target) Space Complexity - O(N*target)
	 */
	private static boolean subsetSumEqualsToTargetDp(int[] arr, int k) {
		int n = arr.length;
		boolean dp[][] = new boolean[n][k + 1];

		// If target == 0, ind can take any value from 0 to n-1,
		// therefore we need to set the value of the first column as true.
		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}

		if (arr[0] <= k)
			dp[0][arr[0]] = true;

		for (int ind = 1; ind < n; ind++) {
			for (int target = 1; target <= k; target++) {

				boolean notTaken = dp[ind - 1][target];

				boolean taken = false;
				if (arr[ind] <= target)
					taken = dp[ind - 1][target - arr[ind]];

				dp[ind][target] = notTaken || taken;
			}
		}

		return dp[n - 1][k];
	}

	/**
	 * Time Complexity - O(N*target) Space Complexity - O(N)
	 */
	static boolean subsetSumEqualsToTargetDpSpaceOptimization(int[] arr, int k) {
		int n = arr.length;
		boolean prev[] = new boolean[k + 1];

		prev[0] = true;

		if (arr[0] <= k)
			prev[arr[0]] = true;

		for (int ind = 1; ind < n; ind++) {
			boolean cur[] = new boolean[k + 1];
			cur[0] = true;
			for (int target = 1; target <= k; target++) {
				boolean notTaken = prev[target];

				boolean taken = false;
				if (arr[ind] <= target)
					taken = prev[target - arr[ind]];

				cur[target] = notTaken || taken;
			}
			prev = cur;
		}
		return prev[k];
	}
}
