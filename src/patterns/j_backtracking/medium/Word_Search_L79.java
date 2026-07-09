package patterns.j_backtracking.medium;

public class Word_Search_L79 {
    static class Solution {

        private boolean recursiveFun(String word, int wi, int i, int j, char [][] board, boolean [][] visited){
            if(word.length()==wi){
                return true;
            }
            if(i<0 || j<0 || i==board.length || j==board[i].length || visited[i][j] || word.charAt(wi)!=board[i][j]){
                return false;
            }
            visited[i][j] = true;
            boolean found = recursiveFun(word, wi+1, i, j+1, board, visited) //left
                    || recursiveFun(word, wi+1, i+1, j, board, visited) //bottom
                    || recursiveFun(word, wi+1, i, j-1, board, visited) //right
                    || recursiveFun(word, wi+1, i-1, j, board, visited); //top

            visited[i][j] = false;
            return found;
        }

        public boolean exist(char[][] board, String word) {

            int m = board.length;
            int n = board[0].length;

            boolean[][] visited = new boolean[m][n];

            for(int i=0; i<board.length; i++){
                for(int j=0; j<board[i].length; j++){
                    if(word.charAt(0)==board[i][j] && recursiveFun(word,0,i,j,board,visited)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println("Solution: "+solution.exist(board, word));
    }
}
