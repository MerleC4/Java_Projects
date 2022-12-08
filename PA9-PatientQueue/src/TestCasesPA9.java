import org.junit.Test;

public class TestCasesPA9 {

    @Test
    public void testEnqueue() {
        System.out.println("\n\n\nTestEnqueue:");
        PatientQueue queue = new PatientQueue();
        queue.enqueue("Avram", 9);
        queue.enqueue("Merle", 2);
        Patient sarah = new Patient("Sarah", 5);
        queue.enqueue(sarah);
        System.out.println(queue);
    }

    @Test
    public void testEnqueueExample() {
        System.out.println("\n\n\nTestEnqueueExample:");
        PatientQueue queue = new PatientQueue();
        queue.enqueue("Anat", 4);
        queue.enqueue("Ben", 9);
        queue.enqueue("Sasha", 8);
        queue.enqueue("Wu", 7);
        System.out.println("Your queue: " + queue);
        System.out.println("Bens queue: {Anat (4),Wu (7),Sasha (8),Ben (9)}\n");
        queue.enqueue("Rein", 6);
        queue.enqueue("Ford", 2);
        queue.enqueue("Eve", 3);
        System.out.println("Your queue: " + queue);
        System.out.println(
                "Bens queue: {Ford (2),Rein (6),Eve (3),Ben (9),Wu (7),Sasha (8),Anat (4)}");

    }

    @Test
    public void testDequeueExample() throws Exception {
        System.out.println("\n\n\nTestDequeueExample");
        PatientQueue queue = new PatientQueue();
        queue.enqueue("Anat", 4);
        queue.enqueue("Ben", 9);
        queue.enqueue("Sasha", 8);
        queue.enqueue("Wu", 7);
        queue.enqueue("Rein", 6);
        queue.enqueue("Ford", 2);
        queue.enqueue("Eve", 3);
        System.out.println("Current queue: " + queue);
        queue.dequeue();
        System.out.println("\nAfter dequeue: " + queue);
        System.out.println(
                "Bens dequeue: {Eve (3),Rein (6),Anat (4),Ben (9),Wu (7),Sasha (8)}");

    }

    @Test
    public void testChangePriority() throws Exception {
        System.out.println("\n\n\nTestChangePriorityExample");
        PatientQueue queue = new PatientQueue();
        queue.enqueue("Anat", 4);
        queue.enqueue("Ben", 9);
        queue.enqueue("Sasha", 8);
        queue.enqueue("Wu", 7);
        queue.enqueue("Rein", 6);
        queue.enqueue("Ford", 2);
        queue.enqueue("Eve", 3);
        queue.dequeue();
        System.out.println("Current queue: " + queue);
        queue.changePriority("Eve", 10);
        System.out.println("\nNew queue: " + queue);
        System.out.println(
                "Bens queue: {Anat (4),Rein (6),Sasha (8),Ben (9),Wu (7),Eve (10)}");
    }

    @Test(expected = Exception.class)
    public void testException() throws Exception {
        System.out.println("\n\n\nTestEverything");
        PatientQueue queue = new PatientQueue();
        queue.enqueue("Merle", 4);
        queue.enqueue("Ben", 9);
        queue.enqueue("Avram", 8);
        queue.enqueue(new Patient("Sarah", 7));
        queue.enqueue("Chase", 6);
        queue.enqueue("Drake", 2);
        queue.enqueue(new Patient("Keegan", 3));
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(queue.peek());
        queue.clear();
        System.out.println(queue.isEmpty());
        queue.dequeue();

    }

    @Test
    public void testMerle() {
        System.out.println("\n\n\nTestMerle");
        PatientQueue queue = new PatientQueue();
        queue.enqueue("Merle", 4);
        queue.enqueue("Ben", 9);
        queue.enqueue("Avram", 8);
        queue.enqueue(new Patient("Sarah", 7));
        queue.enqueue("Chase", 6);
        queue.enqueue("Drake", 2);
        queue.enqueue(new Patient("Keegan", 3));
        queue.enqueue("Cum", 4);
        queue.enqueue("Jen", 9);
        queue.enqueue("E", 1);
        queue.enqueue(new Patient("Ieegan", 3));
        System.out.println(queue);
        System.out.println(queue.size());
    }

}
