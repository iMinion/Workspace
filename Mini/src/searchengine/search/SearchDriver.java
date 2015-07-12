/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 12-10-2009
 */ 


package searchengine.search;


import java.util.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;


/**[
 * 
 * The user interface for the index structure.
 *
 * This class provides a main program that allows users to search a web
 * site for keywords.  It essentially uses the index structure generated
 * by WebIndex or ListWebIndex, depending on parameters, to do this.
 *
 * To run this, type the following:
 *
 *    % java SearchDriver indexfile list|custom keyword1 [keyword2] [keyword3] ...
 *
 * where indexfile is a file containing a saved index and list or custom indicates index structure.
 *
 */
public class SearchDriver
{
	ArrayList<String> list=new ArrayList<String>();
	public static void main(String [] args) throws IOException
	{

		ServerSocket srvsock=new ServerSocket(5555);
		Socket sock;
		System.out.println("hello");

		sock=srvsock.accept();
		System.out.println(sock+" soc,kk");
		PrintStream PS=new PrintStream(sock.getOutputStream());
		InputStreamReader IR=new InputStreamReader(sock.getInputStream());
		BufferedReader BR=new BufferedReader(IR);		
		String keyword;
		int ind = 0;
		while((keyword = BR.readLine()) != null) {
			System.out.println("ind = " + ind++);
			Vector<String> v=new Vector<String>();
			if(args.length<3)
				System.out.println("Usage: java SearchDriver indexfile list|hash keyword1 [keyword2] [keyword3] [...]");
			else
			{
				Indexer w = null;

				// Take care to use the right usage of the Index structure
				// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
				// list - Dictionary Structure based on Linked List 
				// myhash - Dictionary Structure based on a Hashtable implemented by the students
				// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
				// avl - Dictionary Structure based on AVL Tree implemented by the students
				if(args[1].equalsIgnoreCase("list") || args[1].equals("hash") || args[1].equals("myhash") || args[1].equals("bst") 
						|| args[1].equals("avl")){
					w = new Indexer(args[1]);
				}
				else
				{
					System.out.println("Invalid Indexer mode \n");
				}

				try{
					FileInputStream indexSource=new FileInputStream(args[0]);
					w.restore(indexSource);
				}
				catch(IOException e){
					System.out.println(e.toString());
				}
				String[] str = keyword.split(" ");
				Vector<PageElementInterface> vec = new Vector<PageElementInterface>();
				for(int i = 0; i < str.length; i++) {
					PageWord pWord = new PageWord(str[i]);
					vec.add(pWord);
				}
				System.out.println(keyword+" server ");	
				PageWord word = new PageWord(keyword);

				for(int i=2;i<args.length;i++)
					v.addElement(args[i]);
				String result="";
				ObjectIterator<?> i= w.retrievePages(new ObjectIterator<PageElementInterface>(vec));	
				System.out.println(i+" objectiterator");
				int y=0;
				if(i.size()!=0)
				{
					////////////////////////////////////////////////////////////////////
					//  Write your Code here as part of Sorting based on Rank Assignment
					//  
					///////////////////////////////////////////////////////////////////
					while(i.hasNext()){
						String x=i.next().toString()+",";
						result=result+x;
						System.out.println(x+" links");
					}

					System.out.println("Search complete.");
					System.out.println("---------------\n");
				}
				else
				{
					y=1;
					System.out.println("Search complete.  0  hits found.");
				}
				if(y==0){
				System.out.println(result+" hhhh ");
				PS.println(result);
				}
				else{
					result=null;
					PS.println(result);
				}
			}
		}
	}
}