package com.dynamic.programming.basic;
// Java code to generate first 'n' terms

// of the Moser-de Bruijn Sequence

/**
 * The Moser-de Bruijn sequence is the sequence obtained by adding up the
 * distinct powers of the number 4 (For example, 1, 4, 16, 64, etc).
 *
 * It is observed that the terms of the sequence follow the recurrence relation :  
			1) S(2 * n) = 4 * S(n)
			2) S(2 * n + 1) = 4 * S(n) + 1
			with S(0) = 0 and S(1) = 1
 * 
							Input : 5
							Output : 0 1 4 5 16
							
							Input : 10
							Output : 0 1 4 5 16 17 20 21 64 65
 */
class MoserdeBruijnSequence {

	// Function to generate nth term
	// of Moser-de Bruijn Sequence
	public static int gen(int n) {

		// S(0) = 0
		if (n == 0)
			return 0;

		// S(1) = 1
		else if (n == 1)
			return 1;

		// S(2 * n) = 4 * S(n)
		else if (n % 2 == 0)
			return 4 * gen(n / 2);

		// S(2 * n + 1) = 4 * S(n) + 1
		else if (n % 2 == 1)
			return 4 * gen(n / 2) + 1;
		return 0;
	}

	// Generating the first 'n' terms
	// of Moser-de Bruijn Sequence
	public static void moserDeBruijnRecursive(int n) {
		for (int i = 0; i < n; i++)
			System.out.print(gen(i) + " ");
		System.out.println();
	}

	// Driver Code
	public static void main(String args[]) {
		int n = 15;
		System.out.println("First " + n + " terms of " + "Moser-de Bruijn Sequence : ");
		moserDeBruijnRecursive(n);
		System.out.println("dynamic solution ");
		moserDeBruijn(n);
	}
	
	
	// Function to generate nth term
	// of Moser-de Bruijn Sequence
	static int gen1(int n) {
	    int []S = new int [n + 1];
	 
	    S[0] = 0;
	    S[1] = 1;
	 
	    for (int i = 2; i <= n; i++)  {
	         
	        // S(2 * n) = 4 * S(n)
	        if (i % 2 == 0)
	        S[i] = 4 * S[i / 2];
	     
	        // S(2 * n + 1) = 4 * S(n) + 1
	        else
	        S[i] = 4 * S[i/2] + 1;
	    }
	     
	    return S[n];
	}
	 
	// Generating the first 'n' terms
	// of Moser-de Bruijn Sequence
	static void moserDeBruijn(int n) {
	    for (int i = 0; i < n; i++)
	        System.out.print(gen1(i)+" ");
	}
}

