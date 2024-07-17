package com.dynamic.programming.codingninjas;

import java.util.Arrays;

/**
 * 
 * https://leetcode.com/problems/delete-node-in-a-bst/ --> karna h.
 * 
 * Now, let us discuss the Longest Increasing Subsequence (LIS) problem as an
 * example problem that can be solved using Dynamic Programming.
 * 
 * The Longest Increasing Subsequence (LIS) problem is to find the length of the
 * longest subsequence of a given sequence such that all elements of the
 * subsequence are sorted in increasing order. For example, the length of LIS
 * for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60,
 * 80}.
 * 
 * Input: arr[] = {3, 10, 2, 1, 20} Output: Length of LIS = 3 The longest
 * increasing subsequence is 3, 10, 20
 * 
 * Input: arr[] = {3, 2} Output: Length of LIS = 1 The longest increasing
 * subsequences are {3} and {2}
 * 
 * Input: arr[] = {50, 3, 10, 7, 40, 80} Output: Length of LIS = 4 The longest
 * increasing subsequence is {3, 7, 40, 80}
 * 
 * Input : arr[] = {3, 10, 2, 11} f(i): Denotes LIS of subarray ending at index
 * 'i'
 * 
 * (LIS(1)=1)
 * 
 * f(4) {f(4) = 1 + max(f(1), f(2), f(3))} / | \ f(1) f(2) f(3) {f(3) = 1, f(2)
 * and f(1) are > f(3)} | | \ f(1) f(2) f(1) {f(2) = 1 + max(f(1)} | f(1) {f(1)
 * = 1}
 *
 * We can see that there are many subproblems in the above recursive solution
 * which are solved again and again. So this problem has Overlapping
 * Substructure property and recomputation of same subproblems can be avoided by
 * either using Memoization or Tabulation.
 * 
 * The simulation of approach will make things clear: Input : arr[] = {3, 10, 2,
 * 11} LIS[] = {1, 1, 1, 1} (initially)
 * 
 */
class LongestIncreasingSubsequence {
	/*
	 * lis() returns the length of the longest increasing subsequence in arr[]
	 * of size n
	 */
	static int lis(int arr[], int length) {
		int max = lisRecursion(0, -1, arr);
		System.out.println("Length of lis is " + max + "\n");
		int[][] dp = new int[arr.length][arr.length+1];
		fillDp(dp);
		max = lisRecursionDpRecursion(0, -1, arr, dp);
		System.out.println("Length of lis is " + max + "\n");
		max = lisDpIteration(arr);
		System.out.println("Length of lis is " + max + "\n");
		max = lisDpIterationSpaceOptimization(arr);
		System.out.println("Length of lis is " + max + "\n");
		//optimized way
		max = lisDpIterationWithOneDimensionArray(arr);
		System.out.println("Length of lis is " + max + "\n");
		printDpIterationWithOneDimensionArray(arr);

		return max;
	}

	private static int lisDpIterationWithOneDimensionArray(int[] arr) {
		int dp[] = new int[arr.length];
		Arrays.fill(dp, 1); //initialize dp by 1
		int max = Integer.MIN_VALUE;
		for(int i=1; i < arr.length; i++) {
			for(int j=0; j< i; j++) {
				if(arr[j] < arr[i]) {
					max = Math.max(max, 1 + dp[j]);
					dp[i] = max;
				}
			}
		}
		return max;
	}
	
	private static void printDpIterationWithOneDimensionArray(int[] arr) {

		int dp[] = new int[arr.length];
		Arrays.fill(dp, 1); //initialize dp by 1
		int hash[] = new int[arr.length];
		int max = Integer.MIN_VALUE;
		int maxIndex = 0;
		for(int i=1; i < arr.length; i++) {
			for(int previous=0; previous< i; previous++) {
				if(arr[previous] < arr[i] && 1 + dp[previous] > dp[i]) {
					 dp[i] = 1 + dp[previous];
					//whenever calculating max fill hash[] and maxIndex
					hash[i] =previous;
				}
				
				if(dp[i] > max) {
					max = dp[i];
					maxIndex = i;
				}
			}
		}
		System.out.println("maxIndex is = "+ maxIndex);
		System.out.println("max index Array is " + Arrays.toString(hash));
	//TODO: fix me for other cases.s	
		int index = maxIndex;
		while(index != 0) {
			System.out.println(" " + arr[index]);
			index = hash[index];
		}
		if(arr[0] < arr[1]) {
			System.out.println(" " + arr[0]);
		}
	}

	private static int lisDpIteration(int[] arr) {
		int[][] dp = new int[arr.length+1][arr.length+1];
		for (int index =arr.length-1; index >=0; index--) {
			for (int previousIndex = index-1; previousIndex >=-1; previousIndex--) {
				 //copy the recurrence and shift the index
				int notPick = 0 + dp[index + 1][previousIndex+1];
				// pick
				int pick = 0;
				if (previousIndex == -1 || arr[previousIndex] < arr[index]) {
					pick = 1 + dp[index + 1][index+1];
				}
				dp[index][previousIndex+1] = Math.max(pick, notPick);
			}
		}
		printTable(dp);
		return dp[0][-1+1];
	}

	private static int lisDpIterationSpaceOptimization(int[] arr) {
		//int[][] dp = new int[arr.length+1][arr.length+1];
		int[] curr = new int[arr.length+1];
		int[] next = new int[arr.length +1];
		for (int index =arr.length-1; index >=0; index--) {
			for (int previousIndex = index-1; previousIndex >=-1; previousIndex--) {
				 //copy the recurrence and shift the index
				int notPick = 0 + next[previousIndex+1];
				// pick
				int pick = 0;
				if (previousIndex == -1 || arr[previousIndex] < arr[index]) {
					pick = 1 + next[index+1];
				}
				curr[previousIndex+1] = Math.max(pick, notPick);
			}
			next = curr;
		}
		return next[-1+1];
	}
	private static void fillDp(int[][] dp) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}

	}

	private static int lisRecursion(int index, int previousIndex, int arr[]) {
		if (index == arr.length)
			return 0;

		int notPick = 0 + lisRecursion(index + 1, previousIndex, arr);
		// pick
		int pick = 0;
		if (previousIndex == -1 || arr[previousIndex] < arr[index]) {
			pick = 1 + lisRecursion(index + 1, index, arr);
		}

		return Math.max(pick, notPick);
	}

	private static int lisRecursionDpRecursion(int index, int previousIndex, int arr[], int[][] dp) {
		if (index == arr.length)
			return 0;
		if (dp[index][previousIndex + 1] != -1)
			return dp[index][previousIndex + 1];
		int notPick = 0 + lisRecursion(index + 1, previousIndex, arr);
		// pick
		int pick = 0;
		if (previousIndex == -1 || arr[previousIndex] < arr[index]) {
			pick = 1 + lisRecursion(index + 1, index, arr);
		}

		return dp[index][previousIndex + 1] = Math.max(pick, notPick);
	}

	public static void main(String args[]) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int n = arr.length;
		lis(arr, n);
	}

	private static void printTable(int[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j] + "  ");
			}
			System.out.println("");
		}
	}
}
