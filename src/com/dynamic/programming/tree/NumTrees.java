package com.dynamic.programming.tree;

import java.util.Arrays;

public class NumTrees {
	public static int numTrees(int n) {
        if(n ==0) return 1;
        
        int result =0;
        for(int i=1; i<=n; i++) {
        	result += numTrees(i-1) *  numTrees(n-i);
        }
		
		return result;
    }
	
	public static int numTreesDp(int n) {
		int[] dp = new int[n+1];
		Arrays.fill(dp, 1);
		dp[0] = 1;
        
        int result =0;
        for(int i=1; i<=n; i++) {
        	dp[i]  = dp[i-1] *  dp[n-i];
        }
		
		return dp[n];
    }
	
	
	public static void main(String[] args) {
		System.out.println(numTrees(4));
		System.out.println(numTreesDp(4));
	}
}
