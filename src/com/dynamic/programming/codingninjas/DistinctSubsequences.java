package com.dynamic.programming.codingninjas;

public class DistinctSubsequences {

	int distinctSubsequencesRecursive(int i, int j, String s1, String s2) {
		if (j == 0)
			return 1; // found all character of s2
		if (i == 0)
			return 0; // could not found all character of s1

		// characters are matching [used select/not select]
		if (s1.charAt(i - 1) == s2.charAt(j - 1))
			return distinctSubsequencesRecursive(i - 1, j - 1, s1, s2)
					+ distinctSubsequencesRecursive(i - 1, j, s1, s2);
		else
			// matching so moving to i.
			return distinctSubsequencesRecursive(i - 1, j, s1, s2); // not
	}

	int distinctSubsequencesRecursiveDp(int i, int j, String s1, String s2, int[][] dp) {
		if (j == 0)
			return 1; // found all character of s2
		if (i == 0)
			return 0; // could not found all character of s1

		if (dp[i][j] != -1)
			return dp[i][j];

		if (s1.charAt(i - 1) == s2.charAt(j - 1)) // characters are matching
													// [used select/not select]
			return dp[i][j] = distinctSubsequencesRecursive(i - 1, j - 1, s1, s2)
					+ distinctSubsequencesRecursive(i - 1, j, s1, s2);
		else
			return dp[i][j] = distinctSubsequencesRecursive(i - 1, j, s1, s2); // not
																				// matching
																				// so
																				// moving
																				// to
																				// i.
	}

	private int distinctSubsequencesDp(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		// write base case
		for (int i = 0; i < s1.length(); i++)
			dp[i][0] = 1;
		for (int j = 1; j < s2.length(); j++)
			dp[0][j] = 0;

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) // characters are
															// matching [used
															// select/not
															// select]
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j]; // not matching so moving to i.
			}
		}

		return dp[s1.length()][s2.length()];
	}

	private int distinctSubsequencesDpSpaceOptimized(String s1, String s2) {

		int[] previous = new int[s2.length() + 1];
		int[] current = new int[s2.length() + 1];

		// write base case
		previous[0] = 1;
		current[0] = 1;

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) // characters are
															// matching [used
															// select/not
															// select]
					current[j] = previous[j - 1] + previous[j];
				else
					current[j] = previous[j]; // not matching so moving to i.
			}
			previous = current;
		}

		return current[s2.length()];
	}
	
	private int distinctSubsequencesDpSpaceOptimizedOneDArray(String s1, String s2) {
		/*
		 * removing current from complete method.
		 */
		int[] previous = new int[s2.length() + 1];
		//int[] current = new int[s2.length() + 1];

		// write base case
		previous[0] = 1;
		//current[0] = 1;

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = s2.length(); j >= 1; j--) { // change iteration order.
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) // characters are
															// matching [used
															// select/not
															// select]
					previous[j] = previous[j - 1] + previous[j];
				else
					previous[j] = previous[j]; // not matching so moving to j, can also omit to this line.
			}
			//previous = current;
		}

		return previous[s2.length()];
	}

	public static void main(String[] args) {
		DistinctSubsequences dseq = new DistinctSubsequences();
		String s1 = "babgbag";
		String s2 = "bag";

		System.out.println(
				"Count of Distinct Subsequences is" + " " + dseq.distinctSubsequencesRecursive(s1.length(), s2.length(), s1, s2));

		int[][] dp = dseq.getDpInitializedWithMinusOne(s1.length() + 1, s2.length() + 1);
		System.out.println(
				"Count of Distinct Subsequences is" + " " + dseq.distinctSubsequencesRecursiveDp(s1.length(), s2.length(), s1, s2, dp));

		System.out.println("Count of Distinct Subsequences is" + " " + dseq.distinctSubsequencesDp(s1, s2));

		System.out.println("Count of Distinct Subsequences is" + " " + dseq.distinctSubsequencesDpSpaceOptimized(s1, s2));
	
		System.out.println("Count of Distinct Subsequences is" + " " + dseq.distinctSubsequencesDpSpaceOptimizedOneDArray(s1, s2));

		
	
	
	}

	private int[][] getDpInitializedWithMinusOne(int rows, int cols) {
		int[][] array = new int[rows][cols];

		// Initialize all elements to -1
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				array[i][j] = -1;
			}
		}
		return array;
	}
}