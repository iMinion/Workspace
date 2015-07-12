import java.util.ArrayList;
import java.util.Scanner;


public class DepthFirstSearch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int patt = Integer.parseInt(sc.nextLine());
		DFS dfs = new DFS(n);
		boolean graph[][] = new boolean[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) 
				graph[i][j] = false;
		int i = 0;
		int k = 0;
		while(k < n) {
			++k;
			String s[] = sc.nextLine().split(" ");
			for(int j = 0; j < s.length; j++) {
				if(Integer.parseInt(s[j]) == 0) graph[i][j] = false;
				else graph[i][j] = true;
			}
			i++;
		}
		dfs.graph(graph);
		System.out.println(dfs.dfs(patt));
		sc.close();
	}
}

class DFS {

	boolean[][] graph;
	Object nodes[];
	MyStack<Integer> processing;
	ArrayList<Object> visited;

	public DFS(int n) {
		graph = new boolean[n][n];
		nodes = new Object[n];
		processing = new MyStack<Integer>();
		visited = new ArrayList<Object>();
	}

	public void graph(boolean[][] graph) {
		this.graph = graph;
	}

	public String dfs(int index) {
		processing.push(index);
		dFirstSearch(index);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(i = 0; i < visited.size() - 1; i++) {
			sb.append(visited.get(i));
			sb.append(",");
		}
		sb.append(visited.get(i));
		return sb.toString();
	}

	public void dFirstSearch(int index) {
		if(processing.contains(index)) {
			if(!visited.contains(index)) {
				visited.add(index);
			}
			int in = allProcessed(index - 1);
			if(in == -1) {
				processing.pop();
				if(processing.size() > 0) 
					dFirstSearch(processing.peek());
				else return;
			}
			else {
				processing.push(in + 1);
				dFirstSearch(in + 1);
			}
		}
	}

	public int allProcessed(int index) {
		int i = 0;
		for(i = 0; i < graph[index].length; i++) {
			if(graph[index][i]) {
				if(!visited.contains(i+1)) return i;
			}
		}
		return -1;
	}

}



