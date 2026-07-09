package patterns.f_linkedlist.easy;

import datastructure.linkedlist.singlelinkedlist.Node;
import datastructure.linkedlist.singlelinkedlist.SingleLinkedList;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle_L141 {

    static class Solution {
        public boolean detectCycleUsingFastAndSlow(Node<Integer> head) {
            Node<Integer> slow = head;
            Node<Integer> fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }

        public boolean detectCycleUsingHasSet(Node<Integer> head) {
            Set<Node<Integer>> set = new HashSet<>();
            Node<Integer> curNode = head;
            while (curNode != null) {
                if(set.contains(curNode)){
                    return true;
                }
                set.add(curNode);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        Node<Integer> pos = null;
        for(Integer e : new int [] {1,2,3,4,5}){
            list.addLast(e);
            if(e==4){
                pos = list.tail;
            }
        }
        list.tail.next = pos;
        System.out.println("Is cycle present: "+s.detectCycleUsingHasSet(list.head));
    }
}
