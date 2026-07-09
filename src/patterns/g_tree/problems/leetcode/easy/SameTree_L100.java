package patterns.g_tree.problems.leetcode.easy;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

import java.util.*;

public class SameTree_L100 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int data;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int data) { this.data = data; }
     *     TreeNode(int data, TreeNode left, TreeNode right) {
     *         this.data = data;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    static class Solution {

        private void populatedataues(TreeNode root, List<Integer> list) {
            if (root == null) {
                list.add(null);
                return;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.data);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    list.add(null);
                }
            }
        }

        private boolean firstSolution(TreeNode p, TreeNode q) {
            List<Integer> t1 = new ArrayList<>();
            populatedataues(p, t1);
            List<Integer> t2 = new ArrayList<>();
            populatedataues(q, t2);
            if (t1.size() != t2.size()) {
                return false;
            }
            for (int i = 0; i < t1.size(); i++) {
                Integer v1 = t1.get(i);
                Integer v2 = t2.get(i);
                if (!Objects.equals(t1.get(i), t2.get(i))) {
                    return false;
                }
            }
            return true;
        }

        private boolean secondSolution(TreeNode p, TreeNode q) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(p);
            queue.offer(q);
            while (!queue.isEmpty()) {
                System.out.println(queue);
                TreeNode p1 = queue.poll();
                TreeNode q1 = queue.poll();
                if (p1 == null && q1 == null) {
                    continue;
                }
                if (p1 == null || q1 == null || p1.data != q1.data) {
                    return false;
                }
                queue.offer(p1.left);
                queue.offer(q1.left);
                queue.offer(p1.right);
                queue.offer(q1.right);
            }
            return true;

        }


        public boolean thirdSolution(TreeNode p, TreeNode q){
            if(p==null && q==null){
                return true;
            }
            if(p==null || q==null || p.data != q.data){
                return false;
            }

            return thirdSolution(p.left, q.left) && thirdSolution(p.right, q.right);
        }

        public boolean isSameTree(TreeNode p, TreeNode q) {
            return thirdSolution(p, q);
        }
    }

    public static void main(String[] args) {
        int [] t1 = {1,2,-1,-1,3,-1,-1};
        int [] t2 = {1,2,-1,-1,3,-1,-1};
        BinaryTree binaryTree = new BinaryTree(t1);
        BinaryTree binaryTree2 = new BinaryTree(t2);
        Solution solution = new Solution();
        System.out.println("Are two trees same: "+solution.isSameTree(binaryTree.root,binaryTree2.root));
    }
}
