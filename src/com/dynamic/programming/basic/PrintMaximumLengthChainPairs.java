package com.dynamic.programming.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given n pairs of numbers. In every pair, the first number is always
 * smaller than the second number. A pair (c, d) can follow another pair (a, b)
 * if b < c. Chain of pairs can be formed in this fashion. Find the longest
 * chain which can be formed from a given set of pairs.
 * 
 * Examples:
 * 
 * Input:  (5, 24), (39, 60), (15, 28), (27, 40), (50, 90)
	Output: (5, 24), (27, 40), (50, 90)
	
	Input:  (11, 20), {10, 40), (45, 60), (39, 40)
	Output: (11, 20), (39, 40), (45, 60) 
 * 
 * 
 * 
 * L[0] = {arr[0]}
	L[i] = {Max(L[j])} + arr[i] where j < i and arr[j].b < arr[i].a 
	     = arr[i], if there is no such j
 * 
 * Time complexity of above Dynamic Programming solution is O(n2) where n is the
 * number of pairs.
 * 
 * Auxiliary space used by the program is O(n2).
 * 
 * 
 * 
 */
public class PrintMaximumLengthChainPairs {
	int a;
	int b;

	public PrintMaximumLengthChainPairs(int a, int b) {
		this.a = a;
		this.b = b;
	}

	// This function assumes that
	// arr[] is sorted in increasing order
	// according the first (or smaller)
	// values in pairs.
	static int printChainLength(PrintMaximumLengthChainPairs arr[], int n) {
		int i, j, max = 0;
		int mcl[] = new int[n];

		/*
		 * Initialize MCL (max chain length) values for all indexes
		 */
		for (i = 0; i < n; i++)
			mcl[i] = 1;

		/*
		 * Compute optimized chain length values in bottom up manner
		 */
		for (i = 1; i < n; i++)
			for (j = 0; j < i; j++)
				if (arr[i].a > arr[j].b && mcl[i] < mcl[j] + 1)
					mcl[i] = mcl[j] + 1;

		// mcl[i] now stores the maximum
		// chain length ending with pair i

		/* Pick maximum of all MCL values */
		List<PrintMaximumLengthChainPairs> maxChain = new ArrayList<PrintMaximumLengthChainPairs>();

		for (i = 0; i < n; i++) {
			if (max < mcl[i]) {
				max = mcl[i];
				maxChain.add(arr[i]);
			}
		}

		// print pair
		for (PrintMaximumLengthChainPairs pair : maxChain)
			System.out.println("    " + pair.a + "    " + pair.b);

		return max; // returning length
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		PrintMaximumLengthChainPairs arr[] = new PrintMaximumLengthChainPairs[] {
				new PrintMaximumLengthChainPairs(5, 24),
				new PrintMaximumLengthChainPairs(15, 25),
				new PrintMaximumLengthChainPairs(27, 40),
				new PrintMaximumLengthChainPairs(50, 60) };
		System.out.println("Length of maximum  size chain is "
				+ printChainLength(arr, arr.length));
	}
}
