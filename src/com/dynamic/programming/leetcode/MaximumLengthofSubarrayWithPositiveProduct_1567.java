package com.dynamic.programming.leetcode;

/**
 * Given an array of integers nums, find the maximum length of a subarray where
 * the product of all its elements is positive.
 * 
 * A subarray of an array is a consecutive sequence of zero or more values taken
 * out of that array.
 * 
 * Return the maximum length of a subarray with positive product.
 *
 * Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.
 
		Input: nums = [0,1,-2,-3,-4]
		Output: 3
		Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
		Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.

		Input: nums = [-1,-2,-3,0,1]
		Output: 2
		Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 * 
 * 
 * Intuition
At every iteration, tracking maximum length of positive multiplicative result and negative 
multiplicative result can help.
Multiplicative Result : result(+ve/-ve) of multiplication of bunch of numbers(some of which 
can be +ve/-ve)

Algorithm

		If we see a 0, we gotta start off things again
		If we see a positive number :
			2.1. Increase length of positive mutilpicative result till now.
			2.2. Increase length of negative mutilpicative result till now, unless we had not encountered any negative before.
		If we see a negative number:
			ap positive and negative multiplicative results' lengths and do similar task as we did in above case.
		
		In each iteration, use the length of positive mutilpicative result to compute answer.
		Dry Run

			elements      :   9    5    8    2    -6    4    -3    0    2    -5    15    10    -7    9    -2
			positive_len  :   1    2    3    4     0    1     7    0    1     0     1     2     5    6     5
			negative_len  :   0    0    0    0     5    6     2    0    0     2     3     4     3    4     7

 * 
 */
public class MaximumLengthofSubarrayWithPositiveProduct_1567 {

	static class Solution {
		public int getMaxLen(int[] nums) {

			   int positive = 0;
		        int negative = 0;
		        int max = 0;
		        for (int n: nums) {
		            if (n == 0) {
		                positive = 0; 
		                negative = 0; 
		                continue;
		            }
		            
		            positive++;
		            negative = negative > 0 ? negative + 1 : 0;
		            if (n < 0) { // swap if negative number is encountered
		                int tmp = positive;
		                positive = negative;
		                negative = tmp;
		            }
		            max = Math.max(max, positive);
		        }
		        return max;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = { 2, 3, -2, 4 };
		System.out.println(solution.getMaxLen(nums));
		int[] nums2  = {-1,-2,-3,0,1};
		System.out.println(solution.getMaxLen(nums2));
		int[] nums3  = {0,1,-2,-3,-4};
		System.out.println(solution.getMaxLen(nums3));
		int[] nums4  ={1,-2,-3,4};
		System.out.println(solution.getMaxLen(nums4));
	}
}
