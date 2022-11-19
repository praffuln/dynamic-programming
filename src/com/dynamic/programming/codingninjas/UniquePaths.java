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
	        	int[] temp = new int[n];
	            for(int j=0; j<n; j++) {
	                if(i == 0 || j == 0) {
	                	temp[j] = 1;
	                    continue;
	                } else {
	                    int right = previous[j];
	                    int down = temp[j-1];
	                    
	                    temp[j] = right + down;
	                }
	            }
	            previous = temp;
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
    
    public static void main(String[] args) {
    	System.out.println(uniquePaths(3,7)); //expected is 28.
    	System.out.println(uniquePathsSpaceOptimization(3,7)); //expected is 28.
    }
	
}
