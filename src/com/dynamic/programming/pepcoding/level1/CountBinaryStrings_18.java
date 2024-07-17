package com.dynamic.programming.pepcoding.level1;

/**
 * no of binary strings of length n which has o consecutive zeros.
 * 
 * if N=2
 *   then answer will be 3
 *    0 1
 *    1 1
 *    1 0
 *    0 0 -> this is not be part of answer.
 *    
 *    If N=3
 *    then aswer will be 5
 *    0 1 1
 *    1 1 1
 *    1 0 1
 *    1 1 0
 *    0 1 0
 *
 */
public class CountBinaryStrings_18 {

	public static void main(String[] args) {
		CountBinaryStrings_18 obj = new CountBinaryStrings_18();
		obj.countBinaryStrings(3);
	}

	private void countBinaryStrings(int count) {
		int previous = -1;
		int result = countBinaryStringUtil(count - 1, previous);
		System.out.println("recursion - " + result);
	
		Integer[][] dp = new Integer[count+1][2];
		//base case
		dp[0][0] = 1;
		dp[0][1] = 2;
		
		for(int index=1;index<=count-1;index++) {
			for(int prev=0; prev<=1;prev++) {
				//operation
				if (prev == 0) {
					dp[index][prev] = dp[index - 1][1];
				} else {
					dp[index][prev] = dp[index - 1][0] + dp[index - 1][1];
				}
			}
		}
 
//		System.out.println(Arrays.toString(dp[count-1]));
		System.out.println("dp - " + dp[count-1][1]);
	}
	
	
//	private int countBinaryStringUtilDp(int index, int previous) {
//		//base case
//		if(index == 0) {
//			return (previous == 0) ? 1 : 2;
//		}
//		 
//		
//		//operation
//		if (previous == 0) {
//			return  dp[index][previous] = countBinaryStringUtilDp(index - 1, 1, dp);
//		} else {
//			int calculation = countBinaryStringUtilDp(index - 1, 0, dp) + countBinaryStringUtilDp(index - 1, 1, dp);
//			if(previous != -1) {
//				dp[index][previous] = calculation;
//			}
//			return calculation;	
//		   }
//	}
	
	private int countBinaryStringUtil(int index, int previous) {
		//base case
		if(index == 0) {
			return (previous == 0) ? 1 : 2;
		}
		
		//operation
		if (previous == 0) {
			return countBinaryStringUtil(index - 1, 1);
		} else {
			return countBinaryStringUtil(index - 1, 0) + countBinaryStringUtil(index - 1, 1);
		}
	}
}
