package datastructures.stack;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lamuel on 3/25/2016.
 */
public class MyStackResizingArrayTest {
    @Test
    public void useStack() throws Exception {
        MyStack stack = new MyStackResizingArray<>();
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

        assertEquals("Stack failed", stack.pop(), "to");
        System.out.println(stack);

        assertEquals("Stack failed", stack.size(), 0);

    }

    @Test(expected=EmptyStackException.class)
    public void testPopEmptyStackException() {
        MyStack stack = new MyStackResizingArray();
        assertTrue(stack.isEmpty());
        stack.pop();
    }

    @Test(expected=EmptyStackException.class)
    public void testPeekEmptyStackException() {
        MyStack stack = new MyStackResizingArray();
        assertTrue(stack.isEmpty());
        stack.peek();
    }

}