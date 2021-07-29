package com.dynamic.programming.basic;


/**
 * Given a number n then print n terms of fibonacci series in reverse order.

			Examples: 
			
			Input : n = 5
			Output : 3 2 1 1 0
			
			Input : n = 8
			Output : 13 8 5 3 2 1 1 0
 *
		1) Declare an array of size n. 
		2) Initialize a[0] and a[1] to 0 and 1 respectively. 
		3) Run a loop from 2 to n-1 and store 
		sum of a[i-2] and a[i-1] in a[i]. 
		4) Print the array in the reverse order.
 *
 *
 */
class FibonacciReverse {

	static void reverseFibonacci(int n) {
		int a[] = new int[n];

		// assigning first and second elements
		a[0] = 0;
		a[1] = 1;

		for (int i = 2; i < n; i++) {

			// storing sum in the
			// preceding location
			a[i] = a[i - 2] + a[i - 1];
		}

		for (int i = n - 1; i >= 0; i--) {

			// printing array in
			// reverse order
			System.out.print(a[i] + " ");
		}
	}

	// Driver function
	public static void main(String[] args) {
		int n = 5;
		reverseFibonacci(n);

	}
}
