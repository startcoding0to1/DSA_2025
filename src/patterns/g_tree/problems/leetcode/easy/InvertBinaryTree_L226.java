package patterns.g_tree.problems.leetcode.easy;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;
import patterns.g_tree.algorithms.TreeBFS;

public class InvertBinaryTree_L226 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode leftNode = invertTree(root.left);
            TreeNode rightNode = invertTree(root.right);
            root.left = rightNode;
            root.right = leftNode;
            return root;
        }
    }

    public static void main(String[] args) {
        int [] treeNodes = {4,2,1,-1,-1,3,-1,-1,7,6,-1,-1,9,-1,-1};
        BinaryTree binaryTree = new BinaryTree(treeNodes);
        Solution solution = new Solution();
        TreeBFS treeBFS = new TreeBFS();
        System.out.print("Original Binary Tree: ");
        treeBFS.levelOrderTraversal(binaryTree.root);
        solution.invertTree(binaryTree.root);
        System.out.print("Inverted Binary Tree: ");
        //Expected: [4,7,2,9,6,3,1]
        treeBFS.levelOrderTraversal(binaryTree.root);
    }
}
