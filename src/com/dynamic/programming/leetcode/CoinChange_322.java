package com.dynamic.programming.leetcode;
//https://leetcode.com/problems/coin-change/description/

//TODO: change me into DP and DP Optimization with space
 

/**
 * @author hp
 *
 */
public class CoinChange_322 {

	public static void main(String[] args) {
		int[] coins = new int[] { 1,2,5 };
		int amount = 11;

		int answer = coinChange(coins, amount);
		System.out.println(answer);
		 
	}

	   public static int findLowestCoins(int[] coins, int cur, int amount) {
		    
		   //return MAX value as we are interested in min count.
	        if (cur >= coins.length)
	            return Integer.MAX_VALUE - 1;   
	        
	        //return 0 if amount is 0, as zero count has no effect on count.
	        if(amount == 0) 
	        	return 0;
	        
	        int res = -1;
	        if (coins[cur] > amount) {
	            int doNotTakeCoin = 0 + findLowestCoins(coins, cur + 1, amount - 0);
	            res = doNotTakeCoin;
	        }
	        else {
	            int takeCoin = 1 + findLowestCoins(coins, cur + 0, amount - coins[cur]);
	            int doNotTakeCoin = 0 + findLowestCoins(coins, cur + 1, amount - 0);
	            res = Math.min(takeCoin, doNotTakeCoin);
	        }
	        return res;
	    }
	    
	   public static int findLowestCoinsDp(int[] coins, int[][] dp, int cur, int amount) {
		    
		   //return MAX value as we are interested in min count.
	        if (cur >= coins.length)
	            return Integer.MAX_VALUE - 1;   
	        
	        //return 0 if amount is 0, as zero count has no effect on count.
	        if(amount == 0) 
	        	return 0;
	        
	        if(dp[cur][amount] != -1) return dp[cur][amount];
	        
	        int res = -1;
	        if (coins[cur] > amount) {
	            int doNotTakeCoin = 0 + findLowestCoinsDp(coins, dp, cur + 1, amount - 0);
	            res = doNotTakeCoin;
	        }
	        else {
	            int takeCoin = 1 + findLowestCoinsDp(coins, dp, cur + 0, amount - coins[cur]);
	            int doNotTakeCoin = 0 + findLowestCoinsDp(coins,dp, cur + 1, amount - 0);
	            res = Math.min(takeCoin, doNotTakeCoin);
	        }
	        dp[cur][amount] = res;
	        return res;
	    }	   
	   
	    public static int coinChange(int[] coins, int amount) {
	        int res = findLowestCoins(coins, 0, amount);
	        int ans  = res == (Integer.MAX_VALUE - 1 ) ? -1 : res;
	        System.out.println("recursion - " + ans);
	        
	        int[][] dp = new int[coins.length][amount+1];
	        initializeDpTableWithMinusOne(dp);
	        
	        res = findLowestCoinsDp(coins, dp, 0, amount);
	        ans  = res == (Integer.MAX_VALUE - 1 ) ? -1 : res;
	        System.out.println("recursion DP- " + ans);
	        
	        return ans;
	    }
	    
	    private static void initializeDpTableWithMinusOne(int[][] dp) {
	        for (int i=0; i< dp.length; i++)
	            for (int j=0; j< dp[0].length; j++)
	                dp[i][j] = -1;
	    }
}
