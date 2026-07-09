package patterns.g_tree.problems.leetcode.medium;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

public class ValidateBinarySearchTree_L98 {
    static class Solution {
        //=======================================================
        public boolean bruteForce(TreeNode root) {
            if(root == null){
                return true;
            }
            if(!(isValid(root.left, root.data, (data,limit)->data<limit) && isValid(root.right, root.data, (data,limit)->data>limit))){
                return false;
            }
            return bruteForce(root.left) && bruteForce(root.right);
        }

        public boolean isValid(TreeNode root, int limit, CheckFunction check){
            if(root == null){
                return true;
            }
            if(!check.apply(root.data,limit)){
                return false;
            }
            return isValid(root.left,limit,check) && isValid(root.right,limit,check);
        }

        interface CheckFunction{
            boolean apply(int data, int limit);
        }
        //================================================

        public boolean usingDFS(TreeNode root, int min, int max){
            if(root == null){
                return true;
            }
            if(!(root.data > min && root.data < max)){
                return false;
            }
            return usingDFS(root.left, min, root.data) && usingDFS(root.right, root.data, max);
        }


        public boolean isValidBST(TreeNode root) {
//            return bruteForce(root);
            return usingDFS(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    public static void main(String[] args) {
        int [] nodes  = {2,1,-1,-1,3,-1,-1}; // true
//        int [] nodes = {5,1,-1,- 1,4,3,-1,-1,6,-1,-1};//false
//        int [] nodes = {10,5,-1,-1,15,6,-1,-1,20,-1,-1};//false
        BinaryTree tree = new BinaryTree(nodes);
        Solution sol = new Solution();
        System.out.println("Is Binary search tree: "+sol.isValidBST(tree.root));
    }
}
