import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


public class DAG {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int patt = Integer.parseInt(sc.nextLine());
		String src = sc.nextLine();
		String[] str = sc.nextLine().split(",");
		Hashtable<String, Integer> index = new Hashtable<String, Integer>();
		for(int i = 0; i < str.length; i++)
			index.put(str[i], i);	
		DdFS dfs = new DdFS(n);
		
		boolean graph[][] = new boolean[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) 
				graph[i][j] = false;
		
		if(patt == 0) {
			int i = 0;
			while(sc.hasNextLine()) {
				String[] s = sc.nextLine().split("->");
				i = index.get(s[0]);
				int k;
				if(s.length > 1) 
					for(int j = 1; j < s.length; ++j) {
						k = index.get(s[j]);
						graph[i][k] = true;
					}
			}
		}
		else if(patt == 1) {
			int i = 0;
			while(sc.hasNext()) {
				String s[] = sc.nextLine().split(",");
				for(int j = 0; j < s.length; j++) {
					if(Integer.parseInt(s[j]) == 0) graph[i][j] = false;
					else graph[i][j] = true;
				}
				i++;
			}
		}
		dfs.graph(graph);
		System.out.println(dfs.checkCycles(index.get(src), str));
		sc.close();
	}
}

class DdFS {

	boolean[][] graph;
	ArrayList<Object> visited;
	int dfsPos = 1, finishTime = 1;
	int dfsNum[];
	int finTime[];
	int n;
	

	public DdFS(int n) {
		graph = new boolean[n][n];
		visited = new ArrayList<Object>();
		this.n = n;
		dfsNum = new int[n];
		finTime = new int[n];
	}

	public void graph(boolean[][] graph) {
		this.graph = graph;
		this.n = graph.length;
		dfsNum = new int[n];
		finTime = new int[n];
	}
	
	public int[] getDfsNum() {
		return dfsNum;
	}
	
	public int[] getFinishTimes() {
		return finTime;
	}

	public void tbDFS(int src) {
		visited.clear();
		dfsPos = 1;
		finishTime = 1;
		dfsNum = new int[n];
		finTime = new int[n];
		visited.add(src);
		dfsNum[src] = dfsPos++;
		dfs(src, src);
	}
	
	public void dfs(int u, int v)  {
		for(int i = 0; i < graph[v].length; i++) {
			if(graph[v][i]) {
				if(!visited.contains(i)) {
					dfsNum[i] = dfsPos++;
					visited.add(i);
					dfs(v, i);
				}
			}
		}
		finTime[v] = finishTime++;
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

	public int getNext(int val) {
		for(int i: dfsNum) {
			if(i == val) return i;
		}
		return n;
	}
	
	public String checkCycles(int src, String[] index) {
		boolean flag = false;
		tbDFS(src);
		int i;
		for(i = 0; i < n - 1; i++) {
			System.out.print(index[(int) visited.get(i)]);
			System.out.print("");
		}
		System.out.println(index[(int) visited.get(i)]);
		for(i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				if(graph[i][j]) {
					if(dfsNum[i] > dfsNum[j] && finTime[i] < finTime[j]) {
						if(!flag) flag = !flag;
						System.out.println(index[i] + "->" + index[j] + 
								": Backward Edge");
					}
					else if(dfsNum[i] < dfsNum[j] && finTime[j] < finTime[i]) {
						System.out.println(index[i] + "->" + index[j] + 
								": Tree Edge / Forward Edge");
					}
					else System.out.println(index[i] + "->" + index[j] + 
							": Cross Edge");
				}
			}
		}
		if(!flag) return "Graph has no cycles";
		return "The graph has cycles";
	}
	
}