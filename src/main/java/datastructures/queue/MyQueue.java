package datastructures.queue;

/**
 * Created by Lamuel on 3/26/2016.
 */
public interface MyQueue<T> {
    /**
     * is the queue empty
     * @return true if queue is empty
     */
    boolean isEmpty();

    /**
     * is the queue full
     * @return true if queue is full
     */
    default boolean isFull() {
        return false;
    }

    /**
     * number of items
     * @return number of items
     */
    int size();

    /**
     * insert new item onto Queue
     * @param item to be added
     */
    void enQueue(T item);

    /**
     * remove and return an item from queue
     * @return v
     */
    T deQueue();

    /**
     * return an item from queue but not remove
     * @return item to be removed
     */
    T peek();

}
