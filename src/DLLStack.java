/*
 * NAME: Xue Wang
 * PID: A15908778
 */

/**
 * Stack implementation using Doubly-linked list.
 * @author Xue Wang
 * @param <T> generic container
 * @since 04/27/2020
 */
public class DLLStack<T> {

    private DoublyLinkedList<T> stack;

    /**
     * Initialize the instance variables of this stack
     */
    public DLLStack() {
        //Initialize the instance variable
        stack= new DoublyLinkedList<>();
    }

    /**
     * Return the number of elements currently stored in this stack
     *
     * @return the number of elements currently stored in this stack
     */
    public int size() {
        // Return the number of elements currently stored in this stack
        return stack.size();
    }

    /**
     * check if a stack is empty
     *
     * @return Return true if this stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        //call the DLL isEmpty method
        return stack.isEmpty();
    }

    /**
     * Add the given data to this stack
     *
     * @param data the data to be added
     */
    public void push(T data) {
        //Add the given data to this stack
        stack.add(data);
    }

    /**
     * Remove and return the top element from this stack.
     *
     * @return the popped element. Null if the stack is empty
     */
    public T pop() {
        //return null if the stack is empty
        if(stack.isEmpty()){
            return null;
        }
        //Remove and return the top element from this stack.
        return stack.remove(stack.size()-1);
    }

    /**
     * Peek and return the top element from this stack.
     *
     * @return the top element, null if the stack is empty
     */
    public T peek() {
        // return null is the stack is empty
        if(stack.isEmpty()){
            return null;
        }
        //Peek and return the top element from this stack.
        return stack.get(stack.size()-1);
    }

}
