package com.leda2.exam;

import java.util.Scanner;

class SortedListPC2 {
	int[] a;
	int[] b;
	
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] str = s.split(" ");
		a = new int[str.length];
		for(int i = 0; i < str.length; ++i)
			a[i] = Integer.parseInt(str[i]);
		s = sc.nextLine();
		str = s.split(" ");
		b = new int[str.length];
		for(int i = 0; i < str.length; ++i)
			b[i] = Integer.parseInt(str[i]);
		sc.nextLine();
		sc.close();
	}
	
	public void mergeSort() {
		this.b = mergeSort(this.b);
	}
	
	public int[] mergeSort(int[] a) {
		int []b;
		int []c;
		if(a.length == 1) return a;
		else {
			b = new int[a.length/2];
			c = new int[a.length - a.length/2];
			System.arraycopy(a, 0, b, 0, b.length);
			System.arraycopy(a, b.length, c, 0, c.length);
			return merge(mergeSort(b), mergeSort(c));
		}
	}
	
	
	public int[] merge(int []a, int []b) {
		int c[] = new int[a.length + b.length];
		int k = 0;
		int i = 0;
		int j = 0;
		while(k < c.length) {
			if(i == a.length && j != b.length) {
				for(;j < b.length; ++j) {
					c[k++] = b[j];
				}
			}
			else if(i != a.length && j == b.length) {
				for(;i < a.length; ++i) {
					c[k++] = a[i];
				}
			}
			else {
				if(a[i] < b[j]) {
					c[k++] = a[i++];
				}
				else {
					c[k++] = b[j++];
				}
			}
		}
		return c;
	}
	
	public static void main(String[] args) {
		SortedListPC2 sor = new SortedListPC2();
		sor.readInput();
		sor.mergeSort();
		sor.mergeA();
		System.out.println(sor);
	}
	
	public void mergeA() {
		a = merge(a, b);
	}
	
	public String toString() {
		String s = "";
		int i = 0;
		for(; i < a.length - 1; ++i) {
			s = s + a[i] + " ";
		}
		s = s + a[i];
		return s;
	}
}