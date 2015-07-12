package com.Week2;

import java.util.*;

public class RPrims {
	ArrayList<String> keys=new ArrayList<String>();
	Hashtable<String,ArrayList<String>> hash=new Hashtable<String,ArrayList<String>>();
	int[] d;
	String[] p;
	int[] d1;
	public static void main(String[] args) {
		RPrims prm=new RPrims();
		Scanner scan=new Scanner(System.in);
		String numVertice=scan.nextLine();
		int numVertices=Integer.parseInt(numVertice);
		String ver=scan.nextLine();
		String vertex=ver.replace("(", " ").trim();
		String vertices=vertex.replace(")", " ").trim();
		String[] key=vertices.split(",");
		String source=scan.nextLine();
		int[][] weights=new int[numVertices][numVertices];
		for(int i=0;i<numVertices;i++){
			String cost=scan.nextLine();
			String[] weghs=cost.split(" ");
			for(int j =0; j<weghs.length; j++) {
				weights[i][j] = Integer.parseInt(weghs[j]);
			}
			ArrayList<String> list=new ArrayList<String>();
			for(int j=0;j<weghs.length;j++){
				if(Integer.parseInt(weghs[j])!=0){
					list.add(key[j]);
				}
			}
			Collections.sort(list);
			prm.hash.put(key[i],list);
		}
		for(int i=0;i<key.length;i++){
			prm.keys.add(key[i]);
		}
		prm.spanningTree(weights,source);
		scan.close();
	}
	ArrayList<String> visited=new ArrayList<String>();
	private void spanningTree(int[][] weights, String source) {
		// TODO Auto-generated method stub
		d=new int[keys.size()];
		d1=new int[keys.size()];
		p=new String[keys.size()];
		Arrays.fill(d, 9999);
		p[keys.indexOf(source)]=source;
		LinkedList<String> queue=new LinkedList<String>();
		queue.add(source);
		while(!(queue.isEmpty())){
			String ele=min(queue,weights,p);
			if(!(ele.equals("null"))){
				queue.remove(ele);
				if(d[keys.indexOf(ele)]!=9999){
					d1[keys.indexOf(ele)]=d[keys.indexOf(ele)];
				}
				d[keys.indexOf(ele)]=0;
				if(!(visited.contains(ele))){
					visited.add(ele);		
					ArrayList<String> values=hash.get(ele);
					if(values.size()!=0){
						for(int i=0;i<values.size();i++){
							int val=weights[keys.indexOf(ele)][keys.indexOf(values.get(i))];
							int val1=keys.indexOf(values.get(i));
							if(val<d[val1]){
								d[val1]=val;
								p[keys.indexOf(values.get(i))]=ele;
								if(queue.contains(values.get(i))){
									d[keys.indexOf(values.get(i))]=d[keys.indexOf(values.get(i))];
								}
								else{
									queue.add(values.get(i));
								}
							}
						}
					}
				}
			}
			else{
				return;
			}
		}
		System.out.print("(");
		for(int i=0;i<p.length-1;i++){
			System.out.print(p[i]+",");
		}
		System.out.print(p[p.length-1]+")");
		System.out.println();
		System.out.print("(");
		for(int k=0;k<visited.size()-1;k++){
			System.out.print(visited.get(k)+",");
		}
		System.out.println(visited.get(visited.size()-1)+")");
		int num=0;
		for(int k=0;k<d.length;k++){
			num=num+d1[k];
		}
		System.out.println(num);

	}
	private String min(LinkedList<String> queue, int[][] weights, String[] p2) {
		// TODO Auto-generated method stub
		int min=99999;
		String s="";
		for(Object object : queue) {
			String element = (String) object;
			int k=keys.indexOf(element);
				if((d[k])<min){
				min=d[k];
				s=element;
			}
		}
		return s;
	}
}
