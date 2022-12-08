/*
 * AUTHOR: Merle Crutchfield
 * FILE: Flower.java
 * ASSIGNMENT: PA5 - Garden
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This is the first subclass of the Plant object. The Flower
 * object is a 5x5 grid that starts by having the flower at the center
 * of the grid. The character to symbolize this is the first letter of
 * the Flower name. The object is created by the name being passed, and
 * it is plotted with said character. The grow method checks to see how
 * many times it has grown before, and from there grows outwards as seen
 * in the method below. Once it reaches max growth, nothing happens if 
 * the growth method is called again. The getName method returns the 
 * name and the print row prints the coresponding row.
 * 
 */

public class Flower extends Plant {
    // Private instance variable
    private char letter;
    private char[][] grid = new char[5][5];
    private String name;
    private int grownTimes = 0;
    
    // Constructor
    public Flower() {

    }

    // Constructor passed name
    public Flower(String name) {
        this.name = name;
        letter = Character.toLowerCase(this.name.charAt(0));
        plot();
    }

    /*
     * PURPOSE: Returns the 2D grid of an empty plot. It runs
     * through a nested for loop and sets all the values to the
     * '.' and returns the grid. If it is at the middle point,
     * the letter is printed for the flower representation.
     */
    public char[][] plot() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 2) & (j == 2))
                    grid[i][j] = letter;
                else
                    grid[i][j] = '.';
            }
        }
        return grid;
    }
    
    /*
     * PURPOSE: This method will grow the Flower. It has a
     * while loop that runs until the num reaches 0, which
     * is the amount of times it should grow. A switch
     * statement is run on the grownTimes variable which
     * is the amount of times the Flower has grown.
     * It then grows accordingly and grownTimes increases.
     */
    public void grow(int num) {
        while (num > 0) {
            switch (grownTimes) {
            case 0: 
                grid[2][1] = letter; grid[1][2] = letter;
                grid[2][3] = letter; grid[3][2] = letter;
                grownTimes += 1;
                break;
            case 1: 
                grid[0][2] = letter; grid[1][1] = letter;
                grid[1][3] = letter; grid[2][0] = letter;
                grid[2][4] = letter; grid[3][1] = letter;
                grid[3][3] = letter; grid[4][2] = letter;
                grownTimes += 1;
                break;
            case 2: 
                grid[0][1] = letter; grid[0][3] = letter;
                grid[1][0] = letter; grid[1][4] = letter;
                grid[3][0] = letter; grid[3][4] = letter;
                grid[4][1] = letter; grid[4][3] = letter;
                grownTimes += 1;
                break;
            case 3: 
                grid[0][0] = letter; grid[0][4] = letter;
                grid[4][0] = letter; grid[4][4] = letter;
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