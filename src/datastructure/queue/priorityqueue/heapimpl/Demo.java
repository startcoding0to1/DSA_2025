package datastructure.queue.priorityqueue.heapimpl;

import java.util.PriorityQueue;

public class Demo {
    static class Task {
        Integer val;
        Integer priority;
        public Task(Integer val, Integer priority){
            this.val = val;
            this.priority = priority;
        }

//        @Override
//        public int compareTo(Task o) {
//            return this.priority.compareTo(o.priority);
//        }

        @Override
        public String toString() {
            return "{"+val+","+priority+"}";
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Task> pq = new PriorityQueue<>((a,b)->a.priority-b.priority);
        pq.add(new Task(1,6));
        pq.add(new Task(2,3));
        pq.add(new Task(3,9));
        pq.add(new Task(4,5));
        System.out.println(pq);

        for (Task task : pq) {
            System.out.println(task);
        }

        System.out.println(pq);

    }
}
