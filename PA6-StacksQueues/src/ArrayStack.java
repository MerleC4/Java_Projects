/*
 * AUTHOR: Merle Crutchfield
 * FILE: ArrayStack.java
 * ASSIGNMENT: PA6 - ArrayStack
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This code is the implementation of a stack backed by an array.
 * This means that the methods from the StackInterface were implemented
 * in this code, where the class represents the Stack ADT. There are 
 * variables for the size of the array, as well as the array of values,
 * and the actual initialized length of the array. The constuctor
 * sets the values accrodingly. The copy construct is used to copy the
 * ArrayStack entered. The push first ensures that the array has not
 * run out of space, and then adds a value to the end of the array. The
 * pop checks to see if it is empty and then moves the starting place
 * up by one, and returns the removed value. Peek returns the value from 
 * pop but does not remove it from the stack. isEmpty checks to see if
 * it is empty, and size returns the size. Clear resets the stack, and the
 * toString returns a String representation of the stack.
 * 
 */
public class ArrayStack implements StackInterface {
    // Private instance variables
    private int size;
    private int[] vals;
    private int actualLength;

    // Constructor
    public ArrayStack() {
        size = 0;
        vals = new int[10000];
        actualLength = 10000;
    }

    /*
     * Copy constructor, sets the private instance
     * variables to the ones entered by the ArrayStack
     * passed. Rusn a for loop to change the values in
     * the array.
     */
    ArrayStack(ArrayStack c) {
        vals = new int[c.vals.length];
        size = c.size;
        for (int i = 0; i < size; i++) {
            vals[i] = c.vals[i];
        }
    }

    /*
     * Adds the value entered to the stack. First checks
     * to make sure the actualLength hasn't run out, and
     * if it has it adds more by doubling the length and
     * adding the values back in place. It then adds the
     * value to the end and increments the size variable.
     */
    @Override
    public void push(int value) {
        if (size == actualLength) {
            int[] newVals = new int[2 * actualLength];
            for (int i = 0; i < size; i++) {
                newVals[i] = vals[i];
            }
            vals = newVals;

        }
        vals[size] = value;
        size += 1;
    }

    /*
     * First checks to see if it is empty, and if it is
     * then it returns -1. If not it saves the variable,
     * decreases the size by one and returns the variable.
     */
    @Override
    public int pop() {
        if (isEmpty())
            return -1;
        else {
            int top = vals[size - 1];
            size -= 1;
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
            return vals[size - 1];
    }

    /*
     * It checks to see if the size is equal to 0.
     * Returns true if empty and false if not.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /*
     * Returns the size of the stack.
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Reinitializes all of the variables back to what
     * they were (except actualLength which can
     * remain longer).
     */
    @Override
    public void clear() {
        vals = new int[actualLength];
        size = 0;
    }
    
    /*
     * String representation of the ArrayStack. Iterates
     * through all the values and adds them to a String.
     * Returns the String.
     */
    public String toString() {
        String ans = "{";
        for (int i = 0; i < size; i++) {
            ans += Integer.toString(vals[i]);
            if (!(i == size - 1))
                ans += ",";
        }
        return ans + "}";
    }

    /*
     * Equals method to check if the object passed is equal
     * to the ArrayStack. If it is not an instanceof it then
     * it is false. If the sizes are not the same it is false.
     * It then runs a for loop to make sure the values at each
     * index are the same.
     */
    public boolean equals(Object o) {
        if (!(o instanceof ArrayStack))
            return false;
        ArrayStack check = (ArrayStack) o;
        if (check.size != size)
            return false;
        for (int i = 0; i < size; i++) {
            if (check.vals[i] != vals[i])
                return false;
        }
        return true;
    }
}