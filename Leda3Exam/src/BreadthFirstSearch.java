import java.util.ArrayList;
import java.util.Scanner;


class BFS {


	boolean[][] graph;
	Object nodes[];
	int[] dist;
	Queue processing;
	ArrayList<Object> visited;


	public BFS(int n) {
		graph = new boolean[n][n];
		nodes = new Object[n];
		processing = new Queue(n);
		visited = new ArrayList<Object>();
		dist = new int[n];
	}

	public void graph(boolean[][] graph) {
		this.graph = graph;
	}

	public String bFirstSearch(int n) {
		processing.pushBack(n + 1);
        dist[n] = 0;
		int d = 0;
		while(processing.size() != 0) {
			int k = (Integer)processing.peek() - 1;
			if(!visited.contains(k + 1)) { 
				for(int i = 0; i < graph[k].length; i++) 
					if(graph[k][i]) 
                        if(!visited.contains(i+1)) { 
                            processing.pushBack(i+1);
                            dist[i] = d+1;
                        }
				visited.add(k + 1);
			}
			k = (Integer) processing.popFront() - 1;
            if(processing.size() > 0) {
    			int j = (Integer) processing.peek() - 1;
                if(dist[k] != dist[j]) d++;
            }
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(i = 0; i < dist.length - 1; i++) {
			sb.append(dist[i]);
			sb.append(",");
		}
		sb.append(dist[i]);
		sb.append("\n");
		for(i = 0; i < visited.size() - 1; i++) {
			sb.append(visited.get(i));
			sb.append(",");
		}
		sb.append(visited.get(i));
		return sb.toString();
	}

	public String tbBFirstSearch(int n) {
        int []parent = new int[graph.length];
        for(int i = 0; i < parent.length; ++i) parent[i] = -1;
        parent[n] = n;
        ArrayList<Integer> q = new ArrayList<Integer>();
        ArrayList<Integer> q1 = new ArrayList<Integer>();
        int l = 0;
        q.add(n);
        for(l = 0; q.size() != 0; l++) {
            for(int i = 0; i < q.size(); ++i) {
                visited.add(q.get(i) + 1);
                int k = q.get(i);
                for(int j = 0; j < graph[k].length; j++) {
                    if(graph[k][j] && parent[j] == -1) {
                        q1.add(j);
                        dist[j] = l + 1;
                        parent[j] = k;
                    }
                }
            }
            q = q1;
            q1 = new ArrayList<Integer>();
        }

		StringBuilder sb = new StringBuilder();
		StringBuilder str = new StringBuilder();
		int i = 0;
		for(i = 0; i < visited.size() - 1; i++) {
			sb.append(visited.get(i));
			sb.append(",");
			str.append(dist[(Integer) visited.get(i)]);
			str.append(",");
		}
		sb.append(visited.get(i));
		str.append(dist[(Integer) visited.get(i)]);
		str.append("\n");
		str.append(sb);
		return sb.toString();
	}
}


class Queue {
	Object[] b;
	int n;
	int h = -1; //index of first element
	int t = 0; //index of first free entry
	int l = -1; //index of the last entry

	public Queue(int n) {
		this.n = n;
		b = new Object[n];
	}

	public boolean isEmpty() {
		return t == 0;
	}

	public Object getFirst() {
		if(!isEmpty()) return b[h];
		return null;
	}

	public int size() {
		return l + 1;
	}

	public void pushBack(Object e) {
		if(h == -1) h = 0; 
		if(size() < n) {
			b[size()] = e;
			t += 1;
		}
		else {
			Object a[] = new Object[2*n];
			t += 1;
			for(int i = 0; i < n; i++) {
				a[i] = b[i];
			}
			a[n] = e;
			this.n = 2 * n;
			b = new Object[n];
			b = a;
		}
		l++;
	}
	public Object popFront() {
		assert !isEmpty():"Queue is empty";
		Object []a = new Object[n - 1];
		for(int i = 0; i < n - 1; i++) {
			if(b[i] == null) break;
			else a[i] = b[i + 1];
		}
		Object res = b[0];
		n = n -1;
		b = a;
		l--;
		return res;
	}

	public Object peek() {
		return this.b[0];
	}
}

public class BreadthFirstSearch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int patt = Integer.parseInt(sc.nextLine());
		BFS bfs = new BFS(n);
		boolean graph[][] = new boolean[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) 
				graph[i][j] = false;
		int i = 0;
		while(sc.hasNext()) {
			String s[] = sc.nextLine().split(" ");
			for(int j = 0; j < s.length; j++) {
				if(Integer.parseInt(s[j]) == 0) graph[i][j] = false;
				else graph[i][j] = true;
			}
			i++;
		}
		bfs.graph(graph);
		System.out.println(bfs.tbBFirstSearch(patt - 1));
		sc.close();
	}
}