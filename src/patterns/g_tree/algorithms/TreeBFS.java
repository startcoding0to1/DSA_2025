package patterns.g_tree.algorithms;

import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

import java.util.*;

public class TreeBFS {

    public void levelOrderTraversal(TreeNode root) {
        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        System.out.print("[ ");
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.data+" ");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        System.out.println("]");
    }

    public void zigzagLevelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (leftToRight) {
                    level.add(node.data);
                } else {
                    level.add(0, node.data); // insert at beginning
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            System.out.println(level);
            leftToRight = !leftToRight;
        }
    }

    class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public void verticalOrderTraversal(TreeNode root) {
        if (root == null) return;

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int col = pair.col;

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(node.data);

            if (node.left != null) queue.offer(new Pair(node.left, col - 1));
            if (node.right != null) queue.offer(new Pair(node.right, col + 1));
        }

        for (List<Integer> list : map.values()) {
            System.out.println(list);
        }
    }

    /*
    * ✅ Rule
    * First node seen in each column
    */

    public void topView(TreeNode root) {
        if (root == null) return;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int col = pair.col;

            map.putIfAbsent(col, node.data);

            if (node.left != null) queue.offer(new Pair(node.left, col - 1));
            if (node.right != null) queue.offer(new Pair(node.right, col + 1));
        }

        System.out.println(map.values());
    }

    /*
    *✅ Rule
    * Last node seen in each column
    */
    public void bottomView(TreeNode root) {
        if (root == null) return;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int col = pair.col;

            map.put(col, node.data); // overwrite

            if (node.left != null) queue.offer(new Pair(node.left, col - 1));
            if (node.right != null) queue.offer(new Pair(node.right, col + 1));
        }

        System.out.println(map.values());
    }



    public static void main(String[] args) {
        BinaryTree binaryTree =  new BinaryTree(new int [] {4,2,1,-1,-1,3,-1,-1,7,6,-1,-1,9,-1,-1});
        TreeBFS treeBFS = new TreeBFS();
        System.out.println("Level order traversal of binary tree: ");
        treeBFS.levelOrderTraversal(binaryTree.root);
        System.out.println("Zigzag level order traversal of binary tree: " );
        treeBFS.zigzagLevelOrder(binaryTree.root);
        System.out.println("Top view traversal of binary tree: ");
        treeBFS.topView(binaryTree.root);
        System.out.println("Bottom view traversal of binary tree: ");
        treeBFS.bottomView(binaryTree.root);
        System.out.println("Vertical order traversal of binary tree: ");
        treeBFS.verticalOrderTraversal(binaryTree.root);
    }
}
