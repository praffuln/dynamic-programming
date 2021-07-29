package com.dynamic.programming.basic;

/**
 * The Permutation Coefficient represented by P(n, k) is used to represent the
 * number of ways to obtain an ordered subset having k elements from a set of n
 * elements.
 *
 * we do it in O(n) time...
 * 
 * Let us suppose we maintain a single 1D array to compute the factorials up to
 * n. We can use computed factorial value and apply the formula P(n, k) = n! /
 * (n-k)!. Below is a program illustrating the same concept.
 * 
 * 
 */
public class PermutationCoefficient {

	// Returns value of Permutation
	// Coefficient P(n, k)
	static int permutationCoeff(int n, int k) {
		int[] fact = new int[n + 1];

		// base case
		fact[0] = 1;

		// Calculate value
		// factorials up to n
		for (int i = 1; i <= n; i++)
			fact[i] = i * fact[i - 1];

		// P(n,k) = n! / (n - k)!
		return fact[n] / fact[n - k];
	}

	// Driver Code
	static public void main(String[] args) {
		int n = 10, k = 2;
		System.out.println("Value of" + " P( " + n + ", " + k + ") is " + permutationCoeff(n, k));
	}
}