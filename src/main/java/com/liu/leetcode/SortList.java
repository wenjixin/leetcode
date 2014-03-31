package com.liu.leetcode;


public class SortList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}

		@Override
		public String toString() {
			return val + "";
		}
	}

	public static ListNode sortList(ListNode head) {
		return mergeSort(head);
	}

	public static ListNode insertSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode curr = head.next;
		head.next = null;
		while (curr != null) {
			ListNode sigleNode = curr;
			curr = curr.next;
			sigleNode.next = null;
			head = insertCurr(head, sigleNode);
		}
		return head;
	}

	private static ListNode insertCurr(ListNode head, ListNode curr) {
		ListNode dummyNode = new ListNode(0);
		dummyNode.next = head;
		ListNode pre = dummyNode;
		while (head != null) {
			if (curr.val <= head.val) {
				curr.next = head;
				pre.next = curr;
				return dummyNode.next;
			}
			head = head.next;
			pre = pre.next;
		}
		pre.next = curr;
		return dummyNode.next;
	}

	public static ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = getMidNode(head);
		ListNode copyMid = mid.next;
		mid.next = null;
		ListNode l1 = mergeSort(head);
		ListNode l2 = mergeSort(copyMid);
		return merge(l1, l2);
	}

	public static ListNode getMidNode(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static ListNode merge(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode copyNode = head;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}
		head.next = (l1 == null) ? l2 : l1;
		return copyNode.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(100);
		ListNode copyHead = head;
		for (int i = 1000; i > 0; i--) {
			head.next = new ListNode(i);
			head = head.next;
		}
		insertSortList(copyHead);
		while (copyHead != null) {
			System.out.println(copyHead);
			copyHead = copyHead.next;
		}
	}
}
