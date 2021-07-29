package com.dynamic.programming.basic;

/**
 * Time Complexity: O(n^2)
 * 
 * Given a string, print the longest repeating subsequence such that the two
 * subsequence don’t have same string character at same position, i.e., any i’th
 * character in the two subsequences shouldn’t have the same index in the
 * original string.
 * 
                Input: str = "aabb"
                Output: "ab"
                
                Input: str = "aab"
                Output: "a"
                The two subsequence are 'a'(first) and 'a' 
                (second). Note that 'b' cannot be considered 
                as part of subsequence as it would be at same
                index in both.
 * 
 * This problem is just the modification of Longest Common Subsequence problem. 
 * The idea is to find the LCS(str, str) where str is the input string with the restriction
 *  that when both the characters are same, they shouldn’t be on the same index in 
 *  the two strings. 
 *
 * 
 */
public class LongestRepeatedSubsequence {

    public static void main(String[] args) {
        LongestRepeatedSubsequence lcs = new LongestRepeatedSubsequence();

        System.out
                .println("Length of LCS is"
                        + " "
                        + lcs.lcsDynamicProgramingExactlyAsLongestCommonSubSequence("aabb"));

        System.out.println("Length of LCS is" + " "
                + lcs.findLongestRepeatingSubSeq("aabb"));

    }

    private int lcsDynamicProgramingExactlyAsLongestCommonSubSequence(String str) {
        int n = str.length();

        // Create and initialize DP table
        int L[][] = new int[n + 1][n + 1];
        /*
         * Following steps build L[m+1][n+1] in bottom up fashion. Note that
         * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
         */
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (str.charAt(i - 1) == str.charAt(j - 1) && i != j)
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[n][n];
    }

    // Refer https://www.geeksforgeeks.org/longest-repeating-subsequence/
    // for complete code.
    // This function mainly returns LCS(str, str)
    // with a condition that same characters at
    // same index are not considered.
    private int findLongestRepeatingSubSeq(String str) {
        int n = str.length();

        // Create and initialize DP table
        int dp[][] = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = 0;

        // Fill dp table (similar to LCS loops)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match and indexes are
                // not same
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) //only here is difference from common
                    //subsequence conditions
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                // If characters do not match
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n][n];
    }

}