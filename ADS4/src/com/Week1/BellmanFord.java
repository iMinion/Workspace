package com.Week1;

import java.util.Arrays;
import java.util.Scanner;
public class BellmanFord {
	String[] vertices;
	int[][] graph;
	String source;

	public static void main(String[] args) {
		BellmanFord bFord = new BellmanFord();
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		bFord.source = sc.nextLine();
		bFord.vertices = sc.nextLine().split(",");
		bFord.graph = new int[n][n];
		int i = 0;
		while(i < n) {
			String s[] = sc.nextLine().split(",");
			for(int j = 0; j < s.length; j++) {
				int k = Integer.parseInt(s[j]);
				if(k == 0) bFord.graph[i][j] = Integer.MAX_VALUE; 
				else bFord.graph[i][j] = k;
			}
			i++;
		}
		sc.close();
		BellFord bf = new BellFord(bFord.graph, bFord.vertices);
		bf.computeShortestPath();
		System.out.println(bf);
	}

	public int getIndex(String s) {
		for(int i = 0; i < vertices.length; i++) {
			if(vertices[i].equals(s)) 
				return i;
		}
		return -1;
	}
}


class BellFord {
	int[][] graph;
	int[] d;
	int[] parent;
	String[] vertices;
	boolean flag = false;

	public BellFord(int[][] graph, String[] vertices) {
		this.graph = graph;
		this.vertices = vertices;
		d = new int[graph.length];
		parent = new int[graph.length];
		Arrays.fill(d, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
	}

	public ParentDistance computeShortestPath() {
		parent[0] = 0;
		d[0] = 0;
		for(int k = 0; k < graph.length - 1; k++) {
			for(int i = 0; i < graph.length; i++) 
				for(int j = 0; j < graph[i].length; j++) 
					if(graph[i][j] != Integer.MAX_VALUE) 
						if(d[i] + graph[i][j] < d[j] ) {
							d[j] = d[i] + graph[i][j];
							parent[j] = i;
						}
		}
		for(int i = 0; i < graph.length; i++)
			for(int j = 0; j < graph.length; j++) {
				long l = (long) d[i] + (long) graph[i][j];
				if(l < d[j]) {
					flag = true;
				}
			}
		return new ParentDistance(d, parent);
	}

	public void infect(int j) {
		if(d[j] > Integer.MIN_VALUE) {
			d[j] = Integer.MIN_VALUE;
			for(int i = 0; i < graph[j].length; i++)
				if(graph[j][i] != Integer.MAX_VALUE) infect(i);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(flag) sb.append("Graph contains a negative weight cycles.");
		else {
			for(int i = 0; i < vertices.length; i++) {
				StringBuilder rb = new StringBuilder();
				int k = parent[i];
				rb.append(vertices[i]);
				if(k != 0) {
					rb.append(">-");
					while(k != 0) {
						rb.append(vertices[k]);
						int tem = getIndex(vertices[k]);
						k = parent[tem];
						rb.append(">-");
					}
					sb.append(vertices[0]);
				}
				else {
					sb.append(vertices[0]);
					sb.append("->");
				}
				sb.append(reverse(rb.toString()));
				sb.append(":");
				sb.append(d[i]);
				sb.append('\n');
			}
		}
		return sb.toString();
	}

	public int getIndex(String s) {
		for(int i = 0; i < vertices.length; i++) {
			if(vertices[i].equals(s)) 
				return i;
		}
		return -1;
	}

	public String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--)
			sb.append(s.charAt(i));
		return sb.toString();
	}
}


class ParentDistance {
	int[] d;
	int[] parent;
	
	ParentDistance(int[] d, int[] parent) {
		this.parent = parent;
		this.d = d;
	}
	
	int[] getDistances() {
		return d;
	}
	
	int[] getParents() {
		return parent;
	}
	
	void setParent(int[] parent) {
		this.parent = parent;
	}
	
	void setDistances(int[] d) {
		this.d = d;
	}
}