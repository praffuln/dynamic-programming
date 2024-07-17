package com.dynamic.programming.leetcode;

import java.util.Arrays;

public class SuperUglyNumber_313 {

	/*
	 * This function divides a by greatest divisible power of b
	 */
	static int maxDivide(int a, int b) {
		while (a % b == 0)
			a = a / b;
		return a;
	}

	/*
	 * Function to check if a number is ugly or not
	 */
	static int isUgly(int no) {
		no = maxDivide(no, 2);
		no = maxDivide(no, 3);
		no = maxDivide(no, 5);

		return (no == 1) ? 1 : 0;
	}

	/*
	 * Function to get the nth ugly number
	 */
	static int getNthUglyNo(int n) {
		int i = 1;

		// ugly number count
		int count = 1;

		// check for all integers
		// until count becomes n
		while (n > count) {
			i++;
			if (isUgly(i) == 1)
				count++;
		}
		return i;
	}

	static int getNthUglyNoDP(int n) {
		// To store ugly numbers
		int ugly[] = new int[n];
		int i2 = 0, i3 = 0, i5 = 0;
		int next_multiple_of_2 = 2;
		int next_multiple_of_3 = 3;
		int next_multiple_of_5 = 5;
		int next_ugly_no = 1;

		ugly[0] = 1;

		for (int i = 1; i < n; i++) {
			next_ugly_no = Math.min(next_multiple_of_2, 
					Math.min(next_multiple_of_3, next_multiple_of_5));

			ugly[i] = next_ugly_no;
			if (next_ugly_no == next_multiple_of_2) {
				i2 = i2 + 1;
				next_multiple_of_2 = ugly[i2] * 2;
			}
			if (next_ugly_no == next_multiple_of_3) {
				i3 = i3 + 1;
				next_multiple_of_3 = ugly[i3] * 3;
			}
			if (next_ugly_no == next_multiple_of_5) {
				i5 = i5 + 1;
				next_multiple_of_5 = ugly[i5] * 5;
			}
		}

		// End of for loop (i=1; i<n; i++)
		return next_ugly_no;
	}
	
	static int getNthUglyNoDP(int n, int[] arr) {
		// To store ugly numbers
		int ugly[] = new int[n];
		int i2 = 0, i3 = 0, i5 = 0;
		int next_multiple_of_2 = 2;
		int next_multiple_of_3 = 3;
		int next_multiple_of_5 = 5;
		int next_ugly_no = 1;

		int[] nextMultiples = Arrays.copyOf(arr, arr.length);
		System.out.println(Arrays.toString(nextMultiples));
		
		int[] count = new int[arr.length];
		Arrays.fill(count, 0);
		
		ugly[0] = 1;

		for (int i = 1; i < n; i++) {
		
			
//			next_ugly_no = Math.min(next_multiple_of_2, 
//					Math.min(next_multiple_of_3, next_multiple_of_5));

			int minIndex = 0;
			next_ugly_no = Integer.MAX_VALUE;
			for(int j=0; j < arr.length; j++) {
				if(nextMultiples[j] < next_ugly_no) {
					next_ugly_no = nextMultiples[j];
					minIndex = j;
				}
			}
			System.out.println("min is " + next_ugly_no);
			ugly[i] = next_ugly_no;
			count[minIndex] = count[minIndex] +1;
			nextMultiples[minIndex] = nextMultiples[minIndex] * ugly[count[minIndex]];
			
//			ugly[i] = next_ugly_no;
//			if (next_ugly_no == next_multiple_of_2) {
//				i2 = i2 + 1;
//				next_multiple_of_2 = ugly[i2] * 2;
//			}
//			if (next_ugly_no == next_multiple_of_3) {
//				i3 = i3 + 1;
//				next_multiple_of_3 = ugly[i3] * 3;
//			}
//			if (next_ugly_no == next_multiple_of_5) {
//				i5 = i5 + 1;
//				next_multiple_of_5 = ugly[i5] * 5;
//			}
		}

		// End of for loop (i=1; i<n; i++)
		return next_ugly_no;
	}

	/* Driver Code */
	public static void main(String args[]) {
		int no = getNthUglyNo(150);
		System.out.println("150th ugly " + "no. is " + no);
		
		no = getNthUglyNoDP(150);
		System.out.println("150th ugly " + "no. is " + no);
		
		no =  getNthUglyNoDP(150, new int[]{2,3,5});
		System.out.println("150th ugly " + "no. is " + no);
	}

}
