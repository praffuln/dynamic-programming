package com.dynamic.programming.basic;

/**
 *  Bell Numbers (Number of ways to Partition a Set)
 * Given a set of n elements, find number of ways of partitioning it. 
				Examples: 
				
				Input:  n = 2
				Output: Number of ways = 2
				Explanation: Let the set be {1, 2}
				            { {1}, {2} } 
				            { {1, 2} }
				
				Input:  n = 3
				Output: Number of ways = 5
				Explanation: Let the set be {1, 2, 3}
				             { {1}, {2}, {3} }
				             { {1}, {2, 3} }
				             { {2}, {1, 3} }
				             { {3}, {1, 2} }
				             { {1, 2, 3} }. 
 *
 *
 *   A Better Method is to use Bell Triangle. Below is a sample Bell Triangle 
 *   for first few Bell Numbers. 
				1
				1 2
				2 3 5
				5 7 10 15
				15 20 27 37 52
 *
 * // If this is first column of current row 'i'
				If j == 0
				   // Then copy last entry of previous row
				   // Note that i'th row has i entries
				   Bell(i, j) = Bell(i-1, i-1) 
				
				// If this is not first column of current row
				Else 
				   // Then this element is sum of previous element 
				   // in current row and the element just above the
				   // previous element
				   Bell(i, j) = Bell(i-1, j-1) + Bell(i, j-1)
 * 
 * 
 */
class BellNumber {
	// Function to find n'th Bell Number
	static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;

		for (int i = 1; i <= n; i++) {
			// Explicitly fill for j = 0
			bell[i][0] = bell[i - 1][i - 1];

			// Fill for remaining values of j
			for (int j = 1; j <= i; j++)
				bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
		}

		return bell[n][0];
	}

	// Driver program
	public static void main(String[] args) {
		for (int n = 0; n <= 5; n++)
			System.out.println("Bell Number " + n + " is " + bellNumber(n));
	}
}
