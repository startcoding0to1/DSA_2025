package patterns.k_graph.algorithms.algorithms.traverse;

import datastructure.graph.representation.AdjacencyList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    public void bfs(ArrayList<Integer>[] graph, int sourceVertex) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        queue.offer(sourceVertex);
        visited[sourceVertex] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for (Integer neighbour : graph[vertex]) {
                if (!visited[neighbour]) {
                    queue.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        System.out.println("\nBFS is successfully executed");
    }

    public static void main(String[] args) {
        /*
        1 - 3
        /   | \
        0   | 5 -- 6
        \   | /
        2 - 4
        */

        int v = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 0}, {1, 3}, {2, 0}, {2, 4}, {3, 1}, {3, 4}, {3, 5}, {4, 2}, {4, 3}, {4, 5}, {5, 3}, {5, 4}, {5, 6}, {6, 5}};
        AdjacencyList obj = new AdjacencyList();
        ArrayList<Integer>[] graph = obj.createGraph(v, edges, false);
        GraphBFS obj2 = new GraphBFS();
        obj2.bfs(graph, 0);
    }
}
