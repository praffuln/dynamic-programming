package com.dynamic.programming.basic;

/**
 * In mathematics, a Newman–Shanks–Williams prime (NSW prime) is a prime number
 * p which can be written in the form:
 *
 * S[i] = 2 * S[i - 1] + S[i - 2];
 *  with S[0] =1 & S[1] = 1;
 */
class NewmanShanksWilliamsPrime {
	// return nth Newman_Shanks_Williams prime
	public static int nswpn(int n) {
		int dp[] = new int[n + 1];

		// Base case
		dp[0] = dp[1] = 1;

		// Finding nth Newman_Shanks_Williams prime
		for (int i = 2; i <= n; i++)
			dp[i] = 2 * dp[i - 1] + dp[i - 2];

		return dp[n];
	}

	// Driver Program
	public static void main(String[] args) {

		int n = 3;

		System.out.println(nswpn(n));
	}
}
