package com.dynamic.programming.basic;

/**
 * Given a square matrix of size N*N, where each cell is associated with a
 * specific cost. A path is defined as a specific sequence of cells which starts
 * from the top-left cell move only right or down and ends on bottom right cell.
 * We want to find a path with the maximum average over all existing paths.
 * Average is computed as total cost divided by the number of cells visited in
 * the path.
 * 
 * Input : Matrix = [1, 2, 3
                  4, 5, 6
                  7, 8, 9]
        Output : 5.8
        Path with maximum average is, 1 -> 4 -> 7 -> 8 -> 9
        Sum of the path is 29 and average is 29/5 = 5.8
 *
 *
 * One interesting observation is, the only allowed moves are down and right, we need N-1 
 * down moves and N-1 right moves to reach the destination (bottom rightmost). So any path 
 * from top left corner to bottom right corner requires 2N – 1 cells. In average value, the 
 * denominator is fixed and we need to just maximize numerator. Therefore we basically need 
 * to find the maximum sum path. Calculating maximum sum of path is a classic dynamic programming 
 * problem, if dp[i][j] represents maximum sum till cell (i, j) from (0, 0) then at each cell (i, j), 
 * we update dp[i][j] as below,

                for all i, 1 <= i <= N
                   dp[i][0] = dp[i-1][0] + cost[i][0];    
                for all j, 1 <= j <= N
                   dp[0][j] = dp[0][j-1] + cost[0][j];            
                otherwise    
                   dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + cost[i][j]; 
 *        
 */
public class PathWithMaximumAverageValue {

    // method returns maximum average of all
    // path of cost matrix
    public static double maxAverageOfPath(int cost[][], int N) {
        int dp[][] = new int[N + 1][N + 1];
        dp[0][0] = cost[0][0];

        /*
         * Initialize first column of total cost(dp) array
         */
        for (int i = 1; i < N; i++)
            dp[i][0] = dp[i - 1][0] + cost[i][0];

        /* Initialize first row of dp array */
        for (int j = 1; j < N; j++)
            dp[0][j] = dp[0][j - 1] + cost[0][j];

        /* Construct rest of the dp array */
        for (int i = 1; i < N; i++)
            for (int j = 1; j < N; j++)
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + cost[i][j];

        // divide maximum sum by constant path
        // length : (2N - 1) for getting average
        return (double) dp[N - 1][N - 1] / (2 * N - 1);
    }

    /* Driver program to test above function */
    public static void main(String[] args) {
        int cost[][] = { { 1, 2, 3 }, { 6, 5, 4 }, { 7, 3, 9 } };

        System.out.println(maxAverageOfPath(cost, 3));
    }
}
