package com.dynamic.programming.codingninjas;

/**
 * Leetcode - 2035
 * 
 * You are given an integer array nums of 2 * n integers. You need to partition
 * nums into two arrays of length n to minimize the absolute difference of the
 * sums of the arrays. To partition nums, put each element of nums into one of
 * the two arrays.
 * 
 * Return the minimum possible absolute difference.
 *
 * s = [76,8,45,20,74,84,28,1]
 * 
 * this array can be segregated into 2 arrays s1 = [28,20,74,45,1] s2 =
 * [76,8,84]
 * 
 * sum(s) = 336 sum(s1) = 168 sum(s2) = 168
 * 
 * difference = 0
 * 
 * expected difference = 2
 * 
 * 
 * 
 */
public class PartitionArrayIntoTwoArraysToMinimizeSumDifference {

	//Overlapping subProblem.
	public static void main(String[] args) {
		//int[] set = { 1, 6, 11, 5 };  //1
		int[] set = {3,9,7,3};  //2
		int index = set.length;
		int target = 0;
		for (int i : set) {
			target += i;
		}

		boolean[][] dp = new boolean[index][target + 1];
		subsetSumEqualsToTargetDp(set, target, dp);
		boolean[] requiredDp = dp[set.length-1];
		
		int minimum = 10000000;
		for(int s1 =0 ; s1<= requiredDp.length-1; s1++) {
			if(requiredDp[s1]) {
				int s2 = target - s1;
				minimum = Math.min(minimum, Math.abs(s1-s2));
			}
		}
		System.out.println(minimum);
	}
		
	
	/**
	 *  Time Complexity - O(N*target)
	 *  Space Complexity - O(N*target)
	 */
	private static void subsetSumEqualsToTargetDp(int[] arr, int k, boolean dp[][]) {
	    int n = arr.length;
	    
		//If target == 0, ind can take any value from 0 to n-1, 
		//therefore we need to set the value of the first column as true.
	    for(int i=0; i<n; i++) {
	        dp[i][0] = true;
	    }
	    
	    if(arr[0]<=k)
	        dp[0][arr[0]] = true;
	    
	    for(int ind = 1; ind<n; ind++) {
	        for(int target= 1; target<=k; target++){
	            
	            boolean notTaken = dp[ind-1][target];
	    
	            boolean taken = false;
	                if(arr[ind]<=target)
	                    taken = dp[ind-1][target-arr[ind]];
	        
	            dp[ind][target]= notTaken||taken;
	        }
	    }
	}
	
}
