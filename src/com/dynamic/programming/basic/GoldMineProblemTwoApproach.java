package com.dynamic.programming.basic;

import java.util.Arrays;

/**
 * Given a gold mine of n*m dimensions. Each field in this mine contains a
 * positive integer which is the amount of gold in tons. Initially the miner is
 * at first column but can be at any row. He can move only (right->,right up
 * /,right down\) that is from a given cell, the miner can move to the cell
 * diagonally up towards the right or right or diagonally down towards the
 * right. Find out maximum amount of gold he can collect.
 *
 *                     Input : mat[][] = {{1, 3, 3},
                                       {2, 1, 4},
                                      {0, 6, 4}};
                    Output : 12 
                    {(1,0)->(2,1)->(2,2)}
                    
                    Input: mat[][] = { {1, 3, 1, 5},
                                       {2, 2, 4, 1},
                                       {5, 0, 2, 3},
                                       {0, 6, 1, 2}};
                    Output : 16
                    (2,0) -> (1,1) -> (1,2) -> (0,3) OR
                    (2,0) -> (3,1) -> (2,2) -> (2,3)
                    
                    Input : mat[][] = {{10, 33, 13, 15},
                                      {22, 21, 04, 1},
                                      {5, 0, 2, 3},
                                      {0, 6, 14, 2}};
                    Output : 123
 * 
 * Create a 2-D matrix goldTable[][]) of the same as given matrix mat[][]. If we
 * observe the question closely, we can notice following.
 * 
 * Amount of gold is positive, so we would like to cover maximum cells of
 * maximum values under given constraints. In every move, we move one step
 * toward right side. So we always end up in last column. If we are at the last
 * column, then we are unable to move right If we are at the first row or last
 * column, then we are unable to move right-up so just assign 0 otherwise assign
 * the value of goldTable[row-1][col+1] to right_up. 
 * 
 * 
 * If we are at the last row  or last column, then we are unable to move right down 
 * so just assign 0 otherwise assign the value of goldTable[row+1][col+1] to right up. 
 * Now find the maximum of right, right_up, and right_down and then add it with that
 * mat[row][col]. At last, find the maximum of all rows and first column and
 * return it.
 * 
 */
public class GoldMineProblemTwoApproach {

    // Returns maximum amount of gold that
    // can be collected when journey started
    // from first column and moves allowed
    // are right, right-up and right-down
    static int getMaxGold(int gold[][], int m, int n) {

        // Create a table for storing
        // intermediate results and initialize
        // all cells to 0. The first row of
        // goldMineTable gives the maximum
        // gold that the miner can collect
        // when starts that row
        int goldTable[][] = new int[m][n];

        for (int[] rows : goldTable)
            Arrays.fill(rows, 0);
         
        for(int row = 0; row<m; row++) 
        	goldTable[row][0] = gold[row][0];
        
        printGoldTable(goldTable);
        
        //fill to columns then row
        for (int col = 1; col <  n; col++) {
        for (int row = 0; row < m; row++) {
        	   	System.out.println(gold[row][col]);
                // the cell on the right(->)
                //int right = (col == n - 1) ? 0 : goldTable[row][col - 1];
                int right =  goldTable[row][col-1];
                

                // Gold collected on going to
                // the cell to right up (/)
                int right_up = (row == m-1) ? 0 : goldTable[row + 1][col - 1];

                // Gold collected on going to
                // the cell to right down (\)
                int right_down = (row == 0) ? 0 : goldTable[row - 1][col - 1];

                // Max gold collected from taking
                // either of the above 3 paths
                goldTable[row][col] = gold[row][col] + Math.max(right, Math.max(right_up, right_down));

            }
        }
        System.out.println("printing gold table............");
        printGoldTable(goldTable);
        // The max amount of gold collected will be
        // the max value in first column of all rows
        int res = goldTable[0][n-1];

        for (int i = 1; i < m; i++)
            res = Math.max(res, goldTable[i][n-1]);

        return res;
    }

    private static void printGoldTable(int[][] goldTable) {
        for (int i = 0; i < goldTable.length; i++) {
            for (int j = 0; j < goldTable[0].length; j++) {
                System.out.print(goldTable[i][j] + "  ");
            }
            System.out.println("");
        }

    }

    // driver code
    public static void main(String arg[]) {
        int gold[][] = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };
//        int gold[][] = { { 10, 33, 13, 55 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };

        int m = 4, n = 4;

        System.out.print("Maximum gold can collect : " + getMaxGold(gold, m, n));
    }
}
