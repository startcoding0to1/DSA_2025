package patterns.tree.problems;

import patterns.tree.algorithms.TreeDFS;
import datastructure.tree.types.BinaryTree;
import datastructure.tree.types.TreeNode;

public class DiameterOfBinaryTree{

    // Diameter is measured in terms of edges.
    // So we return -1 for null nodes and use (lh + rh + 2) to compute diameter.
    // If diameter were measured in nodes, we would return 0 for null and use (lh + rh).

    public int approach1(TreeNode root) {
        class Helper{
            public int computeHeight(TreeNode root){ //Interms of node
                if(root == null){
                    return 0;
                }
                int leftNodeHeigt = computeHeight(root.left);
                int rightNodeHeigt = computeHeight(root.right);
                return Math.max(leftNodeHeigt, rightNodeHeigt) + 1;
            }

            public int computeDiameter(TreeNode root){
                if(root == null){
                    return 0;
                }
                int diamererOfRoot = computeHeight(root.left)+computeHeight(root.right);
                int diameterOfLeft = computeDiameter(root.left);
                int diameterOfRight = computeDiameter(root.right);
                return Math.max(Math.max(diameterOfLeft, diameterOfRight), diamererOfRoot);
            }
        }
        return new Helper().computeDiameter(root);
    }

    //Approach-2
    int diameter = 0;
    public int computeHeight(TreeNode root) { //Interms of edges
        if(root == null){
            return -1;
        }
        int lh = computeHeight(root.left);
        int rh = computeHeight(root.right);
        diameter = Math.max(diameter, lh+rh+2);
        return Math.max(lh,rh)+1;
    }
    public static void main(String[] args) {
        int [] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,-1};
        BinaryTree binaryTree = new BinaryTree(nodes);
        DiameterOfBinaryTree computeDiameter = new DiameterOfBinaryTree();
        System.out.print("Tree : [ ");
        TreeDFS treeDFS = new TreeDFS();
        treeDFS.preOrderDFS(binaryTree.root);
        System.out.println("] ");
        System.out.print("Diameter of Binary Tree: ");
//        System.out.print(diameter.approach1(binaryTree.root));
        computeDiameter.computeHeight(binaryTree.root);
        System.out.print(computeDiameter.diameter);
    }
}
