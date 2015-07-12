package searchengine.element;


import java.net.*;

import searchengine.url.URLFixer;

public class PageFrame implements PageElementInterface {
	 public PageFrame (String h) throws MalformedURLException {
		 System.out.println(h+" pageframe");
			iframe = new URL(h);
		    }

		    public PageFrame (URL context, String h) throws MalformedURLException {
			iframe = URLFixer.fix(context, h);
		    }

		    public String toString () {
				if(iframe == null )
					return null;
			return iframe.toString();
		    }

		    private URL iframe;

}
