package com.Sorting;

import java.util.Scanner;

public class QuickSortInPlace {
	static int []a;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		sc.close();
		a = new int[str.length];
		for(int i = 0; i < str.length; i++) {
			a[i] = Integer.parseInt(str[i]);
		}
		_quick_sort_(0, a.length -1);
		for(int i: a) System.out.println(i);
	}
	
	public static void _quick_sort_(int low, int high) {
		if(low < high) {
			int p = low;
			int i = low + 1;
			int j = high;
			while(i <= j) {
				while(a[i] < a[p]) i++;
				while(a[j] > a[p]) j--;
				if(i < j) a[i] = (a[i] + a[j]) - (a[j] = a[i]);
			}
			a[p] = (a[p] + a[j]) - (a[j] = a[p]);
			_quick_sort_(low, j -1);
			_quick_sort_(j + 1, high);
		}
	}
}
