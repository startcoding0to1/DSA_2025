package patterns.k_graph.algorithms.problems.medium;

import java.util.*;

public class NumberOfConnectedComponentsInAnUndirectedGraph_323 {


    static class UsingDSU {
        int noOfConnectedComponents;
        int [] parent, size;

        public UsingDSU(int n){
            this.noOfConnectedComponents = n;
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i){
            if (parent[i] != i){
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

//        public int find(int node) {
//            int cur = node;
//            while (cur != parent[cur]) {
//                parent[cur] = parent[parent[cur]];
//                cur = parent[cur];
//            }
//            return cur;
//        }

        public void union(int i, int j){
            int p1 = find(i);
            int p2 = find(j);

            if (p1 == p2){
                return;
            }
            if (size[p1] < size[p2]){
                parent[p1] = p2;
                size[p2] += size[p1];
            }else {
                parent[p2] = p1;
                size[p1] += size[p2];
            }
            noOfConnectedComponents--;
        }

        public int countComponents(int n, int[][] edges) {
            for (int[] edge : edges) {
                union(edge[0], edge[1]);
            }
            return noOfConnectedComponents;
        }
    }

    static class UsingDFS {
        int noOfConnectedComponents = 0;
        public int countComponents(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.addLast(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            Set<Integer> visited = new HashSet<>();
            for(int i = 0; i < n; i++){
                if(!visited.contains(i)){
                    noOfConnectedComponents++;
                    dfs(i, -1,  visited, graph);
                }
            }
            return noOfConnectedComponents;
        }

        public  void dfs(int node, int parent, Set<Integer> visited, List<List<Integer>> graph) {
            if (visited.contains(node)){
                return;
            }
            visited.add(node);
            for(Integer neigbor : graph.get(node)){
                if(neigbor == parent){
                    continue;
                }
                dfs(neigbor, node, visited, graph);
            }
        }
    }

    static class UsingBFS {
        int noOfConnectedComponents = 0;
        public int countComponents(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.addLast(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            boolean[] visited = new boolean[n];
            for(int i = 0; i < n; i++){
                noOfConnectedComponents++;
                bfs(i, visited,  graph);
            }
            return noOfConnectedComponents;
        }

        public void bfs(int node, boolean [] visited, List<List<Integer>> graph) {
            Queue<int []> queue = new LinkedList<>();
            queue.offer(new int[]{node,-1});
            visited[node] = true;
            while(!queue.isEmpty()){
                int [] curr = queue.poll();
                for(Integer neigbor : graph.get(curr[0])){
                    if(neigbor ==  curr[1]){//equals to its parent
                        continue;
                    }
                    if(!visited[neigbor]){
                        visited[neigbor] = true;
                        queue.offer(new int[]{neigbor,curr[0]});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        int [] [] edge = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        int [] [] edge = {{0, 1}, {1, 2}, {3, 4}};
        int n = 5;
//        NumberOfConnectedComponentsInAnUndirectedGraph_323.UsingDSU dsu = new NumberOfConnectedComponentsInAnUndirectedGraph_323.UsingDSU(n);
//        NumberOfConnectedComponentsInAnUndirectedGraph_323.UsingDFS obj = new NumberOfConnectedComponentsInAnUndirectedGraph_323.UsingDFS();
        NumberOfConnectedComponentsInAnUndirectedGraph_323.UsingDFS obj = new NumberOfConnectedComponentsInAnUndirectedGraph_323.UsingDFS();
        System.out.println( "No of connected components: " + obj.countComponents(n, edge));
    }
}
