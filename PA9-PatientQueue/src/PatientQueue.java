/*
 * AUTHOR: Merle Crutchfield
 * FILE: PatientQueue.java
 * ASSIGNMENT: PA9 - PatientQueue
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This assignment is meant to practice implementing our own data structure,
 * being a priority queue using a bianry minimum heap. We implement a class, which is
 * Patient, that has a patient object that holds their name and their priority for
 * our hospital simulation. We then add each of the patients into a queue, and then
 * from there can dequeue or call other methods. We use a binary heap, which is 
 * an array where each element has two children indexes of i * 2 and i * 2 + 1. We
 * start our array at 1 for simplicity. Our PatientQueue constructor initializes a
 * new empty queue of length 10. We have 2 enqueue methods that add the Patient 
 * into the queue and then bubbleUp. Dequeue moves the frontmost person and moves
 * the end to the front, and then bubblesDown. Peek just returns the front patient.
 * ChangePriority modifies the priority, and then bubblesUp or bubblesDown 
 * accordingly. IsEmpty checks to see if it is empty, size returns size, clear clears
 * the queue, and the toString prints it out easier.
 * 
 */

public class PatientQueue {
    // Private fields
    private Patient[] queue;
    private int size;
    private int index;
    private int capacity = 10;

    /*
     * Constructor for the queue. Creates a new queue
     * of capacity 10, and sets the size to 0 and
     * index to 1.
     */
    public PatientQueue() {
        queue = new Patient[capacity];
        size = 0;
        index = 1;
    }

    /*
     * This enqueue method takes a name and priority,
     * and makes a new patient. It then checks to make sure
     * the size is not too small, and then adds the patient
     * to the queue. It calls bubbleUp to move the patient
     * accordingly.
     */
    public void enqueue(String name, int priority) {
        Patient temp = new Patient(name, priority);
        if (index == capacity)
            resize();
        queue[index] = temp;
        index += 1;
        size += 1;
        bubbleUp(index - 1);
    }

    /*
     * This enqueue method takes a patient. It checks to make sure
     * the size is not too small, and then adds the patient
     * to the queue. It calls bubbleUp to move the patient
     * accordingly.
     */
    public void enqueue(Patient patient) {
        if (index == capacity)
            resize();
        queue[index] = patient;
        index += 1;
        size += 1;
        bubbleUp(index - 1);
    }

    /*
     * This method takes the starting point as the parameter. It 
     * then starts at the index, and divides by two until it 
     * reaches the first index, or until it no longer needs to
     * move up in the queue. It uses a while loop to do this, and
     * an if statement to compare the values.
     */
    public void bubbleUp(int start) {
        int curr = start;
        int check = (start) / 2;
        while (curr > 1) {
            if (queue[curr].priority < queue[check].priority
                    || (queue[curr].priority == queue[check].priority)
                            && queue[curr].name.charAt(0) < queue[check].name
                                    .charAt(0)) {
                Patient temp = queue[curr];
                queue[curr] = queue[check];
                queue[check] = temp;
                check /= 2;
                curr /= 2;
            } else {
                curr = 0;
            }
        }
    }

    /*
     * This method takes the starting point as the parameter. It
     * then starts at that index, finds the indexes of the two
     * children and makes sure that they are in range. It then checks
     * to see which of the two children has the higher priority, and
     * then checks that child with the parent. If they should be
     * switched, they are, and the loop continues. If not, then
     * the loop breaks.
     */
    public void bubbleDown(int start) {
        int curr = start;
        while (curr < size) {
            int first = curr * 2;
            int second = curr * 2 + 1;
            int check;
            if (curr * 2 + 1 >= index)
                check = first;
            else if (queue[first].priority < queue[second].priority
                    || (queue[first].priority == queue[second].priority)
                            && queue[first].name.charAt(0) < queue[second].name
                                    .charAt(0))
                check = first;
            else
                check = second;
            if (queue[curr].priority > queue[check].priority
                    || (queue[curr].priority == queue[check].priority)
                            && queue[curr].name.charAt(0) > queue[check].name
                                    .charAt(0)) {
                Patient temp = queue[curr];
                queue[curr] = queue[check];
                queue[check] = temp;
                curr = check;
            } else {
                curr = size;
            }
        }
    }

    /*
     * This method is used to resize the array when it gets
     * too many elements. It changes the capacity, and then
     * adds each of the elements to a new array of double
     * the capacity, and sets the old array equal to it.
     */
    public void resize() {
        capacity *= 2;
        Patient[] newQueue = new Patient[2 * capacity];
        for (int i = 1; i <= size; i++)
            newQueue[i] = queue[i];
        queue = newQueue;
    }

    /*
     * This method is used to dequeue the queue. It gets the
     * name of the first person to return. It then swaps the
     * end element to the front, lowers the index and size,
     * and then bubbles down.
     */
    public String dequeue() throws Exception {
        if (size == 0)
            throw new Exception("The size must be at least 1 to dequeue.");
        String name = queue[1].name;
        queue[1] = queue[index - 1];
        index -= 1;
        size -= 1;
        bubbleDown(1);
        return name;
    }

    /*
     * This method gets the name from the front of the queue
     * and returns it.
     */
    public String peek() throws Exception {
        if (size == 0)
            throw new Exception("The size must be at least 1 to get the name.");
        String name = queue[1].name;
        return name;
    }

    /*
     * This method gets the priority from the front of the
     * queue and then returns it.
     */
    public int peekPriority() throws Exception {
        if (size == 0)
            throw new Exception(
                    "The size must be at least 1 for this to work.");
        int val = queue[1].priority;
        return val;
    }

    /*
     * This method takes a name and the newPriority as
     * arguments. It runs a while loop until it finds
     * the patient that has the matching name, and then
     * switches their priority. It then calls bubbleUp
     * or bubbleDown based on the change of priority.
     */
    public void changePriority(String name, int newPriority) {
        boolean changed = false;
        int i = 1;
        while (!changed) {
            if (queue[i].name.equals(name)) {
                int val = queue[i].priority;
                queue[i].priority = newPriority;
                changed = true;
                if (val < newPriority)
                    bubbleDown(i);
                else
                    bubbleUp(i);
            }
            else if (i == index)
                changed = true;
            else
                i += 1;
        }
    }

    /*
     * This method checks to see if there are any elements
     * in the queue and returns the answer accordingly.
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /*
     * This method returns the size of the queue.
     */
    public int size() {
        return size;
    }

    /*
     * This method empties the queue, and sets the
     * size, index, and capacity back to the starting
     * values.
     */
    public void clear() {
        capacity = 10;
        queue = new Patient[capacity];
        size = 0;
        index = 1;
    }

    /*
     * This method returns a string of the queue in a more
     * accessible way to read.
     */
    public String toString() {
        String ans = "{";
        for (int i = 1; i <= size; i++) {
            ans += queue[i].toString();
            if (i != size)
                ans += ", ";
        }
        return ans + "}";
    }
}