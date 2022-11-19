package com.dynamic.programming.codingninjas;

/**
 * You are given an m x n integer array grid. There is a robot initially located
 * at the top-left corner (i.e., grid[0][0]). The robot tries to move to the
 * bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
 * either down or right at any point in time.
 * 
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that
 * the robot takes cannot include any square that is an obstacle.
 * 
 * Return the number of possible unique paths that the robot can take to reach
 * the bottom-right corner.
 *
 * Same as 63. Unique Paths II --- Leetcode
 */
public class MazeObstacles {
	
	 /**
	 * Time complexity -- m*n
    * Space complexity -- m*n
	 */
	public static int uniquePathsWithObstaclesSpaceOptimization(int[][] obstacleGrid, int m, int n) {
	        
		 int[] previous = new int[n];
	        
	        for(int i=0; i<m; i++) {
	        	
	        	int[] current = new int[n];
	        	
	            for(int j=0; j<n; j++) {
	            	
	            	//obstacles handle
	            	if(obstacleGrid[i][j] == 1) {
	            		current[j] = 0;
	            		continue;
	            	}
	            	
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
		
		
//		int[][] dp = new int[obstacleGrid.length+1][obstacleGrid[0].length+1];
//        dp[1][1] = obstacleGrid[0][0] != 1 ?  1 : 0;
//        for(int i=1; i<dp.length; i++) {
//            for(int j=1; j<dp[0].length; j++) {
//                dp[i][j] +=  obstacleGrid[i-1][j-1] != 1 ? dp[i][j-1] + dp[i-1][j] : 0;
//            }
//        } 
//        return dp[obstacleGrid.length][obstacleGrid[0].length]; 
		
	 }
   /**
    * Time complexity -- m*n
    * Space complexity -- m*n
    */
public static int uniquePathsWithObstacles(int[][] obstacleGrid, int m, int n) {
        
 

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }

   
   public static void main(String[] args) {
	  int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
   	System.out.println(uniquePathsWithObstacles(obstacleGrid, obstacleGrid.length,obstacleGrid[0].length)); //expected is 2.
   	System.out.println(uniquePathsWithObstaclesSpaceOptimization(obstacleGrid, obstacleGrid.length,obstacleGrid[0].length)); //expected is 2.
   
//   	int[][] obstacleGrid1 = {{1,0}};
//   	System.out.println(uniquePathsWithObstaclesSpaceOptimization(obstacleGrid1, obstacleGrid1.length,obstacleGrid1[0].length)); //expected is 0.

   }
	
}
