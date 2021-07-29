package com.dynamic.programming.basic;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest
 * subsequence present in both of them. A subsequence is a sequence that appears
 * in the same relative order, but not necessarily contiguous. For example,
 * “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
 * 
 * In order to find out the complexity of brute force approach, we need to first
 * know the number of possible different subsequences of a string with length n,
 * i.e., find the number of subsequences with lengths ranging from 1,2,..n-1.
 * Recall from theory of permutation and combination that number of combinations
 * with 1 element are nC1. Number of combinations with 2 elements are nC2 and so
 * forth and so on. We know that nC0 + nC1 + nC2 + … nCn = 2n. So a string of
 * length n has 2n-1 different possible subsequences since we do not consider
 * the subsequence with length 0. This implies that the time complexity of the
 * brute force approach will be O(n * 2n). Note that it takes O(n) time to check
 * if a subsequence is common to both the strings. This time complexity can be
 * improved using dynamic programming.
 * 
 * 
 * Examples: LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * 
 * 1) Optimal Substructure:
 * 
 * Let the input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n
 * respectively. And let L(X[0..m-1], Y[0..n-1]) be the length of LCS of the two
 * sequences X and Y. Following is the recursive definition of L(X[0..m-1],
 * Y[0..n-1]).
 * 
 * If last characters of both sequences match (or X[m-1] == Y[n-1]) then
 * 
 * L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])
 * 
 * If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
 * 
 * L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1],
 * Y[0..n-2]) )
 * 
 * 
 * 
 */
public class LongestCommonSubsequence {

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcsRecursive(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1])
            return 1 + lcsRecursive(X, Y, m - 1, n - 1);
        else
            return max(lcsRecursive(X, Y, m, n - 1),
                    lcsRecursive(X, Y, m - 1, n));
    }

    /* Utility function to get max of 2 integers */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " "
                + lcs.lcsRecursive(X, Y, m, n));

        System.out.println("Length of LCS is" + " "
                + lcs.lcsDynamicPrograming(X, Y, m, n));
        
        System.out.println("Length of LCS is" + " "
                + lcs.findLongestRepeatingSubSeq("aab"));
        
        
    }

    private int lcsDynamicPrograming(char[] X, char[] Y, int m, int n) {
        int L[][] = new int[m + 1][n + 1];

        /*
         * Following steps build L[m+1][n+1] in bottom up fashion. Note that
         * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
         */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }

    
    
    
    
 // Refer https://www.geeksforgeeks.org/longest-repeating-subsequence/
 // for complete code.
 // This function mainly returns LCS(str, str)
 // with a condition that same characters at
 // same index are not considered.
 static int findLongestRepeatingSubSeq(String str)
 {
     int n = str.length();
    
     // Create and initialize DP table
     int dp[][] = new int[n+1][n+1];
     for (int i=0; i<=n; i++)
         for (int j=0; j<=n; j++)
             dp[i][j] = 0;
    
     // Fill dp table (similar to LCS loops)
     for (int i=1; i<=n; i++)
     {
         for (int j=1; j<=n; j++)
         {
             // If characters match and indexes are
             // not same
             if (str.charAt(i-1)== str.charAt(j-1) && i != j)
                 dp[i][j] =  1 + dp[i-1][j-1];         
                         
             // If characters do not match
             else
                 dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
         }
     }
     return dp[n][n];
 }

    
    
}