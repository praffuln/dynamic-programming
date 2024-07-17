package com.dynamic.programming.leetcode;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/minimum-cost-for-tickets/description/

/**
 * You have planned some train traveling one year in advance. The days of the
 * year in which you will travel are given as an integer array days. Each day is
 * an integer from 1 to 365.
 * 
 * Train tickets are sold in three different ways:
 * 
 * a 1-day pass is sold for costs[0] dollars, a 7-day pass is sold for costs[1]
 * dollars, and a 30-day pass is sold for costs[2] dollars. The passes allow
 * that many days of consecutive travel.
 * 
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days:
 * 2, 3, 4, 5, 6, 7, and 8. Return the minimum number of dollars you need to
 * travel every day in the given list of days.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15] Output: 11 Explanation: For
 * example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1. On
 * day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4,
 * ..., 9. On day 20, you bought a 1-day pass for costs[0] = $2, which covered
 * day 20. In total, you spent $11 and covered all the days of your travel.
 * Example 2:
 * 
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15] Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel
 * your travel plan: On day 1, you bought a 30-day pass for costs[2] = $15 which
 * covered days 1, 2, ..., 30. On day 31, you bought a 1-day pass for costs[0] =
 * $2 which covered day 31. In total, you spent $17 and covered all the days of
 * your travel.
 * 
 * 
 * Constraints:
 * 
 * 1 <= days.length <= 365 1 <= days[i] <= 365 days is in strictly increasing
 * order. costs.length == 3 1 <= costs[i] <= 1000
 *
 * 
 */
public class MinimumCostForTickets_983 {

	public static void main(String[] args) {
		int[] days = new int[] { 1,4,6,7,8,20 };
		int[] costs = new int[] { 2,7,15 };
		
		int answer = mincostTickets(days, costs);
		System.out.println(answer);

	}

	static Integer[] dp;

	public static int mincostTickets(int[] days, int[] costs) {
		Set<Integer> set = new HashSet<>();
		dp = new Integer[366];
		for (int n : days)
			set.add(n);
		return calculateMinCost(days, costs, 1, set);
	}

	/**
	 * Person is traveling on that day or not. 
	 * If traveling then will take ticket for next 3 conditions. 
	 * If not traveling then why he will buy ticket, will move to next day for better deal. 
	 * set is using to track is user Person on that day or not.
	 * 
	 * @param days
	 * @param costs
	 * @param curr
	 * @param set
	 * @return
	 */
	public static int calculateMinCost(int[] days, int[] costs, int curr, Set<Integer> set) {
		if (curr > 365) {
			return 0;
		}

		if (dp[curr] != null)
			return dp[curr];

		int cost = Integer.MAX_VALUE;
		if (!set.contains(curr)) {
			return calculateMinCost(days, costs, curr + 1, set);
		} else {
			int oDay = costs[0] + calculateMinCost(days, costs, curr + 1, set);
			int sDay = costs[1] + calculateMinCost(days, costs, curr + 7, set);
			int tDay = costs[2] + calculateMinCost(days, costs, curr + 30, set);
			cost = Math.min(oDay, Math.min(sDay, tDay));
		}

		dp[curr] = cost;
		return cost;
	}
}
