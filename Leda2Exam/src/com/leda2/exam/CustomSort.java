package com.leda2.exam;

import java.util.Scanner;



class CustomSort{
	char[] order;
	String[] str;
	public void setStrings(String s) {
		str = s.split(" ");
	}
	public void setOrder(String s) {
		String[] str = s.split(" ");
		order = new char[str.length];
		for(int i = 0; i < str.length; ++i) {
			order[i] = str[i].charAt(0);
		}
	}

	public int findValue(char c) {
		for(int i = 0; i < order.length; ++i) {
			if(order[i] == c) {
				return i;
			}
		}
		return -1;
	}

	public int compare(String s1, String s2) {
		int l = Math.min(s1.length(), s2.length());
		int i = 0;
		while(i < l) {
			int a = findValue(s1.charAt(i));
			int b = findValue(s2.charAt(i));
			if( a != b) {
				return a - b;
			}
			++i;
		}
		return s1.length() - s2.length();
	}
	
	public void sorts() {
		for(int i = 0; i < str.length - 1; ++i) {
			for(int j = i + 1; j < str.length; ++j) {
				if(compare(str[i], str[j]) > 0) {
					String temp = str[j];
					str[j] = str[i];
					str[i] = temp;
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String s: str) {
			sb.append(s);
			sb.append(" ");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomSort cs = new CustomSort();
		cs.setStrings(sc.nextLine());
		cs.setOrder(sc.nextLine());
		sc.nextLine();
		sc.close();
		cs.sorts();
		System.out.println(cs);
	}
}