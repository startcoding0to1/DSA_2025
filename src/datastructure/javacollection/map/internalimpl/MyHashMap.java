package datastructure.javacollection.map.internalimpl;

import java.util.HashMap;
import java.util.Objects;

public class MyHashMap<K, V> {

    // Default settings
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private MapNode<K, V>[] table;
    private int size;
    private int threshold;

    // Constructor
    public MyHashMap() {
        table = new MapNode[DEFAULT_CAPACITY];
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    // Hash function
    private int hash(K key) {
        if (key == null) return 0;
        int h = key.hashCode();
        return h ^ (h >>> 16); // hash spreading means mixing higher bits with lower bits
    }

    // Get index
    private int index(int hash, int length) {
        return hash & (length - 1);
    }

    // PUT
    public void put(K key, V value) {
        int hash = hash(key);
        int index = index(hash, table.length); //bi

        MapNode<K,V> head = table[index];

        // Check existing key
        for (MapNode<K,V> node = head; node != null; node = node.next) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                node.value = value;
                return;
            }
        }

        // Insert at head
        MapNode<K,V> newNode = new MapNode<>(hash, key, value, head);
        table[index] = newNode;

        size++;

        if (size > threshold) {
            resize();
        }
    }

    // GET
    public V get(K key) {
        int hash = hash(key);
        int index = index(hash, table.length);

        MapNode<K,V> node = table[index];

        while (node != null) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    // RESIZE (Rehashing)
    private void resize() {
        int oldCap = table.length;
        int newCap = oldCap * 2;

        MapNode<K,V>[] newTable = new MapNode[newCap];

        // Rehash nodes
        for (MapNode<K,V> node : table) {
            while (node != null) {
                MapNode<K,V> next = node.next;

                int newIndex = index(node.hash, newCap);

                // head insertion
                node.next = newTable[newIndex];
                newTable[newIndex] = node;

                node = next;
            }
        }

        table = newTable;
        threshold = (int)(newCap * LOAD_FACTOR);
    }

    // Size helper
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean containsKey(K key) {
        int hash = hash(key);
        int bi = index(hash,this.table.length);
        for(MapNode<K, V> node = table[bi]; node != null; node = node.next) {
            if(node.hash == hash && Objects.equals(node.key, key)) {
                return true;
            }
        }
        return false;
    }

    // Test main
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);

        System.out.println(map.get("Two"));   // 2
        System.out.println(map.get("Three")); // 3
        System.out.println(map.size());       // 4

        }
    
}
