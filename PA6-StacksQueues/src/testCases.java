
import org.junit.Test;

public class testCases {

    @Test
    public void test1() {
        ArrayStack array = new ArrayStack();
        array.push(10);
        array.push(25);
        System.out.println(array);
        System.out.println(array.pop());
        array.push(20);
        System.out.println(array.peek());
        System.out.println(array);
        System.out.println(array.pop());
        System.out.println(array.pop());
        System.out.println(array.pop());
        System.out.println(array.peek());
        array.push(10);
        array.push(20);
        array.push(30);
        array.push(40);
        System.out.println(array.isEmpty());
        System.out.println(array.size());
        ArrayStack array2 = array;
        System.out.println(array2);
        System.out.println(array.equals(array2));
        array.clear();
        System.out.println(array.isEmpty());
        System.out.println(array);
        System.out.println(array2);
        ArrayStack array3 = new ArrayStack();
        array3.push(1);
        System.out.println(array.equals(array3));
        System.out.println("\n");
    }

    @Test
    public void test2() {
        ListStack array = new ListStack();
        array.push(10);
        array.push(25);
        System.out.println(array);
        System.out.println(array.pop());
        array.push(20);
        System.out.println(array.peek());
        System.out.println(array.size());
        System.out.println(array);
        System.out.println(array.pop());
        System.out.println(array.pop());
        System.out.println(array.pop());
        System.out.println(array.peek());
        array.push(10);
        array.push(20);
        array.push(30);
        array.push(40);
        System.out.println(array.isEmpty());
        System.out.println(array.size());
        ListStack array2 = array;
        System.out.println(array2);
        System.out.println(array.equals(array2));
        array.clear();
        System.out.println(array.isEmpty());
        System.out.println(array);
        System.out.println(array2);
        ListStack array3 = new ListStack();
        array3.push(1);
        System.out.println(array.equals(array3));
        System.out.println("\n");
    }

    @Test
    public void test3() {
        ArrayQueue array = new ArrayQueue();
        array.enqueue(10);
        array.enqueue(25);
        System.out.println(array);
        System.out.println(array.dequeue());
        array.enqueue(20);
        System.out.println(array.peek());
        System.out.println(array.size());
        System.out.println(array);
        System.out.println(array.dequeue());
        System.out.println(array.dequeue());
        System.out.println(array.dequeue());
        System.out.println(array.peek());
        array.enqueue(10);
        array.enqueue(20);
        array.enqueue(30);
        array.enqueue(40);
        System.out.println(array.isEmpty());
        System.out.println(array.size());
        ArrayQueue array2 = array;
        System.out.println(array2);
        System.out.println(array.equals(array2));
        array.clear();
        System.out.println(array.isEmpty());
        System.out.println(array);
        System.out.println(array2);
        ArrayQueue array3 = new ArrayQueue();
        array3.enqueue(1);
        System.out.println(array.equals(array3));
        System.out.println("\n");
    }

    @Test
    public void test4() {
        ListQueue array = new ListQueue();
        array.enqueue(10);
        array.enqueue(25);
        System.out.println(array);
        System.out.println(array.dequeue());
        array.enqueue(20);
        System.out.println(array.peek());
        System.out.println(array.size());
        System.out.println(array);
        System.out.println(array.dequeue());
        System.out.println(array.dequeue());
        System.out.println(array.dequeue());
        System.out.println(array.peek());
        array.enqueue(10);
        array.enqueue(20);
        array.enqueue(30);
        array.enqueue(40);
        System.out.println(array.isEmpty());
        System.out.println(array.size());
        ListQueue array2 = array;
        System.out.println(array2);
        System.out.println(array.equals(array2));
        array.clear();
        System.out.println(array.isEmpty());
        System.out.println(array);
        System.out.println(array2);
        ListQueue array3 = new ListQueue();
        array3.enqueue(1);
        System.out.println(array.equals(array3));
        System.out.println("\n");
    }
}