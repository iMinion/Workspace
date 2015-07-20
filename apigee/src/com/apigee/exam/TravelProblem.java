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
		TP t = new TP(n, litres, dist);
		System.out.println(t.getNearestStation());
		System.out.println(t.getAllStations());
	}
}

class TP {
	int n;
	int[] litres;
	int[] dist;
	boolean[] allTraversed;
	MB m;
	
	TP(int n, int[] litres, int[] dist) {
		this.n = n;
		this.litres = litres;
		this.dist = dist;
		allTraversed = new boolean[n];
	}
	
	public int getNearestStation() {
		for(int i = 0; i < n ; i++) {
			m = new MB();
			if(litres[i] > dist[i]) {
				for(int j = 0; j < n; j++) allTraversed[j] = false;
				if(routeExists(i)) return i;
			}
		}
		return -1;
	}
	
	public int getAllStations() {
		int count = 0;
		for(int i = 0; i < n ; i++) {
			m = new MB();
			if(litres[i] > dist[i]) {
				for(int j = 0; j < n; j++) allTraversed[j] = false;
				if(routeExists(i)) count++;
			}
		}
		return count;
	}
	
	public boolean routeExists(int i) {
		int start = i;
		for(; !allTraversed(); i++) {
			if(i == n) i = 0;
			if(m.getLitLeft() + litres[i] >= dist[i]) {
				m.setLit(m.getLitLeft() + litres[i] - dist[i]);
				allTraversed[i] = true;
			}
			else return false;
		}
		i %= n;
		for(; i != start; i = (i + 1) % n) {
			System.out.println("the " + i);
			if(m.getLitLeft() + litres[i] >= dist[i]) {
				m.setLit(m.getLitLeft() + litres[i] - dist[i]);
			}
			else return false;
		}
		return true;
	}
	
	public boolean isRoutePossible(int i) {
		int start = i;
		for(; i < n; i++) {
			if(m.getLitLeft() + litres[i] > dist[i]) {
				m.setLit(m.getLitLeft() + litres[i] - dist[i]);
				allTraversed[i] = true;
			}
			else {
				return false;
			}
		}
		if(i >= n) i -= 1;
		if(!allTraversed()) {
			for(; i > 0; i--) {
				if(m.getLitLeft() + litres[i] > dist[i]) {
					m.setLit(m.getLitLeft() + litres[i] - dist[i]);
					allTraversed[i] = true;
				}
				else {
					return false;
				}
			}
		}
		if (i <= 0) i += 1;
		if( i != start) {
			if( i > start) {
				for(; i != start; i--) {
					if(m.getLitLeft() + litres[i] > dist[i]) {
						m.setLit(m.getLitLeft() + litres[i] - dist[i]);
					}
					else {
						return false;
					}					
				}
			}
			if( i < start) {
				for(; i != start; i++) {
					if(m.getLitLeft() + litres[i] > dist[i]) {
						m.setLit(m.getLitLeft() + litres[i] - dist[i]);
					}
					else {
						return false;
					}					
				}
			}
		}
		return true;
	}
	
	public boolean allTraversed() {
		for(int i = 0; i < n; i++) {
			if(!allTraversed[i]) return false;
		}
		return true;
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