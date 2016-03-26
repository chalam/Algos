package datastructures.queue;

/**
 * Created by Lamuel on 3/26/2016.
 */
public interface MyQueue<T> {
    /*
    is the queue empty
     */
    boolean isEmpty();

    /*
    number of items
     */
    int size();
    /*
    insert new item onto Queue
     */
    void enQueue(T item);

    /*
    remove and return an item from queue
     */
    T deQueue();

    /*
    return an item from queue but not remove
     */
    T peek();

}
