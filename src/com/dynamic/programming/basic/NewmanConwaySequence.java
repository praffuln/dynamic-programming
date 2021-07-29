package com.dynamic.programming.basic;

/**
 * Newman-Conway Sequence is the one which generates the following integer
 * sequence. 1 1 2 2 3 4 4 4 5 6 7 7…
 * 
 * In mathematical terms, the sequence P(n) of Newman-Conway numbers is defined
 * by recurrence relation
 * 
 * P(n) = P(P(n - 1)) + P(n - P(n - 1))
 *
 * 
 */
class NewmanConwaySequence {

	// Function to find the n-th element
	static int sequence(int n) {
		// Declare array to store sequence
		int f[] = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		f[2] = 1;

		for (int i = 3; i <= n; i++)
			f[i] = f[f[i - 1]] + f[i - f[i - 1]];

		return f[n];
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		int n = 10;
		System.out.println(sequence(n));

	}
}
