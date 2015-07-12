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

	private int i = 0;
	private URL u; 
	int count=0;
	public BreadthFirstSpider (URL u, int i) {
		this.u = u;
		this.i = i;

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
	public ObjectIterator<String> words=new ObjectIterator<String>(word);
	Indexer ind=new Indexer("hash");
	
	public Indexer crawl (int limit) {

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
					count++;
					if(count<=i){
						queue.add(main);
					}
					}
					else{	
						count++;
						if(count<=i){
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
		if(count>=i){
			while(!(queue.isEmpty())){
				//System.out.println(queue.poll());
				URL uw;
				try {
					uw = new URL(queue.poll());
//					System.out.println(count+" count1");
					URLTextReader in1 = new URLTextReader(uw);

					try {
						PageLexer<PageElementInterface> elts1 = new PageLexer<PageElementInterface>(in1,uw);
						while (elts1.hasNext()) {

							PageElementInterface elt = (PageElementInterface)elts1.next();
							//	System.out.println(elt+" second after the elt");
							if (elt instanceof PageWord){
								word.add(elt.toString());
							}
							}
						ind.addPage(uw, words);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					System.out.println("-->" + words.getI());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else{
			while(!(queue.isEmpty())){

				// System.out.println("2");
				//	System.out.println(queue.poll());
				String val=queue.poll().toString();
				//System.out.println(val+" main");

				try {
					URL u1 = new URL(val);
//					System.out.println(count+" count2 "+u1.toString());
					crawltemp(u1,i);
					ind.addPage(u1, words);
//					System.out.println("-->" + words.getI());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
		
		return ind;
	}
	Queue<String> q=new LinkedList<String>();
	public void crawltemp(URL u1,int lim){

		URLTextReader in1 = new URLTextReader(u1);
		
		//System.out.println(u1.toString());
		

		//q.add(u1.toString());
		try {	//System.out.println("hhhhh");
			PageLexer<PageElementInterface> elts1 = new PageLexer<PageElementInterface>(in1,u1);
			//System.out.println("hiii");

			while (elts1.hasNext()) {

				PageElementInterface elt = (PageElementInterface)elts1.next();
				//	System.out.println(elt+" second after the elt");
				if (elt instanceof PageHref){
					count++;
					//System.out.println(count+" count ");

					if(count<lim)
					{
					
						String x=elt.toString();
						
						String fir=x.substring(0,5);
						String sec=x.substring(6);
						String main=fir+sec;
						//System.out.println(main+" parsing");
						queue.add(main);
//						System.out.println(count+" count3 "+main);
//						System.out.println(words+"     ggggggggggg");
//						ind.addPage(new URL(main), words);
						//BreadthFirstSpider	web1=new BreadthFirstSpider(url,10);
						//web1.crawl(10);
						//crawltemp(url,10);
					}

				}
				else if (elt instanceof PageWord){
//					System.out.println(elt.toString()+" ffff");
					word.add(elt.toString());
					
				}
			}
//			System.out.println(q.toString()+" queue "+words.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//	System.out.println("heree");
			//e.printStackTrace();
		}
		////////////////////////////////////////////////////////////////////
		//  Write your Code here as part of Breadth First Spider assignment
		//  
		///////////////////////////////////////////////////////////////////

		//return 0;
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
