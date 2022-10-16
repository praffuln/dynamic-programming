package com.dynamic.programming.leetcode;

/**
 * https://leetcode.com/problems/maximum-product-subarray/discuss/1608862/JAVA-or-3-Solutions-or-Detailed-Explanation-Using-Image
 * 
 * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
 * 
 * 
 * Given an integer array nums, find a contiguous non-empty subarray within the
 * array that has the largest product, and return the product.
 * 
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * 
 * A subarray is a contiguous subsequence of the array.
 *
 * 
			Example 1:
			
			Input: nums = [2,3,-2,4]
			Output: 6
			Explanation: [2,3] has the largest product 6.
			Example 2:
			
			Input: nums = [-2,0,-1]
			Output: 0
			Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
			 
			
			Constraints:
			
			1 <= nums.length <= 2 * 104
			-10 <= nums[i] <= 10
			The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * 
 * 
 * Intution: Since we have to find the contiguous subarray having maximum product then your 
 * approach should be combination of following three cases :

Case1 :- All the elements are positive : Then your answer will be product of all the elements in 
the array.

Case2 :- Array have positive and negative elements both :
If the number of negative elements is even then again your answer will be complete array because 
on multiplying all the negative numbers it will become positive.
If the number of negative elements is odd then you have to remove just one negative element and for 
that u need to check your subarrays to get the max product.

Case3 :- Array also contains 0 : Then there will be not much difference...its just that your array will be 
divided into subarray around that 0. What u have to so is just as soon as your product becomes 0 make 
it 1 for the next iteration, now u will be searching new subarray and previous max will already be updated.
(These cases are much clear in approach 3)
 * 
 * 
 * 
 */
public class MaximumProductSubarray_152 {
	static class Solution {
	    
		public int maxProduct(int[] nums) {
	         
	         int currentMin = 0; int currentMax =0;
	         currentMin = nums[0];
	         currentMax = nums[0];
	         int maxProduct =  nums[0];
	         
	         int count = 0;
	         
	         for(int i=1; i< nums.length; i++) {
	        	 
	        	 if(nums[i] < 0) {
	        		 int temp = currentMin;
	        		 currentMin = currentMax;
	        		 currentMax = temp;
	        	 }
	        	 
	        	 currentMin = Math.min(nums[i], nums[i]*currentMin);
	        	 currentMax = Math.max(nums[i], nums[i]*currentMax);
	        	 
	        	 if(maxProduct < currentMax) {
	        	 maxProduct = Math.max(maxProduct, currentMax);
	        	 count++;
	        	 }
	         }
	         System.out.println("count is  "+  count);
	         return maxProduct;
		}
		
	    /**
		 * Approach 3: Two pointer Approach
		 * 
		 * Explanation :
		 * 
		 * 1.) Through intuition explanation we know that if all the elements are
		 * positive or the negative elements are even then answer will be
		 * product of complete array which u will get in variable l and r at the
		 * last iteration.
		 * 
		 * 2.) But if negative elements are odd then u have to remove one
		 * negative element and it is sure that it will be either right of max
		 * prefix product or left of max suffix product. So u need not to modify
		 * anything in your code as u are getting prefix product in l and suffix
		 * prduxt in r.
		 * 
		 * 3.) If array also contains 0 then your l and r will become 0 at that
		 * point...then just update it to 1(or else u will keep multiplying with
		 * 0) to get the product ahead making another subarray.
		 * 
		 * 
		 */
	    public int maxProductWithTwoPointerApproach(int[] nums) {
	        
	        int n = nums.length;
	        int l=1,r=1;
	        int ans=nums[0];
	        
	        for(int i=0;i<n;i++) {
	            
				//if any of l or r become 0 then update it to 1
	            l = l==0 ? 1 : l;
	            r = r==0 ? 1 : r;
	            
	            l *= nums[i];   //prefix product
	            r *= nums[n-1-i];    //suffix product
	            
	            ans = Math.max(ans,Math.max(l,r));
	            
	        }
	        return ans;
	    }

		/**
		 * The interesting thing about this solution is that it depends not only
		 * on a state (the largest product that can be obtained by a sequence
		 * ending in the previous number), but two states (the largest and
		 * smallest products). A simpler dp problem might just create a dp[] and
		 * put the maximum value in it(in this case, the largest product). But
		 * this solution show us a new way: Our dp array can store more data
		 * than just a single value. The reason why the above solution does not
		 * use the dp array is that dp[i] only depends on dp[i - 1] so there is
		 * no need to save all the previous max and min data, just save the
		 * previous max and min value(dp[i - 1]). The following code uses the
		 * custom inner class Tuple so that both imax and imin can be stored,
		 * and all imax and imin are stored in the dp array. Although it is a
		 * bit more complicated, it helps to deepen understanding.
		 * 
		 */
		public int maxProductDP(int[] nums) {
			Tuple[] dp = new Tuple[nums.length];
			dp[0] = new Tuple(nums[0], nums[0]);
			int res = dp[0].imax;
			
			for (int i = 1; i < nums.length; i++) {
				Tuple prev = dp[i - 1];
				int imax = Math.max(Math.max(nums[i], nums[i] * prev.imax), nums[i] * prev.imin);
				int imin = Math.min(Math.min(nums[i], nums[i] * prev.imax), nums[i] * prev.imin);
				dp[i] = new Tuple(imax, imin);
				res = Math.max(imax, res);
			}
			
			return res;
		}

	    class Tuple {
	        private int imax;
	        private int imin;
	        private Tuple(int imax, int imin) {
	            this.imax = imax;
	            this.imin = imin;
	        }
	    }

		
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums ={2,3,-2,4};
		System.out.println(solution.maxProduct(nums));
		System.out.println(solution.maxProductWithTwoPointerApproach(nums));
		System.out.println(solution.maxProductDP(nums));

		int[] nums2 ={-1,-2,-3,0,1};
		System.out.println(solution.maxProduct(nums2));
		System.out.println(solution.maxProductWithTwoPointerApproach(nums2));
		System.out.println(solution.maxProductDP(nums2));
		
		int[] nums3 ={1,-2,-3,4};
		System.out.println(solution.maxProduct(nums3));
		System.out.println(solution.maxProductWithTwoPointerApproach(nums3));
		System.out.println(solution.maxProductDP(nums3));
	}
}
