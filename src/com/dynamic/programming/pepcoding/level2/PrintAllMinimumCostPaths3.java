package com.dynamic.programming.pepcoding.level2;

import java.util.ArrayDeque;

/**
 * 1. You are given a number n, representing the number of rows.
 * 
 * 2. You are given a number m, representing the number of columns.
 * 
 * 3. You are given n*m numbers, representing elements of 2d array a, which
 * represents a maze.
 * 
 * 4. You are standing in top-left cell and are required to move to bottom-right
 * cell.
 * 
 * 5. You are allowed to move 1 cell right (h move) or 1 cell down (v move) in 1
 * motion.
 * 
 * 6. Each cell has a value that will have to be paid to enter that cell (even
 * for the top-left and bottom-right cell).
 * 
 * 7. You are required to traverse through the matrix and print the cost of the
 * path which is least costly.
 * 
 * 8. Also, you have to print all the paths with minimum cost.
 *
 */
public class PrintAllMinimumCostPaths3 {
	
	private static class Pair {
		int i;
		int j;
		String psf;
		
		public Pair(String psf, int i, int j) {
			this.psf = psf;
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) {
		int[][] arr = new int[][] { 
				{ 2, 6, 1, 1, 3 }, 
				{ 9, 1, 1, 0, 5 }, 
				{ 0, 7, 5, 2, 0 }, 
				{ 4, 3, 0, 4, 7 },
				{ 2, 0, 1, 4, 1 } 
	     };

		PrintAllMinimumCostPaths3 obj = new PrintAllMinimumCostPaths3();
		obj.minimumCostPaths(arr);
	}

	private void minimumCostPaths(int[][] arr) {
		 int[][] dp = new int[arr.length][arr[0].length];
		 for(int i = dp.length-1; i>=0; i--) {
			 for(int j=dp[0].length-1; j>=0; j--) {
				 if(i==dp.length-1 && j==dp[0].length -1) { //last cell(where problem is smaller)
					 dp[i][j] = arr[i][j];
				 }
				 
				 else if(i==dp.length-1) { //if cell is last row
					 dp[i][j] = arr[i][j] + dp[i][j+1];
				 }
				 
				 else if(j==dp[0].length-1) {  //if cell is last column
					 dp[i][j] = arr[i][j] + dp[i+1][j];
				 } 
				 
				 else { //other than above conditions.
					 dp[i][j] = arr[i][j] + Math.min(dp[i][j+1], dp[i+1][j]);
				 }
			 }
		 }
		 //Display DP
		 printTwoDimensionArray(dp);
		 System.out.println("Minimum cost is -- " +dp[0][0]);
		 
		 //print minimum cost sum path
		 minimumCostSumPath(dp);
	}
	
	
	private void minimumCostSumPath(int[][] dp) {
		
		ArrayDeque<Pair> q =new ArrayDeque<>();
		q.add(new Pair("", 0, 0)); //add first element in queue
		
		 while(!q.isEmpty()) {
			 Pair rem = q.removeFirst();
			 
			 if(rem.i==dp.length-1 && rem.j==dp[0].length -1) { //last cell(where problem is smaller)
				 System.out.println(rem.psf);
			 }
			 
			 else if(rem.i==dp.length-1) { //if cell is last row
				 q.add(new Pair(rem.psf + "H", rem.i, rem.j+1));
			 }
			 
			 else if(rem.j==dp[0].length-1) {  //if cell is last column
				 q.add(new Pair(rem.psf + "V", rem.i +1, rem.j));
			 } 
			 
			 else { //other than above conditions.
				  if(dp[rem.i][rem.j +1] < dp[rem.i + 1][rem.j]) {
					  q.add(new Pair(rem.psf + "H", rem.i, rem.j+1));
				  } else if(dp[rem.i][rem.j +1] > dp[rem.i + 1][rem.j]) {
					  q.add(new Pair(rem.psf + "V", rem.i +1, rem.j));
				  } else {
					  q.add(new Pair(rem.psf + "H", rem.i, rem.j+1));
					  q.add(new Pair(rem.psf + "V", rem.i +1, rem.j));
				  }
			 }
			 
		 }
		
	}

	private void printTwoDimensionArray(int[][] arr) {
		for(int i=0; i<=arr.length-1; i++) {
			for(int j=0; j<=arr[0].length-1; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("\n");
		}
	}
}
