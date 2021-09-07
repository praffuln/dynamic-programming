package com.dynamic.programming.basic;

/**
 * An array is given, find length of the subarray having maximum sum.
 * 
 * This problem is mainly a variation of Largest Sum Contiguous Subarray
 * Problem. [LargestSumContiguousSubarray.java]
 * 
            Input :  a[] = {1, -2, 1, 1, -2, 1}
            Output : Length of the subarray is 2
            Explanation: Subarray with consecutive elements 
            and maximum sum will be {1, 1}. So length is 2
            
            Input : ar[] = { -2, -3, 4, -1, -2, 1, 5, -3 }
            Output : Length of the subarray is 5
            Explanation: Subarray with consecutive elements 
            and maximum sum will be {4, -1, -2, 1, 5}. 
 * 
 * 
 * The idea is to update starting index whenever sum ending here becomes less
 * than 0.
 * 
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 * 
 */
public class SizeOfTheSubarrayWithMaximumSum {

    static int maxSubArraySum(int a[], int size) {
        int max_so_far = Integer.MIN_VALUE, 
                max_ending_here = 0, start = 0, end = 0, s = 0;

        for (int i = 0; i < size; i++) {
            max_ending_here += a[i];

            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }

            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        return (end - start + 1);
    }
 
    // Driver code
    public static void main(String[] args)  {
        int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int n = a.length;
        System.out.println(maxSubArraySum(a, n));
    }


}
