package com.dynamic.programming.basic;

/**
 *  Given an integer array arr[] of size N and an integer X, the task is to count 
 *  the number of subsequences in that array such that its sum is less than or 
 *  equal to X. 
        Note: 1 <= N <= 1000 and 1 <= X <= 1000, where N is the size of the array.

                Examples:  
                
                Input : arr[] = {84, 87, 73}, X = 100 
                Output : 3 
                Explanation: The three subsequences with sum less than or equal to 100 are {84}, {87} and {73}.
                
                Input : arr[] = {25, 13, 40}, X = 50 
                Output : 4 
                Explanation: The four subsequences with sum less than or equal to 50 are {25}, {13}, {40} and {25, 13}. 
*
*  Efficient Approach: Generate the count of subsequences using Dynamic Programming. 
*  In order to solve the problem, follow the steps below: 
* 
*  For any index ind, if arr[ind] ≤ X then, the count of subsequences including as well as excluding 
*  the element at the current index: 
*  countSubsequence(ind, X) = countSubsequence(ind + 1, X) (excluding) + countSubsequence(ind + 1, X – arr[ind]) (including)

 * Else, count subsequences excluding the current index: 
        countSubsequence(ind, X) = countSubsequence(ind + 1, X) (excluding)

 * Finally, subtract 1 from the final count returned by the function as it also counts an empty 
 * subsequence.
 *
 */
public class CountSubsequencesHavingSumLessThanX {

    // Utility function to return the count
    // of subsequence in an array with sum
    // less than or equal to X
    static int countSubsequenceUtil(int ind, int sum, int[] A, int N, int[][] dp) {

        // Base condition
        if (ind == N)
            return 1;

        // Return if the sub-problem
        // is already calculated
        if (dp[ind][sum] != -1)
            return dp[ind][sum];

        // Check if the current element is
        // less than or equal to sum
        if (A[ind] <= sum) {

            // Count subsequences excluding
            // the current element
            dp[ind][sum] = countSubsequenceUtil(ind + 1, sum, A, N, dp) +

            // Count subsequences
            // including the current
            // element
                    countSubsequenceUtil(ind + 1, sum - A[ind], A, N, dp);
        } else {

            // Exclude current element
            dp[ind][sum] = countSubsequenceUtil(ind + 1, sum, A, N, dp);
        }

        // Return the result
        return dp[ind][sum];
    }

    // Function to return the count of subsequence
    // in an array with sum less than or equal to X
    static int countSubsequence(int[] A, int N, int X) {

        // Initialize a DP array
        int[][] dp = new int[N][X + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < X + 1; j++) {
                dp[i][j] = -1;
            }
        }

        // Return the result
        return countSubsequenceUtil(0, X, A, N, dp) - 1;
    }

    // Driver Code
    public static void main(String[] args) {
        int arr[] = { 25, 13, 40 }, X = 50;
        int N = arr.length;

        System.out.print(countSubsequence(arr, N, X));
    }
}
