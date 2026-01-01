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
}
