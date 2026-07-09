package datastructure.linkedlist.singlelinkedlist;

import java.util.function.Consumer;

public class SingleLinkedList<E> {

    public Node<E> head;
    public Node<E> tail;

    public void addLast(E data){
        Node<E> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            return;
        }
        Node<E> current = tail;
        current.next = newNode;
        tail = newNode;
    }

    public void addFirst(E data){
        Node<E> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void forEach(Consumer<E> e){
        Node<E> current = head;
        while(current != null){
            e.accept(current.data);
            current = current.next;
        }
    }
}
