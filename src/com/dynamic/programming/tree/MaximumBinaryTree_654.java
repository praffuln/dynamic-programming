package com.dynamic.programming.tree;

/**
 * @author hp
 *
 */
public class MaximumBinaryTree_654 {

	public static void main(String[] args) {
		TreeNode t = new MaximumBinaryTree_654().constructMaximumBinaryTree(new int[]{1,4,9,3,2,7});
		System.out.println(t.val); //9
		System.out.println(t.left.val); //4
		System.out.println(t.left.left.val); //1
	}
	
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return constructMaximumBinaryTreeHelder(0, nums.length-1, nums);
	}
	
	public TreeNode constructMaximumBinaryTreeHelder(int low, int high, int[] nums) {
		if(low > high) return null;
		
		//find maxindex between low to end
		int index = low;
		int max = Integer.MIN_VALUE;
		for(int i=low; i<=high; i++) {
			if(max < nums[i]) {
				index = i;
				max = nums[i];
			}
		}
		
		TreeNode root = new TreeNode(nums[index]);
		root.left = constructMaximumBinaryTreeHelder(low, index-1, nums);
		root.right = constructMaximumBinaryTreeHelder(index+1, high, nums);
		
		return root;

	}

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

}
