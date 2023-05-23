package com.dynamic.programming.codingninjas;

/**
 * Given a number of stairs and a frog, the frog wants to climb from the 0th
 * stair to the (N-1)th stair. At a time the frog can climb either one or two
 * steps. A height[N] array is also given. Whenever the frog jumps from a stair
 * i to stair j, the energy consumed in the jump is abs(height[i]- height[j]),
 * where abs() means the absolute difference.
 * 
 * We need to return the minimum energy that can be used by the frog to jump
 * from stair 0 to stair N-1.
 *
 * 
 */
import java.lang.Math;

public class FrogJump {
	
	static int fromJumpRecurssion(int index, int[] heights) {
		if(index == 0) return 0;
		if(index == 1) return heights[1] - heights[0];
		int left = fromJumpRecurssion(index-1, heights) + Math.abs(heights[index] - heights[index-1]);
		int right = fromJumpRecurssion(index-2, heights) + Math.abs(heights[index] - heights[index-2]);
		return Math.min(left, right);
	}
	
	/**
	 * Time complexity - O(n)
	 * Space complexity - O(n) + O(n)
	 */
	static int fromJumpRecurssionDP(int index, int[] heights, int[] dp) {
		if(index == 0) return 0;
		if(index == 1) return heights[1] - heights[0];
		//memorization with dp
		if(dp[index] != 0) return dp[index];
		int left = fromJumpRecurssionDP(index-1, heights, dp) + Math.abs(heights[index] - heights[index-1]);
		int right = fromJumpRecurssionDP(index-2, heights, dp) + Math.abs(heights[index] - heights[index-2]);
		dp[index] = Math.min(left, right);
		return Math.min(left, right);
	}
	
	private static int fromJumpDP(int[] heights, int[] dp) {
		//check for base cases
		dp[0] = 0;
		dp[1] = heights[1] - heights[0];
		//copy the recurrence
		for(int index=2; index<heights.length; index++) {
			int left = dp[index-1]  + Math.abs(heights[index] - heights[index-1]);
			int right = dp[index-2] + Math.abs(heights[index] - heights[index-2]);
			dp[index] = Math.min(left, right);
		}
		//return last element of dp array.
		return dp[heights.length-1] ;
	}
	
	private static int fromJumpDPSpaceOptimization(int[] heights, int[] dp) {
		//check for base cases
		int first = 0;
		int second =  heights[1] - heights[0];
		dp[0] = 0;
		dp[1] = heights[1] - heights[0];
		//copy the recurrence
		for(int index=2; index<heights.length; index++) {
			int left = second + Math.abs(heights[index] - heights[index-1]);
			int right = first + Math.abs(heights[index] - heights[index-2]);
			first = second;
			second = Math.min(left, right);
		}
		//return last element of dp array.
		return second;
	}
	
	public static void main(String[] args) {
		int[] heights = {30, 10, 60, 10, 60, 50};
		int size = heights.length-1;
		int minimumHeight = fromJumpRecurssion(size, heights);
		System.out.println("minimumHeight fromJumpRecurssion - " + minimumHeight);
		
		int[] dp = new int[size+1];
		minimumHeight = fromJumpRecurssionDP(size, heights, dp);
		System.out.println("minimumHeight fromJumpRecurssionDP - " + minimumHeight);
		
		dp = new int[size+1];
		minimumHeight = fromJumpDP(heights, dp);
		System.out.println("minimumHeight fromJumpDP - " + minimumHeight);
		
		dp = new int[size+1];
		minimumHeight = fromJumpDPSpaceOptimization(heights, dp);
		System.out.println("minimumHeight fromJumpDPSpaceOptimization - " + minimumHeight);
	}
}
