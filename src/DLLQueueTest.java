import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLQueueTest {
    DLLQueue<Integer> queue1;
    DLLQueue<Integer> queue2;
    DLLQueue<Integer> queue3;

    @Before
    public void setUp(){
        queue1=new DLLQueue<Integer>();
        queue2=new DLLQueue<Integer>();
        queue3=new DLLQueue<Integer>();
    }


    @Test
    public void size() {
        queue1.enqueue(1);
        queue1.enqueue(2);
        assertEquals(2,queue1.size());
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(1);
        queue2.enqueue(2);
        assertEquals(4,queue2.size());
        assertEquals(0,queue3.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(queue1.isEmpty());
        queue2.enqueue(1);
        assertFalse(queue2.isEmpty());
        assertTrue(queue3.isEmpty());
    }

    @Test
    public void enqueue() {
        queue1.enqueue(1);
        queue1.enqueue(2);
        assertEquals(2,queue1.size());
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(1);
        queue2.enqueue(2);
        assertEquals(4,queue2.size());
        queue3.enqueue(5);
        assertEquals(1,queue3.size());
    }

    @Test
    public void dequeue() {
        queue1.enqueue(1);
        assertEquals(new Integer(1), queue1.dequeue());
        assertEquals(null, queue2.dequeue());
        queue1.enqueue(4);
        assertEquals(new Integer(4), queue1.dequeue());
        assertTrue(queue2.isEmpty());
        queue3.enqueue(3);
        queue3.enqueue(3);
        queue3.dequeue();
        assertFalse(queue3.isEmpty());
    }

    @Test
    public void peek() {
        queue1.enqueue(1);
        assertEquals(new Integer(1), queue1.peek());
        assertEquals(null, queue2.peek());
        queue1.enqueue(4);
        queue1.dequeue();
        assertEquals(new Integer(4), queue1.peek());
        queue3.enqueue(3);
        queue3.enqueue(3);
        queue3.peek();
        assertFalse(queue3.isEmpty());
    }
}