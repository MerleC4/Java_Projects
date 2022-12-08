
/*
 * AUTHOR: Merle Crutchfield
 * FILE: WordSearch.java
 * ASSIGNMENT: PA1 - WordSearch
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This program reads in two arguments txt files, one contains
 * one word per line serving as a dictionary, and the other is a grid of
 * letters separated by spaces, with the two first lines representing
 * the amount of rows and colomns in the file. The code reads in the
 * files and adds the dictionary into into an array list of strings,
 * while the grid is read into a 2D list. From there, the files are
 * closed, and then the horizontal and vertical methods are called.
 * These use a nested for loop to find the words inside the line. From 
 * there, it calls the findWords method by creating a String of length 
 * 3, and then increasing until it reaches the max length, and then
 * checks to see if it is in the array list dictionary. If it is, the
 * word is printed out and the process repeats.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordSearch {
    // Global variable
    private static final int MIN_WORD_LENGTH = 3;

    public static void main(String[] args) throws FileNotFoundException {
        // Getting files from two inputs
        Scanner dictFile = new Scanner(new File(args[0]));
        Scanner gridFile = new Scanner(new File(args[1]));
        // Row and Column count
        int rows = Integer.valueOf(gridFile.nextLine());
        int cols = Integer.valueOf(gridFile.nextLine());
        // Calling methods to get grid and dictionary
        String[][] grid = readGrid(gridFile, rows, cols);
        ArrayList<String> dictionary = readDict(dictFile);
        dictFile.close();
        gridFile.close();
        // Calling horizontal and vertical methods
        addHorizontal(grid, rows, cols, dictionary);
        addVertical(grid, rows, cols, dictionary);
    }

    /*
     * PURPOSE: This method reads in the grid from the grid text file, which
     * is the second argument passed by user. The arguments passed are the
     * file, the amount of rows, and the amount of columns. The method returns
     * a 2D list of strings.
     */
    public static String[][] readGrid(Scanner gridFile, int rows, int cols) {
        String[][] grid = new String[rows][cols];
        // Repeats until file ends
        while (gridFile.hasNextLine()) {
            // Outer list, corresponds to rows
            for (int i = 0; i < grid.length; i++) {
                // Creates list of the line
                String[] list = gridFile.nextLine().toLowerCase().trim()
                        .split(" ");
                // Inner list, corresponds to cols
                for (int j = 0; j < list.length; j++)
                    grid[i][j] = list[j].toLowerCase();
            }
        }
        return grid;
    }

    /*
     * PURPOSE: This method reads in the dictionary text file, which is
     * the first argument passed by the user. The only value passed is
     * the dictionary file itself, and an array list of strings is
     * returned.
     */
    public static ArrayList<String> readDict(Scanner dictFile) {
        // Defining array list of strings
        ArrayList<String> dictionary = new ArrayList<String>();
        // Runs until file ends
        while (dictFile.hasNextLine()) {
            dictionary.add(dictFile.nextLine().toLowerCase().trim());
        }
        return dictionary;
    }

    /*
     * PURPOSE: This method takes a string, and breaks it up as many
     * times as possible using substrings with the min length of three.
     * Once it does that, it checks to see if the value is in the
     * dictionary, and if so it prints out the word. The arguments
     * passed are the string of the line, the rows, the cols, and the
     * dictionary.
     */
    public static void findWords(String line, int rows, int cols,
            ArrayList<String> dictionary) {
        // Iterate through the start point
        for (int i = 0; i <= line.length(); i++) {
            // Iterate through the valid end points
            for (int j = i + MIN_WORD_LENGTH; j <= line.length(); j++) {
                // Create a word from the line
                String word = line.substring(i, j);
                // Checks to see if it exists
                if (dictionary.contains(word))
                    System.out.println(word);
            }
        }
    }

    /*
     * PURPOSE: This method is used to create a string called line of the
     * horizontal left to right of the grid and then horizontal right to
     * left. The arguments passed are the 2d grid, the row numbers, column
     * numbers, and the dictionary. The method uses a nested for loop to
     * iterate through the grid, and adds the value of each row to a string
     * before calling the findWords method. From there, another nested
     * for loop is used to create the line in reverse and findWords is
     * called again.
     */
    public static void addHorizontal(String[][] grid, int rows, int cols,
            ArrayList<String> dictionary) {
        String line = "";
        // Nested for loop for LtoR
        for (int i = 0; i < rows; i++) {
            // Creating blank string to add each value to
            line = "";
            for (int j = 0; j < cols; j++)
                line += grid[i][j];
            // Calls findWords with newly created line
            findWords(line.toLowerCase(), rows, cols, dictionary);
        }
        // Nested for loop for RtoL
        for (int i = 0; i < rows; i++) {
            // Creating blank string to add each value to
            line = "";
            for (int j = cols - 1; j >= 0; j--)
                line += grid[i][j];
            // Calls findWords with newly created line
            findWords(line.toLowerCase(), rows, cols, dictionary);
        }
    }

    /*
     * PURPOSE: This method is nearly identical to the addHorizontal one
     * but instead it is vertical from top to bottom and then bottom to
     * top. The garguments passed are the 2d grid, the row number, col
     * number, and the dictionary. The method uses a nested for loop to
     * iterate through the grid, and adds the value of each row to a string
     * before calling the findWords method. From there, another nested
     * for loop is used to create the line in reverse and findWords is
     * called again.
     */
    public static void addVertical(String[][] grid, int rows, int cols,
            ArrayList<String> dictionary) {
        String line = "";
        // Nested for loop for TtoB
        for (int i = 0; i < cols; i++) {
            // Creating blank string to add each value to
            line = "";
            for (int j = 0; j < rows; j++)
                line += grid[j][i];
            // Calls findWords with newly created line
            findWords(line.toLowerCase(), rows, cols, dictionary);

        }
        // Nested for loop for BtoT
        for (int i = 0; i < cols; i++) {
            // Creating blank string to add each value to
            line = "";
            for (int j = rows - 1; j >= 0; j--)
                line += grid[j][i];
            // Calls findWords with newly created line
            findWords(line.toLowerCase(), rows, cols, dictionary);
        }
    }
}
