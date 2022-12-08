/*
 * AUTHOR: Merle Crutchfield
 * FILE: User.java
 * ASSIGNMENT: PA4 - Spotify
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file is used to create a User class, which
 * consists of two Strings passed for the name and password,
 * and a list of Playlists that is declared as empty at first.
 * There is one getter method that returns the name. The method
 * attemptLogin checks to see if the String passed is the password
 * and if it is then it returns true and if not false. The 
 * addPlaylist method adds the new Playlist passed as the arguemnt.
 * The getPlaylists returns a list of the Playlists that the user
 * has. The selectPlaylist checks for the Playlist passed as the argument
 * and if it exists then it plays it. Finally, the toString returns
 * a string of all the info about the Playlist.
 */

import java.util.ArrayList;
import java.util.List;

public class User {
	// Private instance variables
	private String name;
	private String password;
	private List<Playlist> playlists;
	
	// Constructor
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		playlists = new ArrayList<Playlist>();
	}
	
	/*
	 * PURPOSE: This returns the String of the name of 
	 * the User.
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * PURPOSE: This returns true if the argument String
	 * is the password. It checks to see if they are the
	 * same and returns true if they are. If not, it
	 * returns false.
	 */
	public boolean attemptLogin(String password) {
		if (password.equals(this.password))
			return true;
		return false;
	}
	
	/*
	 * PURPOSE: This adds the Playlist passed as the arguemnt to
	 * the list of Playlists.
	 */
	public void addPlaylist(Playlist newPlaylist) {
		playlists.add(newPlaylist);
	}
	
	/*
	 * PURPOSE: This returns the list of Playlists.
	 */
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	/*
	 * PURPOSE: This iterates through each of the playlists inside
	 * the list. If the name is equal to the name of the Playlist,
	 * it will call the play method on the Playlist to simulate 
	 * it being played.
	 */
	public void selectPlaylist(String name) {
		for (Playlist list : playlists) {
			if (name.equals(list.getName()))
				list.play();
		}
	}
	
	/*
	 * PURPOSE: This is used to return the user name and playlists.
	 * It first gets the name of the user, then adds it to an empty
	 * String, and then the count of playlists separated by a comma.
	 * It returns the String with the information.
	 */
	public String toString() {
		String ans = "";
		ans += this.getName() + ",";
		int count = playlists.size();
		return ans + count + " playlists\n";
	}
}