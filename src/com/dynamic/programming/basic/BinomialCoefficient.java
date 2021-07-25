package com.dynamic.programming.basic;
// A Dynamic Programming based

// solution that uses table C[][] to
// calculate the Binomial Coefficient

/**
 * 2) Overlapping Subproblems
 * 
 * It should be noted that the above function computes the same subproblems
 * again and again. See the following recursion tree for n = 5 an k = 2. The
 * function C(3, 1) is called two times. For large values of n, there will be
 * many common subproblems.
 * 
 * 
 * A binomial coefficient C(n, k) also gives the number of ways, disregarding
 * order, that k objects can be chosen from among n objects more formally, the
 * number of k-element subsets (or k-combinations) of a n-element set.
 *
 * C(n, k) = C(n-1, k-1) + C(n-1, k) 
 * 
 * C(n, 0) = C(n, n) = 1
 * 
 * Time Complexity: O(n*k) 
 * Auxiliary Space: O(n*k)
 * 
 * 
 */
class BinomialCoefficient {
	// Returns value of Binomial
	// Coefficient C(n, k)
	static int binomialCoeff(int n, int k) {
		int C[][] = new int[n + 1][k + 1];
		int i, j;

		// Calculate value of Binomial
		// Coefficient in bottom up manner
		for (i = 0; i <= n; i++) {
			for (j = 0; j <= min(i, k); j++) {
				// Base Cases
				if (j == 0 || j == i)
					C[i][j] = 1;

				// Calculate value using
				// previously stored values
				else
					C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
			}
		}

		return C[n][k];
	}

	// A utility function to return
	// minimum of two integers
	static int min(int a, int b) {
		return (a < b) ? a : b;
	}

	/* Driver program to test above function */
	public static void main(String args[]) {
		int n = 5, k = 2;
		System.out.println("Value of C(" + n + "," + k + ") is " + binomialCoeff(n, k));
	}
}