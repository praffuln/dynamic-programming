package com.dynamic.programming.basic;

/**
 * TODO: Samajh nahi aaya....
 * 
 * Given a triangular structure of numbers, find the minimum path sum from top
 * to bottom. Each step you may move to adjacent numbers on the row below.
 * 
                Input : 
                   2
                  3 7
                 8 5 6
                6 1 9 3
                Output : 11
                Explanation : 2 + 3 + 5 + 1 = 11
                
                Input :
                   3
                  6 4
                 5 2 7
                9 1 8 6
                Output : 10
                Explanation : 3 + 4 + 2 + 1 = 10
 * 
 * Naive Approach : Going through the Naive approach by traversing every possible path. But, 
 * this is costly. So, use Dynamic Programming here in order to reduce the time complexity.

There are two ways to reach the solution : 

1. Memoization – Starting from the top node, traverse recursively with each node, 
till the pathsum of that node is calculated. And then store the result in an array. 
But this will take O(N^2) space to maintain the array.

2. Bottom up – Start from the nodes on the bottom row; the min pathsum for these nodes 
are the values of the nodes themselves. And after that, minimum pathsum at the ith node of 
kth row would be the minimum of the pathsum of its two children + the node’s value, i.e.: 
 * 
 * memo[k][i] = min( memo[k+1][i], memo[k+1][i+1]) + A[k][i];
 * 
 * For the row k : 
        memo[i] = min( memo[i], memo[i+1]) + A[k][i];
 * 
 */
public class MinimumSumPathInTriangle {

    static int A[][] = { { 2 }, { 3, 9 }, { 1, 6, 7 } };

    // Util function to find
    // minimum sum for a path
    static int minSumPath() {
        // For storing the result
        // in a 1-D array, and
        // simultaneously updating
        // the result.
        int[] memo = new int[A.length];
        int n = A.length - 1;

        // For the bottom row
        for (int i = 0; i < A[n].length; i++)
            memo[i] = A[n][i]; //initialize bottom line in  memo
        //memo[] is initialized with [ 1, 6, 7]

        // Calculation of the
        // remaining rows, in
        // bottom up manner.
        for (int i = A.length - 2; i >= 0; i--)
            for (int j = 0; j < A[i].length; j++)
                memo[j] = A[i][j] + (int) Math.min(memo[j], memo[j + 1]);

        // return the
        // top element
        return memo[0];
    }

    // Driver Code
    public static void main(String args[]) {
        System.out.print(minSumPath());
    }

}
