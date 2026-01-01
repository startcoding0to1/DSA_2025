package datastructure.graph.representation;

import java.util.ArrayList;
import java.util.Collections;

public class AdjacencyMatrix {

    public ArrayList<ArrayList<Integer>> createGraph(int v, int[][] edges, boolean directed) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        // Initialize the matrix with 0
        for(int i = 0; i < v; i++){
            matrix.add(new ArrayList<>(Collections.nCopies(v, 0)));
        }

        for(int i = 0; i < edges.length; i++){
            int sV =  edges[i][0]; //Source vertex
            int dV = edges[i][1]; //Destination vertex
            matrix.get(sV).set(dV,1);
            if(!directed)
                matrix.get(dV).set(sV,1);// since the graph is undirected
        }
        return matrix;
    }

    public static void main(String[] args) {
        int V = 3;

        // List of edges (u, v)
        int[][] edges = {{1, 0},{2, 0},{1, 2}};

        // Build the graph using edges
        AdjacencyMatrix obj = new AdjacencyMatrix();
        ArrayList<ArrayList<Integer>> matrix = obj.createGraph(V, edges, false);

        for (int i = 0; i < V; i++) {
            for(Integer el : matrix.get(i)){
                System.out.print(el+" ");
            }
            System.out.println();
        }
    }
}
