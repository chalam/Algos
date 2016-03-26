package datastructures.queue;

import java.util.NoSuchElementException;

/**
 * Queue implemented with LinkedList
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueLinkedList<T> implements MyQueue<T> {
    /*
        Class to hold linked list
     */
    private class Node<T> {
        T item;
        Node<T> next;

        Node(T item){
            this.item = item;
            this.next = null;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("item=").append(item);
            sb.append(", next=").append(next);
            sb.append('}');
            return sb.toString();
        }
    }

    private Node<T> first = null;
    private Node<T> last = null;
    private int size = 0;

    /*
    is Queue empty
     */
    @Override
    public boolean isEmpty() {
        return (first == null);
    }
    /*
        number of items
     */
    @Override
    public int size() {
        return size;
    }

    /*
        add item to the last of list
     */
    @Override
    public void enQueue(T item) {
        Node<T> oldLast = last;
        last = new Node<>(item);
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    /*
        remove item from the begin of the list
     */
    @Override
    public T deQueue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");

        T item = first.item;
        first = first.next;
        size--;

        // just removed the last item, reset last for avoiding loitering
        if (isEmpty())
            last = null;

        return item;
    }

    /*
        seek the next item that can be removed with deQueue
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyQueueLinkedList{");
        sb.append("first=").append(first);
        sb.append(", last=").append(last);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
