package patterns.k_graph.algorithms.algorithms.topologicalsort;


import datastructure.graph.representation.AdjacencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Approach:
=======
* The idea is to perform a DFS traversal starting from every unvisited vertex (from 0 to n − 1).
* For each DFS call, we first explore all unvisited neighbors of the current node.
* Once the recursive calls for all its neighbors are complete,
* we start pushing these nodes into a stack while backtracking.
* After all vertices are processed,
* we pop elements from the stack one by one into a list —
* this gives a valid topological ordering, as each node is placed before all nodes it points to.
* */
public class TS_DFS {

    public void dfs(ArrayList<Integer>[] graph, boolean[] visited, int vertex, Stack<Integer> stack) {
        visited[vertex] = true;
        for(Integer neibourVertex : graph[vertex]){
            if(!visited[neibourVertex]){
                dfs(graph, visited, neibourVertex, stack);
            }
        }
        stack.push(vertex);
    }

    public List<Integer> findTopologicalSort(ArrayList<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<graph.length;i++){
            if(!visited[i]){
                dfs(graph, visited, i, stack);
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }


    public static void main(String[] args){
//        Input: adj[][]= [[1], [], [1], [2], [2]]
        int [] [] input = {{0,1},{2,1},{3,2},{4,2}};
        AdjacencyList obj = new AdjacencyList();
        int verteces = input.length;
        ArrayList<Integer>[] graph = obj.createGraph(verteces, input, true);
        List<Integer> result = new TS_DFS().findTopologicalSort(graph);
        System.out.println("Topological sort result: "+result);
    }
}
