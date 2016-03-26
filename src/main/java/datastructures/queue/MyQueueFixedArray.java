package datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueFixedArray<T> implements MyQueue<T> {
    private T[] queue;
    private int first;
    private int last;
    private int size;

    public MyQueueFixedArray(int capacity) {
        queue = (T[]) new Object[capacity];
        size = 0;
        first = 0;
        last = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == queue.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enQueue(T item) {
        if (isFull())
            throw new NoSuchElementException("Queue Overflow");

        queue[last++] = item;
        size++;
        if (last == queue.length)
            last = 0;
    }

    @Override
    public T deQueue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue Underflow");

        T item = queue[first];
        queue[first++] = null; // avoid loitering
        size--;
        if (first == queue.length)
            first = 0;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue Underflow");
        return queue[first];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyQueueResizingArray{");
        sb.append("queue=").append(Arrays.toString(queue));
        sb.append(", first=").append(first);
        sb.append(", last=").append(last);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
