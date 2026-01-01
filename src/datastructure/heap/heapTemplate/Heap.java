package datastructure.heap.heapTemplate;

public interface Heap {

    void add(int value);   // insert value into heap

    int peek();            // return root (min or max)

    int poll();            // remove and return root

    int size();            // current number of elements

    boolean isEmpty();     // check if heap is empty

    void printHeap();      // display heap contents
}

