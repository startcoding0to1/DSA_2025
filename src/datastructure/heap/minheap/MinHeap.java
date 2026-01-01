package datastructure.heap.minheap;

import datastructure.heap.heapTemplate.Heap;

import java.util.Arrays;

public class MinHeap implements Heap {

    int capacity = 10;   // default heap array size
    int size = 0;        // number of elements currently in heap
    int[] heap;

    // Default constructor
    public MinHeap() {
        heap = new int[capacity];
    }

    // Constructor with custom capacity
    public MinHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
    }

    /* ---------------- INDEX CALCULATIONS ---------------- */

    private int getLeftChildIndex(int index) { return index * 2 + 1; }
    private int getRightChildIndex(int index) { return index * 2 + 2; }
    private int getParentIndex(int index) { return (index - 1) / 2; }

    /* ---------------- EXISTENCE CHECKS ---------------- */

    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    /* ---------------- VALUE ACCESSORS ---------------- */

    private int leftChild(int index) { return heap[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return heap[getRightChildIndex(index)]; }
    private int parent(int index) { return heap[getParentIndex(index)]; }

    /* ---------------- SWAP UTILITY ---------------- */

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /* ---------------- ARRAY RESIZE ---------------- */

    // Double the array size when full
    private void ensureCapacity() {
        if (size >= capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }
    }

    /* ---------------- PEEK (GET MIN) ---------------- */
    @Override
    public int peek() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        return heap[0]; // root always contains minimum
    }

    /* ---------------- POLL (EXTRACT MIN) ---------------- */
    @Override
    public int poll() {
        if (size == 0) throw new RuntimeException("Heap is empty");

        int minValue = heap[0];         // store min
        heap[0] = heap[size - 1];       // move last element to root
        size--;                         // decrease size
        heapifyDown();                  // restore heap property

        return minValue;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /* ---------------- HEAPIFY DOWN (for poll) ----------------
    * HEAFPIFY Algo:
    * =============
    * Parent should be less than its children
    * */

    private void heapifyDown() {
        int index = 0;

        // Continue until node has no children
        while (hasLeftChild(index)) {

            // Assume left child is smaller
            int smallerChildIndex = getLeftChildIndex(index);

            // Check right child (if exists)
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index); // FIXED
            }

            // If parent is already smaller → heap is valid
            if (heap[index] <= heap[smallerChildIndex]) {
                break;
            }

            // Otherwise swap parent with smaller child
            swap(index, smallerChildIndex);

            // Move index down to child
            index = smallerChildIndex;
        }
    }

    /* ---------------- ADD ELEMENT ---------------- */
    @Override
    public void add(int value) {
        ensureCapacity();      // expand array if full
        heap[size] = value;    // insert at last position
        size++;
        heapifyUp();           // restore heap property
    }

    /* ---------------- HEAPIFY UP (for add) ---------------- */

    private void heapifyUp() {
        int index = size - 1;  // start at last inserted element

        // Move upwards until heap property is restored
        while (hasParent(index) && parent(index) > heap[index]) {

            // Swap with parent
            swap(getParentIndex(index), index);

            // Move index to parent (FIXED)
            index = getParentIndex(index);
        }
    }

    @Override
    public void printHeap() {
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
