
/*
 * AUTHOR: Merle Crutchfield
 * FILE: Plant.java
 * ASSIGNMENT: PA5 - Garden
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file creates the Plant object used in both the Garden object and
 * the main file. There is a default Plant constructor, but the constructor that
 * takes the name is how the plant is created. A 5x5 grid is made of all "." that
 * represents the plant, and there are various functions accordingly. First, the plot
 * returns the grid. The create plant takes a name, and checks to see if it is a flower,
 * tree, or vegetable name as in the arraylist. If it is, then the supclass is called
 * accordingly, and if not the it just returns a default plant. The grow command and get
 * name are for the subclasses, and the return type will return what subclass it is. 
 * Finally, the print row is used for printing out each row later needed in the 
 * garden.
 * 
 */
import java.util.Arrays;
import java.util.List;

public class Plant {
    // Private instance variables
    static List<String> flowers = Arrays.asList("iris", "lily", "rose", "daisy",
            "tulip", "sunflower");
    static List<String> trees = Arrays.asList("oak", "willow", "banana",
            "coconut", "pine");
    static List<String> vegetables = Arrays.asList("garlic", "zucchini",
            "tomato",
            "yam", "lettuce");
    private static char[][] grid = new char[5][5];

    // Constructor
    public Plant() {
    }

    // Constructor passed name
    public Plant(String name) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = '.';
            }
        }
    }

    /*
     * PURPOSE: Returns the 2D grid of an empty plot. It runs
     * through a nested for loop and sets all the values to the
     * '.' and returns the grid.
     */
    public char[][] plot() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = '.';
            }
        }
        return grid;
    }

    /*
     * PURPOSE: This method creates a plant based on the
     * name entered. It checks to see if the name is in
     * the flowers array, then trees, and vegetables, and if
     * so then returns that object with the name. If not, then
     * it returns a default Plant object.
     */
    public static Plant createPlant(String name) {
        if (flowers.contains(name.toLowerCase()))
            return new Flower(name);
        else if (trees.contains(name.toLowerCase()))
            return new Tree(name);
        else if (vegetables.contains(name.toLowerCase()))
            return new Vegetable(name);
        else
            return new Plant("Empty");
    }

    /*
     * PURPOSE: Grow method, used in each of the subclasses
     */
    public void grow(int x) {
    }

    /*
     * PURPOSE: Returns the object type based on the name
     * entered. It checks to see if the name is flower,
     * tree, or vegetable, and returns the coresponding
     * one.
     */
    public Plant returnType(String name) {
        if (name.equalsIgnoreCase("flower"))
            return new Flower();
        else if (name.equalsIgnoreCase("tree"))
            return new Tree();
        return new Vegetable();
    }

    /*
     * PURPOSE: Getter method that returns the name.
     */
    public String getName() {
        return "";
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