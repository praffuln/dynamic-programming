package com.dynamic.programming.basic;

/**
 * Now, let us discuss the Longest Increasing Subsequence (LIS) problem as an
 * example problem that can be solved using Dynamic Programming.
 * 
 * The Longest Increasing Subsequence (LIS) problem is to find the length of the
 * longest subsequence of a given sequence such that all elements of the
 * subsequence are sorted in increasing order. For example, the length of LIS
 * for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60,
 * 80}.
 * 
                Input: arr[] = {3, 10, 2, 1, 20}
                Output: Length of LIS = 3
                The longest increasing subsequence is 3, 10, 20
                
                Input: arr[] = {3, 2}
                Output: Length of LIS = 1
                The longest increasing subsequences are {3} and {2}
                
                Input: arr[] = {50, 3, 10, 7, 40, 80}
                Output: Length of LIS = 4
                The longest increasing subsequence is {3, 7, 40, 80}
 * 
                Input  : arr[] = {3, 10, 2, 11}
                f(i): Denotes LIS of subarray ending at index 'i'
                
                (LIS(1)=1)
                
                      f(4)  {f(4) = 1 + max(f(1), f(2), f(3))}
                  /    |    \
                f(1)  f(2)  f(3) {f(3) = 1, f(2) and f(1) are > f(3)}
                       |      |  \
                      f(1)  f(2)  f(1) {f(2) = 1 + max(f(1)}
                              |
                            f(1) {f(1) = 1}
 *
 *  We can see that there are many subproblems in the above recursive solution 
 *  which are solved again and again. So this problem has Overlapping Substructure
 *  property and recomputation of same subproblems can be avoided by either using 
 *  Memoization or Tabulation.
 *  
 *  The simulation of approach will make things clear:  
        Input  : arr[] = {3, 10, 2, 11}
        LIS[] = {1, 1, 1, 1} (initially)
 *  
 */
class LongestIncreasingSubsequence {
    /*
     * lis() returns the length of the longest increasing subsequence in arr[]
     * of size n
     */
    static int lis(int arr[], int n) {
        int lis[] = new int[n];
        int i, j, max = 0;

        /* Initialize LIS values for all indexes */
        for (i = 0; i < n; i++)
            lis[i] = 1;

        /*
         * Compute optimized LIS values in bottom up manner
         */
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        /* Pick maximum of all LIS values */
        for (i = 0; i < n; i++)
            if (max < lis[i])
                max = lis[i];

        return max;
    }

    public static void main(String args[]) {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr.length;
        System.out.println("Length of lis is " + lis(arr, n) + "\n");
    }
}
