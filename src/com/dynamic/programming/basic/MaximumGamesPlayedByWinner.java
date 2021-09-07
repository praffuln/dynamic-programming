package com.dynamic.programming.basic;

/**
 * There are N players which are playing a tournament. We need to find the
 * maximum number of games the winner can play. In this tournament, two players
 * are allowed to play against each other only if the difference between games
 * played by them is not more than one.
 * 
                    Input  : N = 3
                    Output : 2
                    Maximum games winner can play = 2
                    Assume that player are P1, P2 and P3
                    First, two players will play let (P1, P2)
                    Now winner will play against P3, 
                    making total games played by winner = 2
                    
                    Input  : N = 4
                    Output : 2
                    Maximum games winner can play = 2
                    Assume that player are P1, P2, P3 and P4
                    First two pairs will play lets (P1, P2) and 
                    (P3, P4). Now winner of these two games will 
                    play against each other, making total games
                    played by winner = 2
*
*  We can solve this problem by first computing minimum number of players required 
*  such that the winner will play x games. Once this is computed actual problem is just 
*  inverse of this. Now assume that dp[i] denotes minimum number of players required so 
*  that winner plays i games. We can write a recursive relation among dp values as, 
    dp[i + 1] = dp[i] + dp[i – 1] because if runner up has played (i – 1) games and winner has played i games and 
    all players against which they have played the match are disjoint, total games played by winner 
    will be addition of those two sets of players. 
    Above recursive relation can be written as dp[i] = dp[i – 1] + dp[i – 2] 
    
    Which is same as the Fibonacci series relation, so our final answer will be the index of the 
    maximal Fibonacci number which is less than or equal to given number of players in the input.
*
*
 */
public class MaximumGamesPlayedByWinner {

    // method returns maximum games a winner needs
    // to play in N-player tournament
    static int maxGameByWinner(int N) {
        int[] dp = new int[N];

        // for 0 games, 1 player is needed
        // for 1 game, 2 players are required
        dp[0] = 1;
        dp[1] = 2;

        // loop until i-th Fibonacci number is
        // less than or equal to N
        int i = 2;
        do {
            dp[i] = dp[i - 1] + dp[i - 2];
        } while (dp[i++] <= N);

        // result is (i - 2) because i will be
        // incremented one extra in while loop
        // and we want the last value which is
        // smaller than N, so one more decrement
        return (i - 2);
    }

    // Driver code to test above methods
    public static void main(String args[]) {
        int N = 10;
        System.out.println(maxGameByWinner(N));
    }
}
