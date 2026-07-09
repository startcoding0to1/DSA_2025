package patterns.f_linkedlist.easy;

import datastructure.linkedlist.singlelinkedlist.Node;
import datastructure.linkedlist.singlelinkedlist.SingleLinkedList;

import java.util.Stack;

public class ReverseLinkedList_L206{

    static class Solution{

        /*
        * TC - O(N)
        * SC - O(1)
        * */
        public Node<Integer> usingIteration(Node<Integer> head){
            Node<Integer> previous = null;
            Node<Integer> current = head;
            while(current != null){
                Node<Integer> temp = current.next;
                current.next = previous;
                previous = current;
                current = temp;
            }
            return previous;
        }

        /*
         * TC - O(N)
         * SC - O(N) recursive stack
         * */
        public  Node<Integer> usingRecursion(Node<Integer> head){
            if(head == null || head.next == null){
                return head;
            }
            Node<Integer> reverseHead = usingRecursion(head.next);
            head.next.next = head;
            head.next = null;
            return reverseHead;
        }

        public Node<Integer> usingStack(Node<Integer> head){
            if(head == null || head.next == null){
                return head;
            }
            Stack<Integer> stack = new Stack<>();

            Node<Integer> current = head;
            while(current != null){
                stack.push(current.data);
                current = current.next;
            }

            current =  head;
            while(current != null){
                current.data = stack.pop();
                current = current.next;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        int [] input = {1,2,3,4,5};
        for(int i = 0; i < input.length; i++){
            list.addLast(input[i]);
        }
        System.out.print("Original List: ");
        list.forEach((e)->System.out.print(e+" "));
        System.out.println();
        list.head = new Solution().usingStack(list.head);
        System.out.print("Reverse List: ");
        list.forEach((e)->System.out.print(e+" "));
    }

}
