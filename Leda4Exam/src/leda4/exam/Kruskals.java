package leda4.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskals {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String v = sc.nextLine();
		v = v.substring(1, v.length() - 1);
		int i = 0;
		int graph[][] = new int[n][n];
		while(i < n) {
			String str[] = sc.nextLine().split(" ");
			for (int j = 0; j < str.length; j++) {
				graph[i][j] = Integer.parseInt(str[j]);
			}
			i++;
		}
		Kruskal k = new Kruskal(graph, v.split(","));
		k.applyKruskal();
		sc.close();
	}
}

class Kruskal {
	ArrayList<EdgeL> edges;
	ArrayList<EdgeL> forest;
	int graph[][];
	int n;
	String vertices[];
	Kruskal(int graph[][], String vertices[]) {
		this.graph = graph;
		this.vertices = vertices;
		n = this.graph.length;
		edgeRecognition();
		forest = new ArrayList<EdgeL>();
	}

	private void edgeRecognition() {
		edges = new ArrayList<EdgeL>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(graph[i][j] != 0) {
					EdgeL forw = 
							new EdgeL(vertices[i], vertices[j], graph[i][j]);
					EdgeL back = 
							new EdgeL(vertices[j], vertices[i], graph[i][j]);
					if(!edges.contains(forw) && !edges.contains(back))
						edges.add(forw);
				}
			}
		}
		edges.sort(new Comparator<EdgeL>() {
			@Override
			public int compare(EdgeL o1, EdgeL o2) {
				if(o1.getWeight() == o2.getWeight()) {
					int compSrc = o1.getSrc().compareTo(o2.getSrc());
					if(compSrc == 0)
						return o1.getDest().compareTo(o2.getDest());
					return compSrc;
				}
				return o1.getWeight() - o2.getWeight();
			}
		});
	}

	public void applyKruskal() {
		UnionFind uf = new UnionFind(n, vertices);
		for (int i = 0; i < edges.size(); i++) {
			if(uf.union(edges.get(i))) {
				forest.add(edges.get(i));
			}
		}
		int count = 0;
		for (int i = 0; i < forest.size(); i++) {
			count += forest.get(i).getWeight();
			System.out.println(forest.get(i));
		}
		System.out.println(count);
	}

}

class UnionFind {
	private int n;
	private int[] rank;
	private int[] parent;
	private String[] vertices;
	
	public int getN() {
		return n;
	}
	
	public int[] getParent() {
		return parent;
	}
	
	public int[] getRank() {
		return rank;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public void setParent(int[] parent) {
		this.parent = parent;
	}
	
	public void setRank(int[] rank) {
		this.rank = rank;
	}
	
	public UnionFind(int n, String[] vertices) {
		this.n = n;
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) 
			parent[i] = i;
		Arrays.fill(rank, 0);
		this.vertices = vertices;
	}
	
	public int find(int s) {
		if(parent[s] == s) return s;
		else {
			int l = find(parent[s]);
			parent[s] = l;
			return l;
		}
	}
	
	public void link(int i, int j) {
		if(rank[i] < rank[j]) parent[i] = j;
		else {
			parent[j] = i;
			if(rank[i] == rank[j]) rank[i]++;
		}
	}
	
	public boolean union(EdgeL e) {
		int i = indexOf(e.getSrc());
		int j = indexOf(e.getDest());
		if(find(i) != find(j)) {
			link(find(i), find(j));
			return true;
		}
		return false;
	}
	
	private int indexOf(String s) {
		for (int i = 0; i < vertices.length; i++) {
			if(vertices[i].equals(s)) return i;
		}
		return -1;
	}
}

class EdgeL implements Comparable<EdgeL> {

	private String src;
	private String dest;
	private int weight;

	EdgeL(String src, String dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	public String getDest() {
		return dest;
	}

	public String getSrc() {
		return src;
	}

	public int getWeight() {
		return weight;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(EdgeL o) {
		if(this.weight == o.weight) {
			int compSrc = this.src.compareTo(o.src);
			if(compSrc == 0)
				return this.dest.compareTo(o.dest);
			return compSrc;
		}
		return this.weight - o.weight;
	}

	public boolean equals(Object o) {
		if(this == o) return true;
		if(o instanceof EdgeL) {
			EdgeL temp = (EdgeL) o;
			return this.weight == temp.weight && this.src.equals(temp.src) &&
					this.dest.equals(temp.dest);
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		sb.append(this.src);
		sb.append(',');
		sb.append(this.dest);
		sb.append(')');
		return sb.toString();
	}
}