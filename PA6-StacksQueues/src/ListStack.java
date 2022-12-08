/*
 * AUTHOR: Merle Crutchfield
 * FILE: ListStack.java
 * ASSIGNMENT: PA6 - ListStack
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This code is the implementation of a stack backed by a Linked
 * List. This means that the methods from the StackInterface were implemented
 * in this code, where the class represents the Stack ADT. First, the ListNode
 * class is created and initializes several constructors based on the imput.
 * There are variables for the start index, as well as the size and a front
 * pointer that is first set to null where the constuctor sets the values 
 * accordingly.The constuctor sets the values accrodingly. The copy construct is 
 * used to copy the ListStack entered. The push first ensures that the list has not
 * pop checks to see if it is empty and then moves the starting place
 * up by one, and returns the removed value. Peek returns the value from 
 * pop but does not remove it from the stack. isEmpty checks to see if
 * it is empty, and size returns the size. Clear resets the stack, and the
 * toString returns a String representation of the stack.
 * 
 */
public class ListStack implements StackInterface {
    // Private instance variables
    private int size = 0;

    // ListNode Class
    private class ListNode {
        private int data;
        private ListNode next;

        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        public ListNode(int data) {
            this(data, null);
        }

        public ListNode() {
            this(0, null);
        }
    }

    // Private instance variables
    private ListNode front;

    // Constructor
    public ListStack() {
        front = null;
    }

    /*
     * Copy constuctor, sets the size back to 0 and checks
     * to see if it needs to be made empty. If not, it
     * sets the front to the front data, and then uses
     * a while loop to iterate through the copy pointer
     * until it copies all the nodes currectly and increments
     * size. It then sets the front equal to it.
     */
    ListStack(ListStack c) {
        size = 0;
        if (c == null) {
            front = null;
        } else {
            front = new ListNode(c.front.data);
            ListNode currentC = c.front;
            ListNode currentNode = front;
            while (currentC != null) {
                front.next = currentC.next;
                currentC = currentC.next;
                front = front.next;
                size += 1;
            }
            front = currentNode;
        }
    }

    /*
     * Adds the value passed by the user to the stack. It
     * first checks to see if the front is empty, to set it
     * directly equal to it and increase size. If not then it
     * runs a while loop until the next value is empty and then
     * adds it in the right spot, incrementing the size by 1 at
     * the end.
     */
    @Override
    public void push(int value) {
        if (front == null) {
            front = new ListNode(value);
            size += 1;
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
            size += 1;
        }

    }

    /*
     * Removes the value from the stack. It first checks to
     * see if the stack is empty, and then returns -1 if it
     * is. If it is only one value, then it returns the data,
     * and clears the list. If not, it iterates through until
     * current.next.next is not null, and once it is it sets the
     * current.next to null and saves the data from it. It then
     * returns the data.
     */
    @Override
    public int pop() {
        if (front == null)
            return -1;
        else if (front.next == null) {
            int ans = front.data;
            clear();
            return ans;
        }
        else {
            ListNode current = front;
            while (current.next.next != null) {
                current = current.next;
            }
            int ans = current.next.data;
            current.next = null;
            size -= 1;
            return ans;
        }
    }

    /*
     * First checks to see if it is empty, and if it is
     * then it returns -1. If not it returns the variable.
     */
    @Override
    public int peek() {
        if (front == null)
            return -1;
        else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            int ans = current.data;
            return ans;
        }
    }

    /*
     * It chekcs to see if the head points to null.
     * Returns true if empty and false if not.
     */
    @Override
    public boolean isEmpty() {
        if (front == null)
            return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /*
     * Returns the size of the stack.
     */
    @Override
    public void clear() {
        size = 0;
        front = null;
    }

    /*
     * String representation of the ListStack. Iterates
     * through all the values and adds them to a String.
     * Returns the String.
     */
    public String toString() {
        String ans = "{";
        ListNode current = front;
        while (current != null) {
            ans += Integer.toString(current.data);
            if (current.next != null) {
                ans += ",";
            }
            current = current.next;
        }
        return ans + "}";
    }

    /*
     * Equals method that returns true if the object passed
     * is a ListStack and all of the values are equal and the
     * length is equal. It first checks the instanceof to make
     * sure they are both the same and then uses a while loop
     * to check every value.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListStack))
            return false;
        ListStack check = (ListStack) o;
        ListNode checkNode = check.front;
        ListNode currentNode = front;
        while (currentNode != null) {
            if (checkNode.data != currentNode.data)
                return false;
            checkNode = checkNode.next;
            currentNode = currentNode.next;
        }
        return size == check.size;
    }
}