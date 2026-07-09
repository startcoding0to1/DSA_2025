package datastructure.tree.types;

public class BinaryTree {

    public TreeNode root;

    private int index = -1;

    public BinaryTree(int [] nodes) {
        this.root = this.buildTree(nodes);
    }

    private TreeNode buildTree(int [] nodes) {
        this.index++;
        if(nodes[index] == -1){
            return null;
        }
        TreeNode node = new TreeNode(nodes[index]);
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }

//    public static void main(String[] args) {
//        int [] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,-1};
//        BinaryTree binaryTree = new BinaryTree(nodes);
//    }
}
