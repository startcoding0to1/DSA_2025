package patterns.f_linkedlist.easy;

import datastructure.linkedlist.singlelinkedlist.Node;
import datastructure.linkedlist.singlelinkedlist.SingleLinkedList;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedLists_L21 {
    static class Solution {
        public Node<Integer> usingTwoPointers(Node<Integer> head1, Node<Integer> head2) {
            Node<Integer> dummyHead = new  Node<>(-1);

            Node<Integer> p1 = head1;
            Node<Integer> p2 = head2;

            Node<Integer> currNode = dummyHead;
            while(p1!=null && p2!=null){
                if(p1.data<=p2.data){
                    currNode.next = p1;
                    p1 = p1.next;
                }else {
                    currNode.next = p2;
                    p2 = p2.next;
                }
                currNode = currNode.next;
            }

            if(p1!=null){currNode.next = p1;}
            if(p2!=null){currNode.next = p2;}
            return dummyHead.next;
        }

        public Node<Integer> usingExtraList(Node<Integer> head1, Node<Integer> head2) {
            List<Integer> list = new ArrayList<>();
            Node<Integer> p1 = head1;
            while (p1!=null){
                list.add(p1.data);
                p1 = p1.next;
            }
            Node<Integer> p2 = head2;
            while (p2!=null){
                list.add(p2.data);
                p2 = p2.next;
            }

            list.sort(null);

            Node<Integer> dummyHead = new  Node<Integer>(-1);
            Node<Integer> currNode = dummyHead;
            for(Integer i:list){
                currNode.next = new Node<Integer>(i);
                currNode = currNode.next;
            }
            return dummyHead.next;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        SingleLinkedList<Integer> list1 = new SingleLinkedList<>();
        for (int e : new int [] {1,2,4}){
            list1.addLast(e);
        }
        System.out.print("List1: ");
        list1.forEach((e)-> System.out.print(e+" "));
        SingleLinkedList<Integer> list2 = new SingleLinkedList<>();
        for (int e : new int [] {1,3,4,5}){
            list2.addLast(e);
        }
        System.out.println();
        System.out.print("List2: ");
        list2.forEach((e)-> System.out.print(e+" "));
        System.out.println();
        Node<Integer> node = s.usingExtraList(list1.head, list2.head);
        System.out.print("After merging: ");
        while(node!=null){
            System.out.print(node.data+" ");
            node = node.next;
        }

    }
}
