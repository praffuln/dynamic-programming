package com.dynamic.programming.basic;

/**
 * Observations :--- 
 * 
 * https://www.youtube.com/watch?v=mO8XpGoJwuo
 * 
 * This is KnapsackProblemZeroOne. where 
 *    price[] = {1   5   8   9  10  17  17  20}
 *    weight[] = {1   2   3   4   5   6   7   8}
 *    
 *    and W = N.
 * 
 * Given a rod of length n inches and an array of prices that includes prices of
 * all pieces of size smaller than n. Determine the maximum value obtainable by
 * cutting up the rod and selling the pieces. For example, if the length of the
 * rod is 8 and the values of different pieces are given as the following, then
 * the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and
 * 6)
 *
 * 			 length   | 1   2   3   4   5   6   7   8  
			--------------------------------------------
			price    | 1   5   8   9  10  17  17  20
			And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1) 
			
			length   | 1   2   3   4   5   6   7   8  
			--------------------------------------------
			price    | 3   5   8   9  10  17  17  20
 
 * 
 *  1) Optimal Substructure: 
			We can get the best price by making a cut at different positions and comparing 
			the values obtained after a cut. We can recursively call the same function for a 
			piece obtained after a cut.
			
			Let cutRod(n) be the required (best possible price) value for a rod of length n. 
			cutRod(n) can be written as follows.
			
			cutRod(n) = max(price[i] + cutRod(n-i-1)) for all i in {0, 1 .. n-1}
 * 
 * 
 */
class RodCutting {
	/*
	 * Returns the best obtainable price for a rod of length n and price[] as
	 * prices of different pieces
	 */
	static int cutRod(int price[], int n) {
		int val[] = new int[n + 1];
		val[0] = 0;

		// Build the table val[] in bottom up manner and return
		// the last entry from the table
		for (int i = 1; i <= n; i++) {
			int max_val = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++)
				max_val = Math.max(max_val, price[j] + val[i - j - 1]);
			val[i] = max_val;
		}

		return val[n];
	}

	/* Driver program to test above functions */
	public static void main(String args[]) {
		int arr[] = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
		int size = arr.length;
		System.out.println("Maximum Obtainable Value is " + cutRod(arr, size));
	}
}
