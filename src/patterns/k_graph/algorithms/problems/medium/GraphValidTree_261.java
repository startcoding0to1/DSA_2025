package patterns.k_graph.algorithms.problems.medium;

import java.util.*;

public class GraphValidTree_261 {

    static class UsingDFS {
        public boolean validTree(int n, int[][] edges) {
            if(edges.length > n-1){
                return false;
            }
            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0; i<n; i++){
                graph.add(new ArrayList<>());
            }
            for(int [] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]); //Because UnDirected graph
            }
            Set<Integer> visited = new HashSet<>(); //we can set as well
            if(!dfs(0, -1, visited, graph)){
                return false;
            }
            return visited.size() == n;
        }

        private boolean dfs(int node, int parent, Set<Integer> visited, List<List<Integer>> graph){
            if(visited.contains(node)){
                return false; //cycle detected
            }
            visited.add(node);
            for(Integer nei :  graph.get(node)){
                if(nei == parent){
                    continue;
                }
                if(!dfs(nei, node, visited, graph)){
                    return false;
                }
            }
            return true;
        }

    }

    static class UsingBFS {
        public boolean validTree(int n, int[][] edges) {
            if(edges.length > n-1){
                return false;
            }
            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0; i<n; i++){
                graph.add(new ArrayList<>());
            }
            for(int [] edge : edges){
                graph.get(edge[0]).add(edge[1]);
            }
            Set<Integer> visited = new HashSet<>();
            if (!bfs(0, graph, visited)){
                return false;
            }
            return visited.size() == n;
        }

        private boolean bfs(int node,  List<List<Integer>> graph, Set<Integer> visited){
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{node, -1});
            visited.add(node);
            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                for(int i : graph.get(cur[0])){
                    if(i == cur[1]){ //If it is a parent skip that
                        continue;
                    }
                    if(visited.contains(i)){
                       return false;
                    }
                    visited.add(i);
                    queue.offer(new int[]{i, cur[0]});
                }
            }
            return true;
        }
    }

    static class UsingDSU {
        int [] parent, size;
        int noofComponents;
        public UsingDSU(int n) {
            noofComponents = n;
            this.size = new int [n];
            this.parent = new int [n];
//            Arrays.fill(size, 1);
            for(int i=0; i<n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i){
            if(i != parent[i]){
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public boolean union(int node1, int node2){
            int p1 = find(node1);
            int p2 = find(node2);
            if(p1 == p2){
                return false;
            }
            if(size[p1] < size[p2]){
                parent[p1] = p2;
                size[p2] += size[p1];
            }else {
                parent[p2] = p1;
                size[p1] += size[p2];
            }
            noofComponents--;
            return true;
        }
        public boolean validTree(int n, int[][] edges) {
            if(edges.length > n-1){
                return false;
            }
            for(int []  edge : edges){
                if(!union(edge[0], edge[1])){
                    return false;
                }
            }
            return noofComponents == 1;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        GraphValidTree_261.UsingDSU obj = new GraphValidTree_261.UsingDSU(n);
        boolean result = obj.validTree(n, edges);
        System.out.println("noofComponents: " + obj.noofComponents);
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        GraphValidTree_261 gv = new GraphValidTree_261();
        System.out.println( "Is this graph is valid tree: " + gv.validTree(n, edges1)); //false
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println( "Is this graph is valid tree: " + gv.validTree(n, edges2));
    }
}
