import org.junit.Test;

import java.util.AbstractList;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    DoublyLinkedList<Integer> list1;
    DoublyLinkedList<Integer> list2;
    DoublyLinkedList<Integer> list3;

    @org.junit.Before
    public void setUp() throws Exception {
        list1= new DoublyLinkedList<Integer>();
        list2= new DoublyLinkedList<Integer>();
        list3= new DoublyLinkedList<Integer>();
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void add() {
        list1.add(null);
    }

    @Test
    public void add2(){
        list1.add(1);
        assertEquals(1,list1.size());
        list1.add(2);
        assertEquals(2,list1.size());
        list1.add(3);
        assertEquals(3,list1.size());
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testAdd() {
        list2.add(-1,2);
    }
    @Test(expected = NullPointerException.class)
    public void testAdd2(){
        list2.add(0,null);
    }

    @Test
    public void testAdd3(){
        list2.add(0,1);
        list2.add(1,3);
        list2.add(1,2);
        assertEquals(list2.get(1),new Integer(2));
        list2.add(3,4);
        assertEquals(list2.get(3),new Integer(4));
        list2.add(2,5);
        assertEquals(list2.get(3),new Integer(3));
    }

    @org.junit.Test
    public void clear() {
        list2.clear();
        assertEquals(list2.size(),0);
        list1.clear();
        assertEquals(list1.size(),0);
        list2.clear();
        assertEquals(list2.size(),0);

    }

    @org.junit.Test
    public void contains() {
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        assertTrue(list1.contains(1));
        assertTrue(list1.contains(4));
        assertFalse(list1.contains(5));
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        list1.get(-1);
    }

    @Test
    public  void get2(){
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        assertEquals(list1.get(1),new Integer(1));
        assertEquals(list1.get(0), new Integer(0));
        assertEquals(list1.get(2), new Integer(2));
    }

    @org.junit.Test
    public void isEmpty() {
        assertTrue(list2.isEmpty());
        assertTrue(list1.isEmpty());
        assertTrue(list3.isEmpty());

    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void remove() {
        list1.remove(-1);
    }

    @Test
    public void remove2(){
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        list1.remove(0);
        assertEquals(list1.size(),4);
        assertEquals(new Integer(1), list1.get(0));
        list1.remove(0);
        assertEquals(list1.size(), 3);
    }


    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void set() {
        list1.set(-1,1);
    }

    @Test(expected = NullPointerException.class)
    public void set2(){
        list1.add(1);
        list1.set(0,null);
    }
    @Test
    public void set3(){
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        list1.set(0,9);
        assertEquals(list1.get(0), new Integer(9));
        list1.set(1,9);
        assertEquals(list1.get(1), new Integer(9));
        list1.set(2,9);
        assertEquals(list1.get(2), new Integer(9));
    }

    @org.junit.Test
    public void size() {
        assertEquals(list1.size(),0);
        assertEquals(list2.size(),0);
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        assertEquals(list1.size(),5);
    }

    @org.junit.Test
    public void testToString() {
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        assertEquals(list1.toString(),"[(head) -> 0 -> 1 -> 2 -> 3 -> 4 -> (tail)]");
        assertEquals(list2.toString(),"[(head) -> (tail)]");
        assertEquals(list3.toString(),"[(head) -> (tail)]");

    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void splice() {
        list1.splice(-1,list2);
    }

    @Test
    public void splice2() {
        list1.add(1);
        list1.add(2);
        list2.add(3);
        list2.add(4);
        list1.splice(1,list2);
        assertEquals(list1.toString(),"[(head) -> 1 -> 3 -> 4 -> 2 -> (tail)]");
        list1.splice(0,list2);
        assertEquals(list1.toString(),"[(head) -> 3 -> 4 -> 1 -> 3 -> 4 -> 2 -> (tail)]");
        list3.add(1);
        list2.splice(0,list3);
        assertEquals(list2.toString(),"[(head) -> 1 -> 3 -> 4 -> (tail)]");
    }

    @org.junit.Test
    public void match() {
        for(int i=0; i<5; i++){
            list1.add(i);
        }
        list3.add(1);
        assertArrayEquals(new int[]{1},list1.match(list3));
        list2.add(3);
        list2.add(4);
        assertArrayEquals(new int[]{3},list1.match(list2));
        list2.add(0,2);
        assertArrayEquals(new int[]{2},list1.match(list2));

    }
}