package com.dynamic.programming.basic;

/**
 * Newman-Conway numbers is the one which generates the following integer
 * sequence. 1 1 2 2 3 4 4 4 5 6 7 7….. and follows below recursive formula.
 * 
 * 
 * P(n) = P(P(n - 1)) + P(n - P(n - 1))
 * 
 * 
 */
public class PrintnTermsNewmanConwaySequence {
	// Function to find
	// the n-th element
	public static void sequence(int n) {
		// Declare array to store sequence
		int f[] = new int[n + 1];

		f[0] = 0;
		f[1] = 1;
		f[2] = 1;

		System.out.print(f[1] + " " + f[2] + " ");
		for (int i = 3; i <= n; i++) {
			f[i] = f[f[i - 1]] + f[i - f[i - 1]];
			System.out.print(f[i] + " ");
		}
	}

	// Driver code
	public static void main(String[] args) {
		int n = 13;
		sequence(n);
	}
}
 
