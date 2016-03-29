package datastructures.stack;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/25/2016.
 */
public class MyStackFixedArrayTest {
    @Test
    public void useStack() throws Exception {
        MyStack stack = new MyStackFixedArray<>(6);
        stack.push("to");
        stack.push("be");
        stack.push("or");
        stack.push("not");
        stack.push("to");
        stack.push("be");

        assertEquals("Stack failed", "be", stack.pop());
        assertEquals("Stack failed", "to", stack.pop());
        assertEquals("Stack failed", "not", stack.pop());
        assertEquals("Stack failed", 3, stack.size());

        assertEquals("Stack failed", "or", stack.pop());
        assertEquals("Stack failed", "be", stack.pop());

        assertEquals("Stack failed", "to", stack.peek());
        System.out.println(stack);
        assertEquals("Stack failed", "to", stack.pop());
        assertEquals("Stack failed", 0, stack.size());
    }

    @Test(expected=EmptyStackException.class)
    public void testPopEmptyStackException() {
        MyStack stack = new MyStackFixedArray<String>(1);
        assertTrue(stack.isEmpty());
        stack.pop();
    }

    @Test(expected=EmptyStackException.class)
    public void testPeekEmptyStackException() {
        MyStack stack = new MyStackFixedArray<String>(1);
        assertTrue("Stack should be empty", stack.isEmpty());
        stack.peek();
    }

    @Test(expected=StackOverflowError.class)
    public void testFullStackException() {
        MyStack stack = new MyStackFixedArray<Integer>(1);
        stack.push(100);
        assertTrue(stack.isFull());
        stack.push(100);
        stack.pop();
        assertTrue("Stack should be empty", stack.isEmpty());
    }
}