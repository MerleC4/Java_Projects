/*
 * AUTHOR: Merle Crutchfield
 * FILE: ArrayQueue.java
 * ASSIGNMENT: PA6 - ArrayQueue
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This code is the implementation of a queue backed by an array.
 * This means that the methods from the QueueInterface were implemented
 * in this code, where the class represents the Queue ADT. There are 
 * variables for the start and end index, as well as the array of values,
 * and the actual initialized length of the array. The constuctor
 * sets the values accrodingly. The copy construct is used to copy the
 * ArrayQueue entered. The enqueue first ensures that the array has not
 * run out of space, and then adds a value to the end of the array. The
 * dequeue checks to see if it is empty and then moves the starting place
 * up by one, and returns the removed value. Peek returns the value from 
 * dequeue but does not remove it from the queue. isEmpty checks to see if
 * it is empty, and size returns the size. Clear resets the queue, and the
 * toString returns a String representation of the queue.
 * 
 */
public class ArrayQueue implements QueueInterface {
    // Private instance variables
    private int start;
    private int end;
    private int[] vals;
    private int actualLength;

    // Constructor
    public ArrayQueue() {
        start = 0;
        end = 0;
        vals = new int[10000];
        actualLength = 10000;
    }

    /*
     * Copy constructor, this changes the private instance
     * variables to the ones passed by the user. The for
     * loop runs to change the values in the array to what
     * are in the one to be copied.
     */
    ArrayQueue(ArrayQueue c) {
        vals = new int[c.vals.length];
        start = c.start;
        end = c.end;
        actualLength = c.actualLength;
        for (int i = 0; i < end; i++) {
            vals[i] = c.vals[i];
        }
    }

    /*
     * Adds the value to the queue, first checks to make
     * sure that it hasn't run out of space. If it has,
     * then it doubles the length. Then it adds the value
     * and increases the end index by one.
     */
    @Override
    public void enqueue(int value) {
        if (end == actualLength) {
            int[] newVals = new int[2 * actualLength];
            for (int i = start; i < end; i++) {
                newVals[i] = vals[i];
            }
            vals = newVals;
        }
        vals[end] = value;
        end += 1;

    }

    /*
     * First checks to see if it is empty, and if it is
     * it returns -1. If not, it saves the variable, and
     * then moves the starting index up by one.
     */
    @Override
    public int dequeue() {
        if (isEmpty())
            return -1;
        else {
            int top = vals[start];
            start += 1;
            return top;
        }
    }

    /*
     * First checks to see if it is empty, and if it is
     * then it returns -1. If not it returns the variable.
     */
    @Override
    public int peek() {
        if (isEmpty())
            return -1;
        else
            return vals[start];
    }

    /*
     * It chekcs to see if the end - start is equal to
     * 0 meaning there are no values. Resets the values
     * both to 0. Returns true if empty and false if not.
     */
    @Override
    public boolean isEmpty() {
        if (end - start == 0) {
            start = 0;
            end = 0;
            return true;
        }
        return false;
    }

    /*
     * Returns the size of the queue by taking the end
     * index and subtracting the starting one.
     */
    @Override
    public int size() {
        return (end - start);
    }

    /*
     * Reinitializes all of the variables back to what
     * they were (except actualLength which can
     * remain longer).
     */
    @Override
    public void clear() {
        vals = new int[actualLength];
        end = 0;
        start = 0;
    }

    /*
     * String representation of the ArrayQueue. Iterates
     * through all the values and adds them to a String.
     * Returns the String.
     */
    public String toString() {
        String ans = "{";
        for (int i = start; i < end; i++) {
            ans += Integer.toString(vals[i]);
            if (!(i == end - 1))
                ans += ",";
        }
        return ans + "}";
    }

    /*
     * Equals method to check if the object passed is equal
     * to the ArrayQueue. If it is not an instanceof it then
     * it is false. If the sizes are not the same it is false.
     * It then runs a for loop to make sure the values at each
     * index are the same.
     */
    public boolean equals(Object o) {
        if (!(o instanceof ArrayQueue))
            return false;
        ArrayQueue check = (ArrayQueue) o;
        if (check.size() != size())
            return false;
        for (int i = 0; i < size(); i++) {
            if (check.vals[i] != vals[i])
                return false;
        }
        return true;
    }
}