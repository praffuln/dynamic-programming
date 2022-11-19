package com.dynamic.programming.codingninjas;

import java.util.Arrays;

import com.dynamic.programming.basic.GoldMineProblemTwoApproach;

/**
 * A Ninja has an ‘N’ Day training schedule. He has to perform one of these
 * three activities (Running, Fighting Practice, or Learning New Moves) each
 * day. There are merit points associated with performing an activity each day.
 * The same activity can’t be performed on two consecutive days. We need to find
 * the maximum merit points the ninja can attain in N Days.
 * 
 * We are given a 2D Array POINTS of size ‘N*3’ which tells us the merit point
 * of specific activity on that particular day. Our task is to calculate the
 * maximum number of merit points that the ninja can earn.
 * 
 * 
 * This problem is exactly similar to {@link GoldMineProblemTwoApproach}. Just a
 * simple modification will solve to this problem. Instead of gold we need to
 * think for points.
 * 
 */
public class NinjasTraining {
 
    static int getMaxPoints(int points[][], int m, int n) {

        int pointTable[][] = new int[m][n];

        for (int[] rows : pointTable)
            Arrays.fill(rows, 0);
         
        //first row fill.
        for(int col = 0; col<n; col++) 
        	pointTable[0][col] = points[0][col];
        
        printPointTable(pointTable);
        
        //fill to columns then row
        for (int day = 1; day < m; day++) {
        	for (int point = 0; point <  n; point++) {

                 
        	   	//find maximum from previous day, leave to same col index
        	   	int[] previousDayPoints = pointTable[day-1];
        	   	int maxPointPreviousDay = Integer.MIN_VALUE;
        	   	for(int i=0; i< previousDayPoints.length; i++) {
        	   		if(i != point) {
        	   			maxPointPreviousDay = Math.max(maxPointPreviousDay, previousDayPoints[i]);
        	   		}
        	   	}
        	   	
        	   	System.out.println(points[day][point] +", max -" + maxPointPreviousDay);
        	   	
        	   	//add points for day
                pointTable[day][point] =  points[day][point] + maxPointPreviousDay;
            }
        }
        System.out.println("printing point table............");
        printPointTable(pointTable);
        // The max amount of gold collected will be
        // the max value in first column of all rows
        int res = pointTable[0][n-1];

        for (int i = 1; i < m; i++)
            res = Math.max(res, pointTable[i][n-1]);

        return res;
    }

    private static void printPointTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + "  ");
            }
            System.out.println("");
        }

    }

    public static void main(String arg[]) {
        int points[][] = { { 1,2,5 }, { 3,1,1 }, {3,3,3}};
//        int points[][] = { { 10, 40, 70 }, { 20, 50, 80}, {30, 60, 90}};

        int m = 3, n = 3;

        System.out.print("Maximum point can collect : " + getMaxPoints(points, m, n));
    }
}
