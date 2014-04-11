package com.liu.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BinaryTreeTraversal {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			this.val = x;
		}
	}

	public static ArrayList<Integer> postorderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<Integer> postorder = new ArrayList<Integer>();
		Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
		TreeNode node = root;
		TreeNode visited = null;
		while (!deque.isEmpty() || node != null) {
			if (node != null) {//move to the left node
				deque.push(node);
				node = node.left;
			} else {
				TreeNode peekNode = deque.peek();//get the current node
				if (peekNode.right == visited || peekNode.right == null) {//current node right child visited
					peekNode = deque.pop();//remove from stack
					postorder.add(peekNode.val);
					visited = peekNode;
				} else {
					node = peekNode.right;
				}
			}
		}
		return postorder;
	}

	public static ArrayList<Integer> postorderTraversalRecursion(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<Integer> postorder = new ArrayList<Integer>();
		if (root.left != null) {
			postorder.addAll(postorderTraversalRecursion(root.left));
		}
		if (root.right != null) {
			postorder.addAll(postorderTraversalRecursion(root.right));
		}
		postorder.add(root.val);
		return postorder;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		//		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.right = new TreeNode(1);
		//		root.right.left = new TreeNode(3);
		System.out.println(postorderTraversal(root));
	}
}
