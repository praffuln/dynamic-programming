package com.dynamic.programming.pepcoding.level2;

import java.util.ArrayDeque;

/**
 * 1. You are given a number n, representing the count of elements.
 * 
 * 2. You are given n numbers.
 * 
 * 3. You are given a number "tar".
 * 
 * 4. You are required to calculate and print true or false, if there is a
 * subset the elements of which add up to "tar" or not.
 * 
 * 5. Also, you have to print the indices of elements that should be selected to
 * achieve the given target.
 * 
 * 6. You have to print all such configurations.
 * 
 */
public class PrintSubsetsWithTargetSum5 {

	public static void main(String[] args) {
		int[] arr = { 4, 2, 7, 1, 3 };
		int target = 10;
		PrintSubsetsWithTargetSum5 obj = new PrintSubsetsWithTargetSum5();
		boolean dp[][] = obj.findSubsetsWithTargetSum(arr, target);
		obj.printTwoDimensionArray(dp);
		
		obj.findPath(dp, target,arr);
	}

	private void findPath(boolean[][] dp, int target, int[] arr) {
		ArrayDeque<Pair> q =new ArrayDeque<>();
		q.add(new Pair(dp.length, target, ""));
		
		while(!q.isEmpty()) {
			Pair rem = q.removeFirst();
			
			if(rem.i ==0 && rem.j == 0) {
				System.out.println(rem.psf);
			} else {
				boolean exc = false;
				if(rem.i - 1 >= 0)
					exc = dp[rem.i - 1][rem.j];
				if (exc)
					q.add(new Pair(rem.i - 1, rem.j, rem.psf));

				if (rem.i - 1 >=0 && rem.j - arr[rem.i - 1] >= 0) {
					boolean inc = dp[rem.i - 1][rem.j - arr[rem.i - 1]];
					if (inc) {
						q.add(new Pair(rem.i - 1, rem.j - arr[rem.i - 1], rem.psf + " " + (rem.i-1)));
					}
				}
			}
		}
		
	}

	private boolean[][] findSubsetsWithTargetSum(int[] arr, int k) {
	    int n = arr.length;
		boolean dp[][]= new boolean[n][k+1];
	    
		//If target == 0, ind can take any value from 0 to n-1, 
		//therefore we need to set the value of the first column as true.
	    for(int i=0; i<n; i++) {
	        dp[i][0] = true;
	    }
	    
	    if(arr[0]<=k)
	        dp[0][arr[0]] = true;
	    
	    for(int ind = 1; ind<n; ind++) {
	        for(int target= 1; target<=k; target++){
	            
	            boolean notTaken = dp[ind-1][target];
	    
	            boolean taken = false;
	                if(arr[ind]<=target)
	                    taken = dp[ind-1][target-arr[ind]];
	        
	            dp[ind][target]= notTaken||taken;
	        }
	    }
	    return dp;
	}

	private void printTwoDimensionArray(boolean[][] arr) {
		for (int i = 0; i <= arr.length - 1; i++) {
			for (int j = 0; j <= arr[0].length - 1; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

	private static class Pair {
		int i;
		int j;
		String psf;

		public Pair(int i, int j, String psf) {
			this.psf = psf;
			this.i = i;
			this.j = j;
		}
	}
}
