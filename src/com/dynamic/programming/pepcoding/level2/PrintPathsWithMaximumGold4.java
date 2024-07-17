package com.dynamic.programming.pepcoding.level2;

import java.util.ArrayDeque;

public class PrintPathsWithMaximumGold4 {
	
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
				{3,  2, 3, 1 }, 
				{ 2, 4, 6, 0 }, 
				{ 5, 0, 1, 3}, 
				{ 9, 1, 5, 1 }
	     };

	     PrintPathsWithMaximumGold4 obj = new PrintPathsWithMaximumGold4();
		obj.maximumGold(arr);
	}

	private void maximumGold(int[][] arr) {
		 int[][] dp = new int[arr.length][arr[0].length];
		 
		 //fill the last column
		 for(int i=0; i<arr.length; i++) {
			 dp[i][arr[0].length-1] = arr[i][arr[0].length-1];
		 }
		 
		 for(int j=dp[0].length-2; j>=0; j--) { //column
			 for(int i = 0; i<=arr.length-1; i++) { //row
				 
				 if(i==0) { //if cell is first row
					 dp[i][j] = arr[i][j] + Math.max(dp[i][j+1], dp[i+1][j+1]);
				 }
				 
				 else if(i==dp.length-1) {  //if cell is last row
					 dp[i][j] = arr[i][j] + Math.max(dp[i-1][j+1], dp[i][j+1]);
				 } 
				 
				 else { //other than above conditions.
					 dp[i][j] = arr[i][j] + Math.max(dp[i-1][j+1], Math.max(dp[i][j+1], dp[i+1][j+1]));
				 }
			 }
		 }
		 //Display DP
		 printTwoDimensionArray(dp);
		 System.out.println("Maximum gold collected.....................");
		  
		 System.out.println("Minimum cost is -- ");
		 
		 //print minimum cost sum path
		 maximumGoldCollectionPath(dp);
	}
	
	
	private void maximumGoldCollectionPath(int[][] dp) {
		
		int max = dp[0][0];
		ArrayDeque<Pair> q =new ArrayDeque<>();
		 
		for(int i=0; i<dp.length; i++) {
			if(max > dp[i][0]) {
				max = dp[i][0];
			}
		}
		
		for(int i=0; i<dp.length; i++) {
			if(dp[i][0] == max) {
				q.add(new Pair(i + "", i, 0)); //add max elements in queue
			}
		}
		
		
		 while(!q.isEmpty()) {
			 Pair rem = q.removeFirst();
			 
			 if(rem.j==dp[0].length -1) { //last row.
				 System.out.println(rem.psf);
			 }
			 
			 else if(rem.i==0) { //if cell is last row
				 int maxi = Math.max(dp[rem.i][rem.j+1], dp[rem.i +1][rem.j +1]);
				 
				 if(maxi == dp[rem.i][rem.j+1]) {
					 q.add(new Pair(rem.psf + " d2", rem.i, rem.j+1));
				 }
				 if(maxi == dp[rem.i +1][rem.j +1]) {
					 q.add(new Pair(rem.psf + " d3", rem.i +1, rem.j+1));
				 }
				 
				 
			 }
			 
			 else if(rem.i==dp.length-1) { //if cell is last row
				 int maxi = Math.max(dp[rem.i][rem.j+1], dp[rem.i -1][rem.j +1]);
				 
				 if(maxi == dp[rem.i][rem.j+1]) {
					 q.add(new Pair(rem.psf + " d2", rem.i, rem.j+1));
				 }
				 if(maxi == dp[rem.i -1][rem.j +1]) {
					 q.add(new Pair(rem.psf + " d1", rem.i -1, rem.j+1));
				 }
				 
			 }
			 
			 else { //other than above conditions.
				 int maxi = Math.max(dp[rem.i][rem.j+1], Math.max(dp[rem.i -1][rem.j +1], 
						 dp[rem.i+1][rem.j+1]));
				 
				 if(maxi == dp[rem.i][rem.j+1]) {
					 q.add(new Pair(rem.psf + " d2", rem.i, rem.j+1));
				 }
				 if(maxi == dp[rem.i -1][rem.j +1]) {
					 q.add(new Pair(rem.psf + " d1", rem.i -1, rem.j+1));
				 }
				 
				 if(maxi == dp[rem.i +1][rem.j +1]) {
					 q.add(new Pair(rem.psf + " d3", rem.i +1, rem.j+1));
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
	
	private int calculateMaxInArray(int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int i=0; i<=arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		
		return max;
	}
}
