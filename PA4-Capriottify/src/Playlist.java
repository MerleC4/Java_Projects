/*
 * AUTHOR: Merle Crutchfield
 * FILE: Playlist.java
 * ASSIGNMENT: PA4 - Spotify
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file is used to create the Playlist class, which takes
 * a String as the name of the Playlist as well as a list of Songs which
 * is named contents. If no list of songs is passed, then the list is 
 * declared with no values in it. The getName method returns the name,
 * and the addSong will add the song passed as the argument to the list of
 * songs. The void play function plays every song in the Playlist by calling
 * the play method on the song. The void shuffle method shuffles all of
 * the songs in the Playlist. Finally, the removeSong method removes the
 * specified song if it exists.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
	// Private instance variables
	private String name;
	private List<Song> contents;
	
	/*
	 * Constuctor 1
	 * PURPOSE: Creates the Playlist when only the name is
	 * passed. It sets the name to the String passed, and declares
	 * the list of Songs to be an empty list.
	 */
	public Playlist(String name) {
		this.name = name;
		contents = new ArrayList<Song>();
	}
	
	/*
	 * Conmstructor 2
	 * PURPOSE: Creates the Playlist when both the name and list
	 * of Songs are passed. It sets the name to the String passed
	 * and the list of Songs to the list of Songs passed.
	 */
	public Playlist(String name, List<Song> contents) {
		this.name = name;
		this.contents = contents;
	}
	
	/*
	 * PURPOSE: This returns the name of the playlist.
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * PURPOSE: This adds the song passed by the user to the
	 * list of Songs.
	 */
	public void addSong(Song song) {
		contents.add(song);
	}
	
	/*
	 * PURPOSE: This plays each of the songs in the Playlist. It
	 * iterates through using a for loop, and calls the play
	 * method to simulate playing the Song for each of the
	 * Songs.
	 */
	public void play() {
		for (Song song : contents)
			song.play();
	}
	
	/*
	 * PURPOSE: This will shuffle each of the songs in the 
	 * Playlist. It does this by calling the shuffle method
	 * for lists.
	 */
	public void shuffle() {
		Collections.shuffle(contents);
	}
	
	/*
	 * PURPOSE: This removes the specified Song passed as the
	 * arguement. If the song is not in the Playlist, then the
	 * Playlist remains the same.
	 */
	public void removeSong(Song song) {
		contents.remove(song);
	}
}