import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLStackTest {

    DLLStack<Integer> s1;
    DLLStack<Integer> s2;
    DLLStack<Integer> s3;

    @Before
    public void serUp(){
        s1=new DLLStack<Integer>();
        s2=new DLLStack<Integer>();
        s3=new DLLStack<Integer>();
    }

    @Test
    public void size() {
        s1.push(1);
        s1.push(1);
        assertEquals(2,s1.size());
        s2.push(1);
        assertEquals(1, s2.size());
        assertEquals(0,s3.size());
    }

    @Test
    public void isEmpty() {
        assertEquals(true, s1.isEmpty());
        s2.push(1);
        assertEquals(false, s2.isEmpty());
        s3.push(4);
        s3.push(1);
        assertEquals(false, s3.isEmpty());
    }

    @Test
    public void push() {
        s1.push(1);
        s1.push(1);
        assertEquals(2,s1.size());
        s2.push(1);
        assertEquals(1, s2.size());
        assertEquals(0,s3.size());
    }

    @Test
    public void pop() {
        assertEquals(null, s1.pop());
        s3.push(4);
        s3.push(1);
        assertEquals(new Integer(1),s3.pop());
        s2.push(1);
        assertEquals(new Integer(1),s2.pop());
    }
    @Test
    public void peek(){
        assertEquals(null, s1.peek());
        s3.push(4);
        s3.push(1);
        assertEquals(new Integer(1),s3.peek());
        s2.push(1);
        assertEquals(new Integer(1),s2.peek());
    }
}