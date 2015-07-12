package com.leda2.exam;

import java.util.Scanner;



//import java.util.Queue;

class BinaryHeap {
	private int[] heapArray;
	private int w = 100;
	private int n = 0;
	private String s = "";
	
	
	BinaryHeap() {
		heapArray = new int[w];
		heapArray[0] = -9999;
	}
	
	BinaryHeap(int w) {
		this.w = w;
		heapArray = new int[w];
		heapArray[0] = -9999;
	}
	
	BinaryHeap(int[] heapArray) {
		this.heapArray = heapArray;
		n += (heapArray.length - 1);
	}
	
	void setN(int n) {
		this.n = n;
	}
	
	int size() {
		return this.n;
	}
	
	void swap(int x, int y) {
		heapArray[x] = (heapArray[x] + heapArray[y]) - (heapArray[y] = heapArray[x]);
	}
	
	
	void insert(int e) {
		assert n<w: "Crossed the limits of the Heap Array";
		heapArray[n] = e;
		shiftUp(n++);
	}
	
	void shiftUp(int i) {
		if (i == 0 || heapArray[(i-1)/2] <= heapArray[i]) {
			return;
		}
		else {
			swap(i, (i-1)/2);
			shiftUp((i-1)/2);
		}
	}
	
	int deleteMin() {
		assert n >= 0;
		int result = heapArray[0];
		heapArray[0] = heapArray[n-1];
		heapArray[n-1] = 0;
		--n;
		shiftDown(0);
		return result;
	}
	
	void shiftDown(int i) {
		int m = 0;
		if( (2 * i) + 1 <= n-1) {
			if( (2 * i) + 2 > n-1 || (heapArray[ (2 * i) + 1 ] < heapArray[ (2 * i) +2])) m = (2 * i) + 1;
			else m = (2 * i) + 2;
			if(heapArray[i] > heapArray[m]) {
				swap(i, m);
				shiftDown(m);
			}
		}
	}
	
	void modify(int index, int value) {
		heapArray[index] = value;
		modifyShift(index);
	}
	
	private void modifyShift(int i) {
		
		if(i > 0) {
			if(heapArray[i/2] > heapArray[i]) {
				shiftUp(i);
			}
			else shiftDown(i);
		}
		else if(i == 0) {
			shiftDown(i);
		}
		else if(i<0) {
			return;
		}
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(n == 0) {
			return "";
		}
		else {
			for(int i = 0; i < n - 1; ++i) {
				sb.append(heapArray[i]);
				sb.append(" ");
			}
			sb.append(heapArray[n - 1]);
			return sb.toString();
		}
	}
	String getStringS() {
		return s;
	}
}



class MinHeap {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.nextLine();
		sc.close();
		String[] str = s.split(" , ");
		BinaryHeap heap = new BinaryHeap();
		for(int i = 0; i < str.length; i++) {
			if(str[i].charAt(0) == 'i') {
				heap.insert(Integer.parseInt(str[i].substring(str[i].indexOf(' ') + 1)));
			}
			else if(str[i].charAt(0) == 'd') {
				heap.deleteMin();
			}
			else {
				String mo[] = str[i].split(" ");
				heap.modify(Integer.parseInt(mo[1]) - 1, Integer.parseInt(mo[2]));
			}
			System.out.println(heap);
		}
	}
}