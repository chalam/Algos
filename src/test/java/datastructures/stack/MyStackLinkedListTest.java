package datastructures.stack;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/25/2016.
 */
public class MyStackLinkedListTest {

    @Test
    public void useStack() throws Exception {
        MyStack stack = new MyStackLinkedList<String>();
        stack.push("to");
        System.out.println(stack);

        stack.push("be");
        System.out.println(stack);

        stack.push("or");
        System.out.println(stack);

        stack.push("not");
        System.out.println(stack);

        stack.push("to");
        System.out.println(stack);

        stack.push("be");
        System.out.println(stack);

        assertEquals("Stack failed", stack.pop(), "be");
        System.out.println(stack);

        assertEquals("Stack failed", stack.pop(), "to");
        System.out.println(stack);

        assertEquals("Stack failed", stack.pop(), "not");
        System.out.println(stack);

        assertEquals("Stack failed", stack.size(), 3);

        assertEquals("Stack failed", stack.pop(), "or");
        System.out.println(stack);

        assertEquals("Stack failed", stack.pop(), "be");
        System.out.println(stack);

        assertEquals("Stack failed", stack.peek(), "to");
        System.out.println(stack);

        assertEquals("Stack failed", stack.pop(), "to");
        System.out.println(stack);

        assertEquals("Stack failed", stack.size(), 0);
    }

    @Test(expected=EmptyStackException.class)
    public void testPopEmptyStackException() {
        MyStack stack = new MyStackLinkedList<String>();
        assertTrue(stack.isEmpty());
        assertEquals("Stack failed", stack.isFull(), false);
        stack.pop();
    }

    @Test(expected=EmptyStackException.class)
    public void testPeekEmptyStackException() {
        MyStack stack = new MyStackLinkedList<String>();
        assertTrue(stack.isEmpty());
        stack.peek();
    }

}