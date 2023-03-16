package com.dynamic.programming.codingninjas;

/**
 * 931. Minimum Falling Path Sum
 * 
 * Given an n x n array of integers matrix, return the minimum sum of any
 * falling path through matrix.
 * 
 * A falling path starts at any element in the first row and chooses the element
 * in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col
 * - 1), (row + 1, col), or (row + 1, col + 1).
 *
 * 
 * 
 */
public class MinimumMaximumFallingPathSum {
	/**
	 * TODO: Fix me.
	 * 
	 *  Time complexity -- O(N*M)
	 *  SPACE complexity -- (n) 
	 */
	public static int minPathSumSpaceOptimization(int[][] triangle) {

	       int R = triangle.length;
	        int C = triangle[R-1].length;
	        //int[][] dp =new int[R][C]; 
	        int[] dp = new int[C];
		//as below line is variable, we don't know at which i index will be minimum sum
		// so in dp array, fill first base case from bottom.
		for(int j=0; j< C; j++) {
			dp[j] = triangle[0][j];
		}
		 
		
		for(int i=1; i<=R-1; i++) {
			int[] previous = new int[C];
			for(int j= 0; j<=C-1; j++) { //j starts from 0 as it is triangle
				
				int up = triangle[i][j] + dp[j];
				int left = Integer.MAX_VALUE;
				if(j-1>=0) left = triangle[i][j] + dp[j-1];
				int right = Integer.MAX_VALUE;
				if(j+1<C) right = triangle[i][j] + dp[j+1];
				
				previous[j] = calculateMinOfThree(up, left, right);
			}
			dp = previous;
		}
		System.out.println("\n");
		
		//Min from below dp
	    int min = dp[0];
	    for(int i=1;i<=C-1;i++) 
	    	min = Math.min(min, dp[i]);
		return min;
	
	}
	
	/**
	 *  Time complexity -- O(N*M) + O(M)
	 *  SPACE complexity -- O( n) 
	 */
	public static int minPathSum(int[][] triangle) {
	       int R = triangle.length;
	        int C = triangle[R-1].length;
	        int[][] dp =new int[R][C]; 
		
		//as below line is variable, we don't know at which i index will be minimum sum
		// so in dp array, fill first base case from bottom.
		for(int j=0; j< C; j++) {
			dp[0][j] = triangle[0][j];
		}
		printTable(dp);
		
		for(int i=1; i<=R-1; i++) {
			for(int j= 0; j<=C-1; j++) { //j starts from 0 as it is triangle
				
 				int up = triangle[i][j] + dp[i-1][j];
 				int left = Integer.MAX_VALUE;
 				if(j-1>=0) left = triangle[i][j] + dp[i-1][j-1];
 				int right = Integer.MAX_VALUE;
 				if(j+1<C) right = triangle[i][j] + dp[i-1][j+1];
 				
				dp[i][j] = calculateMinOfThree(up, left, right);
			}
		}
		System.out.println("\n");
		printTable(dp);
		//Min from below dp
	    int min = dp[R-1][0];
	    for(int i=1;i<=C-1;i++) 
	    	min = Math.min(min, dp[R-1][i]);
		return min;
	}

	private static int calculateMinOfThree(int up, int left, int right) {
		return Math.min(up,Math.min(left, right));
	}
	
	public static void main(String[] args) {
		int[][] grid1 = {{2,1,3},{6,5,4},{7,8,9}};
		System.out.println("\n minPathSum is - "+ minPathSum(grid1)); // expected 11
		
		System.out.println("\n");
		int[][] grid2 = { {-19,57},{-40,-5}};
		System.out.println("\n minPathSum is - "+ minPathSum(grid2)); // expected -10
		
		System.out.println("\n");
		int[][] grid3 = { {17,82},{1,-44}};
		System.out.println("\n minPathSum is - "+ minPathSum(grid3)); // expected -10
		
		System.out.println("\n minPathSum space complexity is - "+ minPathSumSpaceOptimization(grid1)); // expected 11
		System.out.println("\n");
		System.out.println("\n minPathSum space complexity is - "+ minPathSumSpaceOptimization(grid2)); // expected -10
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
