package com.dynamic.programming.pepcoding.level1;

import java.util.Arrays;

/**
 * 
 * 1. You are given a number n, representing the number of houses.
 * 
 * 2. In the next n rows, you are given
 * 
 * 3 space separated numbers representing the cost of painting nth house with
 * red or blue or green color. 3. You are required to calculate and print the
 * minimum cost of painting all houses without painting any consecutive house
 * with same color.
 * 
 * https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/submissions/1167344122/
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/
 * 
 * 
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
 * 
 */
public class PaintHouse_23 {

	public static void main(String[] args) {
		PaintHouse_23 obj = new PaintHouse_23();
		obj.paintHouses();

		int[][] arr = { { 1, 5, 3, 1 }, // color to 4 houses by red
				{ 5, 8, 2, 2 }, // color to 4 houses by green
				{ 7, 4, 9, 4 }, // color to 4 houses by blue
		};
		new PaintHouse_23().paintHousesUtilDp(arr);
	}

	private void paintHouses() {
		int[][] arr = { { 1, 5, 3, 1 }, // color to 4 houses by red
				{ 5, 8, 2, 2 }, // color to 4 houses by green
				{ 7, 4, 9, 4 }, // color to 4 houses by blue
		};
		System.out.println(arr.length);
		System.out.println(arr[0].length);

		int red_color_result = paintHousesUtil(arr, arr[0].length - 1, 0);
		int green_color_result = paintHousesUtil(arr, arr[0].length - 1, 1);
		int blue_color_result = paintHousesUtil(arr, arr[0].length - 1, 2);

		// min of all is min cost
		int minimumCost = Math.min(red_color_result, Math.min(green_color_result, blue_color_result));
		System.out.println("minimumCost " + minimumCost);

	}

	private void paintHousesUtilDp(int[][] arr) {
		int[][] dp = new int[arr.length][arr[0].length];

		for (int j = 0; j <= arr.length - 1; j++) {
			dp[j][0] = arr[j][0];
		}
		System.out.println(Arrays.toString(dp[0]));
		System.out.println(Arrays.toString(dp[1]));
		System.out.println(Arrays.toString(dp[2]));

		for (int color = 0; color <= arr.length - 1; color++) {
			for (int index = 1; index <= arr[0].length - 1; index++) {
				//TODO: Complete me.
			}
		}
		System.out.println("print dp......\n");
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

	private int paintHousesUtil(int[][] arr, int index, int color) {

		// System.out.println(index + " "+ color);
		// base case
		if (index == 0) {
			return arr[index][0];
		}

		// red
		if (color == 0) {
			int green = arr[0][index] + paintHousesUtil(arr, index - 1, 1);
			int blue = arr[0][index] + paintHousesUtil(arr, index - 1, 2);
			return Math.min(green, blue);
		}

		// green
		if (color == 1) {
			int red = arr[1][index] + paintHousesUtil(arr, index - 1, 0);
			int blue = arr[1][index] + paintHousesUtil(arr, index - 1, 2);
			return Math.min(red, blue);
		}

		// blue
		if (color == 2) {
			int red = arr[2][index] + paintHousesUtil(arr, index - 1, 0);
			int green = arr[2][index] + paintHousesUtil(arr, index - 1, 1);
			return Math.min(red, green);
		}
		return 0;
	}
}
