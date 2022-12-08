/*
 * AUTHOR: Merle Crutchfield
 * FILE: Tree.java
 * ASSIGNMENT: PA5 - Garden
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This is the second subclass of the Plant object. The Tree
 * object is a 5x5 grid that starts by having the tree at the bottom center
 * of the grid. The character to symbolize this is the first letter of
 * the Tree name. The object is created by the name being passed, and
 * it is plotted with said character. The grow method checks to see how
 * many times it has grown before, and from there grows upwards as seen
 * in the method below. Once it reaches max growth, nothing happens if 
 * the growth method is called again. The getName method returns the 
 * name and the print row prints the coresponding row.
 * 
 */

public class Tree extends Plant {
    // Private instance variable
    private char letter;
    private char[][] grid = new char[5][5];
    private String name;
    private int grownTimes = 0;

    // Constructor
    public Tree() {

    }

    // Constructor passed name
    public Tree(String name) {
        this.name = name;
        letter = Character.toLowerCase(this.name.charAt(0));
        plot();
    }

    /*
     * PURPOSE: Returns the 2D grid of an empty plot. It runs
     * through a nested for loop and sets all the values to the
     * '.' and returns the grid. If it is at the bottom middle
     * point, the letter is printed for the tree representation.
     */
    public char[][] plot() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 4) & (j == 2))
                    grid[i][j] = letter;
                else
                    grid[i][j] = '.';
            }
        }
        return grid;
    }

    /*
     * PURPOSE: This method will grow the Tree. It has a
     * while loop that runs until the num reaches 0, which
     * is the amount of times it should grow. A switch
     * statement is run on the grownTimes variable which
     * is the amount of times the Tree has grown.
     * It then grows accordingly and grownTimes increases.
     */
    public void grow(int num) {
        while (num > 0) {
            switch (grownTimes) {
            case 0:
                grid[3][2] = letter;
                grownTimes += 1;
                break;
            case 1:
                grid[2][2] = letter;
                grownTimes += 1;
                break;
            case 2:
                grid[1][2] = letter;
                grownTimes += 1;
                break;
            case 3:
                grid[0][2] = letter;
                grownTimes += 1;
                break;
            default:
                break;
            }
            num -= 1;
        }
    }

    /*
     * PURPOSE: Getter method that returns the name.
     */
    public String getName() {
        return name;
    }

    /*
     * PURPOSE: Void method that prints out the row. The
     * row value is passed, and it iterates through each
     * col to add it to a String that is eventually printed
     * out without the endline character.
     */
    public void printRow(int row) {
        String rowString = "";
        for (int i = 0; i < 5; i++) {
            rowString += grid[row][i];
        }
        System.out.print(rowString);
    }

}