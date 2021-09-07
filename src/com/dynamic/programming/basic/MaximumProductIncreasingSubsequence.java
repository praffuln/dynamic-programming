package com.dynamic.programming.basic;

/* Dynamic programming Java implementation
 of maximum product of an increasing
 subsequence */

/**
 * Given an array of numbers, find the maximum product formed by multiplying
 * numbers of an increasing subsequence of that array. Note: A single number is
 * supposed to be an increasing subsequence of size 1.
 * 
            Input : arr[] = { 3, 100, 4, 5, 150, 6 }
            Output : 45000
            Maximum product is 45000 formed by the 
            increasing subsequence 3, 100, 150. Note
            that the longest increasing subsequence 
            is different {3, 4, 5, 6}
            
            Input : arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 }
            Output : 21780000
            Maximum product is 21780000 formed by the 
            increasing subsequence 10, 22, 33, 50, 60.
 *
 *
 * Approach: Use a dynamic approach to maintain a table mpis[]. The value of 
 * mpis[i] stores product maximum product increasing subsequence ending with 
 * arr[i]. Initially all the values of increasing subsequence table are initialized to 
 * arr[i]. We use recursive approach similar to LIS problem to find the result. 
 *
 * Time Complexity: O(n^2) 
 *	Auxiliary Space : O(n)
 *
 */
class MaximumProductIncreasingSubsequence {

    // Returns product of maximum product
    // increasing subsequence.
    static int lis(int[] arr, int n) {
        int[] mpis = new int[n];
        int max = Integer.MIN_VALUE;

        /* Initialize MPIS values */
        for (int i = 0; i < n; i++)
            mpis[i] = arr[i];

        /*
         * Compute optimized MPIS values considering every element as ending
         * element of sequence
         */
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && mpis[i] < (mpis[j] * arr[i]))
                    mpis[i] = mpis[j] * arr[i];

        /*
         * Pick maximum of all product values using for loop
         */
        for (int k = 0; k < mpis.length; k++) {
            if (mpis[k] > max) {
                max = mpis[k];
            }
        }

        return max;
    }

    // Driver program to test above function
    static public void main(String[] args) {

        int[] arr = { 3, 100, 4, 5, 150, 6 };
        int n = arr.length;

        System.out.println(lis(arr, n));
    }
}
