package com.dynamic.programming.basic;

// Java program to find nth ugly number
import java.lang.Math;

/**
 * Here is a time efficient solution with O(n) extra space. The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … 
     because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below: 
     (1) 1×2, 2×2, 3×2, 4×2, 5×2, … 
     (2) 1×3, 2×3, 3×3, 4×3, 5×3, … 
     (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5. Then we use similar merge method as merge sort, to get every ugly number from the three subsequence. Every step we choose the smallest one, and move one step after.

1 Declare an array for ugly numbers:  ugly[n]
2 Initialize first ugly no:  ugly[0] = 1
3 Initialize three array index variables i2, i3, i5 to point to 
   1st element of the ugly array: 
        i2 = i3 = i5 =0; 
4 Initialize 3 choices for the next ugly no:
         next_mulitple_of_2 = ugly[i2]*2;
         next_mulitple_of_3 = ugly[i3]*3
         next_mulitple_of_5 = ugly[i5]*5;
5 Now go in a loop to fill all ugly numbers till 150:
For (i = 1; i < 150; i++ ) 
{
    /* These small steps are not optimized for good 
      readability. Will optimize them in C program  
    next_ugly_no  = Min(next_mulitple_of_2,
                        next_mulitple_of_3,
                        next_mulitple_of_5); 

    ugly[i] =  next_ugly_no       

    if (next_ugly_no  == next_mulitple_of_2) 
    {             
        i2 = i2 + 1;        
        next_mulitple_of_2 = ugly[i2]*2;
    } 
    if (next_ugly_no  == next_mulitple_of_3) 
    {             
        i3 = i3 + 1;        
        next_mulitple_of_3 = ugly[i3]*3;
     }            
     if (next_ugly_no  == next_mulitple_of_5)
     {    
        i5 = i5 + 1;        
        next_mulitple_of_5 = ugly[i5]*5;
     } 
     
} 
6.return next_ugly_no

 *
 */
class UglyNumber {
	// Function to get the nth ugly number
	int getNthUglyNo(int n) {
		// To store ugly numbers
		int ugly[] = new int[n];
		int i2 = 0, i3 = 0, i5 = 0;
		int next_multiple_of_2 = 2;
		int next_multiple_of_3 = 3;
		int next_multiple_of_5 = 5;
		int next_ugly_no = 1;

		ugly[0] = 1;

		for (int i = 1; i < n; i++) {
			next_ugly_no = Math.min(next_multiple_of_2, Math.min(next_multiple_of_3, next_multiple_of_5));

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

	// Driver code
	public static void main(String args[]) {

		int n = 150;

		// Function call
		UglyNumber obj = new UglyNumber();
		System.out.println(obj.getNthUglyNo(n));
	}
}

 