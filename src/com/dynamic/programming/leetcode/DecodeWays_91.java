package com.dynamic.programming.leetcode;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"

To decode an encoded message, all the digits must be grouped then mapped back into letters using 
the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped
into:

"AAJF" with the grouping (1 1 10 6)

"KJF" with the grouping (11 10 6)

Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is 
different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.

 *	   Example 1:

		Input: s = "12"
		Output: 2
		Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
		Example 2:
		
		Input: s = "226"
		Output: 3
		Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
		Example 3:
		
		Input: s = "06"
		Output: 0
		Explanation: "06" cannot be mapped to "F" because of the leading zero 
		("6" is different from "06").
 */
public class DecodeWays_91 {

	/**
	 * This problem is recursive and can be broken into sub-problems. We start
	 * from the end of the given digit sequence. We initialize the total count
	 * of decodings as 0. We recur for two subproblems.
	 * 
	 * 1) If the last digit is non-zero, recur for the remaining (n-1) digits
	 * and add the result to the total count.
	 * 
	 * 2) If the last two digits form a valid character (or smaller than 27),
	 * recur for remaining (n-2) digits and add the result to the total count.
	 *
	 */
	static class Solution {
		
	    public int numDecodings(String s) {
	        char[] digits = s.toCharArray();
	        
	        int[] count = new int[s.length() + 1];
	        
	        count[0] = 1;
	        count[1] = 1;
	        
	        //for base condition "01123" should return 0
	        if(digits[0] == '0') return 0;
	        
	                
	        for(int i=2; i<=digits.length; i++) {
	            //count[i] = 0;
	            
	            
	         // If the last digit is not 0,
	        // then last digit must add to
	        // the number of words
	        if (digits[i - 1] > '0')
	            count[i] += count[i - 1];
	 
	        // If second last digit is smaller
	        // than 2 and last digit is smaller
	        // than 7, then last two digits
	        // form a valid character
	        if (digits[i - 2] == '1' ||
	           (digits[i - 2] == '2' &&
	            digits[i - 1] < '7'))
	            count[i] += count[i - 2];
	        }
	        
	        return count[digits.length];
	    }
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		int result = solution.numDecodings("123"); //AW, ABC, LC [result is 3.]
		System.out.println("dynamic programing result is -  "+ result);
	}
	
	
	//A naive recursive Java implementation
	//to count number of decodings that
	//can be formed from a given digit sequence
	static class DecodeWaysRecursive {

		// recurring function to find
		// ways in how many ways a
		// string can be decoded of length
		// greater than 0 and starting with
		// digit 1 and greater.
		static int countDecoding(char[] digits, int n) {
			// base cases
			if (n == 0 || n == 1)
				return 1;

			// for base condition "01123" should return 0
			if (digits[0] == '0')
				return 0;

			// Initialize count
			int count = 0;

			// If the last digit is not 0, then
			// last digit must add to
			// the number of words
			if (digits[n - 1] > '0')
				count = countDecoding(digits, n - 1);

			// If the last two digits form a number
			// smaller than or equal to 26,
			// then consider last two digits and recur
			if (digits[n - 2] == '1' || (digits[n - 2] == '2' && digits[n - 1] < '7'))
				count += countDecoding(digits, n - 2);

			return count;
		}

		// Given a digit sequence of length n,
		// returns count of possible decodings by
		// replacing 1 with A, 2 with B, ... 26 with Z
		static int countWays(char[] digits, int n) {
			if (n == 0 || (n == 1 && digits[0] == '0'))
				return 0;
			return countDecoding(digits, n);
		}

		// Driver code
		public static void main(String[] args) {
			char digits[] = { '1', '2', '3', '4', '1', '6' };
			int n = digits.length;
			System.out.printf("Recursive Count is %d", countWays(digits, n));
		}
	}
}