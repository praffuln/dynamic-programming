package com.dynamic.programming.leetcode;

import java.util.Arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 * 
 * A subarray is a contiguous part of an array.
 *
 * 
				Example 1:
				
				Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
				Output: 6
				Explanation: [4,-1,2,1] has the largest sum = 6.
				
				Example 2:
				
				Input: nums = [1]
				Output: 1
				
				Example 3:
				
				Input: nums = [5,4,-1,7,8]
				Output: 23
 * 
 * This is kadance's algorithm.s
 * 
 */
public class MaximumSubarray_53 {
	private static class Solution {
	   int maxSubArray(int[] nums) {
	    	 
	    	if(nums.length == 0) {
	             return 0;
	         }
	         int curMax = nums[0];
	         int allMax = nums[0];
	         
	         for(int i = 1; i < nums.length; i++) {
	             curMax = Math.max(nums[i], curMax + nums[i]);
	             allMax = Math.max(allMax, curMax);
	         }
	         return allMax;
	    }

		int dynamicProgrammingMaxSubArray(int[] nums) {
			if(nums.length == 0) return 0; 
			
			int[] dp = new int[nums.length];
			dp[0] = nums[0];
			
			for(int i=1; i<nums.length; i++) {
				dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
			}
			
			//return max from dp array 
			return Arrays.stream(dp).max().getAsInt();
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(new Solution().maxSubArray(nums));
		
		System.out.println(new Solution().dynamicProgrammingMaxSubArray(nums));
	}
}
