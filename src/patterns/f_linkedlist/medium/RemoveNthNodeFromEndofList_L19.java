package patterns.f_linkedlist.medium;

import datastructure.linkedlist.singlelinkedlist.Node;
import datastructure.linkedlist.singlelinkedlist.SingleLinkedList;

public class RemoveNthNodeFromEndofList_L19 {

    static class Solution {

        private Node<Integer> usingBruteForce(Node<Integer> head, int n){
            int size = 0;
            Node<Integer> currNode = head;
            while (currNode != null){
                currNode = currNode.next;
                size++;
            }
            System.out.println(size);
            int nth = size - n;
            if (nth <= 0){
                return head.next;
            }
            int i=1;
            currNode = head;
            while(i<nth){
                currNode = currNode.next;
                i++;
            }
            currNode.next = currNode.next.next;
            return head;
        }
        int c = 0;
        private Node<Integer> usingRecursion(Node<Integer> head, Integer n){
            if (head == null || head.next == null){
                return head;
            }
            usingRecursion(head.next, n);
            c++;
            if(c==n){
                head.next = head.next.next;
            }
            return head;
        }

        public Node<Integer> usingRecursionBetterPractice(Node<Integer> head, int n) {
            Node<Integer> dummy = new Node<>(0);
            dummy.next = head;
            dfs(dummy, n);
            return dummy.next;
        }

        private int dfs(Node<Integer> node, int n) {
            if (node == null || node.next == null) return 1;

            int idx = dfs(node.next, n);

            if (idx == n) {
                node.next = node.next.next;
            }
            return idx+1;
        }

        public Node<Integer> removeNthFromEnd(Node<Integer> head, int n) {
            return usingRecursion(head, n);
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.addLast(i);
        }
        System.out.print("Original List: ");
        list.forEach((e)->System.out.print(e+" "));
        System.out.println();
        list.head = s.usingRecursionBetterPractice(list.head, 5);
        System.out.print("After removeNthFromEnd: ");
        list.forEach((e)->System.out.print(e+" "));
    }
}
