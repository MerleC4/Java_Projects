/*
 * AUTHOR: Merle Crutchfield
 * FILE: Song.java
 * ASSIGNMENT: PA4 - Spotify
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file is used to create the Song class, which takes in
 * a title and artists. It also holds a variable for the times played,
 * which is initialized to 0. It has three getter methods, ones that
 * return the title, artist, and the times the song has been played.
 * There is a void function play that prints out the toString value
 * of the song, as well as adding one to the times the song has been
 * played. The toString method returns a string description of the song
 * consisting of the title, the artist, and the times played. 
 */

public class Song {
	// Private instance variables
	private String title;
	private String artist;
	private int played;
	
	// Constructor
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		this.played = 0;
	}
	
	/*
	 * PURPOSE: Returns the String title of the song
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * PURPOSE: Returns the String artist of the song
	 */
	public String getArtist() {
		return artist;
	}
	
	/*
	 * PURPOSE: Returns the integer of amount of times the
	 * song has been played.
	 */
	public int getTimesPlayed() {
		return played;
	}
	
	/*
	 * PURPOSE: This method playes the song by printing out
	 * the information of the song, and adding 1 to the played
	 * value of the song. It calls the toString method to be
	 * printed out.
	 */
	public void play() {
		System.out.println(this.toString());
		played += 1;
	}
	
	/*
	 * PURPOSE: This method returns the String of the title, the
	 * author, and the times played all in one line. It does this
	 * by adding the values to a String to return, and words to
	 * make the format correct. It returns the String.
	 */
	public String toString() {
		String ans = this.getTitle() + " by " + this.getArtist();
		ans += ", " + this.getTimesPlayed() + " play(s)\n";
		return ans;
	}
}