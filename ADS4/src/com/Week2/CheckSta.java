package com.Week2;

import java.util.ArrayList;

/**
 * 
 * @author iMinion
 * Checking with static and non static assignments
 */

public class CheckSta {
	static ArrayList<Integer> i = new ArrayList<Integer>();
	ArrayList<Integer> j = new ArrayList<Integer>();
	public static void main(String[] args) {
		CheckSta cs = new CheckSta();
		i.add(10);
		i.add(20);
		cs.j = new ArrayList<Integer>(i);
		cs.j.remove(0);
		System.out.println("i = " + i);
		System.out.println("j = " + cs.j);
	}
}
