package datastructure.heap.maxheap;

public class MaxHeapDemo {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        System.out.println("=== MaxHeap Demo ===");

        // Add elements
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(5);
        maxHeap.add(30);
        maxHeap.add(40);

        System.out.print("Heap after insertions: ");
        maxHeap.printHeap();   // Should show max-heap structure
        System.out.println();

        // Peek root element
        System.out.println("Peek (max element): " + maxHeap.peek());

        // Poll elements one by one
        System.out.println("\nPolling elements:");
        while (!maxHeap.isEmpty()) {
            System.out.println("Polled: " + maxHeap.poll());
            System.out.print("Heap now: ");
            maxHeap.printHeap();
            System.out.println();
        }
    }
}

