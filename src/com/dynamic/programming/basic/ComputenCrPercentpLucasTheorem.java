package com.dynamic.programming.basic;

/**
 * Given three numbers n, r and p, compute value of nCr mod p. Examples:
 * 
 * 
 * Input: n = 10, r = 2, p = 13 Output: 6 Explanation: 10C2 is 45 and 45 % 13 is
 * 6.
 * 
 * Input: n = 1000, r = 900, p = 13 Output: 8
 *
 *
 * We have introduced overflow problem and discussed Dynamic Programming based
 * solution in above set 1. The time complexity of the DP based solution is
 * O(n*r) and it required O(n) space. The time taken and extra space become very
 * high for large values of n, especially values close to 109.
 * 
 * In this post, Lucas Theorem based solution is discussed. Time complexity of
 * this solution is O(p2 * Logp n) and it requires only O(p) space.
 *
 * ***************** Using Lucas Theorem for nCr % p *****************
 * 
 * Lucas theorem basically suggests that the value of nCr can be computed by
 * multiplying results of niCri where ni and ri are individual same-positioned
 * digits in base p representations of n and r respectively..
 * 
 * The idea is to one by one compute niCri for individual digits ni and ri in
 * base p. We can compute these values DP based solution discussed in previous
 * post. Since these digits are in base p, we would never need more than O(p)
 * space and time complexity of these individual computations would be bounded
 * by O(p2).
 * 
 */
public class ComputenCrPercentpLucasTheorem {
	
	// Returns nCr % p. In this Lucas Theorem based program,
	// this function is only called for n < p and r < p.
	static int nCrModpDP(int n, int r, int p) {
		// The array C is going to store last row of
		// pascal triangle at the end. And last entry
		// of last row is nCr
		int[] C = new int[r + 1];

		C[0] = 1; // Top row of Pascal Triangle

		// One by constructs remaining rows of Pascal
		// Triangle from top to bottom
		for (int i = 1; i <= n; i++) {
			// Fill entries of current row using previous
			// row values
			for (int j = Math.min(i, r); j > 0; j--)

				// nCj = (n-1)Cj + (n-1)C(j-1);
				C[j] = (C[j] + C[j - 1]) % p;
		}
		return C[r];
	}

	// Lucas Theorem based function that returns nCr % p
	// This function works like decimal to binary conversion
	// recursive function. First we compute last digits of
	// n and r in base p, then recur for remaining digits
	static int nCrModpLucas(int n, int r, int p) {
		// Base case
		if (r == 0)
			return 1;

		// Compute last digits of n and r in base p
		int ni = n % p;
		int ri = r % p;

		// Compute result for last digits computed above, and
		// for remaining digits. Multiply the two results and
		// compute the result of multiplication in modulo p.
		return (nCrModpLucas(n / p, r / p, p) * // Last digits of n and r
				nCrModpDP(ni, ri, p)) % p; // Remaining digits
	}

	// Driver program
	public static void main(String[] args) {
		int n = 1000, r = 900, p = 13;
		System.out.println("Value of nCr % p is " + nCrModpLucas(n, r, p));
	}
}
