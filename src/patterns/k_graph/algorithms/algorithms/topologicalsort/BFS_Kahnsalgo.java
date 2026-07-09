package patterns.k_graph.algorithms.algorithms.topologicalsort;


import datastructure.graph.representation.AdjacencyList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Approach:
========
* The idea is to use Kahn’s Algorithm, which applies BFS to generate a valid topological ordering.
 We first compute the in-degree of every vertex — representing how many incoming edges each vertex has.
 Then, all vertices with an in-degree of 0 are added to a queue, as they can appear first in the ordering.
 We repeatedly remove a vertex from the queue, add it to our result list, and reduce the in-degree of all its adjacent vertices.
 If any of those vertices now have an in-degree of 0, they are added to the queue.
 This process continues until the queue is empty, and the resulting order represents one valid topological sort of the graph.
* */
public class BFS_Kahnsalgo {


    public List<Integer> findTopologicalSort(ArrayList<Integer>[] graph, int verteces) {

        int [] indegree = new int[verteces];
        for(int i = 0;  i < verteces; i++) {
            for(Integer neibourVertex : graph[i]) {
                indegree[neibourVertex]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[verteces];

        for(int i = 0;  i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            visited[vertex] = true;
            result.add(vertex);
            for(Integer neibourVertex : graph[vertex]) {
                indegree[neibourVertex]--;
                if(!visited[neibourVertex]) {
                    if(indegree[neibourVertex] == 0) {
                        queue.add(neibourVertex);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
//      Input: adj[][] = [[1], [2], [], [2, 4], []]
//        int [][] input = {{0,1},{1,2},{3,2},{3,4}};
//      Input: adj[][]= [[1], [2], [3], [], [5], [1, 2]]
        int [][] input = {{0,1},{1,2},{2,3},{4,5},{5,1},{5,2}};
        int verteces = input.length;
        ArrayList<Integer>[] graph = new AdjacencyList().createGraph(verteces,input,true);
        List<Integer> result = new BFS_Kahnsalgo().findTopologicalSort(graph,verteces);
        System.out.println(result);
    }
}
