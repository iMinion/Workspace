package com.leda2.exam;

import java.util.Scanner;


class DAryHeap {
	private int[] heapArray;
	private int w = 100;
	private int n = 0;
	private String s = "";
	private int d;
	
	
	DAryHeap() {
		heapArray = new int[w];
	}
	
	DAryHeap(int d) {
		this.d = d;
	}
	
	DAryHeap(int w, int d) {
		this.w = w;
		heapArray = new int[w];
		this.d = d;
	}
	
	DAryHeap(int[] heapArray) {
		this.heapArray = heapArray;
		n += (heapArray.length - 1);
	}
	
	void setD(int d) {
		this.d = d;
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
		if (i == 0 || heapArray[(i-1)/d] >= heapArray[i]) {
			return;
		}
		else {
			int j = (i-1)/d;
			
			for(j = d*j + 1; j < d*j + d; j++) {
				
			}
			swap(i, (i-1)/d);
			shiftUp((i-1)/d);
		}
	}
	
	int deleteMax() {
		assert n > 0;
		int result = heapArray[0];
		heapArray[0] = heapArray[n-1];
		heapArray[n-1] = 0;
		--n;
		shiftDown(0);
		return result;
	}
	
	void shiftDown(int i) {
		int m = 0;
		if( (d * i) + 1 <= n-1) {
			if( (d * i) + d > n-1 || (heapArray[ (d * i) + 1 ] > heapArray[ (d * i) + d])) m = (d * i) + 1;
			else m = (d * i) + d;
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
			if(heapArray[(i-1)/2] < heapArray[i]) {
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


public class DArySol {
	public static void main(String[] args) {
		DAryHeap max = new DAryHeap();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String str[] = s.split(" ");
		max.setD(Integer.parseInt(sc.nextLine()));
		sc.close();
		for(int i = 0; i < str.length; ++i) {
			max.insert(Integer.parseInt(str[i]));
			System.out.println(max);
		}
		int []sorted = new int[str.length];
		int i = 0;
		while((i = max.size()) > 0) {
			System.out.println(max);
			sorted[i-1] = max.deleteMax();
		}
		for(;i < str.length; ++i) {
			System.out.println(sorted[i]);
		}
	}
}