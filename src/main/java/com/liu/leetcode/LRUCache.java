package com.liu.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	boolean debug=true;
	private int capacity;
	private int position;
	private Map<Integer, DoubleLinkedList> cacheMap;
	private DoubleLinkedList start;
	private DoubleLinkedList end;

	void D(String msg){
		if(debug){
			System.out.println(msg);
		}
	}
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cacheMap = new HashMap<Integer, DoubleLinkedList>();
		this.position = 0;
		start = new DoubleLinkedList(0, 0);
		end = new DoubleLinkedList(0, 0);
		start.next = end;
		end.pre = start;
	}

	public int get(int key) {
		DoubleLinkedList node = cacheMap.get(key);
		if (node == null) {
			D("-1");
			return -1;
		}
		removeFromList(node);
		addToStart(node);
		D(""+node.value);
		return node.value;
	}

	private void addToStart(DoubleLinkedList node) {
		start.next.pre = node;// add to start node
		node.next = start.next;
		node.pre = start;
		start.next = node;
	}

	private void removeFromList(DoubleLinkedList node) {
		node.pre.next = node.next;// remove from linkdeList
		node.next.pre = node.pre;
	}

	public void set(int key, int value) {
		DoubleLinkedList node = cacheMap.get(key);
		if (node == null) {
			position++;
			node = new DoubleLinkedList(key, value);
			if (position > capacity) {
				// remove overflow end node first
				cacheMap.remove(end.pre.key);
				D("remove:"+end.pre.key);
				// remove end previous node
				end.pre.pre.next = end;
				end.pre = end.pre.pre;
			}
			cacheMap.put(key, node);
			// add to start
			addToStart(node);
		} else {
			node.value = value;
			// remove from linkedList
			removeFromList(node);
			// add to start
			addToStart(node);
		}
	}

	static class DoubleLinkedList {
		int key;
		int value;
		DoubleLinkedList pre;
		DoubleLinkedList next;

		public DoubleLinkedList(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(2);

		// lruCache.set(1, 1);
		// lruCache.set(2, 2);
		// lruCache.set(3, 3);
		// lruCache.set(4, 4);
		// lruCache.set(5, 5);

		// lruCache.set(2, 1);
		// lruCache.set(1, 1);
		// lruCache.get(2);
		// lruCache.set(4, 1);
		// lruCache.get(1);
		// lruCache.get(2);

		// lruCache.set(2, 1);
		// lruCache.set(2, 2);
		// System.out.println(lruCache.get(2));
		// lruCache.set(1, 1);
		// lruCache.set(4, 1);
		// lruCache.get(2);
		//
		// System.out.println(lruCache.get(2));

		lruCache.set(1, 1);
		lruCache.set(2, 2);
		lruCache.set(3, 3);
		lruCache.set(4, 4);
		lruCache.get(4);
		lruCache.get(3);
		lruCache.get(2);
		lruCache.get(1);
		lruCache.set(5, 5);
		lruCache.get(1);
		lruCache.get(2);
		lruCache.get(3);
		lruCache.get(4);
		lruCache.get(5);
	}
}
