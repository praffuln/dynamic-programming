package com.dynamic.programming.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The array-form of an integer num is an array representing its digits in left
 * to right order.
 * 
 * For example, for num = 1321, the array form is [1,3,2,1].
 * 
 * Given num, the array-form of an integer, and an integer k, return the
 * array-form of the integer num + k.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: num = [1,2,0,0], k = 34 Output: [1,2,3,4] Explanation: 1200 + 34 =
 * 1234 Example 2:
 * 
 * Input: num = [2,7,4], k = 181 Output: [4,5,5] Explanation: 274 + 181 = 455
 * Example 3:
 * 
 * Input: num = [2,1,5], k = 806 Output: [1,0,2,1] Explanation: 215 + 806 = 1021
 *
 * https://leetcode.com/problems/add-to-array-form-of-integer/solutions/
 * 
 */
public class AddToArrayFormOfInteger_989 {

	public static List<Integer> addToArrayForm(int[] num, int k) {
		List<Integer> ans = new ArrayList<>();
		int carry = 0;
		for (int i = num.length - 1; i >= 0 || k > 0; i--) {
			int sum = 0;
			if (i >= 0)
				sum = num[i] + carry + (k % 10);
			else
				sum = carry + (k % 10);
			carry = sum / 10;
			ans.add(sum % 10);
			k = k / 10;
		}

		if (carry != 0) {
			ans.add(carry);
		}
		Collections.reverse(ans); // reverse the list
		return ans;

	}

	public static void main(String args[]) {
		List<Integer> ans = addToArrayForm(new int[] { 2, 7, 4 }, 181);
		ans.stream().forEach(i -> System.out.print(i));
	}
}
