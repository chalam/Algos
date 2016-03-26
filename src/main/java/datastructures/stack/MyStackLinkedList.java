package datastructures.stack;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Lamuel on 3/25/2016.
 */
public class MyStackLinkedList<T> implements MyStack<T> {
    /*
        Class to hold linked list
     */
    private static class Node<T> {
        T item;
        Node<T> next;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("item=").append(item);
            sb.append(", next=").append(next);
            sb.append('}');
            return sb.toString();
        }
    }

    private Node<T> top = null;
    private int size = 0;

    /*
        Test if the stack is empty
    */
    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    /*
        Test if the stack is empty
    */
    @Override
    public boolean isFull() {
        return false;
    }

    /*
        Test if the stack is empty
    */
    @Override
    public int size() {
        return size;
    }

    /*
    add to the first of list
     */
    @Override
    public void push(T item) {
        Node<T> oldTop = top;
        top = new Node<T>();
        top.item = item;
        top.next = oldTop;
        size++;
    }

    /*
        Remove from the first of the list
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            EmptyStackException excep = new EmptyStackException();
            throw excep;
        }
        T item = (T) top.item;
        top = top.next;
        size--;
        return item;
    }

    /*
     Get the the top of stack, but dont remove it
     */
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            EmptyStackException excep = new EmptyStackException();
            throw excep;
        }
        return top.item;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyStackLinkedList{");
        sb.append("top=").append(top);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}