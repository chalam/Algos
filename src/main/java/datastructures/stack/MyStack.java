package datastructures.stack;

import java.util.EmptyStackException;

/**
 * Data Structures as part of ALgo Coursera
 * Created by Lamuel on 3/25/2016.
 */
public interface MyStack<T> {
    /*
        Test if the stack is empty
     */
    boolean isEmpty();

    /*
    Test if the stack is full
    */
    boolean isFull();

    /*
        Get the stack size
    */
    int size();

    /*
    Add an item to the top of the stack
     */
    void push(T item);

    /*
        Remove an item from the top of the stack
        */
    T pop() throws EmptyStackException;

    /*
     Get the the top of stack, but dont remove it
    */
    T peek() throws EmptyStackException;
}