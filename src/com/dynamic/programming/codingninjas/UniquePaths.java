package com.dynamic.programming.codingninjas;

/**
 * This is also leetcode problem. 62 - Unique Paths
 *
 * There is a robot on an m x n grid. The robot is initially located at the
 * top-left corner (i.e., grid[0][0]). The robot tries to move to the
 * bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
 * either down or right at any point in time.
 * 
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 * 
 * The test cases are generated so that the answer will be less than or equal to
 * 2 * 109.
 * 
			Input: m = 3, n = 2
			Output: 3
			Explanation: From the top-left corner, there are a total of 3 ways to reach the 
			                   bottom-right corner:
			1. Right -> Down -> Down
			2. Down -> Down -> Right
			3. Down -> Right -> Down
 */
public class UniquePaths {
	
	 /**
	 * Time complexity -- m*n
     * Space complexity -- m*n
	 */
	public static int uniquePathsSpaceOptimization(int m, int n) {
	        
		 int[] previous = new int[n];
	        
	        for(int i=0; i<m; i++) {
	        	int[] current = new int[n];
	            for(int j=0; j<n; j++) {
	                if(i == 0 || j == 0) {
	                	current[j] = 1;
	                    continue;
	                } else {
	                    int right = previous[j];
	                    int down = current[j-1];
	                    
	                    current[j] = right + down;
	                }
	            }
	            previous = current;
	        }
	        return previous[n-1];
		 
	 }
    /**
     * Time complexity -- m*n
     * Space complexity -- m*n
     */
    public static int uniquePaths(int m, int n) {
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                
                int right = dp[i-1][j];
                int down = dp[i][j-1];
                
                dp[i][j] = right + down;
            }
            
        }
        return dp[m-1][n-1];
    }
    
    /**
     * TC - o(2 pow m*n)
     * SC -> O(m-1 + n-1)
     */
    public static int uniquePathsRecursion(int m, int n) {
    	if(m==0 &&  n==0) return 1;
    	if(m<0 || n< 0) return 0;
    	
    	int left = uniquePathsRecursion(m-1, n);
    	int right = uniquePathsRecursion(m, n-1);
    	return left + right;
    }

    /**
      * TC - o(m*n)
     * SC -> O((m-1) + (n-1)) +  O(m*n)
     */
    public static int uniquePathsRecursionDP(int m, int n, int[][] dp) {
    	if(m==0 &&  n==0) return 1;
    	if(m<0 || n< 0) return 0;
    	if(dp[m][n] != -1) return dp[m][n];
    	
    	int left = uniquePathsRecursionDP(m-1, n, dp);
    	int right = uniquePathsRecursionDP(m, n-1, dp);
    	return dp[m][n] = left + right;
    }
    
    
    public static void main(String[] args) {
    	System.out.println(uniquePaths(3,7)); //expected is 28.
    	System.out.println(uniquePathsSpaceOptimization(3,7)); //expected is 28.
    	int m =3, n=3;
    	System.out.println("uniquePathsRecursion  "+ uniquePathsRecursion(m-1,n-1)); //expected is 6.
    	int[][] dp = new int[m][n];
    	fillArrayWithMinusONe(m, n, dp);
		System.out.println("uniquePathsRecursionDP  "+uniquePathsRecursionDP(m-1,n-1, dp)); //expected is 6.
    	
		System.out.println("uniquePaths  "+uniquePaths(3,3)); //expected is 6.
    	System.out.println("uniquePathsSpaceOptimization  "+ uniquePathsSpaceOptimization(3,3)); //expected is 6.
    }
    
	private static void fillArrayWithMinusONe(int m, int n, int[][] dp) {
		for(int i=0; i<m;i++) {
    		for(int j=0; j<n; j++) {
    			dp[i][j] = -1;
    		}
    	}
	}
	
}
