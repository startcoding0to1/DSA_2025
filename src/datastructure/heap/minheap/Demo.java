package datastructure.heap.minheap;

public class Demo {
    public static void main(String[] args) {

        MinHeap minHeap = new MinHeap();

        minHeap.add(50);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(15);
        minHeap.add(10);

        // Print the heap array
        minHeap.printHeap();

        System.out.println("Peek (min): " + minHeap.peek());
        System.out.println("Poll: " + minHeap.poll());
        System.out.println("After poll:");
        minHeap.printHeap();
    }
}
