import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;
/*
 * AUTHOR: Merle Crutchfield
 * FILE: WikiRacer.java
 * ASSIGNMENT: PA10 - WikiRacer
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file is used to find a wikipedia link ladder between
 * two pages. This is done by getting the links from the starting link,
 * then checking to see if the end link is in there, and if not 
 * iterating into each of the links until we find the path. This file
 * uses WikiScraper.java to get the webpage into a string, and then
 * find all of the links on the entered webpage. MaxPQ.java is used
 * to create a binary max heap as the data structure for holding the
 * link lists. We also implement memoization to help speed up the process.
 * This ultimately teaches us about optimizing our code so the runtime
 * is as efficient as possible. The main method takes two arguments, being
 * the start and end subjects, and the findWIkiLadder returns the path. 
 * The commonLinks method is a helper method that returns the
 * number of commonLinks between two sets.
 */

public class WikiRacer {
	// Private field
	private static Set<String> visited = new HashSet<String>();

	/*
	 * Main function, calls findWikiLadder and prints out the
	 * path.
	 */
	public static void main(String[] args) {
		List<String> ladder = findWikiLadder(args[0], args[1]);
		System.out.println(ladder);
	}
	
	// HashMap that stores the link, and all the links that corespond to it
	public static HashMap<String, Set<String>> memo = new HashMap<String, Set<String>>();
	
	/*
	 * This method returns a list of the path from the starting link to the
	 * end link. It takes the starting and ending subject as passed by the
	 * user. It initializes a new queue, enqueues the starting ladder,
	 * and then runs until the queue is empty. It dequeues the queue, and
	 * getst he set of links on the page. If the end page is there, it adds
	 * it to the ladder and returns it. If not, it uses parallelStream to
	 * iterate throught the wikiLinks at once. It then runs through each
	 * of the links in the link set, and adds it to the copied ladder,
	 * which then goes into the queue. If the while loop finishes, no
	 * path was found and so an empty list is returned.
	 */
	private static List<String> findWikiLadder(String start, String end) {
		visited.add(start);
		List<String> ladder = new ArrayList<>();
		Set<String> endLinks = WikiScraper.findWikiLinks(end);
		ladder.add(start);
		MaxPQ queue = new MaxPQ();
        queue.enqueue(ladder, 0);
        while (!(queue.isEmpty())) {
        	List<String> temp = queue.dequeue();
        	Set<String> links = WikiScraper.findWikiLinks(temp.get(temp.size() - 1));
        	if (links.contains(end)) {
        		temp.add(end);
        		return temp;
        	}
        	links.parallelStream().forEach(link -> {
        		WikiScraper.findWikiLinks(link);
        		});
        	for (String link : links) {
        		if (!(visited.contains(link))) {
        			visited.add(link);
        			List<String> newLadder = new ArrayList<String>();
        			for (String name : ladder) {
        				newLadder.add(name);
        			}
        			newLadder.add(link);
        			queue.enqueue(newLadder, commonLinks(endLinks, link));
        		}
        	}
        }
		return new ArrayList<String>();
	}
	
	/*
	 * Helper method that returns the common links between two sets.
	 * The first set is always the end set, as we are looking to see
	 * how many there are in common, and the second string is the one
	 * being added to the queue. findWikiLinks is called to return a
	 * set of links for the link, and a for loop runs to check to see
	 * how many links are in both. This int is returned.
	 */
	private static int commonLinks(Set<String> first, String second) {
		int val = 0;
		Set<String> secondLinks = WikiScraper.findWikiLinks(second);
		for (String link : secondLinks) {
			if (first.contains(link))
				val += 1;
		}
		return val;
	}

}
