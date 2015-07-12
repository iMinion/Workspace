package com.Week1;

import java.util.Scanner;

public class FloydWarshall {
	String[] vertices;
	int[][] graph;
	public static void main(String[] args) {
		FloydWarshall fwar = new FloydWarshall();
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		fwar.vertices = new String[n];
		fwar.graph = new int[n][n];
		fwar.vertices = sc.nextLine().split(",");
		int i = 0;
		while(i < n) {
			String[] str = sc.nextLine().split(",");
			for(int j = 0; j < n; j++) {
				fwar.graph[i][j] = Integer.parseInt(str[j]);
			}
			i++;
		}
		sc.close();
		FWarshall fw = new FWarshall(fwar.vertices, fwar.graph);
		fw.computeShortestPaths();
		System.out.println(fw);
	}
}

class FWarshall {
	String[] vertices;
	int[][] graph;

	public FWarshall(String[] vertices, int[][] graph) {
		this.vertices = vertices;
		this.graph = graph;
	}

	public void computeShortestPaths() {
		for(int k = 0; k < vertices.length; k++) 
			for(int i = 0; i < vertices.length; i++)
				for(int j = 0; j < vertices.length; j++)
					if(graph[i][j] > graph[i][k] + graph[k][j]) 
						graph[i][j] = graph[i][k] + graph[k][j];
		for(int i = 0; i < vertices.length; i++)
			for(int j = 0; j < vertices.length; j++) 
				if(i == j) 
					if(graph[i][j] == 0)
						System.out.println("Graph Contains a negative cycle");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < graph.length; i++) 
			for(int j = 0; j < graph.length; j++) {
				sb.append(graph[i][j]);
				if(j == graph.length - 1)  sb.append('\n');
				else sb.append(',');
			}
		return sb.toString();		
	}
}