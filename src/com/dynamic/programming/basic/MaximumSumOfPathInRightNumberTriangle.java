package com.dynamic.programming.basic;

/**
 * TODO: Samajh nahi aaya....
 * 
 * Given a right triangle of numbers, find the largest of the sum of numbers
 * that appear on the paths starting from the top towards the base, so that on
 * each path the next number is located directly below or
 * below-and-one-place-to-the-right.
 * 
Input :  1
            1 2
            4 1 2
            2 3 1 1        
Output : 9
Explanation : 1 + 1 + 4 + 3

Input :  2
            4 1
            1 2 7
    Output : 10
Explanation : 2 + 1 + 7

 * 
 * The idea is to find the largest sum ending at every cell of the last row and
 * return a maximum of these sums. We can recursively compute these sums by
 * recursively considering the above two cells. Since there are overlapping
 * subproblems, we use dynamic programming to find the maximum sum ending at a
 * particular cell of the last row.
 * 
 * 
 */
public class MaximumSumOfPathInRightNumberTriangle {

    // function to find maximum sum path
    static int maxSum(int tri[][], int n) {

        // Adding the element of row 1 to both the
        // elements of row 2 to reduce a step from
        // the loop
        if (n > 1)
            tri[1][1] = tri[1][1] + tri[0][0];
        tri[1][0] = tri[1][0] + tri[0][0];

        // Traverse remaining rows
        for (int i = 2; i < n; i++) {
            tri[i][0] = tri[i][0] + tri[i - 1][0];
            tri[i][i] = tri[i][i] + tri[i - 1][i - 1];

            // Loop to traverse columns
            for (int j = 1; j < i; j++) {

                // Checking the two conditions,
                // directly below and below right.
                // Considering the greater one

                // tri[i] would store the possible
                // combinations of sum of the paths
                if (tri[i][j] + tri[i - 1][j - 1] >= tri[i][j] + tri[i - 1][j])

                    tri[i][j] = tri[i][j] + tri[i - 1][j - 1];

                else
                    tri[i][j] = tri[i][j] + tri[i - 1][j];
            }
        }

        // array at n-1 index (tri[i]) stores
        // all possible adding combination,
        // finding the maximum one out of them
        int max = tri[n - 1][0];

        for (int i = 1; i < n; i++) {
            if (max < tri[n - 1][i])
                max = tri[n - 1][i];
        }

        return max;
    }

    // Driver code
    public static void main(String[] args) {
        int tri[][] = { { 1 }, { 2, 1 }, { 3, 3, 2 } };

        System.out.println(maxSum(tri, 3));
    }
}
