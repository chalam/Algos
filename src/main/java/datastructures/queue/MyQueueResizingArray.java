package datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Queue implemented with Resizing Array capacity
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueResizingArray<T> implements MyQueue<T> {
    private T[] queue;
    private int first;
    private int last;
    private int size;

    public MyQueueResizingArray() {
        queue = (T[]) new Object[1];
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
        return first == last;
    }

    @Override
    public int size() {
        return size;
    }

    /*
        extend array to new capacity
     */
    private void resize(int newCapacity) {
        assert newCapacity >= size;
        T[] newQueue = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newQueue[i] = queue[first + i % queue.length];

        queue = newQueue;
        first = 0;
        last = size;
    }

    @Override
    public void enQueue(T item) {
        // expand the sail at 100%
        if (size == queue.length)
            resize(2 * queue.length);

        queue[last] = item;
        size++;
        last = last + 1 % queue.length;
    }

    @Override
    public T deQueue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue Underflow");

        T item = queue[first];
        queue[first] = null; // avoid loitering
        size--;
        first = first + 1 % queue.length;

        // trim the sail at 25%
        if (size > 0 && size == queue.length / 4 )
            resize(queue.length / 2);

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
