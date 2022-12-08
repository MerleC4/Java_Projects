
/*
 * AUTHOR: Merle Crutchfield
 * FILE: ListQueue.java
 * ASSIGNMENT: PA7 - ListQueue<E>
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This code is the implementation of a queue backed by a linked list.
 * This means that the methods from the QueueInterface were implemented
 * in this code, where the class represents the Queue ADT. This was also meant
 * to teach us about generics, so that the data type does not have to be an int
 * but can be a string, int, char, or anything else passed. First, the ListNode
 * class is created and initializes several constructors based on the imput.
 * There are variables for the start index, as well as the size and a front
 * pointer that is first set to null where the constuctor sets the values accrodingly. 
 * The copy construct is used to copy the ArrayQueue entered. The enqueue first 
 * ensures that the array has not run out of space, and then adds a value to the 
 * end of the array. The dequeue checks to see if it is empty and then moves the 
 * starting place up by one, and returns the removed value. Peek returns the value 
 * from dequeue but does not remove it from the queue. isEmpty checks to see if
 * it is empty, and size returns the size. Clear resets the queue, and the
 * toString returns a String representation of the queue.
 * 
 */
public class ListQueue<E> implements QueueInterface<E> {
    // Private instance variable
    private int size = 0;

    // ListNode Class
    private class ListNode {
        private E data;
        private ListNode next;

        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        public ListNode(E data) {
            this(data, null);
        }

        public ListNode() {
            this(null, null);
        }
    }

    // Private instance variables
    private ListNode front;

    // Constructor
    public ListQueue() {
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
    ListQueue(ListQueue c) {
        size = 0;
        if (c == null) {
            front = null;
        } else {
            front = new ListNode((E) c.front.data);
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
     * Adds the value passed by the user to the queue. It
     * first checks to see if the front is empty, to set it
     * directly equal to it and increase size. If not then it
     * runs a while loop until the next value is empty and then
     * adds it in the right spot, incrementing the size by 1 at
     * the end.
     */
    @Override
    public void enqueue(E value) {
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
     * Removes and returns the queue value. It first makes sure
     * that the queue isn't empty, and then saves the variable
     * and moves the list up a spot, then decreases the size and
     * returns the value.
     */
    @Override
    public E dequeue() {
        if (front == null)
            return null;
        else {
            E ans = front.data;
            front = front.next;
            size -= 1;
            return ans;
        }
    }

    /*
     * First checks to see if it is empty, and if it is
     * then it returns null. If not it returns the variable.
     */
    @Override
    public E peek() {
        if (front == null)
            return null;
        else {
            E ans = front.data;
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

    /*
     * Returns the size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Reinitializes all of the variables back to what
     * they were and moves pointer back to null.
     */
    @Override
    public void clear() {
        size = 0;
        front = null;
    }

    /*
     * String representation of the ListQueue. Iterates
     * through all the values and adds them to a String.
     * Returns the String.
     */
    public String toString() {
        String ans = "{";
        ListNode current = front;
        while (current != null) {
            ans += current.data;
            if (current.next != null) {
                ans += ",";
            }
            current = current.next;
        }
        return ans + "}";
    }

    /*
     * Equals method that returns true if the object passed
     * is a ListQueue and all of the values are equal and the
     * length is equal. It first checks the instanceof to make
     * sure they are both the same and then uses a while loop
     * to check every value.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListQueue))
            return false;
        ListQueue check = (ListQueue) o;
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