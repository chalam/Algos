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

        assertEquals("Stack failed", stack.pop(), "be");
        assertEquals("Stack failed", stack.pop(), "to");
        assertEquals("Stack failed", stack.pop(), "not");
        assertEquals("Stack failed", stack.size(), 3);


        assertEquals("Stack failed", stack.pop(), "or");
        assertEquals("Stack failed", stack.pop(), "be");

        assertEquals("Stack failed", stack.peek(), "to");
        System.out.println(stack);
        assertEquals("Stack failed", stack.pop(), "to");
        assertEquals("Stack failed", stack.size(), 0);


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
        assertTrue(stack.isEmpty());
        stack.peek();
    }

    @Test(expected=StackOverflowError.class)
    public void testFullStackException() {
        MyStack stack = new MyStackFixedArray<Integer>(1);
        stack.push(100);
        assertTrue(stack.isFull());
        stack.push(100);
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}