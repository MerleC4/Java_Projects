import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/*
 * AUTHOR: Merle Crutchfield
 * FILE: MaxPQ.java
 * ASSIGNMENT: PA10 - WikiRacer
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This class is used to create a binary max heap,
 * which is similar to the binary min heap from our last PA,
 * but instead the higher priority is the larger numbers. The
 * data structure is a list of lists of strings, as each of
 * the ladders are just a list of strings. When we enqueue,
 * we add the ladder to the next available spot in the queue
 * and then bubble up to make sure that the queue is in order.
 * When we dequeue, we remove the element at the head, move the
 * element at the end to the front, and then call bubbleDown to
 * ensure that the queue is in the correct order again. There
 * are several other methods that return if it is empty, the size,
 * and resize the queue if we run out of spots.
 */

public class MaxPQ {
	// Private fields
    private Ladder[] queue;
    private int size;
    private int index;
    private int capacity = 10;

    /*
     * Constructor for the queue. Creates a new queue
     * of capacity 10, and sets the size to 0 and
     * index to 1.
     */
    public MaxPQ() {
        queue = new Ladder[capacity];
        size = 0;
        index = 1;
    }

    /*
     * This enqueue method takes a name and priority,
     * and makes a new Ladder. It then checks to make sure
     * the size is not too small, and then adds the Ladder
     * to the queue. It calls bubbleUp to move the Ladder
     * accordingly.
     */
    public void enqueue(List<String> name, int priority) {
        Ladder temp = new Ladder(name, priority);
        if (index == capacity)
            resize();
        queue[index] = temp;
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
            if (queue[curr].priority > queue[check].priority) {
                Ladder temp = queue[curr];
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
        while (curr * 2 < size) {
            int first = curr * 2;
            int second = curr * 2 + 1;
            int check;
            if (curr * 2 + 1 >= index)
                check = first;
            else if (queue[first].priority > queue[second].priority)
                check = first;
            else
                check = second;
            if (queue[curr].priority < queue[check].priority) {
                Ladder temp = queue[curr];
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
        Ladder[] newQueue = new Ladder[2 * capacity];
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
    public List<String> dequeue() {
    	List<String> name = queue[1].name;
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
    public List<String> peek() {
    	List<String> name = queue[1].name;
        return name;
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
        queue = new Ladder[capacity];
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
    
    /*
     * Ladder class, the queue is made up of Ladders, which
     * are constructed below.
     */
    public class Ladder {
    	// Private fields
        public List<String> name;
        public int priority;

        /*
         * Constructor for the ladder. Sets the name to
         * the list of strings passed and the priority
         * likewise.
         */
        public Ladder(List<String> name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        /*
         * Prints out the Ladder in a readable way, used
         * when debugging.
         */
		public String toString() {
			return name + " (" + priority + ")";
		}

    }
}
