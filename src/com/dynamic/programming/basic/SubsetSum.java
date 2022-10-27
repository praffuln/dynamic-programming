package com.dynamic.programming.basic;

/**
 * Given a set of non-negative integers, and a value sum, determine if there is
 * a subset of the given set with sum equal to given sum.
 * 
			Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
			Output: True  
			There is a subset (4, 5) with sum 9.
			
			Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
			Output: False
			There is no subset that add up to 30.
 * 
 * This is variation of KnapsackProblemZeroOne.java
 * 
 * So we will create a 2D array of size (arr.size() + 1) * (target + 1) of type
 * boolean. The state DP[i][j] will be true if there exists a subset of elements
 * from A[0….i] with sum value = ‘j’. The approach for the problem is:
 * 
 * if (A[i-1] > j) DP[i][j] = DP[i-1][j]
 * 
 * else DP[i][j] = DP[i-1][j] OR DP[i-1][j-A[i-1]]
 * 
 * This means that if current element has value greater than ‘current sum value’
 * we will copy the answer for previous cases And if the current sum value is
 * greater than the ‘ith’ element we will see if any of previous states have
 * already experienced the sum=’j’ OR any previous states experienced a value ‘j
 * – A[i]’ which will solve our purpose. The below simulation will clarify the
 * above approach:
 * 
 * set[]={3, 4, 5, 2} target=6
 * 
 * 			0 		1 		2 		3 		4 		5 		6
 *    |
 * 0 |	T 		F 		F 		F 		F 		F 		F
 *    |
 * 3 |	T 		F 		F 		T 		F 		F 		F
 * 	   |
 * 4 |	T 		F 		F 		T 		T 		F 		F
 * 	   |	
 * 5 |	T 		F 		F 		T 		T 		T 		F
 *    |
 * 2 |	T 		F 		T 		T 		T 		T 		T
 *
 * 
 */
public class SubsetSum {
	static boolean isSubsetSum(int set[], int n, int sum) {
		// The value of subset[i][j] will be
		// true if there is a subset of
		// set[0..j-1] with sum equal to i
		boolean subset[][] = new boolean[sum + 1][n + 1];

		// If sum is 0, then answer is true
		for (int i = 0; i <= n; i++)
			subset[0][i] = true;

		// If sum is not 0 and set is empty,
		// then answer is false
		for (int i = 1; i <= sum; i++)
			subset[i][0] = false;

		// Fill the subset table in bottom
		// up manner
		for (int i = 1; i <= sum; i++) {
			for (int j = 1; j <= n; j++) {
				subset[i][j] = subset[i][j - 1];
				if (i >= set[j - 1])
					subset[i][j] = subset[i][j] || subset[i - set[j - 1]][j - 1];
			}
		}

		/*
		 * // uncomment this code to print table for (int i = 0; i <= sum; i++)
		 * { for (int j = 0; j <= n; j++) System.out.println (subset[i][j]); }
		 */

		return subset[sum][n];
	}

	/* Driver code */
	public static void main(String args[]) {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		int sum = 9;
		int n = set.length;
		if (isSubsetSum(set, n, sum) == true)
			System.out.println("Found a subset" + " with given sum");
		else
			System.out.println("No subset with" + " given sum");
	}

}
