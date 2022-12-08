/*
 * Merle Crutchfield
 * 9/23/2020
 * This file will hold all of your testcases. Remember, to run all
 * of your tests, right-click on 'RunTests.java' and select 'Run As' ->
 * JUnit Test.
 */
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class RecursionTestClass {
	
	/*
	 * Here I have provided an example of one of the tests that I
	 * would write. For each of your tests, leave a brief comment
	 * above the test specifying its purpose. For example, for this
	 * test, I might write, "indexOf_test1 tests when s2 is not a 
	 * substring of s1. This should return -1."
	 */
	@Test
	public void test_indexOf_test1() {
	    int result = Recursion.indexOf("Hello", "World");
	    System.out.println("indexOf(Hello, World), got " + result);
	    assertEquals(-1, result);
	}
	
	/*
	 * This tests when s2 is the final element of s1. It should
	 * return 4.
	 */
	@Test
	public void test_indexOf_test2() {
	    int result = Recursion.indexOf("Hello", "o");
	    System.out.println("indexOf(Hello, o), got " + result);
	    assertEquals(4, result);
	}
	
	/*
	 * This tests when s2 is the first elemment/same as s1. It should
	 * return 0.
	 */
	@Test
	public void test_indexOf_test3() {
	    int result = Recursion.indexOf("Hello", "Hello");
	    System.out.println("indexOf(Hello, Hello), got " + result);
	    assertEquals(0, result);
	}
	
	/*
	 * This tests when there is a mix of even and odd numbers. It 
	 * should return 24.
	 */
	@Test
	public void test_evenDigits_test1() {
		int result = Recursion.evenDigits(12345);
	    System.out.println("evenDigits of (12345), got " + result);
	    assertEquals(24, result);
	}
	
	/*
	 * This tests when there is a mix of even and odd numbers and
	 * is negative. It should return 24.
	 */
	@Test
	public void test_evenDigits_test2() {
		int result = Recursion.evenDigits(-12345);
	    System.out.println("evenDigits of (-12345), got " + result);
	    assertEquals(24, result);
	}
	
	/*
	 * This tests when there is all odd numbers. It should return 0.
	 */
	@Test
	public void test_evenDigits_test3() {
		int result = Recursion.evenDigits(135);
	    System.out.println("evenDigits of (135), got " + result);
	    assertEquals(0, result);
	}
	
	/*
	 * This tests when there is an even number starting with 0. It 
	 * should return 2.
	 */
	@Test
	public void test_evenDigits_test4() {
		int result = Recursion.evenDigits(02);
	    System.out.println("evenDigits of (02), got " + result);
	    assertEquals(2, result);
	}
	
	/*
	 * This tests when there is all even numbers. It should return 26.
	 */
	@Test
	public void test_evenDigits_test5() {
		int result = Recursion.evenDigits(26);
	    System.out.println("evenDigits of (26), got " + result);
	    assertEquals(26, result);
	}
	
	/*
	 * This tests when 0 is entered. It should return 0. 
	 */
	@Test
	public void test_evenDigits_test6() {
		int result = Recursion.evenDigits(0);
	    System.out.println("evenDigits of (0), got " + result);
	    assertEquals(0, result);
	}
	
	/*
	 * This tests a stack of even and odd numbers where there is more
	 * even numbers than should be removed. It should return 2 removed.
	 */
	@Test
	public void test_removeEvenNumbers_test1() {
	    Stack<Integer> stack = new Stack<>();
	    stack.push(6); stack.push(5); stack.push(4);
	    stack.push(3); stack.push(2);
	    int n = Recursion.removeEvenNumbers(stack, 2);
	    System.out.println("removeEvenNumbers of (2), got " + n);
		assertEquals(2, n);
	}
	
	/*
	 * This tests when the stack has even and odd numbers and is
	 * told to remove more numbers than there are present. It 
	 * should return 4.
	 */
	@Test
	public void test_removeEvenNumbers_test2() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2); stack.push(3); stack.push(4); stack.push(55);
		stack.push(6); stack.push(17); stack.push(8);
		int n = Recursion.removeEvenNumbers(stack, 5);
		System.out.println("removeEvenNumbers of (5), got " + n);
		assertEquals(4, n);
	}
	
	/*
	 * This tests when the stack only has odd numbers and so no
	 * numbers are removed. It should return 0.
	 */
	@Test
	public void test_removeEvenNumbers_test3() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); stack.push(3); stack.push(5); stack.push(7);
		stack.push(9); stack.push(11); stack.push(13);
		int n = Recursion.removeEvenNumbers(stack, 3);
		System.out.println("removeEvenNumbers of (3), got " + n);
		assertEquals(0, n);
	}
	
	/*
	 * This tests when there are only even numbers and all of them
	 * should be removed since the number entered is the exact 
	 * count. It should return 7.
	 */
	@Test
	public void test_removeEvenNumbers_test4() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2); stack.push(4); stack.push(6); stack.push(8);
		stack.push(10); stack.push(12); stack.push(40);
		int n = Recursion.removeEvenNumbers(stack, 7);
		System.out.println("removeEvenNumbers of (7), got " + n);
		assertEquals(7, n);
	}
	
	/*
	 * This tests when k is less than 0 which should not error
	 * out. Instead, it should return 0.
	 */
	@Test
	public void test_removeEvenNumbers_test5() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2); stack.push(4); stack.push(6); stack.push(8);
		stack.push(10); stack.push(12); stack.push(40);
		int n = Recursion.removeEvenNumbers(stack, -1);
		System.out.println("removeEvenNumbers of (-1), got " + n);
		assertEquals(0, n);
	}
	
	/*
	 * This simplty tests addition inside of parenthesis. It should
	 * return 2.
	 */
	@Test
	public void test_evaluate_test1() {
		Queue<Character> queue = new LinkedList<>();
		String expr = "(1+1)";
		 for (char ch: expr.toCharArray()) {
	        	queue.add(ch);
	        }
        int ans = Recursion.evaluate(queue);
        System.out.println("evaluate of " + expr + ", got " + ans);
        assertEquals(2, ans);
	}
	
	/*
	 * This tests when the brackets, parenthesis, addition, and
	 * multiplication are all used. It should return 10.
	 */
	@Test
	public void test_evaluate_test2() {
		Queue<Character> queue = new LinkedList<>();
		String expr = "{(1*2)+(4+4)}";
		 for (char ch: expr.toCharArray()) {
	        	queue.add(ch);
	        }
        int ans = Recursion.evaluate(queue);
        System.out.println("evaluate of " + expr + ", got " + ans);
        assertEquals(10, ans);
	}
	
	/*
	 * This case tests parenthesis inside more parenthesis
	 * before doing the operations. This should return 32.
	 */
	@Test
	public void test_evaluate_test3() {
		String expr = "(((4+2)*(3+1))+(2*(2+2)))";
        Queue<Character> q = new LinkedList<Character>();
        for (char ch: expr.toCharArray()) {
        	q.add(ch);
        }
        int ans = Recursion.evaluate(q);
        System.out.println("evaluate of " + expr + ", got " + ans);
        assertEquals(32, ans);
	}
	
	/*
	 * This tests when the stack is simply 1,2,3 to ensure it
	 * works properly. It should return a stack of 1,1,2,2,3,3.
	 */
	@Test
	public void test_repeatStack_test1() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); stack.push(2); stack.push(3);
		// Same as above but not altered for printing it out
		Stack<Integer> stackPrint = new Stack<Integer>();
		stackPrint.push(1); stackPrint.push(2); stackPrint.push(3);
		// Correct value for checking solution
		Stack<Integer> ans = new Stack<Integer>();
		ans.push(1); ans.push(1); ans.push(2); ans.push(2);
		ans.push(3); ans.push(3);
		
		Recursion.repeatStack(stack);
		System.out.println("repeatStack of (" + stackPrint + "), got " + stack);
		assertEquals(stack, ans);
	}
	
	/*
	 * This tests when the stack enertered is empty to make sure
	 * the edge cases work. It should return an empty stack.
	 */
	@Test
	public void test_repeatStack_test2() {
		Stack<Integer> stack = new Stack<Integer>();
		// Same as above but not altered for printing it out
		Stack<Integer> stackPrint = new Stack<Integer>();
		// Correct value for checking solution
		Stack<Integer> ans = new Stack<Integer>();
		
		Recursion.repeatStack(stack);
		System.out.println("repeatStack of (" + stackPrint + "), got " + stack);
		assertEquals(stack, ans);
	}
	
	/*
	 * This tests when the stack has negative numbers as well as
	 * 0. It should return 0,0,-10,-10,-5,-5.
	 */
	@Test
	public void test_repeatStack_test3() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0); stack.push(-10); stack.push(-5);
		// Same as above but not altered for printing it out
		Stack<Integer> stackPrint = new Stack<Integer>();
		stackPrint.push(0); stackPrint.push(-10); stackPrint.push(-5);
		// Correct value for checking solution
		Stack<Integer> ans = new Stack<Integer>();
		ans.push(0); ans.push(0); ans.push(-10); ans.push(-10);
		ans.push(-5); ans.push(-5);
		
		Recursion.repeatStack(stack);
		System.out.println("repeatStack of (" + stackPrint + "), got " + stack);
		assertEquals(stack, ans);
	}
	
	/*
	 * This tests a queue that contains 1,2,3 to ensure that it works
	 * properly. It should return a queue of 2,4,6.
	 */
	@Test
	  public void test_doubleElements_test1() {
	        Queue<Integer> queue = new LinkedList<>();
	        queue.add(1); queue.add(2); queue.add(3);
	        // Same as above but not altered for printing it out
	        Queue<Integer> queuePrint = new LinkedList<>();
	        queuePrint.add(1); queuePrint.add(2); queuePrint.add(3);
	        // Correct value for checking solution
	        Queue<Integer> ans = new LinkedList<>();
	        ans.add(2); ans.add(4); ans.add(6);
	        Recursion.doubleElements(queue);
	        System.out.println("doubleElements of " + queuePrint + "), got " + queue);
	        assertEquals(queue, ans);
		}
	
	/*
	 * This tests a queue that contains negative numbers as well as 0. It
	 * should return a queue of -2,-6,0.
	 */
	@Test
	  public void test_doubleElements_test2() {
	        Queue<Integer> queue = new LinkedList<>();
	        queue.add(-1); queue.add(-3); queue.add(0);
	        // Same as above but not altered for printing it out
	        Queue<Integer> queuePrint = new LinkedList<>();
	        queuePrint.add(-1); queuePrint.add(-3); queuePrint.add(0);
	        // Correct value for checking solution
	        Queue<Integer> ans = new LinkedList<>();
	        ans.add(-2); ans.add(-6); ans.add(0);
	        Recursion.doubleElements(queue);
	        System.out.println("doubleElements of " + queuePrint + "), got " + queue);
	        assertEquals(queue, ans);
		}
	
	/*
	 * This tests an empty queue to make sure that the edge cases work. It
	 * should return an empty queue.
	 */
	@Test
	  public void test_doubleElements_test3() {
	        Queue<Integer> queue = new LinkedList<>();
	        // Same as above but not altered for printing it out
	        Queue<Integer> queuePrint = new LinkedList<>();
	        // Correct value for checking solution
	        Queue<Integer> ans = new LinkedList<>();
	        Recursion.doubleElements(queue);
	        System.out.println("doubleElements of " + queuePrint + "), got " + queue);
	    assertEquals(queue, ans);
	}
}

