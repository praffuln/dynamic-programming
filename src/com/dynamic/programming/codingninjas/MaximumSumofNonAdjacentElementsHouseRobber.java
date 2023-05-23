package com.dynamic.programming.codingninjas;

import java.util.Arrays;

/**
 * Given an array arr[] of positive numbers, The task is to find the maximum sum
 * of a subsequence such that no 2 numbers in the sequence should be adjacent in
 * the array.
 * 
 * Examples:
 *
		Input: arr[] = {5, 5, 10, 100, 10, 5}
		Output: 110
		Explanation: Pick the subsequence {5, 100, 5}.
		The sum is 110 and no two elements are adjacent. This is the highest possible sum.
		
		Input: arr[] = {3, 2, 7, 10}
		Output: 13
		Explanation: The subsequence is {3, 10}. This gives sum = 13.
		This is the highest possible sum of a subsequence following the given criteria
 *
 * 
 */
public class MaximumSumofNonAdjacentElementsHouseRobber {
	
	/*
	 * Recursion
	 *  TC -> O(2 power n)
	 *  SC -> O(N)
	 */
	static int maximumSumRecurssion(int index, int[] arr) {
		if(index == 0) return arr[0];
		if(index < 0) return 0;
		int select = arr[index] + maximumSumRecurssion(index-2, arr);
		int notSelect = 0 + maximumSumRecurssion(index-1, arr);
		return Math.max(select, notSelect);
	}

	/**
	 * 	/*
	 * Recursion
	 *  TC -> O(2 power n)
	 *  SC -> O(N) + O(N)
	 */
	private static int maximumSumRecurssionDP(int index, int[] arr, int[] dp) {
		if(index == 0) return arr[0];
		if(index < 0) return 0;
		if(dp[index] != -1) return dp[index]; //check dp
		int select = arr[index] + maximumSumRecurssion(index-2, arr);
		int notSelect = 0 + maximumSumRecurssion(index-1, arr);
		return dp[index] = Math.max(select, notSelect); //store dp
	}
	
	/*
	 * TC -> O(N)
	 * SC -> O(N)
	 */
	private static int maximumSumDP(int[] arr, int[] dp) {
		dp[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int notSelect = 0 + dp[i - 1];
			int select = Integer.MIN_VALUE;
			if (i - 2 >= 0) {
				select = arr[i] + dp[i - 2];
			}
			dp[i] = Math.max(select, notSelect);
		}
		return dp[arr.length - 1];
	}
	
	/**
	 * TC -> O(N) 
	 * SC -> O(1)
	 */
	private static int maximumSumDPSpaceOptimization(int[] arr, int[] dp) {
		int previous = arr[0];
		int previous_previous = 0;
		for (int i = 1; i < arr.length; i++) {
			int notSelect = 0 + previous;
			int select = arr[i] + previous_previous;

			int current = Math.max(select, notSelect);
			previous_previous = previous;
			previous = current;
		}
		return previous;
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 2, 7, 10};
		int size = arr.length-1;
		int maximumSum = maximumSumRecurssion(size, arr);
		System.out.println("maximumSum maximumSumRecurssion - " + maximumSum);
		
		
		int[] dp = new int[size+1];
		Arrays.fill(dp, -1);
		maximumSum = maximumSumRecurssionDP(size, arr, dp);
		System.out.println("maximumSum maximumSumRecurssion - " + maximumSum);
		
		dp = new int[size+1];
		maximumSum = maximumSumDP(arr, dp);
		System.out.println("maximumSum maximumSumRecurssion - " + maximumSum);
		
		
		dp = new int[size+1];
		maximumSum = maximumSumDPSpaceOptimization(arr, dp);
		System.out.println("maximumSum maximumSumRecurssion - " + maximumSum);
	}


}
