package patterns.tree.algorithms;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

public class TreeDFS {

    public void preOrderDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrderDFS(root.left);
        preOrderDFS(root.right);
    }

    public void inOrderDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderDFS(root.left);
        System.out.print(root.data + " ");
        inOrderDFS(root.right);
    }

    public void postOrderDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderDFS(root.left);
        postOrderDFS(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(new int [] {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1});
        TreeDFS dfs = new TreeDFS();
        System.out.print("Preorder traversal of binary tree: ");
        dfs.preOrderDFS(binaryTree.root);
        System.out.println();

        System.out.print("Inorder traversal of binary tree: ");
        dfs.inOrderDFS(binaryTree.root);
        System.out.println();

        System.out.print("Postorder traversal of binary tree: ");
        dfs.postOrderDFS(binaryTree.root);
        System.out.println();
    }
}
