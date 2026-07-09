package patterns.g_tree.problems.leetcode.easy;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

public class SubtreeOfAnotherTree_L572 {
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

        private boolean isSameTree(TreeNode root, TreeNode subRoot) {
            if(root==null && subRoot == null){
                return true;
            }
            if(root == null || subRoot == null || root.data != subRoot.data){
                return false;
            }
            return isSameTree(root.left,subRoot.left) && isSameTree(root.right,subRoot.right);
        }

        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null) {
                return false;
            }
            if (root.data == subRoot.data) {
                if(isSameTree(root, subRoot)){
                    return true;
                }
            }
            return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
        }
    }

    public static void main(String[] args) {
        int [] nt =  {3,4,1,-1,-1,2,-1,-1,5,-1,-1};
        int [] ns = {4,1,-1,-1,2,-1,-1};
        BinaryTree binaryTree = new BinaryTree(nt);
        BinaryTree subTree = new BinaryTree(ns);
        Solution solution = new Solution();
        System.out.println("Is subtree exist: "+solution.isSubtree(binaryTree.root, subTree.root));
    }
}
