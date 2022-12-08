/*
 * Merle Crutchfield
 * 9/23/2020
 * There is no requirement for a file header comment for this 
 * assignment. Spend your time writing good testcases instead!
 */
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class Recursion {

	/**
	 * Write a recursive function that finds the index of s2 in s1. Do not use any
	 * string functions except for .length(), .equals(), and .substring(). Do not use
	 * any loops, or any data structures.
	 * @param s1
	 * @param s2
	 * @return Returns the index of the first time that
	 * 			s2 appears in s1 or -1 if s2 is not contained
	 * 			in s1.
	 */
	public static int indexOf(String s1, String s2) {
		// Out of bounds case
		if (s2.length() > s1.length())
			return -1;
		else {
			// Index found
			if (s1.substring(0, s2.length()).equals(s2))
				return 0;
			
			// Checks to see if out of bounds
			if (indexOf(s1.substring(1), s2) == -1)
				return -1;
			// There exists an index so it is recursively called again
			else
				return 1 + indexOf(s1.substring(1), s2);
		}
	}

	/**
	 * Write a recursive function that removes the first k even numbers
	 * from the stack. If there are less than k even elements in the stack,
	 * just remove all even elements. Do not use any loops or data structures
	 * other than the stack passed in as a parameter.
	 * @param stack
	 * @param k
	 * @return Returns the number of elements removed from the stack.
	 */
	
	public static int removeEvenNumbers(Stack<Integer> stack, int k) {
		// Empty stack ends code case
		if (stack.empty()) {
			return 0;
		}
		else {
			int removed = stack.pop();
			int total = 0;
			if (k > 0) {
				// Checking to see if even number
				if  (removed % 2 == 0)
					// Recursive call with total value increased
					return 1 + removeEvenNumbers(stack, k-1);
			}
			// Recursive call 
			total += removeEvenNumbers(stack, k);
			// Need to add the odd numbers all back
			if (removed % 2 == 1)
				stack.push(removed);
			return total;
		}
	}

	/**
	 * Write a recursive function that accepts an integer and
	 * returns a new number containing only the even digits, in the same
	 * order. If there are no even digits, return 0. Your function should
	 * work for positive or negative numbers. You are NOT allowed
	 * to use any data structures. You are not allowed to use Strings to
	 * solve this problem either.
	 * @param n
	 * @return The input with only the even digits remaining in the same
	 * order.
	 */
	public static int evenDigits(int n) {
		// Ensuring no negative numbers
		if (n < 0)
			return evenDigits(-n);
		// Edge case
		if (n == 0)
			return 0;
		else {
			// Gets the value recursively, and gives it a space on the end
			int sum = evenDigits(n / 10) * 10;
			int lastDigit = n % 10;
			// If even then it is added to the space
			if (lastDigit % 2 == 0)
				sum += lastDigit;
			// If odd then the space is removed from the end
			else
				sum /= 10;
			
			return sum;
		}
	}

	/**
	 * Write a recursive function that evaluates a Queue<Character> as a mathematical
	 * expression. This queue can have any of the following characters:
	 * { '(', ')', '+', '*'} or any single digit number. Evaluate this expression and
	 * return the result. For example, for the expression:
	 * "(((1+2)*(3+1))+(1*(2+2)))", each of these characters would be in the
	 * q. As you recursively evaluate characters from the expression, you will
	 * remove the characters from the q. After evaluating the above expression,
	 * you should return 16. You are guaranteed that there are NO two digit numbers,
	 * and that the expression is well formed (parenthesis match, etc...). Do not use any
	 * loops. Do not use any data structures besides the q passed in as a parameter.
	 * @param q
	 * @return The result of the mathematical expression.
	 */	
	public static int evaluate(Queue<Character> q) {
		// Grab first element
		char headVal = q.poll();
		// If not digit then recursively call evalute until it is
		if (!Character.isDigit(headVal)) {
			int valOne, valTwo;
			char operator, endPiece;
			// First value
			valOne = evaluate(q);
			// Operator always comes afterwards
			operator = q.poll();
			// Second value will recursively call until an int
			valTwo = evaluate(q);
			// Get rid of close braces
			endPiece = q.poll();
			// Check which operator is used
			switch (operator) {
				case '+':
					return valOne + valTwo;
				case '*':
					return valOne * valTwo;
				default:
					return 0;
			}
		}
		// If digit then return val
		else
			return Character.getNumericValue(headVal);
	} 
	  
	
	/**
	 * Write a recursive function that accepts a stack of integers and
	 * replaces each int with two copies of that integer. For example,
	 * calling repeatStack and passing in a stack of { 1, 2, 3} would change
	 * the stack to hold { 1, 1, 2, 2, 3, 3}. Do not use any loops. Do not use
	 * any data structures other than the stack passed in as a parameter.
	 * @param stack
	 */
	public static void repeatStack(Stack<Integer> stack) {
		// Runs until empty
		if (!stack.isEmpty()) {
			// Pulls out value
			int val = stack.pop();
			repeatStack(stack);
			// Puts the value in twice after recursive call
			stack.push(val);
			stack.push(val);
		}
	}

	/**
	 * Write a recursive function that accepts a Queue<Integer>. It
	 * should change every int in this queue to be double its original
	 * value. You may NOT use loops or any other data structures besides
	 * the queue passed in as a parameter. You may use a helper function.
	 * @param q
	 */
	public static void doubleElements(Queue<Integer> q) {
		// Calls helper function
		doubleElementsHelper(q, q.size());
	}
	
	public static void doubleElementsHelper(Queue<Integer> q, int size) {
		// Running until no more element needs to be doubled
		if (size > 0) {
			// Adds doubled element to the end
			q.add(q.poll()*2);
			// recursive call where size decreases by 1
			doubleElementsHelper(q, size-1);
		}
	}
	
}
