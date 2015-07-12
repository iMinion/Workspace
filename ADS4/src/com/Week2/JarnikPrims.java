/**
 * Change it as soon as possible
 * Fails as of now!
 */

package com.Week2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.String;
import java.util.Comparator;


/**
 * 
 * @author iMinion
 * Jarnik Prims Algorithm Implementation for Minimum Spanning Tree
 * Algorithm can be found in pg: 219 in Algorithms and Data Structures The 
 * Basic Tool box
 * 
 * INPUT 1
 * 7
 * (A,B,C,D,E,F,G)
 * 0 2 0 3 3 0 0
 * 2 0 3 0 4 0 0
 * 0 3 0 0 1 8 0
 * 3 0 0 0 0 7 0
 * 3 4 1 0 0 6 0
 * 0 0 8 7 6 0 9
 * 0 0 0 0 0 9 0
 * 
 * OUTPUT 1
 * (B,C,C,A,C,E,F)
 * (C,E,B,A,D,F,G)
 * 24
 * 
 * INPUT 2
 * 6
 * (A,B,C,D,E,F)
 * 0 3 0 0 6 5
 * 3 0 1 0 0 4
 * 0 1 0 6 0 4
 * 0 0 6 0 8 5
 * 6 0 0 8 0 2
 * 5 4 4 5 2 0
 * 
 * OUTPUT 2
 * (B,B,B,F,F,B)
 * (B,C,A,F,E,D)
 * 15
 */

public class JarnikPrims {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String ver = sc.nextLine();
		String []vert = ver.substring(1, ver.length() - 1).split(",");
		int i = 0;
		int graph[][] = new int[n][n];
		while(i < n) {
			String[] str = sc.nextLine().split(" ");
			for(int j = 0; j < str.length; j++) {
				graph[i][j] = Integer.parseInt(str[j]);
			}
			i++;
		}
		JarnikPrim jp = new JarnikPrim(graph, vert);
		ArrayList<Edge> ed = new ArrayList<Edge>();
		for(i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(graph[i][j] != 0) 
					ed.add(new Edge(vert[i], vert[j], graph[i][j]));
			}
		}
		ed.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				int a = o1.w - o2.w;
				if(a == 0) {
					a = o1.src.compareTo(o2.src);
					if(a == 0) return o1.dest.compareTo(o2.dest);
					return a;
				}
				return a;
			}
		});
		jp.doJP(ed.get(0).getSrc());
		System.out.println(jp);
		sc.close();
	}
}

class JarnikPrim {
	int[][] graph;
	int[] d;
	int[] p;
	int n;
	String[] vertices;
	PriorityQueue<Edge> q;
	int count = 0;

	JarnikPrim(int[][] graph, String[] vertices) {
		this.graph = graph;
		this.vertices = vertices;
		n = graph.length;
		d = new int[n];
		p = new int[n];
	}
	
	public int[] getD() {
		return d;
	}
	public int[][] getGraph() {
		return graph;
	}
	public int getN() {
		return n;
	}
	public String[] getVertices() {
		return vertices;
	}
	public void setN(int n) {
		this.n = n;
	}
	public void setVertices(String[] vertices) {
		this.vertices = vertices;
	}
	public void setGraph(int[][] graph) {
		this.graph = graph;
	}

	public int indexOf(String str) {
		for(int i = 0; i < vertices.length; i++) 
			if(vertices[i].equalsIgnoreCase(str)) return i;
		return -1;
	}

	public void doJP(String src) {
		q = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				int a = o1.w - o2.w;
				if(a == 0) {
					a = o1.src.compareTo(o2.src);
					if(a == 0) return o1.dest.compareTo(o2.dest);
					return a;
				}
				return a;
			}
		});

		Arrays.fill(d, 9999);
		Arrays.fill(p, -1);
		Edge e = new Edge(src, src, 0);
		q.add(e);
		while(q.size() != 0) {
			Edge ed = q.poll();
			String srcd = ed.getSrc();
			int ind = indexOf(srcd);
			count += d[ind];
			d[ind] = 0;
			
			for(int i = 0; i < graph[ind].length; i++) {
				if( i != ind) {
					if(graph[ind][i] < d[i]) {
						d[i] = graph[ind][i];
						p[i] = ind;
						Edge ede = new Edge(vertices[ind], vertices[i], d[i]);
						if(q.contains(ede)) {
							q.remove(ede);
							q.add(ede);
						}
						else q.add(ede);
					}
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		int i = 0;
		for(i = 0; i < p.length - 1; i++) {
			sb.append(vertices[p[i]]);
			sb.append(',');
		}
		sb.append(vertices[p[i]]);
		sb.append(')');
		sb.append('\n');
		sb.append(count);
		return sb.toString();
	}
}


class Edge implements Comparable<Edge> {
	String src;
	String dest;
	int w;

	Edge(String src, String dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.w = weight;
	}

	public String getDest() {
		return dest;
	}

	public String getSrc() {
		return src;
	}

	public int getWeight() {
		return w;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setWeight(int w) {
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		int a = this.w - o.w;
		if(a == 0) {
			a = this.src.compareTo(o.src);
			if(a == 0) return this.dest.compareTo(o.dest);
			return a;
		}
		return a;
	}	

	public boolean equals(Object o) {
		if(this == o) return true;
		else if(o instanceof Edge) {
			Edge e = (Edge) o;
			return this.src.compareTo(e.src) == 0 &&
					this.dest.compareTo(e.dest) == 0;
		}
		return false;
	}
}