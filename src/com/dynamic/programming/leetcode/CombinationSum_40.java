package com.dynamic.programming.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), 
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * 
 * Note: The solution set must not contain duplicate combinations.

			Example 1:
			
			Input: candidates = [10,1,2,7,6,1,5], target = 8
			Output: 
			[
			[1,1,6],
			[1,2,5],
			[1,7],
			[2,6]
			]
			Example 2:
			
			Input: candidates = [2,5,2,1,2], target = 5
			Output: 
			[
			[1,2,2],
			[5]
			]
 *
 */
public class CombinationSum_40 {

	private static class Solution {
		public List<List<Integer>> combinationSum2(int[] candidates, int target) {
			Arrays.sort(candidates);
			List<List<Integer>> result = new ArrayList<>();
			combinationSumHelper(candidates, target, 0, new ArrayList<>(), result);
			return result;
		}

		private void combinationSumHelper(int[] candidates, int target, int start, List<Integer> temp,
				List<List<Integer>> result) {
			if (target == 0) {
				result.add(new ArrayList<>(temp));
				return;
			}
			for (int i = start; i < candidates.length; i++) {
				if (i > start && candidates[i] == candidates[i - 1]) {
					continue; // Skip duplicates
				}
				if (candidates[i] <= target) {
					temp.add(candidates[i]);
					combinationSumHelper(candidates, target - candidates[i], i + 1, temp, result);
					temp.remove(temp.size() - 1);
				}
			}
		}
	}

	private static class SolutionRecursion {
		Set<List<Integer>> ans = new HashSet<List<Integer>>();

		public List<List<Integer>> combinationSum2(int[] candidates, int target) {
			Arrays.sort(candidates);

			combinationSumHelper(0, target, candidates, new ArrayList<Integer>());
			return new ArrayList<List<Integer>>(ans);
		}

		public void combinationSumHelper(int i, int target, int[] candidates, List<Integer> temp) {

			if (target == 0) {
				List<Integer> l = new ArrayList<>(temp);
				ans.add(l);
				return;
			}

			if (i == candidates.length)
				return;

			if (target < 0) {
				return;
			}

			// select
			temp.add(candidates[i]); // add in a list selected candidate
			combinationSumHelper(i + 1, target - candidates[i], candidates, temp);
			temp.remove(temp.size() - 1); // remove from the list if selected

			// not select
			combinationSumHelper(i + 1, target, candidates, temp);
		}

	}
}
