import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * AUTHOR: Merle Crutchfield
 * FILE: WikiScraper.java
 * ASSIGNMENT: PA10 - WikiRacer
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This class is used for several methods used in the
 * WikiRacer class. They are used to get the wiki links in the
 * form of a set, as well as iterating through the html to find
 * links. The findWikiLinks method uses memoization to get the
 * set of links by calling the other methods. The fetchHTML
 * gets the HTML source code from the website into a string.
 * The getURL converts the entered string into a link, and the
 * scrapeHTML method returns a set of the links from the 
 * HTML string.
 */

public class WikiScraper {
			
	/*
	 * This method returns the links from a single link
	 * by calling the fetchHTML and scrapeHTML methods.
	 * It uses memoization by checking to see if the link
	 * is in the HashMap already. If it is then it returns
	 * the link, if not then it calls the methods from
	 * above and returns the links.
	 */
	public static Set<String> findWikiLinks(String link) {
		if (WikiRacer.memo.containsKey(link))
			return WikiRacer.memo.get(link);
		String html = fetchHTML(link);
		Set<String> links = scrapeHTML(html);
		WikiRacer.memo.put(link, links);
		return links;
	}
	
	/*
	 * This method gets the url by calling the getURL method,
	 * and then opens the webpage. It then adds each of the
	 * characters from the source code of the wikipedia
	 * webpage, which returns the HTML in a form of a 
	 * string. This string is returned.
	 */
	private static String fetchHTML(String link) {
		StringBuffer buffer = null;
		try {
			URL url = new URL(getURL(link));
			InputStream is = url.openStream();
			int ptr = 0;
			buffer = new StringBuffer();
			while ((ptr = is.read()) != -1) {
			    buffer.append((char)ptr);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	/*
	 * This method returns the weblink to the wikipedia
	 * page for the entered link. It adds the beginning
	 * part since the link passed is only the subject,
	 * not the full link.
	 */
	private static String getURL(String link) {
		return "https://en.wikipedia.org/wiki/" + link;
	}
	
	/*
	 * This method returns a set of the links found from the
	 * html string passed. It runs through a while loop until
	 * it does not find the start of the link, and checks to
	 * see if the link contains any forbidden characters. If it
	 * doesn't, then it adds the link to the set. It then returns
	 * the set.
	 */
	private static Set<String> scrapeHTML(String html) {
		Set<String> ans = new HashSet<String>();
		String start = "<a href=\"/wiki/";
		while(html.contains(start)) {
			html = html.substring(html.indexOf(start) + start.length());
			String link = html.substring(0, html.indexOf("\">")).split(" ")[0];
			if (!link.contains("#") & !link.contains(":")) {
				ans.add(link.split("\"")[0]);
			}
		}
		return ans;
	}
	
}
