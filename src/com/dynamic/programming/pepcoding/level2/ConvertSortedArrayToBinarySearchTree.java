package com.dynamic.programming.pepcoding.level2;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class ConvertSortedArrayToBinarySearchTree {

	TreeNode ans;

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) {
			return null;
		}
		int n = nums.length;
		ans = dfs(nums, 0, n - 1);
		return ans;
	}

	public TreeNode dfs(int[] nums, int first, int last) {
		if (first > last) {
			return null;
		}
		int mid = first + (last - first) / 2;
		System.out.println("mid is " + mid);
		TreeNode node = new TreeNode(nums[mid]);
		node.left = dfs(nums, first, mid - 1);
		node.right = dfs(nums, mid + 1, last);
		return node;
	}

	public static void main(String[] args) {
		ConvertSortedArrayToBinarySearchTree snipet = new ConvertSortedArrayToBinarySearchTree();
		snipet.sortedArrayToBST(new int[] { -10, -3, 0, 5, 9 });
	}

	public class TreeNode {
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