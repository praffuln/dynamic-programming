package com.dynamic.programming.pepcoding;

/**
 * 1. You are given a number n, representing the number of days.
 * 
 * 2. You are given n numbers, where ith number represents price of stock on ith
 * day.
 * 
 * 3. You are required to print the maximum profit you can make if you are
 * allowed a single transaction.
 *
 */
public class BestTimeToBuyAndSellStock_30 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		int minPriceToBuy = arr[0];
		int profile = 0;
		for(int i=1; i< arr.length; i++) {
			int newProfile = arr[i] - minPriceToBuy;
			if(profile <= newProfile) {
				profile = newProfile;
			}
			
			if(minPriceToBuy >= arr[i]) {
				minPriceToBuy = arr[i];
			}
		}
		
		System.out.println("profile - "+ profile);
	}
}
