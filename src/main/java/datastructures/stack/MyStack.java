package datastructures.stack;

import java.util.EmptyStackException;

/**
 * Data Structures as part of ALgo Coursera
 * Created by Lamuel on 3/25/2016.
 */
public interface MyStack<T> {

    /**
     * Test if the stack is empty
     * @return true if empty
     */
    boolean isEmpty();

    /**
     * Test if the stack is full
     * @return true if full
     */
    boolean isFull();

    /**
     * Get the stack size
     * @return size
     */
    int size();

    /**
     * Add an item to the top of the stack
     * @param item added
     */
    void push(T item);

    /**
     *  Remove an item from the top of the stack
     * @return item removed
     * @throws EmptyStackException
     */
    T pop() throws EmptyStackException;

    /**
     * Get the the top of stack, but dont remove it
     * @return item to be removed
     * @throws EmptyStackException
     */
    T peek() throws EmptyStackException;
}