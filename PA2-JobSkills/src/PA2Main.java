
/*
 * AUTHOR: Merle Crutchfield
 * FILE: PA2Main.java
 * ASSIGNMENT: PA2 - Job Skills
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This code is used to learn how to use the abstract dictionary
 * data type using HashMaps, where the key is a string and the value is
 * an integer. We first import a scanner for the file, and check to see
 * if the user passes "catcount" or "locations" as the second argument.
 * If it is neither of the ones above, it is said to be invalid and the
 * code ends. If it is the first one above, the catcount method is called
 * where a while loop runs through each line of the text file, and the
 * categories as well as the count of the categories are saved as the key
 * and value of the HashMap. If the second one is called, the locations 
 * method is called where another while loop runs through each line of the
 * text file and checks to see if the category equals the third argument
 * passed by the user. If it does, it checks to see if the key of the location
 * exists, and if it doesn't then it sets the value to 1, but if it does, it 
 * adds 1 to the value. Once all of the keys and values have been added to the
 * HashMap, the printMap method is called which sorts creates an ArrayList of
 * Strings from the keys of the HashMap, and then sorts them. Once sorted, it
 * iterates through the keys and prints out the corresponding key and value.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PA2Main {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, Integer> map = new HashMap<>();
        Scanner file = new Scanner(new File(args[0]));
        // Skips first line since it is the top part
        file.nextLine();
        if (args[1].equals("CATCOUNT")) {
            catcount(map, file);
            System.out.println("Number of positions for each category");
            printMap(map);
        } else if (args[1].equals("LOCATIONS")) {
            locations(map, file, args[2]);
            System.out.println(args[1] + " for category: " + args[2]);
            printMap(map);
        } else {
            // Not correct second user argument entered
            System.out.println("Invalid Command");
        }
        file.close();
    }

    /*
     * PURPOSE: This method takes the empty HashMap and the file as
     * arguments. It iterates through each line in the file until the
     * end is reached using a while loop. An empty list of strings is
     * made of each line split at the comma. It then checks to see if
     * the third element is in the HashMap keys, and if not then it
     * adds it with the value 1 for count, if it is then increments the
     * value by 1.
     */
    public static void catcount(HashMap<String, Integer> map, Scanner file) {
        while (file.hasNextLine()) {
            // Splits at comma
            String[] list = file.nextLine().split(",");
            // Checks to see if in HashMap
            if (map.containsKey(list[2]))
                map.replace(list[2], map.get(list[2]) + 1);
            else
                map.put(list[2], 1);
        }
    }

    /*
     * PURPOSE: This method takes the empty HashMap, the file, and the 3rd
     * user argument as arguments. It iterates through each line in the file
     * until the end is reached using a while loop. An empty list of strings
     * is made of each line split at the comma. It then checks to see if
     * the second element is in the HashMap keys, and if the second element is
     * the same as the users third argument. If not then it adds it with the
     * value 1 for count, if it is then increments the value by 1.
     */
    public static void locations(HashMap<String, Integer> map, Scanner file,
            String category) {
        while (file.hasNextLine()) {
            // Splits at comma
            String[] list = file.nextLine().split(",");
            // Checks to see if in HashMap
            if (map.containsKey(list[3]) && list[2].equals(category)) {
                map.replace(list[3], map.get(list[3]) + 1);
            } else if (list[2].equals(category))
                map.put(list[3], 1);
        }
    }

    /*
     * PURPOSE: This method takes the HashMap filled with all the keys
     * and values. Once it is passed, a line is printed out, and a list
     * of the keys of the HashMap is created. It is then sorted and run
     * through a for loop, which prints out the key with a comma and then
     * the value along with it. It will print out all of them in order
     * since the keys are iterated through in order.
     */
    public static void printMap(HashMap<String, Integer> map) {
        System.out.println("-------------------------------------");
        List<String> sorted = new ArrayList<String>(map.keySet());
        Collections.sort(sorted);
        for (String key : sorted) {
            System.out.println(key + ", " + map.get(key));
        }
    }
}

