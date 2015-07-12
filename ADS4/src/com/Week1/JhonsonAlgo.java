package com.Week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author iMinion
 * Jhonson Algorithm for all pair shortest path implementation
 */

public class JhonsonAlgo {
	String[] vertices;
	int[][] graph;


	public static void main(String[] args) {
		JhonsonAlgo jAl = new JhonsonAlgo();
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		jAl.vertices = sc.nextLine().split(",");
		int i = 0;
		jAl.graph = new int[n][n];
		while(i < n) {
			String[] str = sc.nextLine().split(",");
			for(int j = 0; j < n; j++) {
				jAl.graph[i][j] = Integer.parseInt(str[j]);
			}
			i++;
		}
		sc.close();
		Jhonson jo = new Jhonson(jAl.vertices, jAl.graph);
		jo.impJhonson();
	}
}


class Jhonson {
	String[] vertices;
	int[][] graph;
	int[] d;
	int[] parent;
	int n;
	int[] dist;
	ArrayList<String> eleList=new ArrayList<String>();
	PriorityQueue<String> q;

	Jhonson(String[] vertices, int[][] graph) {
		this.graph = graph;
		this.vertices = vertices;
		eleList = new ArrayList<String>(Arrays.asList(vertices));
		n = vertices.length;
		d = new int[n + 1];
		Arrays.fill(d, 9999);
	}

	public void impJhonson() {
		bellFord();
		StringBuilder rb = new StringBuilder();
		int[][] gr = new int[n][n];
		for(int i = 0; i < vertices.length; i++) {
			int[] dist = dijkstras(graph, vertices[i]);
			for(int j = 0; j < dist.length; j++) 
				gr[i][j] = dist[j] + d[j + 1] - d[i + 1];
		}
		
		for(int i = 0; i < graph.length; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < graph.length; j++) {
				sb.append(gr[i][j]);
				sb.append(',');
			}
			rb.append(sb.substring(0, sb.length() - 1));
			rb.append('\n');
		}
		System.out.println(rb.toString());
	}


	/**
	 * Alter edge weights using Bellman Ford's Algorithm 
	 */

	public void bellFord() {
		int[][] altGraph = new int[n+1][n+1];
		Arrays.fill(altGraph[0], 0);
		// Add the source and update graph
		for(int i = 1; i < altGraph.length; i++) {
			altGraph[i][0] = 999;
		}
		// We can reach from the added source to any node in a 
		// single hop by and edge of weight of 0
		Arrays.fill(d, 0);
		for(int i = 1; i < altGraph.length; i++) {
			for (int j = 1; j < altGraph.length; j++) {
				altGraph[i][j] = graph[i-1][j-1];
			}
		}

		for(int k = 0; k < altGraph.length - 1; k++) {
			for(int i = 0; i < altGraph.length; i++) 
				for(int j = 0; j < altGraph[i].length; j++) 
					if(altGraph[i][j] != 999) 
						if(d[i] + altGraph[i][j] < d[j] ) {
							d[j] = d[i] + altGraph[i][j];
						}
		}

		for(int i = 1; i < altGraph.length; i++)
			for(int j = 1; j < altGraph.length; j++) {
				long l = (long) d[i] + (long) altGraph[i][j];
				if(l < d[j]) {
					System.out.println("Graph contains a negative weight "
							+ "cycles. Can't able to find the shortest path "
							+ "distances between every pair of vertices for "
							+ "the given graph.");
					System.exit(0);
				}
			}

		// Update the original graph
		// graph[i][j] = graph[i][j] + d[i + 1] - d[j + 1];
		// this is equivalent to 
		// c'(e) = c(e) + pot(u) - pot(v);
		// where pot(u) = d[u] from added source

		for(int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(graph[i][j] != 999) {
					graph[i][j] = graph[i][j] + d[i + 1] - d[j + 1];
				}
			}
		}
	}

	public int[] dijkstras(int[][] graph, String src) {
		q = new PriorityQueue<String>();
		int[] dist = new int[n];
		for(int i = 0; i < dist.length; i++) dist[i] = 99999;
		int el = eleList.indexOf(src);
		dist[el] = 0;

		q.add(src);
		while(!(q.isEmpty())){
			String ele = getMin();
			q.remove(ele);
			int ind = eleList.indexOf(ele);
			for(int i = 0; i < graph[ind].length; i++) {
				int v = dist[ind] + graph[ind][i];
				if(v < dist[i]){
					dist[i] = v;
					if(q.contains(vertices[i])){
						dist[i] = dist[ind] + v;
					}
					else{
						q.add(vertices[i]);
					}
				}
			}
		}
		return dist;
	}

	private String getMin() {
		int min = 99999;
		String str = new String("");
		for(String s: q) {
			String ele = s;
			if((d[eleList.indexOf(ele)]) < min){
				min = d[eleList.indexOf(ele)];
				str = ele;
			}
		}
		return str;
	}

}