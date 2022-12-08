import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * AUTHOR: Merle Crutchfield
 * FILE: PA12Main.java
 * ASSIGNMENT: PA12 - Anagrams
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This assignment is meant to practice using recursive backtrackign and
 * decomposition of a given class to help solve our problem. In this assignment,
 * we are given a dictionary text file, a word, and a letter of limits of words
 * in the found anagrams. Our code first finds all possible words in the initial
 * word, then goes through and uses recursive backtracking to find the anagrams.
 * Once this is done, they are printed out as a list. The dictionaryCreate 
 * method takes in a file and adds all of the words into a dictionary, which
 * is a list of Strings. The anagramFinder method is used recursively with the
 * path, the list of words possible, the letterInventory of the word, and the
 * limit.
 */

public class PA12Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Phrase to scramble: " + args[1] + "\n");
        System.out.println("All words found in " + args[1] + ":");
        List<String> dictionary = dictionaryCreate(args[0]);
        List<String> words = new ArrayList<String>();
        LetterInventory given = new LetterInventory(args[1]);
        for (String word : dictionary) {
            if (given.contains(word))
                words.add(word);
        }
        System.out.println(words + "\n");
        System.out.println("Anagrams for " + args[1] + ":");
        List<String> path = new ArrayList<String>();
        if (Integer.valueOf(args[2]) == 0)
            anagramFinder(path, words, given, Integer.MAX_VALUE);
        else
            anagramFinder(path, words, given, Integer.valueOf(args[2]));
    }

    /*
     * This method takes the name of the file that is the
     * dictionary as the only argument. It throws a FileNotFoundException
     * and returns a list of Strings of the dictionary. It uses a while
     * loop to iterate through the file and add each word to the
     * list before returning it.
     */
    public static List<String> dictionaryCreate(String name)
            throws FileNotFoundException {
        List<String> dictionary = new ArrayList<String>();
        Scanner file = new Scanner(new File(name));
        String line = file.nextLine();
        while (line != null) {
            dictionary.add(line);
            if (file.hasNext())
                line = file.nextLine();
            else
                line = null;
        }
        return dictionary;
    }

    /*
     * This method takes an empty list as the path, a list of all the
     * possible words, the letterInventory of the word passed, and the
     * length of the limit. The base case checks to see if the path is
     * too long, and if it is then it recurses out. The other base case
     * is to check if given is empty, meaning the anagram has been found,
     * and it is printed out and then returned. If not, it iterates through
     * a for loop, checking to see if the word is in the letterInventory,
     * then subtrats the words from it, adds it to the list path, and recurses.
     * It then recurses out by removing the word from the path list and adds
     * the word back to letterInventory.
     */
    public static void anagramFinder(List<String> path, List<String> words,
            LetterInventory given, int length) {
        if (path.size() >= length && !(given.isEmpty()))
            return;
        if (given.isEmpty()) {
            System.out.println(path);
            return;
        }
        else {
            for (String word : words) {
                if (given.contains(word)) {
                    given.subtract(word);
                    path.add(word);
                    // System.out.println(path);
                    anagramFinder(path, words, given, length);
                    path.remove(path.size() - 1);
                    given.add(word);
                }
            }
        }
    }
}
