# RoundRobin-Process-Scheduler
<b>This is an implementation of the <a href="https://en.wikipedia.org/wiki/Round-robin_scheduling#:~:text=To%20schedule%20processes%20fairly%2C%20a,is%20assigned%20to%20that%20process.">RoundRobin Process</a> Schdeduler</b>
To schedule processes fairly, a round-robin scheduler generally employs time-sharing, giving each job a time slot or quantum (its allowance of CPU time), and interrupting the job if it is not completed by then. The job is resumed next time a time slot is assigned to that process. If the process terminates or changes its state to waiting during its attributed time quantum, the scheduler selects the first process in the ready queue to execute(Wikipedia).
<ul>
  <li>Doubly linked list implementation of the algoritm</li>
  <li>goes through the tasks in the waitlist, schedules them in order for one quantum period and then returns it to the queue or marks it completed as necessary.</li>
  <li>Keeps track of the burst and wait times</li>
