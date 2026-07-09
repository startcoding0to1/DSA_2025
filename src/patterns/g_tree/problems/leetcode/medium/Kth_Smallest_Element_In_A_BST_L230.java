package patterns.g_tree.problems.leetcode.medium;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Kth_Smallest_Element_In_A_BST_L230 {

    static class Solution {
        public int usingBFS(TreeNode root, int k){
            Queue<Integer> pq = new PriorityQueue<>((a, b)->b-a);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode currNode = queue.poll();
                pq.offer(currNode.data);
                while(pq.size()>k){
                    pq.poll();
                }
                if(currNode.left != null){
                    queue.offer(currNode.left);
                }
                if(currNode.right != null){
                    queue.offer(currNode.right);
                }
            }
            return pq.peek();
        }


        private List<Integer> usingDFSANDList(TreeNode root, int k, List<Integer> list){
            if(root == null){
                return list;
            }
            list = usingDFSANDList(root.left, k, list);
            list.add(root.data);
            return usingDFSANDList(root.right, k, list);
        }

        int count = 0;
        private int usingDFS(TreeNode root, int k){
            if(root == null){
                return -1;
            }
            int left = usingDFS(root.left, k);
            if(left != -1){
                return left;
            }
            count++;
            if(count == k){
                return root.data;
            }
            return usingDFS(root.right, k);
        }

        public int kthSmallest(TreeNode root, int k) {
            // return usingBFS(root, k);
            // return usingDFSANDList(root, k, new ArrayList<>()).get(k-1);
            return usingDFS(root, k);
        }
    }

    public static void main(String[] args) {
        int  k = 3;
        int [] nodes = {5,3,2,1,-1,-1,-1,4,-1,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree(nodes);
        Solution s = new Solution();
        System.out.println("Kth Smallest Element in a BST: "+s.kthSmallest(tree.root, k));

    }
}
