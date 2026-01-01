package datastructure.heap.maxheap;

import datastructure.heap.heapTemplate.Heap;
import java.util.Arrays;

public class MaxHeap implements Heap {

    int capacity = 10;     // Initial array size
    int size = 0;          // Number of elements in heap
    int[] heap;            // Underlying array storage

    // Constructor with custom capacity
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
    }

    // Default constructor
    public MaxHeap() {
        heap = new int[capacity];
    }

    /* ---------------- INDEX CALCULATIONS ---------------- */

    private int getLeftChildIndex(int index)  { return index * 2 + 1; }
    private int getRightChildIndex(int index) { return index * 2 + 2; }
    private int getParentIndex(int index)     { return (index - 1) / 2; }

    /* ---------------- EXISTENCE CHECKS ---------------- */

    private boolean hasLeftChild(int index)  { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index)     { return getParentIndex(index) >= 0; }

    /* ---------------- VALUE ACCESSORS ---------------- */

    private int leftChild(int index)  { return heap[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return heap[getRightChildIndex(index)]; }
    private int parent(int index)     { return heap[getParentIndex(index)]; }

    /* ---------------- SWAP UTILITY ---------------- */

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /* ---------------- ARRAY RESIZE ---------------- */

    // Doubles array size when full
    private void ensureCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }
    }

    /* ---------------- ADD ELEMENT ---------------- */

    @Override
    public void add(int value) {
        ensureCapacity();
        heap[size++] = value;   // Insert at end
        heapifyUp();            // Fix heap (bubble up)
    }

    /* ---------------- HEAPIFY UP (for add) ----------------
       In MaxHeap: Child should NOT be larger than parent.
       If child > parent → swap upward.
    ---------------------------------------------------------- */

    private void heapifyUp() {
        int index = size - 1;              // Start at inserted node

        while (hasParent(index)) {

            int parentIndex = getParentIndex(index);

            // If parent is larger or equal → correct place reached
            if (heap[parentIndex] >= heap[index]) {
                break;
            }

            // Else swap upward
            swap(index, parentIndex);
            index = parentIndex;           // Move up
        }
    }

    /* ---------------- POLL (EXTRACT MAX) ---------------- */

    @Override
    public int poll() {
        if (size == 0) throw new RuntimeException("Heap is empty");

        int value = heap[0];               // Root contains max value

        heap[0] = heap[size - 1];          // Move last element to root
        size--;                            // Shrink heap
        heapifyDown();                     // Fix heap (bubble down)

        return value;
    }

    /* ---------------- HEAPIFY DOWN (for poll) ----------------
       In MaxHeap: Parent must be >= both children.
       If parent < larger child → swap downward.
    ------------------------------------------------------------ */

    private void heapifyDown() {
        int index = 0;

        // Continue until node has no left child
        while (hasLeftChild(index)) {

            // Pick the larger child
            int largestChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) &&
                    rightChild(index) > leftChild(index))
            {
                largestChildIndex = getRightChildIndex(index);
            }

            // If parent is already >= largest child → heap is valid
            if (heap[index] >= heap[largestChildIndex]) {
                break;
            }

            // Otherwise swap down
            swap(index, largestChildIndex);
            index = largestChildIndex;     // Move down
        }
    }

    /* ---------------- PEEK (GET MAX) ---------------- */

    @Override
    public int peek() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        return heap[0];    // Root contains max element
    }

    /* ---------------- SIZE & EMPTY ---------------- */

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /* ---------------- PRINT HEAP ---------------- */

    @Override
    public void printHeap() {
        System.out.print("[ ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.print("]");
    }
}
