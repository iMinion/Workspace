/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import searchengine.dictionary.HashDictionary;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in breadth-first order.
 */
public class BreadthFirstSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private int limit = 10;
	private Indexer ind = null;
	private URL u; 
	int count=0;
	public BreadthFirstSpider (URL u, Indexer ind) {
		this.u = u;
		this.ind = ind;

	}
	public BreadthFirstSpider() {
		// TODO Auto-generated constructor stub
	}
	HashDictionary hd=new HashDictionary();
	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 */
	Queue<String> queue=new LinkedList<String>();
	public Vector<String> word=new Vector<String>();
	ArrayList<String> check=new ArrayList<String>();
	public ObjectIterator<String> words=new ObjectIterator<String>(word);

	public Indexer crawl (int limit) {
		this.limit = limit;
		System.out.println("before try "+ limit);

		URLTextReader in = new URLTextReader(u);
		//System.out.println("here "+u.toString());
		PageLexer<PageElementInterface> elts;
		try{

			elts = new PageLexer<PageElementInterface>(in,u);
			System.out.println("after try "+elts);
			while (elts.hasNext()) {

				//count++;
				//				System.out.println("enterd url");
				PageElementInterface elt = (PageElementInterface)elts.next();
				//	System.out.println(elt);
				if (elt instanceof PageHref){
					String x=elt.toString();
					//					System.out.println(x+" xx");
					if(x.charAt(0)!='h'){
						String fir=x.substring(0,5);
						String sec=x.substring(6);
						String main=fir+sec;
						//					System.out.println(main+" main ");
						if(count< limit){
							count++;
							queue.add(main);
						}
					}
					else{	
						if(count < limit){
							count++;
							queue.add(x);
						}
					}

				}
				else if (elt instanceof PageWord){
					//					System.out.println(elt.toString()+" ffff");
					word.add(elt.toString());

				}
			}
			System.out.println(words);
		} catch (IOException e){
			// TODO Auto-generated catch block
			System.out.println("no");
			//e.printStackTrace();
		}	
		if(count == limit){
			while(!(queue.isEmpty())){
				URL uw;
				try {
					uw = new URL(queue.poll());
					ind.addPage(uw, words);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		else{
			while(!(queue.isEmpty())){
				String val=queue.poll().toString();
				check.add(val);
				try {
					URL u1 = new URL(val);
					ind.addPage(u1, words);
					crawltemp(u1);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}

		return ind;
	}
	public void crawltemp(URL u1){

		URLTextReader in1 = new URLTextReader(u1);
		try {	//System.out.println("hhhhh");
			PageLexer<PageElementInterface> elts1 = new PageLexer<PageElementInterface>(in1,u1);
			while (elts1.hasNext()) {

				PageElementInterface elt = (PageElementInterface)elts1.next();
				if (elt instanceof PageHref){
					String x=elt.toString();
					if(x.charAt(0)!='h'){
						String fir=x.substring(0,5);
						String sec=x.substring(6);
						String main=fir+sec;
						if(!check.contains(main)){
							if(count< limit){
								count++;
								queue.add(main);
							}
						}

						else{	
							if(count < limit){
								count++;
								queue.add(x);
							}
						}
					}

				}
				else if (elt instanceof PageWord){
					word.add(elt.toString());
				}
			}

		} catch (IOException e) {
		}
	}

	/** Crawl the web, up to the default number of web pages.
	 * @return 
	 */
	public Indexer  crawl() {
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: "+u.toString());
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;

}
