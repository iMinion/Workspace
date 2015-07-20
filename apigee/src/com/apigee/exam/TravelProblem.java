package com.apigee.exam;
import java.util.Scanner;

public class TravelProblem {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int[] litres = new int[n];
		int[] dist = new int[n];
		for(int i = 0; i < n; i++) {
			String[] str = sc.nextLine().split(" ");
			litres[i] = Integer.parseInt(str[0]);
			dist[i] = Integer.parseInt(str[1]);
		}
		sc.close();
	}
}

class TP {
	int n;
	int[] litres;
	int[] dist;
	
	TP(int n, int[] litres, int[] dist) {
		this.n = n;
		this.litres = litres;
		this.dist = dist;
	}
	
	
}

class MB {
	int lit = 0;
	int dis = 0;
	
	public int getDis() {
		return dis;
	}
	
	public int getLitLeft() {
		return lit;
	}
	
	public void setDis(int dis) {
		this.dis = dis;
	}
	
	public void setLit(int lit) {
		this.lit = lit;
	}
	
	
}