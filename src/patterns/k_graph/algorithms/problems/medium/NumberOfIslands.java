package patterns.k_graph.algorithms.problems.medium;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    static class Solution1 { //DFS

        public void checkAdjacentsUsingDFS(int i, int j, char[][] grid) {
            if (i == grid.length || i == -1 || j == grid[0].length || j == -1 || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            checkAdjacentsUsingDFS(i, j + 1, grid); //L
            checkAdjacentsUsingDFS(i + 1, j, grid); //B
            checkAdjacentsUsingDFS(i, j - 1, grid); //R
            checkAdjacentsUsingDFS(i - 1, j, grid); //T
        }

        public void checkAdjacentsUsingBFS(int i, int j, char[][] grid){
            int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
            Queue<int []> queue = new LinkedList<>();
            queue.add(new int [] {i, j});
            grid[i][j] = '0';
            while(!queue.isEmpty()){
                int [] node = queue.poll();
                int row = node[0];
                int col = node[1];
                for(int[] direction : directions){
                    int r = row+direction[0];
                    int c = col+direction[1];
                    if(r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c]=='1'){
                        queue.add(new int[]{r, c});
                        grid[r][c] = '0';
                    }
                }
            }
        }

        public int numIslands(char[][] grid) {
            int noOfIslands = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        noOfIslands++;
                        checkAdjacentsUsingBFS(i, j, grid);
                    }
                }
            }
            return noOfIslands;
        }
    }



    public static void main(String[] args) {

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        Solution1 sol = new Solution1();
        int result = sol.numIslands(grid);

        System.out.println("Number of Islands: " + result); //3
    }
}