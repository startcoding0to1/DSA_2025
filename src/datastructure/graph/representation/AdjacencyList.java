package datastructure.graph.representation;

import java.util.ArrayList;

public class AdjacencyList {

    public ArrayList<Integer>[] createGraph(int vertices, int [][] edges, boolean directed){
        ArrayList<Integer>[] graph = new ArrayList[vertices];
        for(int v=0;v<vertices;v++){
            graph[v] = new ArrayList<>();
        }
        for(int e=0; e<edges.length; e++){
            int sV =  edges[e][0];
            int dV =  edges[e][1];
            graph[sV].add(dV);
            if(!directed){
                graph[dV].add(sV);
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        int v = 3;

        // List of edges (u, v)
        int[][] edges = {{1, 0},{2, 0},{1, 2}};

        // Build the graph using edges
        AdjacencyList obj = new AdjacencyList();
        ArrayList<Integer>[] graph =  obj.createGraph(v, edges, false);

        for (int i = 0; i < v; i++) {
            System.out.println(i + " -> "+graph[i]);
        }
    }
}
