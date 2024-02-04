package com.dynamic.programming.codingninjas;

/**
 * This problem is 120. Triangle
 * 
 * For each step, you may move to an adjacent number of the row below. More
 * formally, if you are on index i on the current row, you may move to either
 * index i or index i + 1 on the next row.
 * 
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
	Output: 11
	Explanation: The triangle looks like:
	  2
	  3 4
	 6 5 7
	4 1 8 3
	The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * 
 * Input: triangle = [[-10]]
	Output: -10
 * 
 */
public class Triangle {

	/**
	 *  Time complexity -- O(N*M)
	 *  SPACE complexity -- (n) 
	 */
	public static int minPathSumSpaceOptimization(int[][] triangle) {
	       int R = triangle.length;
	        int C = triangle[R-1].length;
//	        int[][] dp =new int[R][C]; 
	        int[] dp = new int[C];
		
		//as below line is variable, we don't know at which i index will be minimum sum
		// so in dp array, fill first base case from bottom.
		for(int j=0; j< C; j++) {
			dp[j] = triangle[C-1][j];
		}
		
		//now go to reverse from n-2 to 0. As we have variable ending point in bottom. 
		for(int i=R-2; i>=0; i--) {
			int[] curr = new int[C];
			for(int j= i; j>=0; j--) { //j starts from i as it is triangle
				
				int dpUp = triangle[i][j] + dp[j]; 
				int dpSide = triangle[i][j] + dp[j+1];
				
				curr[j] = Math.min(dpUp, dpSide);
			}
			dp = curr;
		}
		
//		printTable(dp);
		return dp[0];
		
	}
	
	/**
	 *  Time complexity -- O(N*M)
	 *  SPACE complexity -- (m * n) 
	 */
	public static int minPathSum(int[][] triangle) {
	       int R = triangle.length;
	        int C = triangle[R-1].length;
	        int[][] dp =new int[R][C]; 
		
		//as below line is variable, we don't know at which i index will be minimum sum
		// so in dp array, fill first base case from bottom.
		for(int j=0; j< C; j++) {
			dp[C-1][j] = triangle[C-1][j];
		}
		printTable(dp);
		
		//now go to reverse from n-2 to 0. As we have variable ending point in bottom. 
		for(int i=R-2; i>=0; i--) {
			for(int j= i; j>=0; j--) { //j starts from i as it is triangle
				
 				int dpUp = triangle[i][j] + dp[i+1][j]; 
 				int dpSide = triangle[i][j] + dp[i+1][j+1];
 				
				dp[i][j] = Math.min(dpUp, dpSide);
			}
		}
		printTable(dp);
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		int[][] grid0 = { {2},{3,4},{6,5,7},{4,1,8,3}};
		System.out.println("\n minPathSumRecursion is - "+ minPathSumRecursion(grid0)); // expected 11
		
		int[][] grid1 = { {2},{3,4},{6,5,7},{4,1,8,3}};
		System.out.println("\n minPathSum is - "+ minPathSum(grid1)); // expected 11
		
		System.out.println("\n");
		int[][] grid2 = { {-10}};
		System.out.println("\n minPathSum is - "+ minPathSum(grid2)); // expected -10
		
		
		System.out.println("\n minPathSum space complexity is - "+ minPathSumSpaceOptimization(grid1)); // expected 11
		System.out.println("\n");
		System.out.println("\n minPathSum space complexity is - "+ minPathSumSpaceOptimization(grid2)); // expected -10
	}
	
	
    private static int minPathSumRecursion(int[][] a) {
    	int R = a.length;
        int C = a[R-1].length; 
        return minPatshSumRecursionUtil(a, 0, 0, R, C);
	}

	/**
	 * TC = 2 power (1+2+3 ..... + (n-1))
	 * SC = O(n)
	 * 
	 * Memorization - 
	 * TC = O(N* N)
	 * SC = O(N*N) + O(N) 
	 */
	private static int minPatshSumRecursionUtil(int[][] a, int i, int j, int r, int c) {
		if(i == r-1) return a[i][j]; //there is only 1 base case
		int down = a[i][j] + minPatshSumRecursionUtil(a, i+1, j, r,c);
		int diagonal = a[i][j] + minPatshSumRecursionUtil(a, i+1, j+1, r,c);
		
		return Math.min(down, diagonal);
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
