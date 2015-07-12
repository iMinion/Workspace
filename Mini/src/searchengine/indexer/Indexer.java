/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */ 

package searchengine.indexer;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import searchengine.dictionary.AVLDictionary;
import searchengine.dictionary.BSTDictionary;
import searchengine.dictionary.DictionaryInterface;
import searchengine.dictionary.HashDictionary;
import searchengine.dictionary.ListDictionary;
import searchengine.dictionary.MyHashDictionary;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.parser.PageLexer;
import searchengine.spider.BreadthFirstSpider;
import searchengine.url.URLTextReader;


/**
 * Web-indexing objects.  This class implements the Indexer interface
 * using a list-based index structure.

A Hash Map based implementation of Indexing 

 */
public class Indexer implements IndexerInterface
{
	/** The constructor for ListWebIndex.
	 */

	DictionaryInterface index;
	DictionaryInterface indexer;
	//	// This is for calculating the term frequency
	//	HashMap<String,HashMap> urlList= new HashMap<String,HashMap>();
	//	HashMap<String,HashMap> indexUrls= new HashMap<String,HashMap>();
	ArrayList<String> wordList=new ArrayList<String>();
	ArrayList<Rank> ranksList;
	ArrayList<Rank> sortedRanksList;
	String word="";
	public Indexer(String mode)
	{
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students

		if (mode.equals("hash")) {
			index = new HashDictionary();
			indexer = new HashDictionary();
		}
		else if(mode.equals("list")){
			index = new ListDictionary();
			indexer = new ListDictionary();
		}
		else if(mode.equals("myhash")) {
			index = new MyHashDictionary();
			indexer = new MyHashDictionary();
		}
		else if(mode.equals("bst")) {
			index = new BSTDictionary();
			indexer = new BSTDictionary();
		}
		else if(mode.equals("avl")) {
			index = new AVLDictionary();
			indexer = new AVLDictionary();
		}
	}

	/** Add the given web page to the index.
	 *
	 * @param url The web page to add to the index
	 * @param keywords The keywords that are in the web page
	 * @param links The hyperlinks that are in the web page
	 */ 
	ArrayList<String> words=new ArrayList<String>();
	public void addPage(URL url, ObjectIterator<?> keywords)	
	{
		int count=0;
		try {
			PageLexer<PageElementInterface> elts;
			URLTextReader in = new URLTextReader(url);
			//System.out.println(url+" "+in+" indexer url ");
			elts = new PageLexer<PageElementInterface>(in,url);
			while (elts.hasNext()) {
				PageElementInterface elt = (PageElementInterface)elts.next();
				if (elt instanceof PageWord){
					words.add(elt.toString());
				}
			}

			while(keywords.hasNext()){			
				word=(String) keywords.next();
				//				if(!(wordList.contains(word))){
				//					wordList.add(word);
				//				}
				for(int i=0;i<words.size();i++){
					if(!word.equals("null") && (!word.equals(null))){
						if(word.equals(words.get(i))){
							count++;
						}
					}
				}
				if(count!=0){
					HashMap<String,Integer> temp=new HashMap<String, Integer>();
					System.out.println(word);
					if(index.contains(word)) {
						System.out.println(count+" wwwwwww "+word+" "+index.contains(word));

						//System.out.println("entered");
						temp = (HashMap<String, Integer>) index.getValue(word);
						System.out.println(temp+" ttttttteeeeeemmmp "+count);
						if(temp!=null){
							temp.put(url.toString(), count);
							System.out.println(url.toString()+" uuuuuuuuu ");
							System.out.println(temp+" ttttttttttt ");
						}
					}
					else {
						System.out.println(count+" cccccccc "+word+" "+index.contains(word));
						temp = new HashMap<String, Integer>();
						temp.put(url.toString(), count);
					}
					index.insert(word, temp);
					count=0;
				}
			}
			words.clear();
			keywords.setI(0);
			//System.out.println("outer hash table1 " + index);
			//System.out.println(wordList+" wordsList");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Produce a printable representation of the index.
	 *
	 * @return a String representation of the index structure
	 */
	public String toString()
	{
		////////////////////////////////////////////////////////////////////
		//  Write your Code here as part of Integrating and Running Mini Google assignment
		//  
		///////////////////////////////////////////////////////////////////
		return "You dont need to implement it\n";
	}

	/** Retrieve all of the web pages that contain the given keyword.
	 *
	 * @param keyword The keyword to search on
	 * @return An iterator of the web pages that match.
	 */
	Rank[] test;
	public ObjectIterator<PageElementInterface> retrievePages(PageWord keyword)
	{
		////////////////////////////////////////////////////////////////////
		//  Write your Code here as part of Integrating and Running Mini Google assignment
		//  
		///////////////////////////////////////////////////////////////////
		//	System.out.println(wordList);
		System.out.println(indexer);
		HashMap<String,Integer> list;
		Vector<PageElementInterface> urls = new Vector<PageElementInterface>();
		PageHref url;
		sortedRanksList= new ArrayList<Rank>() ;
		int count=0;
		System.out.println(keyword.toString()+" keyword ");
		Set<String> word2 = new HashSet<String>(Arrays.asList(indexer.getKeys()));
		System.out.println(word2.size());
		for(String wor: word2) {
			count = 0;
			System.out.println(wor+" bbbbbbbbbbb ");
			if(wor.equals(keyword.toString())) {
				System.out.println("entered");
				ranksList = new ArrayList<Rank>();
				list = (HashMap<String, Integer>) indexer.getValue(keyword.toString());
				System.out.println("list " + list);
				Set<String> key = list.keySet();
				for(String url1: key) {
					for(int j = 0; j < url1.length(); j++) {
						if(url1.charAt(j) == '/') {
							count++;
						}
					}
					int frequency = list.get(url1);
					int rank=(frequency/count)*100;
					ranksList.add(new Rank(url1,rank));
					//					System.out.println(ranksList.size()+" innnnnnnnnnnnsssssssss");
					count=0;
				}
				break;
			}
		}
		if(ranksList == null) {

		}
		else if(!(ranksList.size() < 0)){
			test = new Rank[ranksList.size()];
			test = ranksList.toArray(test);

			for(int i = 0; i < test.length - 1; i++) {
				for(int j = i + 1; j < test.length; j++) {
					if(test[i].getRank() > test[j].getRank()) {
						Rank tem = test[j];
						test[j] = test[i];
						test[i] = tem;
					}
				}
			}
			if(test.length > 0) {
				for(int j = 0; j < test.length; j++) {
					if(!sortedRanksList.contains(test[j])){
						sortedRanksList.add(new Rank(test[j].getUrl(), test[j].getRank()));
					}
				}
			}
		}	
		System.out.println(sortedRanksList.size()+" sorted ");
		System.out.println(sortedRanksList);
		for (int i = 0; i <sortedRanksList.size(); i++) {
			//		  System.out.println(sortedRanksList.get(i).getUrl().toString()+"   "+sortedRanksList.get(i).getRank());
			try {
				url = new PageHref(sortedRanksList.get(i).getUrl().toString());
				if(!urls.contains(url)) {  
					urls.add(url);
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// adds the url's in string formatt
		//	System.out.println(urls);
		return new ObjectIterator<PageElementInterface>(urls);
	}	


	/** Retrieve all of the web pages that contain any of the given keywords.
	 *	
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 * Calculating the Intersection of the pages here itself
	 **/
	public ObjectIterator<PageElementInterface> retrievePages(ObjectIterator<?> keywords)
	{
		Vector<PageElementInterface> urls = new Vector<PageElementInterface>();
		while(keywords.hasNext()){
			String word = keywords.next().toString();
			PageWord pWord = new PageWord(word);
			ObjectIterator<PageElementInterface> val = (ObjectIterator<PageElementInterface>) retrievePages(pWord);
			while(val.hasNext()) {
				String url1 = val.next().toString();
				PageHref fUrl;
				try {
					fUrl = new PageHref(url1);
					urls.add(fUrl);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new ObjectIterator<PageElementInterface>(urls);
	}

	/** Save the index to a file.
	 * @param isaveFile 
	 *
	 * @param stream The stream to write the index
	 * @throws IOException 
	 */
	//	public void save(FileOutputStream stream) throws IOException
	//	{
	//		////////////////////////////////////////////////////////////////////
	//		//  Write your Code here as part of Integrating and Running Mini Google assignment
	//		//  
	//		///////////////////////////////////////////////////////////////////	
	//	}
	public void save(FileOutputStream isaveFile) throws IOException{

		//		System.out.println("entered serialization");
		//		ObjectOutputStream stream=new ObjectOutputStream(isaveFile);
		//		stream.writeObject(index);	
		//		stream.close();
		//		System.out.println("completed");
		System.out.println("entered saving");
		PrintStream printtext = new PrintStream (isaveFile);
		String words[] =index.getKeys();
		//Arrays.to;
		System.out.println("wordslist: "+words.length);
		for(int s=0;s<words.length;s++){
			System.out.print(words[s]+" ");}
		System.out.println();
		for(int i=0;i<words.length;i++){
			//	System.out.println((!words[i].equals("null"))+" "+(!(words[i].equals(" "))));
			//boolean x=false;
			String foo = "null";
			boolean x=words[i].equals(foo);

			System.out.println(words[i]+x);
			if(x==false){
				String s=words[i]+"@";
				System.out.println(words[i]+"save");
				HashMap<String,Integer> map = (HashMap<String, Integer>) index.getValue(words[i]);
				System.out.println(map+" nnnnnnn ");
				Set<String> urls = map.keySet();
				//printtext.print(words[i]);
				for(String url: urls){
					int c=map.get(url);
					s=s+url+"#"+c+",";


				}
				printtext.print(s);
				printtext.println();
			}
		}
		System.out.println("Done writing");
		printtext.close();
	}

	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */

	public void restore(FileInputStream stream) throws IOException
	{
		int c;
		String s = "";
		while((c = stream.read()) != -1) {
			s = s + (char) c; 
		}
		System.out.println(s+" vvv ");
		System.out.println("Done reading");
		String str[] = s.split("\n");
		System.out.println(str.length+" kkk ");
		for(int i = 0; i < str.length; i++) {
//			if(str[i].contains("@")){
				//System.out.println("Parsing "+str[i].indexOf('@'));
				String key = str[i].substring(0, str[i].indexOf('@'));
				String value = str[i].substring(str[i].indexOf('@') + 1);
				HashMap<String, Integer> val = new HashMap<String, Integer>();
				String values[] = value.split(",");
				System.out.println(values.length+": length");
				for(int j = 0; j < values.length; j++) {
					if(values[j].length()>1){
						//System.out.println(values[j].indexOf('#')+" "+values[j].length());
						String vKey = values[j].substring(0, values[j].indexOf('#'));
						int vVal = Integer.parseInt(values[j].substring(values[j].indexOf('#') + 1));
						val.put(vKey, vVal);
					}
					if(val.size()!=0){
					System.out.println(key+" iiiinnnnndddexingggggg "+val);

					indexer.insert(key,val);
					System.out.println(indexer);

					}
				}
//			}

		}
		//		System.out.println(s);
	}

	/* Remove Page method not implemented right now
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}

};
