package patterns.g_tree.problems.leetcode.easy;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

public class MaximumDepthofBinaryTree_L104 {

    static class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            int leftMaxDepth = maxDepth(root.left);
            int rightMaxDepth = maxDepth(root.right);
            return Math.max(leftMaxDepth, rightMaxDepth) + 1;
        }
    }

    public static void main(String[] args) {
        int [] nodes = {3,9,-1,-1,20,15,-1,-1,7,-1,-1};
        BinaryTree binaryTree = new BinaryTree(nodes);
        Solution solution = new Solution();
        System.out.println("Maximum Depth Of Binary Tree L104: "+solution.maxDepth(binaryTree.root));
    }
}
