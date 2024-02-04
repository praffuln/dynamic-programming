package com.dynamic.programming.pepcoding;

/**
 * 1. You are given a number n, representing the count of elements.
 * 
 * 2. You are given n numbers, representing n elements.
 * 
 * 3. You are required to find the maximum sum of a subsequence with no adjacent
 * elements.
 *
 * 
 */
public class MaximumSumNonAdjacentElements_22 {

	public static void main(String[] args) {
		MaximumSumNonAdjacentElements_22 obj = new MaximumSumNonAdjacentElements_22();
		obj.maximumSumNonAdjacentElements();
	}

	private void maximumSumNonAdjacentElements() {
		int[] arr = { 5, 10, 10, 100, 5, 6 };
		int result = maximumSumNonAdjacentElementsUtil(arr, arr.length - 1);
		System.out.println("recursion - " + result);

		Integer[] dp = new Integer[arr.length];
		// base case
		dp[0] = arr[0];
		dp[1] = arr[1];

		for (int index = 2; index <= arr.length - 1; index++) {
			// if selected/notselected
			int selected = arr[index] + dp[index - 2];

			int notSelected = 0 + dp[index - 1];

			dp[index] = Math.max(selected, notSelected);
		}
		System.out.println("dp - " + dp[arr.length - 1]);
	}

	private int maximumSumNonAdjacentElementsUtil(int[] arr, int index) {
		// base case
		if (index == 0 || index == 1) {
			return arr[index];
		}

		// if selected/notselected
		int selected = arr[index] + maximumSumNonAdjacentElementsUtil(arr, index - 2);

		int notSelected = 0 + maximumSumNonAdjacentElementsUtil(arr, index - 1);

		return Math.max(selected, notSelected);
	}
}
