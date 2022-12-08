/*
 * AUTHOR: Merle Crutchfield
 * FILE: Library.java
 * ASSIGNMENT: PA4 - Spotify
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file is used to create the Library class, which 
 * holds the songs in this library of our Spotify program. The
 * class does not take any arguments, but declares a list of
 * Songs. The method getSong will return the song based on a title
 * if it exists, and if not then it will return null. There is one
 * getter method, which returns the list of Songs. There are two void
 * methods called addSong, which adds a song passed by the user, and
 * removeSong, which removes a song passed by the user. The toString
 * method returns a string representation of the library by first
 * sorting the songs in alphabetical order by song name, then adding
 * the songs toString value all to one string, which it returns.
 */

import java.util.ArrayList;
import java.util.List;

public class Library {
	// Private instance variables
	private List<Song> songs;
	
	// Constructor
	public Library() {
		songs = new ArrayList<Song>();
	}
	
	/*
	 * PURPOSE: This will return the song if it exists in the library's
	 * list of Songs, and if not will return null. It uses a for loop
	 * to iterate through the songs in the songs list, and checks to see
	 * if the title passed is equal to the title of the song. If it is,
	 * then it will return the song, and if not it will continue going
	 * through each Song.
	 */
	public Song getSong(String title) {
		for (Song song : songs) {
			if (title.equals(song.getTitle()))
				return song;
		}
		return null;
	}
	
	/*
	 * PURPOSE: This returns the list of Songs in the library.
	 */
	public List<Song> getAllSongs() {
		return songs;
	}
	
	/*
	 * PURPOSE: This adds the Song passed by the user to the list
	 * of songs in the library.
	 */
	public void addSong(Song song) {
		songs.add(song);
	}
	
	/*
	 * PURPOSE: This removes the Song passed by the user to the list
	 * of songs in the library. If it does not exist, the list does
	 * not change.
	 */
	public void removeSong(Song song) {
		songs.remove(song);
	}
	
	/*
	 * PURPOSE: This returns a string description of the song, which is
	 * all of the toStrings of the songs in alphabetical order based on
	 * names. It first adds all of the song titles to a list, and then
	 * iterates through that list using a nested for loop. It checks to
	 * see if the title is alphabetically behind the title behind it, and
	 * if it is it switches them by passing it to a temp variable. It does
	 * this until they are all sorted. Then, the sorted names lists is 
	 * iterated through again with a nested for loop with the Songs list,
	 * and it prints it out in order.
	 */
	public String toString() {
		String ans = "";
		List<String> songStrings = new ArrayList<>();
		// Add song titles to list of Strings
		for (Song song : songs) {
			songStrings.add(song.getTitle());
		}
		for (int i = 0; i < songStrings.size(); i++) {
			for (int j = i+1; j < songStrings.size(); j++) {
				// Check to see if not alphabetical
				if (songStrings.get(i).compareTo(songStrings.get(j)) > 0) {
					// Swaping two vals
					String temp = songStrings.get(i);
					songStrings.set(i, songStrings.get(j));
					songStrings.set(j, temp);
				}
			}
		}
		for (String song : songStrings) {
			for (Song name : songs) {
				if (song.equals(name.getTitle()))
					ans += name.toString();
			}
		}
		return ans;
	}
}