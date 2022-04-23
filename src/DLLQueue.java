/*
 * NAME: Xue Wang
 * PID: A15908778
 */

/**
 * Queue implementation using Doubly-linked list.
 *
 * @author Xue Wang
 * @param <T> generic container
 * @since 04/27/2020
 */


public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    /**
     * Initialize the instance variables of this stack/queue.
     */
    public DLLQueue() {
        //initialize the queue
        queue=new DoublyLinkedList<>();
    }

    /**
     * Return the number of elements currently stored in this stack/queue.
     *
     * @return Return the number of elements
     */
    public int size() {
        //Return the number of elements
        return queue.size();
    }

    /**
     * Return true if this queue is empty, false otherwise.
     *
     * @return boolean;  true if this queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        // call DLL isEmpty method
        return queue.isEmpty();
    }

    /**
     * Add the given data to this queue.
     *
     * @param data the given data to be added
     */
    public void enqueue(T data) {
        // Add the given data to this queue
        queue.add(data);
    }

    /**
     * Remove and return the top element from this queue.
     *
     * @return the removed element; null if there's no element to be removed
     */
    public T dequeue() {
        //return null if there's no element to be removed
        if(queue.isEmpty()){
            return null;
        }
        //return the moved element
        return queue.remove(0);
    }

    /**
     * Peek and return the top element from this queue.
     *
     * @return the top element; null if there's no element to be peeked
     */
    public T peek() {
        //return null if there's no element to be peeked
        if(queue.isEmpty()){
            return null;
        }
        //return the top element
        return queue.get(0);
    }

}
