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

        assertEquals("Stack failed", "be", stack.pop());
        System.out.println(stack);

        assertEquals("Stack failed", "to", stack.pop());
        System.out.println(stack);

        assertEquals("Stack failed", "not", stack.pop());
        System.out.println(stack);

        assertEquals("Stack failed", 3, stack.size());

        assertEquals("Stack failed", "or", stack.pop());
        System.out.println(stack);

        assertEquals("Stack failed", "be", stack.pop());
        System.out.println(stack);

        assertEquals("Stack failed", "to", stack.peek());
        assertEquals("Stack failed", "to", stack.pop());

        System.out.println(stack);

        assertEquals("Stack failed", 0, stack.size());
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
        assertTrue("Stack should be empty", stack.isEmpty());
        stack.peek();
    }

}