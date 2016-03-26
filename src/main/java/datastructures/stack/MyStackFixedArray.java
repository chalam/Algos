package datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by Lamuel on 3/25/2016.
 */

public class MyStackFixedArray<T> implements MyStack<T> {
    private T[] hold;
    private int top = 0;

    public MyStackFixedArray(int capacity) {
//        hold = new T[capacity];
        hold = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return (top  == 0);
    }

    public boolean isFull() {
        return (top  == hold.length);
    }

    public int size() {
        return top;
    }

    public void push(T item) {
        if(isFull()) {
            throw new StackOverflowError();
        }
        hold[top++] = item;
    }

    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return hold[--top];
    }

    public T peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return hold[top-1];
    }

    @Override
    public String toString() {
        return "MyStackFixedArray{" +
                "hold=" + Arrays.toString(hold) +
                ", top=" + top +
                '}';
    }
}