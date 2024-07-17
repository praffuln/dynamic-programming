package com.dynamic.programming.leetcode;

/**
 * Given two non-negative integers, num1 and num2 represented as string, return the 
 * sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers 
(such as BigInteger). You must also not convert the inputs to integers directly.

 

Example 1:

Input: num1 = "11", num2 = "123"
Output: "134"
Example 2:

Input: num1 = "456", num2 = "77"
Output: "533"
Example 3:

Input: num1 = "0", num2 = "0"
Output: "0"

 *
 */
public class AddStrings_415 {
	
	
	public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while(i >=0 || j>=0 || carry > 0) {
            int digit1 = i>=0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j>=0 ? num2.charAt(j) - '0' : 0;

            //decrease i and j
            i--;
            j--;

            int sum = digit1 + digit2 + carry;
            carry = sum/10;
            sb.append(sum%10);
        }
        return sb.reverse().toString();
    }
	public static void main(String args[]) {
		System.out.println(addStrings("11", "123"));
		
	}
}
