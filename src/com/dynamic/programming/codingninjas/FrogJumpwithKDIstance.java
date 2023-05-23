package com.dynamic.programming.codingninjas;

import java.util.Arrays;

/**
 * This is a follow-up question to “FrogJump.java” In the previous question, the
 * frog was allowed to jump either one or two steps at a time. In this question,
 * the frog is allowed to jump up to ‘K’ steps at a time. If K=4, the frog can
 * jump 1,2,3, or 4 steps at every index.
 * 
 * 
 */
public class FrogJumpwithKDIstance {

	/**
	 * TC = K*O(N) 
	 * 
	 * SC =O(N) 
	 */
	static int fromJumpRecurssion(int index, int[] heights, int k) {
		if (index == 0)
			return 0;

		int answer = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			if (index - j >= 0) {
				int b = fromJumpRecurssion(index - j, heights, k) + Math.abs(heights[index] - heights[index - j]);
				answer = Math.min(answer, b);
			}
		}
		return answer;
	}
	
	/**
	 * TC = K*O(N) 
	 * 
	 * SC =O(N) + O(N)
	 */
	static int fromJumpRecurssionDP(int index, int[] heights, int k, int[] dp) {
		if (index == 0)
			return 0;
        if(dp[index] != -1) return dp[index];
        
		int answer = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			if (index - j >= 0) {
				int b = fromJumpRecurssion(index - j, heights, k) + Math.abs(heights[index] - heights[index - j]);
				answer = Math.min(answer, b);
			}
		}
		return dp[index] = answer;
	}

	/*
	 * DP SPACE AND TIME
	 * 
	 * TC = K*O(N) 
	 * 
	 * SC =O(N)
	 * 
	 */
	private static int fromJumpDP(int[] heights, int k, int[] dp) {
		//check for base cases
		dp[0] = 0;
 		//copy the recurrence
		for(int index=1; index<heights.length; index++) {
			
			int answer = Integer.MAX_VALUE;
			for(int j=1; j<=k; j++) {
				if(index-j>=0) {
					int left = dp[index-j]  + Math.abs(heights[index] - heights[index-j]);
					answer = Math.min(answer, left);
				}
			}
			dp[index] = answer;
		}
		return dp[heights.length-1] ;
	}
	
	private static int fromJumpDPSpaceOptimization(int[] heights, int k, int[] dp) {
		//We need to store at least k elements for different jumps
		// in the end if k=n worse case space complexity will be O(N)
		return 0;
	}

	
	public static void main(String[] args) {
		int[] heights = { 30, 10, 60, 10, 60, 50 };
		int size = heights.length;
		int k = 2;

		int minimumHeight = fromJumpRecurssion(size - 1, heights, k);
		System.out.println("minimumHeight fromJumpRecurssion - " + minimumHeight);

		 int[] dp = new int[size];
		 Arrays.fill(dp, -1);
		 minimumHeight = fromJumpRecurssionDP(size-1, heights, k, dp);
		 System.out.println("minimumHeight fromJumpRecurssionDP - " +
		 minimumHeight);
		
		 dp = new int[size+1];
		 minimumHeight = fromJumpDP(heights, k, dp);
		 System.out.println("minimumHeight fromJumpDP - " + minimumHeight);
		
		 dp = new int[size+1];
		 minimumHeight = fromJumpDPSpaceOptimization(heights, k, dp);
		 System.out.println("minimumHeight fromJumpDPSpaceOptimization - " +
		 minimumHeight);
	}

}
