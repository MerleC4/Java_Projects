/*
 * AUTHOR: Merle Crutchfield
 * FILE: Garden.java
 * ASSIGNMENT: PA5 - Garden
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This is the Garden object used in the main file. It is a 2D
 * list of Plant objects that takes the rows and cols as parameters. It
 * then creates the 2D list of empty Plant objects, which can be changed
 * by adding or removing plants. The addPlant calls the create plant
 * method from the Plant object, and puts it at the specified coordinates.
 * The removePlant creates the empty plant at the location specified to
 * reset it. The getPlant method returns the Plant at the specified 
 * coordinates. The printGarden method prints out the Garden using
 * several for loops to ensure the rows are printed out in order.
 * 
 */

public class Garden {
    // Private instance variable
    private static Plant[][] garden;

    // Constructor
    public Garden() {
    }

    // Constructor passed rows and cols
    public Garden(int rows, int cols) {
        garden = new Plant[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                garden[i][j] = Plant.createPlant("Empty");
            }
        }
    }

    /*
     * PURPOSE: This void function adds the specified Plant to the
     * Garden. It takes the coordiates and the name of the plant,
     * and calls the createPlant function with the name passed.
     */
    public void addPlant(int x, int y, String name) {
        garden[x][y] = Plant.createPlant(name);
    }

    /*
     * PURPOSE: This void function removes the specified Plant from
     * the Garden. It takes the coordiates and the name of the plant,
     * and calls the createPlant function with the empty plant so that
     * it is set back to an empty plot.
     */
    public void removePlant(int x, int y) {
        garden[x][y] = Plant.createPlant("Empty");
    }

    /*
     * PURPOSE: Getter function for the Plant when the row and col
     * values are passed. Returns the plant at the location.
     */
    public Plant getPlant(int x, int y) {
        return garden[x][y];
    }

    /*
     * PURPOSE: Void function that prints out the garden. Uses a for
     * loop to iterate through the rows in the garden, then a for
     * loop that runs 5 times for each row, and then a for loop that
     * goes through each Plant in the row so it prints out the top
     * row then the next row and so on. Calls the printRow method
     * for the plant.
     */
    public void printGarden() {
        for (Plant[] rows : garden) {
            for (int i = 0; i < 5; i++) {
                for (Plant plant : rows) {
                    if (plant == null)
                        plant = new Plant();
                    plant.printRow(i);
                }
                System.out.print("\n");
            }
        }
        System.out.println();
    }
}