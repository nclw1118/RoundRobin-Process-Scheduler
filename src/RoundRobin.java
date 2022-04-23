/*
 * NAME: Xue Wang
 * PID: A15908778
 */

import java.util.Queue;

/**
 * RoundRobin process scheduler implementation
 *
 * @author Xue Wang
 * @since 04/27/2020
 */

public class RoundRobin {

    // constants
    private static final int DEFAULT_QUANTUM =  3;
    private static final String TASK_HANDLED = "All tasks are already handled.";
    private static final int SUBTRACTOR = 4;

    // instance variables
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;

    /**
     * constructor that call the constructor below with
     * the default quantum declared as a constant named DEFAULT_QUANTUM
     * @param toHandle array of task to initiate the variable waitlist
     */
    public RoundRobin(Task[] toHandle) {
        // call the constructor below with the default quantum
        this(DEFAULT_QUANTUM,toHandle);
    }

    /**
     *  constructor which initializes the instance variables
     *
     * @param quantum input quantum to initialize quantum variable
     * @param toHandle input array of Task to initialize waitlist variable
     * @throws IllegalArgumentException if quantum is less than one or
     *         toHandle is null or there's no task in toHandle.
     */
    public RoundRobin(int quantum, Task[] toHandle) {
        //input validation check
        if(quantum<1){
            throw new IllegalArgumentException();
        }
        if(toHandle==null|| toHandle.length==0) {
            throw new IllegalArgumentException();
        }
        //initiate the waitlist with toHandle
        waitlist= new DoublyLinkedList<>();
        for(int i=0; i<toHandle.length; i++){
            waitlist.add(toHandle[i]);
        }
        //Initiate quantum and finished
        this.quantum=quantum;
        this.finished=new DoublyLinkedList<>();
    }

    /**
     * fundamental method of this class that does most of the job. It goes through
     * the tasks in the waitlist, schedules them in order for one quantum period and then
     * returns it to the queue or marks it completed as necessary.
     * It keeps track of the burst and wait times
     *
     * @return a String of TASK_HANDLED if there's no task in the waitlist,
     *         other wise return a string of waittime, bursttime, and the order
     */
    public String handleAllTasks() {
        // if there's no Task in the wailist, return TASK_HANDLED
        if(waitlist.size()==0){
            return TASK_HANDLED;
        }

        //handle the tasks in the waitlist
        while (waitlist.size() > 0) {
            //always handle the first task
            Task t= waitlist.get(0);
            // loop through the quantum
            for( int j=0; j<quantum;j++){
                // everytime the task is handled, the burst time is incremented by 1
                // the wait time is incremented by 1*the number of people in the wailist
                t.handleTask();
                burstTime+=1;
                waitTime+= waitlist.size()-1;
                // after the handle, if the task is finished, add it to the finished
                // and remove it from the wait list
                if(t.isFinished()) {
                    finished.add(t);
                    waitlist.remove(0);
                    // break the loop so that the burst time and wait time
                    // don't have excessive increment
                    break;
                }

            }
            //after a person finished one quantum, if he is not finished,
            // move him from the beginning to the back of waitlist,
            if(!t.isFinished()){
                waitlist.add(t);
                waitlist.remove(0);
            }
        }

        // make the string format
        String out="All tasks are handled within "+burstTime+" units of burst time and" +
                " "+waitTime+" units of wait time. The tasks are finished in this order:\\n";
        for( int k=0; k<finished.size();k++){
            out+=finished.get(k).toString()+" -> ";
        }
        //return the string, use substring to cut out the redundant arrow
        return out.substring(0,out.length()-SUBTRACTOR);
    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A",  3), new Task("B", 4), new Task("C", 4),
                new Task("D", 12), new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8), new Task("C", 6),
                new Task("D", 4), new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(4, test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5), new Task("C", 3), new Task("D", 1),
                new Task("E", 2), new Task("F", 4), new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}