package datastructure.javacollection.map.internalimpl;

public class MapNode<K,V> {
    final int hash;
    final K key;
    V value;
    MapNode<K, V> next;

    public MapNode(int hash, K key, V value, MapNode<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
