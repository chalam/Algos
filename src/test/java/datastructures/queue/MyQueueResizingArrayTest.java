package datastructures.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueResizingArrayTest {

    @Test
    public void useQueue() throws Exception {
        MyQueue que = new MyQueueResizingArray<String>();
        que.enQueue("one");
        System.out.println(que);

        que.enQueue("two");
        System.out.println(que);

        que.enQueue("three");
        System.out.println(que);

        que.enQueue("four");
        System.out.println(que);

        que.enQueue("five");
        System.out.println(que);

        que.enQueue("six");
        System.out.println(que);

        que.enQueue("seven");
        System.out.println(que);

        assertEquals("Queue failed", "one", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", "two", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", "three", que.deQueue());
        System.out.println(que);


        assertEquals("Queue failed", 4, que.size());

        assertEquals("Queue failed", "four", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", "five", que.peek());

        assertEquals("Queue failed", "five", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", 2, que.size());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyDeQueue() {
        MyQueue que = new MyQueueResizingArray<String>();
        assertTrue(que.isEmpty());
        assertEquals("Queue failed", que.peek(), false);
        que.deQueue();
    }

}