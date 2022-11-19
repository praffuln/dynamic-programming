package com.dynamic.programming.codingninjas;

import java.util.Arrays;

/**
 * This problem is 64. Minimum Path Sum
 * 
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right, which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 */
public class MinimumPathSum {

	
	public static int minPathSumSpaceOptimization(int[][] grid) {
	       int R = grid.length;
	        int C = grid[0].length;
	        int[] previous =new int[C];
	        
	       //assign first element 
	        previous[0] = grid[0][0];
	      
 
	        //fill column
	        for(int i=1;i<C;i++) {
	        	previous[i]  = previous[i-1] + grid[0][i]; 
	        }
	        
	        System.out.println(Arrays.toString(previous));
	        
	        //run dp [in space optimization start loop from 0]
	        for (int i = 0; i < R; i++) {
	        	int[] current = new int[C];
	            for (int j = 0; j < C; j++) {
	            	if(j > 0) {
	            		current[j] = grid[i][j] + Math.min(previous[j], current[j-1]);
	            	} else {
	            		current[j] = grid[i][j];
	            	}
	            }
	            previous = current;
	        }
	        
	        return previous[C-1];
	}
	
	
	public static int minPathSum(int[][] grid) {
	       int R = grid.length;
	        int C = grid[0].length;
	        int[][] dp =new int[R][C];
	        
	       //assign first element 
	        dp[0][0] = grid[0][0];
	      
	        //fill row
	        for(int i=1;i<C;i++) {
	        	dp[0][i]  = dp[0][i-1] + grid[0][i]; 
	        }
	        
	        //fill column
	        for(int i=1;i<R;i++) {
	        	dp[i][0]  = dp[i-1][0] + grid[i][0]; 
	        }
	        
	        printTable(dp);
	        
	        //run dp
	        for (int i = 1; i < R; i++) {
	            for (int j = 1; j < C; j++) {
	                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
	            }
	        }
	        
	        return dp[R-1][C-1];
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(minPathSum(grid1)); // expected 7
		
		int[][] grid2 = { { 1,2,3 }, { 4,5,6 } };
		System.out.println(minPathSum(grid2)); // expected 12
		
		System.out.println("\n\n space optimization \n\n");
		System.out.println(minPathSumSpaceOptimization(grid1)); // expected 7
		System.out.println(minPathSumSpaceOptimization(grid2)); // expected 12
	}

	
    private static void printTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + "  ");
            }
            System.out.println("");
        }

    }
}
