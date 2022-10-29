package com.dynamic.programming.basic;

/**
 *  Given three numbers n, r and p, compute value of nCr mod p. 
				Example: 
				
				Input:  n = 10, r = 2, p = 13
				Output: 6
				Explanation: 10C2 is 45 and 45 % 13 is 6.

 * 
 *    C(n, r) = C(n-1, r-1) + C(n-1, r)
      C(n, 0) = C(n, n) = 1
 *  
 *  The 2D array based dynamic programming solution can be further 
 *  optimized by constructing one row at a time. See Space optimized version 
 *  in below post for details.

 *  Binomial Coefficient using Dynamic Programming (BinomialCoefficient.java)
 *  
 *  Below is implementation based on the space optimized version discussed in above post.  
 *  
 * 
 */
class ComputenCrPercentp {

	// Returns nCr % p
	static int nCrModp(int n, int r, int p)
	{
		if (r > n - r)
			r = n - r;

		// The array C is going to store last
		// row of pascal triangle at the end.
		// And last entry of last row is nCr
		int C[] = new int[r + 1];

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

	// Driver program
	public static void main(String args[])
	{
		int n = 10, r = 2, p = 13;
		System.out.println("Value of nCr % p is "
						+ nCrModp(n, r, p));
	}
}

 
