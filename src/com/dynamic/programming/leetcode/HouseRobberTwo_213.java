package com.dynamic.programming.leetcode;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed. All houses at this place are
 * arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have a security system connected, and it will
 * automatically contact the police if two adjacent houses were broken into on
 * the same night.
 * 
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the
 * police.
 *
 * 						Example 1:
						
						Input: nums = [2,3,2]
						Output: 3
						Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), 
						because they are adjacent houses.
						Example 2:
						
						Input: nums = [1,2,3,1]
						Output: 4
						Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
						Total amount you can rob = 1 + 3 = 4.
						Example 3:
						
						Input: nums = [1,2,3]
						Output: 3
 * 
 * 
 * Approach :::: Since House[1] and House[n] are adjacent, they cannot be robbed together. 
 * Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], 
 * depending on which choice offers more money. Now the problem has degenerated to the 
 * House Robber, which is already been solved.
 * 
 * 
 */
public class HouseRobberTwo_213 {

	private static class Solution {
		
		public int rob(int[] nums) {
			int n = nums.length;
			
			if (nums.length == 0)
				return 0;
			if (nums.length == 1)
				return nums[0];
			
			return Math.max(robUtil(nums, 0, n-1), robUtil(nums, 1, n));
		}
		
		private int robUtil(int[] nums, int start, int end) {

			int[] rob = new int[nums.length];

			if(start ==0) {
				rob[0] = nums[0];
				rob[1] = Math.max(nums[1], rob[0]);
            } else {
            	rob[0] = 0;
            	rob[1] = nums[1];
            }
			
			for (int i = 2; i < end; i++) {
				rob[i] = Math.max(rob[i - 2] + nums[i], rob[i - 1]);
			}

			System.out.println(Arrays.toString(rob));

			return rob[end-1];
		}

	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] houses = {1,2,3};
		int answer = solution.rob(houses);
		
		System.out.println("maximum amount is "+ answer);
	
	}

}
