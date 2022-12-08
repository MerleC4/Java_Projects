
/*
 * AUTHOR: Merle Crutchfield
 * FILE: PA5Main.java
 * ASSIGNMENT: PA5 - Garden
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This file is the main java file for implementing the Plant and Garden
 * objects, as well as the flower, tree, and vegetable objects that extend the
 * plant object. This is to simulate a garden, where the user can enter specific
 * commands that will plant, grow, or remove the plants in the garden. First, the
 * main function takes the input file name as an argument, and it checks to make
 * sure the rows and cols are less than 17. It then creates a garden object based
 * on the rows and cols, and starts iterating through each line in the file. It checks
 * to see if it is the print command, and if not then it goes to the other input cases.
 * From there, the cases will either be plant: plants a plant at the location specified,
 * grow: grows the plant at the location, or based on the 3rd argument, or harvest, 
 * pick, or cut, which each get rid of the flower, tree, or vegetable according to
 * the argument given on the line. It will do this by calling several functions below
 * to properly find the argument that the user wants, and will run until all the lines
 * are done in the file.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PA5Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File(args[0]));
        int rows = Integer.parseInt(file.nextLine().split(" ")[1]);
        int cols = Integer.parseInt(file.nextLine().split(" ")[1]);
        file.nextLine();
        if (rows > 16)
            System.out.println("Too many plot rows.");
        else if (cols > 16)
            System.out.println("Too many plot columns.");
        else {
            Garden garden = new Garden(rows, cols);
            fileLines(file, garden, rows, cols);
        }
    }

    /*
     * PURPOSE: This method takes the file, the garden, and the rows and
     * cols count. It runs a while loops that iterates through each line
     * of the file, and makes the line a string. It then splits the string
     * at the spaces, and runs a for loop to get the part after the command
     * keyword in lowercase. It the checks to see if the command is print,
     * and if it is it calls the printGarden method. If not, it calls
     * inputCases to see which command case it is.
     */
    public static void fileLines(Scanner file, Garden garden, int rows,
            int cols) {
        while (file.hasNextLine()) {
            String printStatement = file.nextLine();
            String[] list = printStatement.split(" ");
            String printEnd = "";
            // Creates the lowercase part of the statement printed out
            for (int i = 1; i < list.length; i++) {
                printEnd += list[i].toLowerCase();
                printEnd += " ";
            }
            // Print command, if not calls inputCases
            if (list[0].equalsIgnoreCase("PRINT")) {
                System.out.println("> PRINT");
                garden.printGarden();
            }
            else
                inputCases(list, printEnd, rows, cols, garden);
        }
    }

    /*
     * PURPOSE: This method takes the list of keywords from each line, the
     * string to be printed out in lowercase, the rows and cols count, and
     * the garden. It checks to see if the command is plant, and if it is
     * it finds the row and col value to be planted at. It then makes sure
     * the row and col is valid, and if so calls the addPlant method to add
     * it to the Garden. If it is grow, it calls the grow method, if harvest
     * then harvest method, if pick then pick method, and finally if cut the
     * cut method.
     */
    public static void inputCases(String[] list, String printEnd, int rows,
            int cols, Garden garden) {
        // If-elif for each valid command
        if (list[0].equalsIgnoreCase("PLANT")) {
            // Gets the two values as integers making sure to split at the comma
            int row = Integer.parseInt(list[1].split(",")[0].substring(1));
            int col = Integer.parseInt(list[1].split(",")[1].substring(0,
                    list[1].split(",")[1].length() - 1));
            // Valid check for the row and col
            if (row < rows & col < cols)
                garden.addPlant(row, col, list[2]);
            else
                System.out.println("Can't grow there.\n");
        } else if (list[0].equalsIgnoreCase("GROW")) {
            System.out.println("> GROW " + printEnd + "\n");
            growInput(list, rows, cols, garden);
        } else if (list[0].equalsIgnoreCase("HARVEST")) {
            System.out.println("> HARVEST " + printEnd + "\n");
            harvestInput(list, rows, cols, garden);
        } else if (list[0].equalsIgnoreCase("PICK")) {
            System.out.println("> PICK " + printEnd + "\n");
            pickInput(list, rows, cols, garden);
        } else if (list[0].equalsIgnoreCase("CUT")) {
            System.out.println("> CUT " + printEnd + "\n");
            cutInput(list, rows, cols, garden);
        }
    }

    /*
     * PURPOSE: This method takes the list of keywords from each line, the
     * string to be printed out in lowercase, the rows and cols count, and
     * the garden. It creates an array of the plant subclasses, and checks to
     * see if the length of the list is 2, which is the specific case to grow
     * all the plants. It then uses a nested for loop to do that. It then checks
     * to see if the third keyword in the list is a plant subclass object, and
     * if it is then runs through each object in the garden, checks to see if it
     * is the right object, and if so will grow it. If not, the
     * growInputSecondCases method is called for the final two cases.
     */
    public static void growInput(String[] list, int rows, int cols, Garden garden) {
        List<String> plants = Arrays.asList("flower", "tree", "vegetable");
        // Everything grows condition
        if (list.length == 2) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    garden.getPlant(i, j)
                            .grow(Integer.parseInt(list[1]));
            }
        }
        // Only the Plant subclass object grows condition
        else if (plants.contains(list[2].toLowerCase())) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (garden.getPlant(i, j) instanceof Flower
                            & list[2].toLowerCase().equals("flower"))
                        garden.getPlant(i, j).grow(Integer.parseInt(list[1]));
                    else if (garden.getPlant(i, j) instanceof Tree
                            & list[2].toLowerCase().equals("tree"))
                        garden.getPlant(i, j).grow(Integer.parseInt(list[1]));
                    else if (garden.getPlant(i, j) instanceof Vegetable
                            & list[2].toLowerCase().equals("vegetable"))
                        garden.getPlant(i, j).grow(Integer.parseInt(list[1]));
                }
            }
        }
        else
            growInputSecondCases(list, rows, cols, garden);
    }

    /*
     * PURPOSE: This method takes the list of keywords from each line, the
     * string to be printed out in lowercase, the rows and cols count, and
     * the garden. The first condition checked is to see if the specific
     * coordinate should be grown, and if so it gets the row and col value.
     * It then ensures that is within the right range, and that it is the
     * right object. If so, then it will grow. The final condition is the
     * name of the Plant condition. The for loops get the name of each
     * Plant in the Garden, and if it matches the input command then it will
     * grow.
     */
    public static void growInputSecondCases(String[] list, int rows, int cols,
            Garden garden) {
        // Specific coordinate case
        if (list[2].charAt(0) == '(') {
            int row = Integer.parseInt(list[2].split(",")[0].substring(1));
            int col = Integer.parseInt(list[2].split(",")[1].substring(0,
                    list[2].split(",")[1].length() - 1));
            if (row >= rows | col >= cols)
                System.out.println("Can't grow there.\n");
            else if (garden.getPlant(row, col) instanceof Plant) {
                garden.getPlant(row, col).grow(Integer.parseInt(list[1]));
            }
        }
        // Final case based on the name of the Plant
        else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (garden.getPlant(i, j).getName()
                            .equalsIgnoreCase(list[2]))
                        garden.getPlant(i, j).grow(Integer.parseInt(list[1]));
                }
            }
        }
    }

    /*
     * PURPOSE: This method takes the list of keywords from each line, the
     * string to be printed out in lowercase, the rows and cols count, and
     * the garden. The first condition checked is that everything is harvested,
     * and if so it checks to make sure the Plant at each spot in the grid
     * is a Vegetable, and if so it then harvests it. The second case is
     * the specific coordinate, which then gets the coordinate from the
     * line. Once it has it, it ensures that it is within the limits, and
     * then checks to make sure it is a Vegetable, and if so then it will
     * harvest it. If not it prints out that it is invalid. The final
     * condition is the name of the vegetable case, which checks to see if
     * the command name is the same name as the plant, and if it is then
     * it harvests it.
     */
    public static void harvestInput(String[] list, int rows, int cols,
            Garden garden) {
        if (list.length == 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    if (garden.getPlant(i, j) instanceof Vegetable)
                        garden.removePlant(i, j);
            }
        } else if (list[1].charAt(0) == '(') {
            int row = Integer.parseInt(list[1].split(",")[0].substring(1));
            int col = Integer.parseInt(list[1].split(",")[1].substring(0,
                    list[1].split(",")[1].length() - 1));
            if (row <= rows & col <= cols) {
                if (garden.getPlant(row, col) instanceof Vegetable) {
                garden.removePlant(row, col);
                } else
                    System.out.println("Can't harvest there.\n");
            } else
                System.out.println("Can't harvest there.\n");
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (garden.getPlant(i, j).getName()
                            .equalsIgnoreCase(list[1]))
                        garden.removePlant(i, j);
                }
            }
        }
    }

    /*
     * PURPOSE: This method takes the list of keywords from each line, the
     * string to be printed out in lowercase, the rows and cols count, and
     * the garden. The first condition checked is that everything is picked,
     * and if so it checks to make sure the Plant at each spot in the grid
     * is a Flower, and if so it then picks it. The second case is
     * the specific coordinate, which then gets the coordinate from the
     * line. Once it has it, it ensures that it is within the limits, and
     * then checks to make sure it is a Flower, and if so then it will
     * pick it. If not it prints out that it is invalid. The final
     * condition is the name of the Flower case, which checks to see if
     * the command name is the same name as the plant, and if it is then
     * it picks it.
     */
    public static void pickInput(String[] list, int rows, int cols,
            Garden garden) {
        if (list.length == 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    if (garden.getPlant(i, j) instanceof Flower)
                        garden.removePlant(i, j);
            }
        } else if (list[1].charAt(0) == '(') {
            int row = Integer.parseInt(list[1].split(",")[0].substring(1));
            int col = Integer.parseInt(list[1].split(",")[1].substring(0,
                    list[1].split(",")[1].length() - 1));
            if (row <= rows & col <= cols) {
                if (garden.getPlant(row, col) instanceof Flower) {
                    garden.removePlant(row, col);
                } else
                    System.out.println("Can't pick there.\n");

            } else
                System.out.println("Can't pick there.\n");
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (garden.getPlant(i, j).getName()
                            .equalsIgnoreCase(list[1]))
                        garden.removePlant(i, j);
                }
            }
        }
    }

    /*
     * PURPOSE: This method takes the list of keywords from each line, the
     * string to be printed out in lowercase, the rows and cols count, and
     * the garden. The first condition checked is that everything is cut,
     * and if so it checks to make sure the Plant at each spot in the grid
     * is a Tree, and if so it then cuts it. The second case is
     * the specific coordinate, which then gets the coordinate from the
     * line. Once it has it, it ensures that it is within the limits, and
     * then checks to make sure it is a Tree, and if so then it will
     * cut it. If not it prints out that it is invalid. The final
     * condition is the name of the tree case, which checks to see if
     * the command name is the same name as the plant, and if it is then
     * it cuts it.
     */
    public static void cutInput(String[] list, int rows, int cols,
            Garden garden) {
        if (list.length == 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    if (garden.getPlant(i, j) instanceof Tree)
                        garden.removePlant(i, j);
            }
        } else if (list[1].charAt(0) == '(') {
            int row = Integer.parseInt(list[1].split(",")[0].substring(1));
            int col = Integer.parseInt(list[1].split(",")[1].substring(0,
                    list[1].split(",")[1].length() - 1));
            if (row <= rows & col <= cols) {
                if (garden.getPlant(row, col) instanceof Tree) {
                    garden.removePlant(row, col);
                } else
                    System.out.println("Can't cut there.\n");

            } else
                System.out.println("Can't cut there.\n");
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (garden.getPlant(i, j).getName()
                            .equalsIgnoreCase(list[1]))
                        garden.removePlant(i, j);
                }
            }
        }
    }
}