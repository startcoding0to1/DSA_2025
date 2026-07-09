package patterns.k_graph.algorithms.problems.medium;

import patterns.k_graph.algorithms.problems.Node;

import java.util.*;

public class CloneGraph {
    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

    static class Solution {

        public Node usingDFS(Node node, Map<Node, Node> map){
            if(node == null){
                return null;
            }
            if(map.containsKey(node)){
                return map.get(node);
            }
            map.put(node, new Node(node.val));
            for(Node neighbor : node.neighbors){
                Node nbNode = usingDFS(neighbor, map);
                map.get(node).neighbors.add(nbNode);
            }
            return map.get(node);
        }

        public Node usingBFS(Node node){
            if(node == null){
                return null;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);
            Map<Node,Node> map = new HashMap<>();
            map.put(node,new Node(node.val));

            while(!queue.isEmpty()){
                Node currNode = queue.poll();
                for(Node neibor : currNode.neighbors){
                    if(!map.containsKey(neibor)){
                        map.put(neibor, new Node(neibor.val));
                        queue.offer(neibor);
                    }
                    map.get(currNode).neighbors.add(map.get(neibor));
                }
            }
            return map.get(node);
        }
        public Node cloneGraph(Node node) {
            // return usingBFS(node);
            return usingDFS(node, new HashMap<>());
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node clonedNode1 = solution.cloneGraph(node1);

        Queue<Node> queue = new LinkedList<>();
        queue.add(node1);
        Queue<Node> queueCloned = new LinkedList<>();
        queueCloned.add(clonedNode1);

        Set<Node> visited = new HashSet<>();

        while (!queue.isEmpty() && !queueCloned.isEmpty()) {
            Node currNode = queue.poll();
            Node clonedNode = queueCloned.poll();

            if (visited.contains(currNode)) continue;
            visited.add(currNode);

            // 1. Value check
            if (currNode.val != clonedNode.val) {
                System.out.println("Not clone (value mismatch)");
                return;
            }

            // 2. Reference check (VERY IMPORTANT)
            if (currNode == clonedNode) {
                System.out.println("Not clone (same reference)");
                return;
            }

            // 3. Neighbor size check
            if (currNode.neighbors.size() != clonedNode.neighbors.size()) {
                System.out.println("Not clone (neighbors size mismatch)");
                return;
            }

            // 4. Traverse neighbors
            for (int i = 0; i < currNode.neighbors.size(); i++) {
                Node neighbor = currNode.neighbors.get(i);
                Node clonedNeighbor = clonedNode.neighbors.get(i);

                queue.offer(neighbor);
                queueCloned.offer(clonedNeighbor);
            }
        }

        System.out.println("Graph cloned successfully ✅");
    }

}
