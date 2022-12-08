/*
 * AUTHOR: Merle Crutchfield
 * FILE: UserCollection.java
 * ASSIGNMENT: PA4 - Spotify
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This is used to track all of the users in Spotify,
 * which creates a class of the UserCollection. The constructor
 * does not take any arguments, but declares a list of Users to
 * be added to later. The userExists method iterates through the
 * list of Users, and checks to see if the user is in the list of
 * Users 
 */

import java.util.ArrayList;
import java.util.List;

public class UserCollection {
	// Private instance variables
	private List<User> users;
	
	// Constructor
	public UserCollection() {
		users = new ArrayList<User>();
	}
	
	/*
	 * PURPOSE: This method returns whether the username
	 * is in the list of Users. It iterates through the list
	 * of users, and checks to see if the name using the 
	 * getter function for the name is the same as the String
	 * argument for the username. If it is, then it returns
	 * true and if not it checks all the others. If it isn't found
	 * then it returns false.
	 */
	public boolean userExists(String username) {
		for (User user : users) {
			if (user.getName().equals(username))
				return true;
		}
		return false;
	}
	
	/*
	 * PURPOSE: This method returns the user if the login is successful
	 * and will return null if is not. It iterates through all the users
	 * in the list of Users using a for loop, and checks to see if the
	 * name of the User is the same as the name passed as the argument. If
	 * it is, then it checks to see if attemptLogin with the password passed
	 * is true, and if so then it will return the User. If not it will 
	 * iterate through until the end, and if no user is correct the it will
	 * return null.
	 */
	public User login(String username, String password) {
		for (User user : users) {
			if (user.getName().equals(username))
				if (user.attemptLogin(password))
					return user;
		}
		return null;
	}
	
	/*
	 * PURPOSE: This adds the user to the list of users passed as the
	 * argument.
	 */
	public void addUser(User add) {
		users.add(add);
	}
	
	/*
	 * PURPOSE: This is used to return a String description of the users. It
	 * first creates a String of the starting curly brace, and then goes through 
	 * each user and adds the name separated by a colon, and then the toString 
	 * for users. It adds the ending curly brace and then returns the String.
	 */
	public String toString() {
		String ans = "{";
		for (User user : users) {
			ans += user.getName();
			ans += ": ";
			ans += user.toString();
			ans += ", ";
		}
		return ans + "}";
	}
}