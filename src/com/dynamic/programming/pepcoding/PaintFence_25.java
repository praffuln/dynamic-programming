package com.dynamic.programming.pepcoding;

/**
 * 1. You are given a number n and a number k in separate lines, representing
 * the number of fences and number of colors.
 * 
 * 2. You are required to calculate and print the number of ways in which the
 * fences could be painted so that not more than two fences have same colors.
 *
 * https://leetcode.com/problems/cousins-in-binary-tree-ii/description/
 * https://leetcode.com/problems/cousins-in-binary-tree/submissions/1172736747/
 */
public class PaintFence_25 {

	public static void main(String[] args) {
		int n =5; 
		int k =3;
		int paintFence = paintFence(n,k);
		System.out.println(paintFence);
		
		int[] dp = new int[]{-1, -1, -1, -1, -1,-1};

		paintFence = paintFenceDp(n,k, dp);
		System.out.println(paintFence);
		
		paintFence =  paintFenceDpIterative(n,k);
		System.out.println(paintFence);
	}

	private static int paintFence(int n, int k) {
		if(n==1) return k;
		if(n==2) return k+ (k*(k-1));
		
		//last 2 same 
		int same = paintFence(n-2, k)*(k-1)*1;
		int difference = paintFence(n-1, k)*(k-1);
		
		return same + difference;
	}
	
	private static int paintFenceDp(int n, int k, int[] dp) {
		if(n==1) return k;
		if(n==2) return k+ (k*(k-1));
		if(dp[n] != -1) return dp[n];
		
		//last 2 same 
		int same = paintFence(n-2, k)*(k-1)*1;
		int difference = paintFence(n-1, k)*(k-1);
		
		return dp[n] = same + difference;
	}
	
	private static int paintFenceDpIterative(int n, int k) {
		int[] dp = new int[]{-1, -1, -1, -1, -1,-1};
		dp[0] = k; 
		dp[1] = (k+(k*(k-1)));
		
		for(int i=2; i<=n; i++) {
			int same = paintFence(n-2, k)*(k-1)*1;
			int difference = paintFence(n-1, k)*(k-1);
			dp[n] = same + difference;
		}
		
		return dp[n];
	}
	
}
