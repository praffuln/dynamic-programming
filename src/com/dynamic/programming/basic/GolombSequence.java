package com.dynamic.programming.basic;

/**
 * https://en.wikipedia.org/wiki/Golomb_sequence
 * 
 * In mathematics, the Golomb sequence is a non-decreasing integer sequence where 
 * n-th term is equal to number of times n appears in the sequence.
   The first few values are 
		1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, ……
		Explanation of few terms: 
		Third term is 2, note that three appears 2 times. 
		Second term is 2, note that two appears 2 times. 
		Fourth term is 3, note that four appears 3 times.
		Given a positive integer n. The task is to find the first n terms of Golomb sequence. 
		Examples : 
		 

Input : n = 4
Output : 1 2 2 3

Input : n = 6
Output : 1 2 2 3 3 4
 *
 *
 *The recurrence relation to find the nth term of Golomb sequence: 
	a(1) = 1 
	a(n + 1) = 1 + a(n + 1 – a(a(n))) 
	
	or a(n) = 1 + a(n-a(a(n-1)))  --- based on above formula
 *
 */
class GolombSequence {

	public static void printGolomb(int n) {
		int dp[] = new int[n + 1];

		// base cases
		dp[1] = 1;
		System.out.print(dp[1] + " ");

		// Finding and printing first n
		// terms of Golomb Sequence.
		for (int i = 2; i <= n; i++) {
			dp[i] = 1 + dp[i - dp[dp[i - 1]]];

			System.out.print(dp[i] + " ");
		}
	}

	// Driver code
	public static void main(String[] args) {
		int n = 9;

		printGolomb(n);
	}
}
