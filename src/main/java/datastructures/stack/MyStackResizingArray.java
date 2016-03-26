package datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by Lamuel on 3/25/2016.
 */

public class MyStackResizingArray<T> implements MyStack<T> {
    private T[] hold;
    private int top = 0;

    public MyStackResizingArray() {
        hold = (T[]) new Object[1];
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

    private void resize(int newCapacity) {
        System.out.printf("\nStack Resizing to %d", newCapacity);
        T[] newHold = (T[]) new Object[newCapacity];
        for (int i = 0; i < top; i++) {
            newHold[i] = hold[i];
        }
        hold = newHold;
    }

    private boolean canDownSize() {
        return (top > 0 && top == hold.length/4);
    }
    public void push(T item) {
        if(isFull()) {
            resize(2 * size());
        }
        hold[top++] = item;
    }

    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        T item = hold[--top];

        if(canDownSize()) {
            resize(hold.length/2);
        }

        return item;
    }

    public T peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return hold[top-1];
    }

    @Override
    public String toString() {
        return "\nMyStackResizingArray{" +
                "hold=" + Arrays.toString(hold) +
                ", top=" + top +
                '}';
    }
}