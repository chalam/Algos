package datastructures.queue;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueFixedArrayTest {

    @Test
    public void useQueue() throws Exception {
        MyQueue que = new MyQueueFixedArray<String>(4);
        que.enQueue("one");
        System.out.println(que);

        que.enQueue("two");
        System.out.println(que);

        que.enQueue("three");
        System.out.println(que);

        que.enQueue("four");
        System.out.println(que);

        assertEquals("Queue failed", "one", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", "two", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", "three", que.deQueue());
        System.out.println(que);

        que.enQueue("five");
        System.out.println(que);

        que.enQueue("six");
        System.out.println(que);

        que.enQueue("seven");
        System.out.println(que);

        assertEquals("Queue failed", que.size(), 4);

        assertEquals("Queue failed", "four", que.deQueue());
        System.out.println(que);

        assertEquals("Queue failed", "five", que.peek());
        assertEquals("Queue failed", "five", que.deQueue());

        System.out.println(que);


        que.enQueue("eight");
        System.out.println(que);

        que.enQueue("nine");
        System.out.println(que);

        assertEquals("Queue failed", 4, que.size());

//        assertThat(que, Matchers.contains("A", "C", "E", "H", "M", "R", "S", "X"));
//        assertThat(que.iterator(), Matchers.hasItem("M"));
//        assertThat(que, Matchers.hasItemInArray({"A", "C", "E", "H", "M", "R", "S", "X"}));
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyDeQueue() {
        MyQueue que = new MyQueueResizingArray<String>();
        assertTrue(que.isEmpty());
        assertEquals("Queue failed", false, que.peek());
        que.deQueue();
    }

}