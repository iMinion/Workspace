package com.leda2.exam;

import java.util.Scanner;

class try1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = "";
		String r = "";
		sc.useDelimiter("\n");
		for(int i = 0; i < 10; ++i) {
			String t = sc.nextLine();
			s = s + t + "\n";
			r = r + t;
		}
		System.out.println("s = " + s + "\n r = " + r);
		sc.close();
	}
}