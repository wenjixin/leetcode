package com.liu.leetcode;

public class ReorderList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}

	/**
	 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,<br>
	 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…<br>
	 * You must do this in-place without altering the nodes' values.<br>
	 * For example,
	 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 * @param head
	 */
	public static void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode halfNode = getMiddleNode(head);
		ListNode halfHead = halfNode.next;
		halfNode.next = null;
//		printList(halfHead);
		halfHead=reverseList(halfHead);
//		System.out.println("********************************");
//		printList(halfHead);
//		System.out.println("********************************");
		mergerTwoList(head, halfHead);
	}

	public static ListNode reverseList(ListNode halfHead) {
		if (halfHead == null) {
			return halfHead;
		}
		ListNode copyHead = halfHead;
		ListNode head = halfHead;
		while (copyHead.next != null) {
			ListNode removeNode = copyHead.next;
			copyHead.next = copyHead.next.next;
			removeNode.next = head;
			head = removeNode;
		}
		halfHead = head;
		return head;
	}

	public static void mergerTwoList(ListNode head, ListNode halfHead) {
		while (head != null && halfHead != null) {
			ListNode copyNext = halfHead.next;
			halfHead.next = head.next;
			head.next = halfHead;
			head = head.next.next;
			halfHead = copyNext;
		}
	}

	public static ListNode getMiddleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static void printList(ListNode head) {
		if (head == null) {
			return;
		}
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		reorderList(head);
		printList(head);
	}
}
