/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in Priority-first order.
 */
public class PriorityBasedSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer ind = null;
	private URL u; 
	private int count=0;
	//private int i=0;
	public PriorityBasedSpider (URL u, Indexer i) {
		this.u = u;
		this.ind = i;

	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 * @return 
	 */
	Queue<String> queue=new LinkedList<String>();
	public Vector<String> word=new Vector<String>();
	public ObjectIterator<String> words=new ObjectIterator<String>(word);
	public Indexer crawl (int limit) {
	
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Priority Based Spider assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
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
					if((count<=limit) && (!(queue.contains(main)))){
						queue.add(main);
						System.out.println(main+" mainnnnn ");
						crawltemp(new URL(main));
					}
					}
					else{	
						count++;
						if((count<=limit) && (!(queue.contains(x)))){
							queue.add(x);
							System.out.println(x+" xxxxxxxx ");
							crawltemp(new URL(x));

						}
					}
				}
				else if (elt instanceof PageWord){
//					System.out.println(elt.toString()+" ffff");
					word.add(elt.toString());
					
				}
			}
		//	System.out.println(words);
		} catch (IOException e){
			// TODO Auto-generated catch block
			System.out.println("no");
			//e.printStackTrace();
		}
		while(!(queue.isEmpty())){
			String s=queue.poll();
			try {
				ind.addPage(new URL(s), words);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ind;
	}
	public void crawltemp(URL u1){

		URLTextReader in1 = new URLTextReader(u1);
		
		//System.out.println(u1.toString());
		//crawlLimitDefault=lim;

		//q.add(u1.toString());
		try {	//System.out.println("hhhhh");
			PageLexer<PageElementInterface> elts1 = new PageLexer<PageElementInterface>(in1,u1);
			//System.out.println("hiii");

			while (elts1.hasNext()) {

				PageElementInterface elt = (PageElementInterface)elts1.next();
				//	System.out.println(elt+" second after the elt");
				if (elt instanceof PageHref){
					//System.out.println(count+" count ");

					if(count<crawlLimitDefault)
					{
					
						String x=elt.toString();
						
						if(x.charAt(0)!='h'){
							String fir=x.substring(0,5);
							String sec=x.substring(6);
							String main=fir+sec;
//							System.out.println(main+" main ");
							count++;
							if((count<=crawlLimitDefault) && (!(queue.contains(main)))){
								queue.add(main);
								System.out.println(main+" main111111");
								crawltemp(new URL(main));
							}
							}
							else{	
								count++;
								if((count<=crawlLimitDefault) && (!(queue.contains(x)))){
									queue.add(x);
									System.out.println(x+" xxxxxxxxxx ");
									crawltemp(new URL(x));

								}
							}
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
		//System.out.println("Crawling: "+u.toString());
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;

}
