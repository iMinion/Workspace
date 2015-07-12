package searchengine.parser;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class BFS2Node {
	public char data;
	public boolean visited;
	public List<BFS2Node> adjNodes = new LinkedList<BFS2Node>();
	
	public BFS2Node(char data){
		this.data = data;
		visited = false;
	}
	public void setData(char ch){
		this.data = ch;
	}
	public void addAdjacent(BFS2Node node){
		adjNodes.add(node);
		node.adjNodes.add(this);
	}
	public String toString(){
		String str = "";
		str = str + this.data;
		return str;
	}
}

public class BFS {
	BFS2Node root;
	ArrayList<BFS2Node> lst = new ArrayList<BFS2Node>();
	ArrayList<String> adjList = new ArrayList<String>();
	int noOfVertices;
	int[][] adjMatrix;
	public BFS(int n){
		this.noOfVertices = n;
		adjMatrix = new int[noOfVertices][noOfVertices];
	}
	public void display(){
		for(int i=0;i<lst.size();i++){
			System.out.println(lst.get(i));
		}
	}
	public void addToList(String str){
		BFS2Node node= new BFS2Node(str.charAt(0));
		lst.add(node);
		
	}
	public BFS2Node getNode(char ch){
		for(int i=0;i<lst.size();i++){
			if(lst.get(i).data == ch)
				return lst.get(i);
		}
		return null;
	}
	public void addToList(){
		for(int i=0;i<adjList.size();i++){
			BFS2Node node= new BFS2Node(adjList.get(i).charAt(0));
			lst.add(node);
		}
		
	}
	public void fillGraph(){
		for(int i=0;i<adjList.size();i++){
			addAdjacencyNodes(adjList.get(i));
		}
	}
	public void getAdjacencyList(){
		String str = "";
		for(int i=0;i<noOfVertices;i++){
			str = "";
			str = str + (i+1);
			for(int j=0;j<noOfVertices;j++){
				if(adjMatrix[i][j] == 1){
					str = str + " " + (j+1);
				}
				
			}
			//System.out.println(str);
			adjList.add(str);
		}
	}
	public void addAdjacencyNodes(String str){
		BFS2Node n = getNode(str.charAt(0));
		for(int i=1;i<str.length();i++){
			char ch = str.charAt(i);
			if(Character.isDigit(ch)){
				n.addAdjacent(getNode(ch));
			}
		}
		if(root == null){
			root = n;
		}
	}
	public void bfsTraversal(){
		Queue q = new LinkedList();
		q.add(root);
		root.visited = true;
		while(!q.isEmpty()){
			BFS2Node n = (BFS2Node)q.poll();
			System.out.print(n.data+" ");
			for(BFS2Node adj : n.adjNodes){
				if(!adj.visited){
					adj.visited = true;
					q.add(adj);
				}
			}
		}
	}
	public static void main(String args[]){
		
		
	}

}
