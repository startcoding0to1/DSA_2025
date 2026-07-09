package patterns.k_graph.algorithms.algorithms.traverse;

import datastructure.graph.representation.AdjacencyList;

import java.util.ArrayList;

public class GraphDFS {

    private void doDFS(ArrayList<Integer>[] graph, int sourceVertex, boolean[] visited) {
        System.out.print(sourceVertex + " ");
        visited[sourceVertex] = true;
        for (int v : graph[sourceVertex]) {
            if (!visited[v]) {
                doDFS(graph, v, visited);
            }
        }
    }

    public void dfs(ArrayList<Integer>[] graph, int sourceVertex){
        boolean[] visited = new boolean[graph.length];
        doDFS(graph, sourceVertex,visited);
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
        int [][] edges = {{0,1},{0,2},{1,0},{1,3},{2,0},{2,4},{3,1},{3,4},{3,5},{4,2},{4,3},{4,5},{5,3},{5,4},{5,6},{6,5}};
        AdjacencyList obj = new AdjacencyList();
        ArrayList<Integer> [] graph = obj.createGraph(v,edges, false);
        GraphDFS gdfs = new GraphDFS();
        gdfs.dfs(graph, 0);
    }
}
