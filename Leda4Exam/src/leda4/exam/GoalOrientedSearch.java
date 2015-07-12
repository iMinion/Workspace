package leda4.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GoalOrientedSearch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String str = sc.nextLine();
		str = str.substring(1, str.length() - 1);
		String[] vertices = str.split(",");
		String src = sc.nextLine();
		String dest = sc.nextLine();
		int[][] graph = new int[n][n];
		int i = 0;
		while(i < n) {
			String s[] = sc.nextLine().split(" ");
			for(int j = 0; j < s.length; j++) 
				graph[i][j] = Integer.parseInt(s[j]);
			i++;
		}
		sc.close();
		GOS gSearch = new GOS(graph, vertices, src, dest);
		gSearch.dijkstras();
		System.out.println(gSearch);
	}
}


class GOS {
	int[][] graph;
	String[] vertices;
	String src;
	String dest;
	int n;
	PriorityQueue<String> q;
	ArrayList<String> eleList;
	int dist[];
	int[] p;

	GOS(int[][] graph, String[] vertices, String src, String dest) {
		this.vertices = vertices;
		this.src = src;
		this.dest = dest;
		eleList = new ArrayList<String>(Arrays.asList(vertices));
		n = graph.length;
		this.graph = new int[n][n];
		
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(graph[i][j] < 0) {
					System.out.println("Not possible.");
					System.exit(0);
				}
				else if(graph[i][j] == 0) this.graph[i][j] = 999;
				else this.graph[i][j] = graph[i][j];
			}
		}
		n = graph.length;
		dist = new int[n];
		Arrays.fill(dist, 9999);
		p = new int[n];
		Arrays.fill(p, -1);
	}

	public void dijkstras() {
		q = new PriorityQueue<String>();
		int el = eleList.indexOf(src);
		dist[el] = 0;
		p[el] = el;
		q.add(src);
		while(!(q.isEmpty())){
			String ele = getMin();
			q.remove(ele);
			if(ele.equals(dest)) return;
			int ind = eleList.indexOf(ele);
			for(int i = 0; i < graph[ind].length; i++) {
				if(graph[ind][i] != 999) {
					int v = dist[ind] + graph[ind][i];
					if(v < dist[i]) {
						dist[i] = v;
						p[i] = ind;
						if(q.contains(vertices[i]))
							dist[i] = dist[ind] + v;
						else q.add(vertices[i]);
					}
				}
			}
		}
	}
	private String getMin() {
		int min = 99999;
		String str = "";
		for(String ele: q) {
			if((dist[eleList.indexOf(ele)]) < min){
				min = dist[eleList.indexOf(ele)];
				str = ele;
			}
		}
		return str;
	}

	public String toString() {
		System.out.println(dist.length);
		for (int i = 0; i < dist.length; i++) {
			System.out.println(dist[i]);
		}

		int el = eleList.indexOf(dest);
		int s = eleList.indexOf(src);
		StringBuilder sb = new StringBuilder();
		StringBuilder rb = new StringBuilder();
		if(p[el] == -1) {
			rb.append("Not possible.");
		}
		else {
			rb.append(src);
			while(el != s) {
				sb.append(vertices[el]);
				sb.append(">-");
				el = p[el];
			}
			rb.append(reverse(sb.toString()));
			rb.append(':');
			rb.append(dist[eleList.indexOf(dest)]);
		}
		return rb.toString();
	}

	public String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--)
			sb.append(s.charAt(i));
		return sb.toString();
	}
}