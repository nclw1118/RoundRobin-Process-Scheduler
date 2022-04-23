/*
 * NAME: Xue Wang
 * PID: A15908778
 */

import java.util.AbstractList;
import java.util.Objects;

/**
 * Doubly-Linked List Implementation
 *
 * @author Xue Wang
 * @since 04/23/2020
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            data = element;
            next= null;
            prev= null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.next=nextNode;
            this.prev=prevNode;
            this.data=element;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data=element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next=n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev=p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            if(this.prev==head){
                //if this is the only element in the list
                if(this.next==tail){
                    head.next=tail;
                    tail.prev=head;
                }
                // if it is the first element in the list
                else{
                    this.next.prev=head;
                    head.next=this.next;
                }
            }
            else{
                //if this is at the tail of a list
                if(this.next==tail){
                    this.prev.next=tail;
                    tail.prev=this.prev;
                }
                //if it is in the middle
                else{
                    this.prev.next=this.next;
                    this.next.prev=this.prev;
                }
            }
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        //TODO: complete default constructor
        head= new Node(null);
        tail= new Node(null);
        head.next=tail;
        tail.prev=head;
        nelems=0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //implementation of adding the new data
        if (element==null){
            throw new NullPointerException();
        }
        // assign the previous and the next node
        Node toAdd= new Node(element);
        toAdd.next=tail;
        toAdd.prev=tail.prev;
        toAdd.prev.next=toAdd;
        tail.prev= toAdd;
        nelems+=1;
        return true;

    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index the index after which the element is added
     * @param element the element to be added
     * @throws IndexOutOfBoundsException when the index is out if range
     * @throws NullPointerException when element is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //input validation check
        if(index<0||index>nelems){
            throw  new IndexOutOfBoundsException();
        }
        if(element==null){
            throw new NullPointerException();
        }
        //implementation of adding the new data
        Node toAdd= new Node(element);
        if(index==0){
            // add to a empty list
            if(nelems==0){
                head.next=toAdd;
                toAdd.prev=head;
                tail.prev=toAdd;
                toAdd.next=tail;
            }
            else{
                // add after the first node of the list
                toAdd.next=head.next;
                head.next.prev=toAdd;
                toAdd.prev=head;
                head.next=toAdd;
            }
        }
        else{
            // add at the end of the list
            if(index==nelems){
                toAdd.prev=tail.prev;
                tail.prev.next=toAdd;
                tail.prev=toAdd;
                toAdd.next=tail;
            }
            else{
                // add in the middle of the list
                Node before;
                Node after;
                Node start=head;
                //loop through the list to get the Curnode
                for (int i=0; i<index; i++){
                    start=start.next;
                }
                before=start;
                after=start.next;
                before.next=toAdd;
                toAdd.prev=before.next;
                after.prev=toAdd;
                toAdd.next=after;
            }
        }
        nelems+=1;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        // set the head hand tail to the beginning setting and zero the nelems
        head.next=tail;
        tail.prev=head;
        nelems=0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element the object element to be checked
     * @return boolean true if the object element is in the list, false otherwise
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        Node start=head.next;
        // loop through the list to check if any element equals the object
        for(int i=0; i<nelems;i++){
            // if the object is found, return true
            if(start.data.equals(element)){
                return true;
            }
            else{
                start=start.next;
            }
        }
        //if nor found, return false
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     * @param index the index at which the element is returned
     * @return the data of the element at the given index
     * @throws IndexOutOfBoundsException when index is out of range
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        //input validation
        if(index<0||index>nelems-1){
            throw new IndexOutOfBoundsException();
        }
        // loop through the list to get the object element
        Node start=head;
        for(int i=0; i<index; i++){
            start=start.next;
        }
        //return the data of the object
        return start.next.data;
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index the index at which the element is returned
     * @return the element at the given index
     */
    private Node getNth(int index) {
        // loop through the list to get the object
        Node start=head;
        for(int i=0; i<index; i++){
            start=start.next;
        }
        return start.next;
    }

    /**
     * Determine if the list empty
     *
     * @return boolean, true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        // return true if the list is empty
        if(nelems==0) {
            return true;
        }
        // return false if it is not empty
        else {
            return false;
        }
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index the index at which element is to be removed
     * @return the data of the element that is removed
     * @throws IndexOutOfBoundsException when the index is out of range
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //input validation
        if(index<0||index>nelems-1){
            throw new IndexOutOfBoundsException();
        }
        //remove the Nth element
        Node removed= getNth(index);
        getNth(index).remove();
        nelems-=1;
        //return the data of the removed element
        return removed.data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index the index at which the element is to be set
     * @param element the new element to set the original one
     * @return return the original data
     * @throws IndexOutOfBoundsException when the index is our of range
     * @throws NullPointerException when the given element is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //input validation
        if(index<0||index>nelems-1) {
            throw new IndexOutOfBoundsException();
        }
        if(element==null){
            throw new NullPointerException();
        }
        //set the element
        T previous= getNth(index).data;
        getNth(index).setElement(element);
        //return the data of the original element
        return  previous;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        // return the size of the list
        return nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return the string format of the list
     */
    @Override
    public String toString() {
        String out="[(head) -> ";
        //iterate through the list to add each element to the string
        for(int i=0; i<nelems; i++){
            out+=get(i)+" -> ";
        }
        out+="(tail)]";
        // return the string
        return out;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Inserts another linked list of the same type into this one
     *
     * @param index the index at which the other list to be inserted
     * @param otherList the other list of the same type to be inserted
     * @throws IndexOutOfBoundsException when the given index is out of range
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        // input validation
        if(index<0||index>nelems) {
            throw new IndexOutOfBoundsException();
        }
        //add the elements in the other list in reverse order to the index
        for(int i=otherList.nelems-1;i>=0; i--){
            this.add(index,otherList.get(i));
        }
    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * @param subsequence the subsequence to be matched
     * @return an array of int represents the starting indices of the subsequence
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //iterate through the list at a group of size of the subsequence
        int subsize=subsequence.size();
        for(int i=0; i<=this.nelems-subsize; i++){
            boolean check=true;
            //iterate through the size of subsequence to see
            // if every element in the group matches the subsequence
            for(int j=0; j<subsize; j++){
                //if there's one element that doesn't match, the check if false
                if(this.get(i+j)!=subsequence.get(j)){
                    check=false;
                }
            }
            //if check is true, add that index to the output array
            if (check==true){
                indices.add(i);
            }
        }
        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        //return the array
        return startingIndices;
    }


}