package patterns.g_tree.problems.leetcode.medium;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree_L235 {
    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root==null){
                return root;
            }
            if(Math.max(p.data,q.data)<root.data){
                return lowestCommonAncestor(root.left, p, q);
            }else if(Math.min(p.data,q.data)>root.data){
                return lowestCommonAncestor(root.right, p, q);
            }else{
                return root;
            }
        }
    }

    public static void main(String[] args) {
        int [] nodes = {6,2,0,-1,-1,4,3,-1,-1,5,-1,-1,8,7,-1,-1,9,-1,-1};
        BinaryTree tree = new BinaryTree(nodes);
        Solution s = new Solution();
        System.out.println("Lowest Common Ancestor of a Binary Search Tree: "+s.lowestCommonAncestor(tree.root, new TreeNode(2),new TreeNode(8)).data);
    }
}
