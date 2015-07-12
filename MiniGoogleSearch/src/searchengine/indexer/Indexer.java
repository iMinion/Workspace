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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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

	// Index Structure 
	DictionaryInterface<String, HashMap<String, Integer>> index;

	// This is for calculating the term frequency
	HashMap<String,HashMap> urlList= new HashMap<String,HashMap>();
	HashMap<String,HashMap> indexUrls= new HashMap<String,HashMap>();
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

		if (mode.equals("hash")) 
			index = new HashDictionary<String, HashMap<String, Integer>>();
		else if(mode.equals("list"))
			index = new ListDictionary<String, HashMap<String, Integer>>();
		else if(mode.equals("myhash"))
			index = new MyHashDictionary<String, HashMap<String, Integer>>();
		else if(mode.equals("bst"))
			index = new BSTDictionary<String, HashMap<String, Integer>>();
		else if(mode.equals("avl"))
			index = new AVLDictionary<String, HashMap<String, Integer>>();
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
				if(!(wordList.contains(word))){
					wordList.add(word);
				}
				for(int i=0;i<words.size();i++){
					if(word.equals(words.get(i))){
						count++;
					}
				}

				if(count!=0){
					HashMap<String,Integer> temp;

					if(urlList.containsKey(word)){
						temp=urlList.get(word);
						temp.put(url.toString(),count);
					}
					else{	
						temp = new HashMap<String,Integer>();
						temp.put(url.toString(),count);
					}
					urlList.put(word,temp);
					count=0;
				}
			}
			words.clear();
			keywords.setI(0);
			System.out.println("outer hash table1 " + urlList);
			System.out.println(wordList+" wordsList");
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
	public ObjectIterator<?> retrievePages(PageWord keyword)
	{
		////////////////////////////////////////////////////////////////////
		//  Write your Code here as part of Integrating and Running Mini Google assignment
		//  
		///////////////////////////////////////////////////////////////////
		//	System.out.println(wordList);
		System.out.println(indexUrls);
		HashMap<String,Integer> list;
		Vector<PageElementInterface> urls = new Vector<PageElementInterface>();
		PageHref url;
		sortedRanksList= new ArrayList<Rank>() ;
		int count=0;
		System.out.println(keyword.toString()+" keyword ");
		Set<String> word1=indexUrls.keySet();
		for(String wor : word1){
			count=0;
			if(wor.equals(keyword.toString())){
				System.out.println("entered");
				ranksList=new ArrayList<Rank>();
				list=indexUrls.get(keyword.toString());
				System.out.println(list+" list ");
				Set<String> key=list.keySet();
				for(String url1 : key){
					for(int j=0;j<url1.length();j++){
						if((url1.charAt(j)=='/')){
							count++;
						}
					}
					int frequency=list.get(url1);
					int rank=(frequency/count)*100;
					ranksList.add(new Rank(url1,rank));
					//					System.out.println(ranksList.size()+" innnnnnnnnnnnsssssssss");
					count=0;
				}
				break;
			}
		}

		if(ranksList != null){
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
			}// adds the url's in string formatt
			//	System.out.println(urls);
			return new ObjectIterator<PageElementInterface>(urls);
		}
		else return null;
	}	


	/** Retrieve all of the web pages that contain any of the given keywords.
	 *	
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 * Calculating the Intersection of the pages here itself
	 **/
	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords)
	{
		Vector<PageElementInterface> urls = new Vector<PageElementInterface>();
		while(keywords.hasNext()){
			String word = keywords.next().toString();
			PageWord pWord = new PageWord(word);
			ObjectIterator<PageElementInterface> val = (ObjectIterator<PageElementInterface>) retrievePages(pWord);
			if(val != null) {
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


		ObjectOutputStream stream=new ObjectOutputStream(isaveFile);
		stream.writeObject(urlList);	
		stream.close();

	}

	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */
	@SuppressWarnings("unchecked")
	public void restore(FileInputStream stream) throws IOException
	{
		////////////////////////////////////////////////////////////////////
		//  Write your Code here as part of Integrating and Running Mini Google assignment
		//  
		///////////////////////////////////////////////////////////////////
		ObjectInputStream file=new ObjectInputStream(stream);
		try {
			indexUrls=(HashMap<String, HashMap>) file.readObject();
			System.out.println(indexUrls+" restore");
			file.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Remove Page method not implemented right now
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}

};
