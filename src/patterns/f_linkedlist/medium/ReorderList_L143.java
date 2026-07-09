package patterns.f_linkedlist.medium;

import datastructure.linkedlist.singlelinkedlist.Node;

import java.util.HashMap;
import java.util.Map;

public class ReorderList_L143 {
    static class Solution {

        public void usingHashMap(Node<Integer> head){
            Node<Integer> currNode = head;
            Map<Integer, Node<Integer>> map = new HashMap<>();
            int p = 0;
            while(currNode != null){
                Node<Integer> temp = currNode.next;
                currNode.next = null;
                map.put(p++,currNode);
                currNode = temp;
            }
            int n = map.size();
            Node<Integer> dummyNode = new Node<Integer>(-1);
            Node<Integer> cN = dummyNode;
            int i=0;
            for(int j=n-1; i<n/2; i++,j--){
                cN.next = map.get(i);
                cN.next.next = map.get(j);
                cN = cN.next.next;
            }
            System.out.println(i);
            if(n%2!=0){
                cN.next=map.get(i);
            }
            head = dummyNode.next;
        }

        private Node<Integer> reverseList(Node<Integer> head){
            if(head==null || head.next==null){
                return head;
            }
            Node<Integer> reverseHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return reverseHead;
        }

        public void usingFastAndSlow(Node<Integer> head){
            //Find the middle of the linkedlist
            Node<Integer> slow = head;
            Node<Integer> fast = head;
            while (fast.next != null || fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            Node<Integer> p1 = head;
            Node<Integer> currNode = head.next;
            while(currNode!=slow.next){
                p1 = p1.next;
                p1 = p1.next;
                currNode = currNode.next;
            }
            Node<Integer> p2 = reverseList(slow.next);
            currNode = head;
            while(p1!=null && p2!=null){
                currNode.next = p2;
                Node<Integer> temp = p2.next;
                p2.next = p1;
                p1 = p1.next;
                p2 = temp;
            }
            if(p1!=null){
                currNode.next = p1;
            }
            if(p2!=null){
                currNode.next = p2;
            }
        }

        public void reorderList(Node<Integer> head) {
            usingFastAndSlow(head);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
    }
}
